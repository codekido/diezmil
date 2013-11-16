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
	
}
