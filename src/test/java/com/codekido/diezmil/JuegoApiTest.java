package com.codekido.diezmil;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class JuegoApiTest {

	private JuegoTesteable juego;

	@Before
	public void setUp() throws Exception {
		juego = new JuegoTesteable(2);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testJuego() throws MovidaInvalida {
		@SuppressWarnings("unused")
		Juego juegoVacio = new Juego(0);
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testJugadores() throws MovidaInvalida {
		List<? extends Jugador> changos = juego.jugadores();
		changos.remove(1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testValoresValidosDeDados() throws MovidaInvalida {
		juego.forzar(-3, 0, 9, 2334343, 2);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCantidadValidaDeDados() throws MovidaInvalida {
		juego.forzar(1, 2, 3, 4, 5, 4, 3);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCantidadValidaDeDados2() throws MovidaInvalida {
		juego.forzar();
	}

	@Test
	public void testJugadorActual() {
	}

	@Test
	public void testJugador() {
	}

	@Test
	public void testSePlanta() {
	}

	@Test
	public void testFinalizado() {
	}

	@Test
	public void testGanador() {
	}

}
