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
}
