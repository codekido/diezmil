package tdd;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JuegoTest {

	private Juego juego;
	
	@Before
	public void setUp() throws Exception {
		juego = new Juego(2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTodosEmpiezanConCeroPuntos() {
		for (Jugador j : juego.jugadores()) {
			assertEquals(String.format("El jugador '%s' debería tener 0 puntos.", j.nombre()), 0, j.puntos());
		}
	}
	
	@Test
	public void testElUnoVale100() throws InvalidMove {
		assertEquals("El primer jugador debería tener 100 puntos por el uno.", 100, juego.forzar(1, 2, 3, 4, 4).jugador(0).puntos());
	}

	@Test
	public void testDosUnosValen200() throws InvalidMove {
		assertEquals("El primer jugador debería tener 200 puntos por los dos unos.", 200, juego.forzar(1, 2, 3, 1, 4).jugador(0).puntos());
	}
	
	@Test
	public void testTresUnosValen1000() throws InvalidMove {
		assertEquals("El primer jugador debería tener 1000 puntos por los tres unos.", 1000, juego.forzar(1, 2, 1, 1, 4).jugador(0).puntos());
	}
	
	@Test
	public void testCuatroUnosValen1100() throws InvalidMove {
		assertEquals("El primer jugador debería tener 1100 puntos por los cuatro unos.", 1100, juego.forzar(1, 2, 1, 1, 1).jugador(0).puntos());
	}
	
	@Test
	public void testCincoUnosValen1200() throws InvalidMove {
		assertEquals("El primer jugador debería tener 1200 puntos por los cinco unos.", 1200, juego.forzar(1, 1, 1, 1, 1).jugador(0).puntos());
	}
	
	@Test
	public void testElDosVale0() throws InvalidMove {
		assertEquals("El primer jugador debería tener 0 puntos porque no hay números especiales.", 0, juego.forzar(3, 2, 3, 4, 4).jugador(0).puntos());
	}
	
	@Test
	public void testTresDosValen200() throws InvalidMove {
		assertEquals("El primer jugador debería tener 200 puntos por los tres dos.", 200, juego.forzar(2, 3, 2, 3, 2).jugador(0).puntos());
	}

	@Test
	public void testTresTresValen300() throws InvalidMove {
		assertEquals("El primer jugador debería tener 300 puntos por los tres tres.", 300, juego.forzar(2, 3, 3, 3, 2).jugador(0).puntos());
	}
	
	@Test
	public void testTresCuatrosValen400() throws InvalidMove {
		assertEquals("El primer jugador debería tener 400 puntos por los tres cuatros.", 400, juego.forzar(2, 4, 4, 4, 2).jugador(0).puntos());
	}
	
	@Test
	public void testTresCincosValen500() throws InvalidMove {
		assertEquals("El primer jugador debería tener 500 puntos por los tres cincos.", 500, juego.forzar(2, 5, 5, 5, 2).jugador(0).puntos());
	}
	
	@Test
	public void testTresSiesValen600() throws InvalidMove {
		assertEquals("El primer jugador debería tener 600 puntos por los tres seis.", 600, juego.forzar(2, 6, 6, 6, 2).jugador(0).puntos());
	}
	
	@Test
	public void testElCincoVale50() throws InvalidMove {
		assertEquals("El primer jugador debería tener 50 puntos por el cinco.", 50, juego.forzar(2, 2, 3, 4, 5).jugador(0).puntos());
	}
	
	@Test
	public void testCuatroCincosVale550() throws InvalidMove {
		assertEquals("El primer jugador debería tener 550 puntos por los cuatro cincos.", 550, juego.forzar(2, 5, 5, 5, 5).jugador(0).puntos());
	}

	@Test
	public void testTres5Y21Valen700() throws InvalidMove {
		assertEquals("El primer jugador debería tener 700 puntos por tres 5 y dos 1.", 700, juego.forzar(2, 2, 5, 5, 5).forzar(1, 1).jugador(0).puntos());
	}

	@Test
	public void testTodosSumanSigueJugando() throws InvalidMove {
		juego.forzar(5, 5, 1, 1, 1);
		assertEquals("Si todos los dados dan puntos, sigue jugando el mismo jugador.",
				juego.jugador(0),
				juego.jugadorActual());
	}
	
	@Test
	public void testNingunoSumaJuegaOtro() throws InvalidMove {
		juego.forzar(2, 2, 3, 3, 4);
		assertNotEquals("Si ningún dado da puntos, juega otro jugador.",
				juego.jugador(0),
				juego.jugadorActual());		
	}

	@Test(expected=InvalidMove.class)
	public void testSoloSeTiranLosDadosQueNoSumaron() throws InvalidMove {
		juego.forzar(1, 2, 3, 4, 5).forzar(1, 2, 3, 4);
		fail("Sólo puede relanzar 3 dados que son los que no suman");
	}
	
	@Test
	public void testCincoDadosSumanJugadorEligePlantarse() throws InvalidMove {
		juego.forzar(1, 1, 1, 1, 1);
		juego.sePlanta();
		assertEquals("El turno debería ser de jugador 1 ya que 0 se plantó", juego.jugador(1), juego.jugadorActual());
		assertEquals("El jugador 0 debería tener 1200 puntos por plantarse", 1200, juego.jugador(0).puntos());
	}
	
	@Test
	public void testSigueJugandoPierdeTodo() throws InvalidMove {
		juego.forzar(1, 1, 1, 1, 1).forzar(2, 2, 3, 3, 4);
		assertEquals("El jugador 0 debería tener 0 puntos por seguir y perder", 0, juego.jugador(0).puntos());
	}
	
	@Test 
	public void testJuegaTresVecesPierdeTodo() throws InvalidMove {
		juego.forzar(1,1,1,1,1).forzar(2,2,2,4,5).forzar(3);
		assertEquals("El jugador 0 debería tener 0 puntos por seguir y perder", 0, juego.jugador(0).puntos());
	}
	
	@Test
	public void testJuegaTresVecesSePlanta() throws InvalidMove {
		juego.forzar(1,1,1,1,1).forzar(2,2,2,3,3).forzar(1,2);
		juego.sePlanta();
		assertEquals("El turno debería ser de jugador 1 ya que 0 se plantó", juego.jugador(1), juego.jugadorActual());
		assertEquals("El jugador 0 debería tener 1500 puntos por plantarse", 1500, juego.jugador(0).puntos());
	}
	
	@Test
	public void testElTurnoVuelveAlJugadorUno() throws InvalidMove {
		juego.forzar(1,1,1,3,2);
		juego.sePlanta();
		juego.forzar(2,3,4,6,2);
		assertEquals("El turno corresponde al jugador uno, ya que el jugador dos no sumó puntos en su tirada.", juego.jugador(1), juego.jugadorActual());
	}
}
