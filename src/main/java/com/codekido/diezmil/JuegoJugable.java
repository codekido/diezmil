package com.codekido.diezmil;

import java.util.List;

public class JuegoJugable implements Juego {
	private Juego juegoTesteable;
	
	public JuegoJugable(int cantJugadores, String... nombres) {
		juegoTesteable = new JuegoTesteable(cantJugadores, nombres);
	}

	@Override
	public List<? extends Jugador> jugadores() {
		return juegoTesteable.jugadores();
	}

	@Override
	public Jugador jugadorActual() {
		return juegoTesteable.jugadorActual();
	}

	@Override
	public Jugador jugador(int orden) {
		return juegoTesteable.jugador(orden);
	}

	@Override
	public Juego sePlanta() throws MovidaInvalida {
		return juegoTesteable.sePlanta();
	}

	@Override
	public boolean finalizado() {
		return juegoTesteable.finalizado();
	}

	@Override
	public Jugador ganador() {
		return juegoTesteable.ganador();
	}

	@Override
	public void tirar() throws MovidaInvalida {
		juegoTesteable.tirar();
	}

	@Override
	public int dadosParaJugar() {
		return juegoTesteable.dadosParaJugar();
	}
}
