package com.codekido.diezmil;

import java.util.List;

public interface Juego {

	public abstract List<? extends Jugador> jugadores();

	public abstract Jugador jugadorActual();

	public abstract Jugador jugador(int orden);

	public abstract Juego sePlanta() throws MovidaInvalida;

	public abstract boolean finalizado();

	public abstract Jugador ganador();

	public abstract void tirar() throws MovidaInvalida;

	public abstract int dadosParaJugar();

}