package com.codekido.diezmil;

public class JugadorImpl implements Jugador {
	private int puntos;
	private int puntosSeguros;
	
	public String nombre() {
		return "Jugador";
	}

	public int puntos() {
		return puntos;
	}
	
	public Jugador add(int cant) {
		puntos+=cant;
		return this;
	}

	public Jugador pierdePuntos() {
		puntos = puntosSeguros;
		return this;
	}
	
	public Jugador aseguraPuntos() {
		puntosSeguros = puntos;
		return this;
	}
}
