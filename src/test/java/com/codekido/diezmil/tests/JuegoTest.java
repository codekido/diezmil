package com.codekido.diezmil.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.codekido.diezmil.Juego;
import com.codekido.diezmil.Jugador;
import com.codekido.diezmil.MovidaInvalida;

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
	public void testElUnoVale100() throws MovidaInvalida {
		assertEquals("El primer jugador debería tener 100 puntos por el uno.", 100, juego.forzar(1, 2, 3, 4, 4).jugador(0).puntos());
	}

	@Test
	public void testDosValen200() throws MovidaInvalida {
		assertEquals("El primer jugador debería tener 200 puntos por los dos unos.", 200, juego.forzar(1, 2, 3, 1, 4).jugador(0).puntos());
	}
	
	@Test
	public void testTresUnosValen1000() throws MovidaInvalida {
		assertEquals("El primer jugador debería tener 1000 puntos por los tres unos.", 1000, juego.forzar(1, 2, 1, 1, 4).jugador(0).puntos());
	}
	
	@Test
	public void testCuatroUnosValen1100() throws MovidaInvalida {
		assertEquals("El primer jugador debería tener 1100 puntos por los cuatro unos.", 1100, juego.forzar(1, 2, 1, 1, 1).jugador(0).puntos());
	}
	
	@Test
	public void testCincoUnosValen1200() throws MovidaInvalida {
		assertEquals("El primer jugador debería tener 1200 puntos por los cinco unos.", 1200, juego.forzar(1, 1, 1, 1, 1).jugador(0).puntos());
	}
	
	@Test
	public void testElDosVale0() throws MovidaInvalida {
		assertEquals("El primer jugador debería tener 0 puntos porque no hay números especiales.", 0, juego.forzar(3, 2, 3, 4, 4).jugador(0).puntos());
	}
	
	@Test
	public void testTresDosValen200() throws MovidaInvalida {
		assertEquals("El primer jugador debería tener 200 puntos por los tres dos.", 200, juego.forzar(2, 3, 2, 3, 2).jugador(0).puntos());
	}

	@Test
	public void testTresTresValen300() throws MovidaInvalida {
		assertEquals("El primer jugador debería tener 300 puntos por los tres tres.", 300, juego.forzar(2, 3, 3, 3, 2).jugador(0).puntos());
	}
	
	@Test
	public void testTresCuatrosValen400() throws MovidaInvalida {
		assertEquals("El primer jugador debería tener 400 puntos por los tres cuatros.", 400, juego.forzar(2, 4, 4, 4, 2).jugador(0).puntos());
	}
	
	@Test
	public void testTresCincosValen500() throws MovidaInvalida {
		assertEquals("El primer jugador debería tener 500 puntos por los tres cincos.", 500, juego.forzar(2, 5, 5, 5, 2).jugador(0).puntos());
	}
	
	@Test
	public void testTresSiesValen600() throws MovidaInvalida {
		assertEquals("El primer jugador debería tener 600 puntos por los tres seis.", 600, juego.forzar(2, 6, 6, 6, 2).jugador(0).puntos());
	}
	
	@Test
	public void testElCincoVale50() throws MovidaInvalida {
		assertEquals("El primer jugador debería tener 50 puntos por el cinco.", 50, juego.forzar(2, 2, 3, 4, 5).jugador(0).puntos());
	}
	
	@Test
	public void testCuatroCincosVale550() throws MovidaInvalida {
		assertEquals("El primer jugador debería tener 550 puntos por los cuatro cincos.", 550, juego.forzar(2, 5, 5, 5, 5).jugador(0).puntos());
	}

	@Test
	public void testTres5Y21Valen700() throws MovidaInvalida {
		assertEquals("El primer jugador debería tener 700 puntos por tres 5 y dos 1.", 700, juego.forzar(2, 2, 5, 5, 5).forzar(1, 1).jugador(0).puntos());
	}

	@Test
	public void testTodosSumanSigueJugando() throws MovidaInvalida {
		juego.forzar(5, 5, 1, 1, 1);
		assertEquals("Si todos los dados dan puntos, sigue jugando el mismo jugador.",
				juego.jugador(0),
				juego.jugadorActual());
	}
	
	@Test
	public void testNingunoSumaJuegaOtro() throws MovidaInvalida {
		juego.forzar(2, 2, 3, 3, 4);
		assertNotEquals("Si ningún dado da puntos, juega otro jugador.",
				juego.jugador(0),
				juego.jugadorActual());		
	}

	@Test(expected=MovidaInvalida.class)
	public void testSoloSeTiranLosDadosQueNoSumaron() throws MovidaInvalida {
		juego.forzar(1, 2, 3, 4, 5).forzar(1, 2, 3, 4);
		fail("Sólo puede relanzar 3 dados que son los que no suman");
	}
	
	@Test
	public void testCincoDadosSumanJugadorEligePlantarse() throws MovidaInvalida {
		juego.forzar(1, 1, 1, 1, 1);
		juego.sePlanta();
		assertEquals("El turno debería ser de jugador 1 ya que 0 se plantó", juego.jugador(1), juego.jugadorActual());
		assertEquals("El jugador 0 debería tener 1200 puntos por plantarse", 1200, juego.jugador(0).puntos());
	}
	
	@Test
	public void testSigueJugandoPierdeTodo() throws MovidaInvalida {
		juego.forzar(1, 1, 1, 1, 1).forzar(2, 2, 3, 3, 4);
		assertEquals("El jugador 0 debería tener 0 puntos por seguir y perder", 0, juego.jugador(0).puntos());
	}
	
	@Test 
	public void testJuegaTresVecesPierdeTodo() throws MovidaInvalida {
		juego.forzar(1,1,1,1,1).forzar(2,2,2,4,5).forzar(3);
		assertEquals("El jugador 0 debería tener 0 puntos por seguir y perder", 0, juego.jugador(0).puntos());
	}
	
	@Test
	public void testJuegaTresVecesSePlanta() throws MovidaInvalida {
		juego.forzar(1,1,1,1,1).forzar(2,2,2,3,3).forzar(1,2);
		juego.sePlanta();
		assertEquals("El turno debería ser de jugador 1 ya que 0 se plantó", juego.jugador(1), juego.jugadorActual());
		assertEquals("El jugador 0 debería tener 1500 puntos por plantarse", 1500, juego.jugador(0).puntos());
	}
	
	@Test
	public void testElTurnoVuelveAlJugadorUno() throws MovidaInvalida {
		juego.forzar(1, 1, 1, 3, 2);
		juego.sePlanta();
		juego.forzar(2, 3, 4, 6, 2);
		assertEquals("El turno corresponde al jugador uno, ya que el jugador dos no sumó puntos en su tirada.", juego.jugador(0), juego.jugadorActual());
	}

	@Test
	public void testArriesgaYPierdeSoloPuntosTurnoActual() throws MovidaInvalida {
		juego.forzar(1, 1, 1, 3, 2);
		juego.sePlanta();
		juego.forzar(2, 3, 4, 6, 2);
		juego.forzar(1, 2, 3, 4, 5);
		juego.forzar(2, 3, 2);
		assertEquals("El primer jugaodr debería tener 1000 puntos del turno anterior.", 1000, juego.jugador(0).puntos());
	}

	@Test
	public void testEn10000TerminaElJuego() throws MovidaInvalida {
		for(int i=0; i<10; i++) {
			juego.forzar(2, 3, 2, 3, 4);
			juego.forzar(1, 1, 1, 2, 3);
			juego.sePlanta();
		}
		assertTrue("El juego debería terminar porque jugador 2 llegó a 10000.", juego.finalizado());
		assertEquals("El ganador debería ser el jugador 2 porque tiene 10000 puntos y el jugador 1 tiene cero.", juego.jugador(1), juego.ganador());
	}

	@Test
	public void testHasta10000NoTerminaElJuego() throws MovidaInvalida {
		for(int i=0; i<9; i++) {
			juego.forzar(2, 3, 2, 3, 4);
			juego.forzar(1, 1, 1, 2, 3);
			juego.sePlanta();
		}
		juego.forzar(2, 3, 2, 3, 4);
		juego.forzar(5, 5, 5, 1, 1);
		juego.forzar(1, 1, 5, 2, 2);
		assertFalse("El juego no debería terminar porque jugador 2 llegó a 9950.", juego.finalizado());
		assertNull("No hay ganador porque el juego no terminó.", juego.ganador());
	}
	
	@Test
	public void testElJuegoTerminaCon10000Exactos() throws MovidaInvalida {
		for(int i=0; i<9; i++) {
			juego.forzar(2, 3, 2, 3, 4);
			juego.forzar(1, 1, 1, 2, 3);
			juego.sePlanta();
		}
		juego.forzar(2, 3, 2, 3, 4);
		juego.forzar(5, 5, 5, 1, 1);
		juego.forzar(1, 1, 1, 5, 2);
		assertFalse("El juego no debería terminar porque jugador 2 superó los 10000.", juego.finalizado());
		assertNull("No hay ganador porque el juego terminó.", juego.ganador());
	}
	
	@Test(expected=MovidaInvalida.class)
	public void testNoSePuedePasarElTurno() throws MovidaInvalida {
		juego.sePlanta();
	}
	
	@Test
	public void testElTurnoVuelveAlJugadorUnoPara3Jugadores() throws MovidaInvalida {
		Juego juego3 = new Juego(3);
		juego3.forzar(1, 1, 1, 3, 2);
		juego3.sePlanta();
		juego3.forzar(2, 3, 4, 6, 2);
		juego3.forzar(1, 1, 1, 1, 1);
		juego3.sePlanta();
		assertEquals("El turno corresponde al jugador uno, ya que todos los jugadores pasaron.", juego3.jugador(0), juego3.jugadorActual());
	}

	@Test
	public void testElTurnoVuelveAlJugadorUnoSinImportarCuantosJuegan() throws MovidaInvalida {
		Juego juegoMuchos = new Juego(34);
		for (int i=0; i<34; ++i) {
			juegoMuchos.forzar(1, 1, 1, 3, 2);
			juegoMuchos.sePlanta();
		}
		assertEquals("El turno corresponde al jugador uno, ya que todos los jugadores pasaron.", juegoMuchos.jugador(0), juegoMuchos.jugadorActual());
	}

	@Test
	public void testElTurnoLlegaAlUltimoJugador() throws MovidaInvalida {
		Juego juegoMuchos = new Juego(34);
		for (int i=0; i<33; ++i) {
			juegoMuchos.forzar(1, 1, 1, 3, 2);
			juegoMuchos.sePlanta();
		}
		assertEquals("El turno corresponde al último jugador uno, ya que todos los jugadores pasaron.", juegoMuchos.jugador(33), juegoMuchos.jugadorActual());
	}

	
}