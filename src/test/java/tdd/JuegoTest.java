package tdd;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JuegoTest {

	private Juego juego;
	
	@Before
	public void setUp() throws Exception {
		juego = new Juego(1);
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
	public void testElUnoVale100() {
		assertEquals("El primer jugador debería tener 100 puntos por el uno.", 100, juego.forzar(1, 2, 3, 4, 4).jugador(0).puntos());
	}

	@Test
	public void testDosUnosValen200() {
		assertEquals("El primer jugador debería tener 200 puntos por los dos unos.", 200, juego.forzar(1, 2, 3, 1, 4).jugador(0).puntos());
	}
	
	@Test
	public void testTresUnosValen1000() {
		assertEquals("El primer jugador debería tener 1000 puntos por los tres unos.", 1000, juego.forzar(1, 2, 1, 1, 4).jugador(0).puntos());
	}
	
	@Test
	public void testCuatroUnosValen1100() {
		assertEquals("El primer jugador debería tener 1100 puntos por los cuatro unos.", 1100, juego.forzar(1, 2, 1, 1, 1).jugador(0).puntos());
	}
	
	@Test
	public void testCincoUnosValen1200() {
		assertEquals("El primer jugador debería tener 1200 puntos por los cinco unos.", 1200, juego.forzar(1, 1, 1, 1, 1).jugador(0).puntos());
	}
	
	@Test
	public void testElDosVale0() {
		assertEquals("El primer jugador debería tener 0 puntos porque no hay números especiales.", 0, juego.forzar(3, 2, 3, 4, 4).jugador(0).puntos());
	}
	
	@Test
	public void testTresDosValen200() {
		assertEquals("El primer jugador debería tener 200 puntos por los tres dos.", 200, juego.forzar(2, 3, 2, 3, 2).jugador(0).puntos());
	}

	@Test
	public void testTresTresValen300() {
		assertEquals("El primer jugador debería tener 300 puntos por los tres tres.", 300, juego.forzar(2, 3, 3, 3, 2).jugador(0).puntos());
	}
	
	@Test
	public void testTresCuatrosValen400() {
		assertEquals("El primer jugador debería tener 400 puntos por los tres cuatros.", 400, juego.forzar(2, 4, 4, 4, 2).jugador(0).puntos());
	}
	
	@Test
	public void testTresCincosValen500() {
		assertEquals("El primer jugador debería tener 500 puntos por los tres cincos.", 500, juego.forzar(2, 5, 5, 5, 2).jugador(0).puntos());
	}
	
	@Test
	public void testTresSiesValen600() {
		assertEquals("El primer jugador debería tener 600 puntos por los tres seis.", 600, juego.forzar(2, 6, 6, 6, 2).jugador(0).puntos());
	}
	
	@Test
	public void testElCincoVale50() {
		assertEquals("El primer jugador debería tener 50 puntos por el cinco.", 50, juego.forzar(2, 2, 3, 4, 5).jugador(0).puntos());
	}
	
	@Test
	public void testCuatroCincosVale550() {
		assertEquals("El primer jugador debería tener 550 puntos por los cuatro cincos.", 550, juego.forzar(2, 5, 5, 5, 5).jugador(0).puntos());
	}

	@Test
	public void testTres5Y21Valen600() {
		assertEquals("El primer jugador debería tener 700 puntos por tres 5 y dos 1.", 700, juego.forzar(2, 2, 5, 5, 5).forzar(1, 1).jugador(0).puntos());
	}

	@Test(expected=InvalidMove.class)
	public void test() {
		assertEquals("Si todos los dados dan puntos, no se puede seguir.", 1100, juego.forzar(5, 5, 1, 1, 1).forzar(1, 1));
	}

}
