package tdd;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JuegoTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTodosEmpiezanConCeroPuntos() {
		Juego juego = new Juego(1);
		for (Jugador j : juego.jugadores()) {
			assertEquals(String.format("El jugador '%s' debería tener 0 puntos.", j.nombre()), 0, j.puntos());
		}
	}
	
	@Test
	public void testElUnoVale100() {
		Juego juego = new Juego(1);
		assertEquals("El primer jugador debería tener 100 puntos por el uno.", 100, juego.forzar(1, 2, 3, 4, 4).jugador(0).puntos());
	}

	@Test
	public void testDosUnosValen200() {
		Juego juego = new Juego(1);
		assertEquals("El primer jugador debería tener 200 puntos por los dos unos.", 200, juego.forzar(1, 2, 3, 1, 4).jugador(0).puntos());
	}
}
