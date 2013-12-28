package com.codekido.diezmil;

public class JugadorImpl implements Jugador {
	private int puntos;
	private int puntosSeguros;
	private String nombre;
	
	public JugadorImpl(String nombre) {
		this.nombre = nombre;
	}
	
	public String nombre() {
		return nombre;
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
	
	public String toString() {
		return nombre();
	}
}
