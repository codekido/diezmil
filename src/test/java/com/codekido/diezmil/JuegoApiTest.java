package com.codekido.diezmil;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class JuegoApiTest {

	private Juego juego;

	@Before
	public void setUp() throws Exception {
		juego = new Juego(2);
	}

	@Test
	public void testJuego() {
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testJugadores() throws MovidaInvalida {
		List<Jugador> changos = juego.jugadores();
		changos.remove(1);
	}

	@Test
	public void testForzar() {
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
