package com.codekido.diezmil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.Bag;
import org.apache.commons.collections.bag.HashBag;

public class Juego {

	private ArrayList<Jugador> jugadores;
	private int jugadorActual;
	private int dadosParaJugar = 5;
	private int tiradas;
	
	public Juego(int cantJugadores) {
		requisitoApi(cantJugadores>0, "Debe haber al menos un jugador");
		jugadores = new ArrayList<Jugador>();
		for (int i=0; i<cantJugadores; ++i) {
			jugadores.add(new Jugador());
		}
	}

	public List<Jugador> jugadores() {
		return Collections.unmodifiableList(jugadores);
	}

	public Juego forzar(int... dados) throws MovidaInvalida {
		tiradas++;
		int puntosAntes = calculaPuntosJugada(dados);
		resuelveTurnos(puntosAntes);
		
		return this;
	}

	private void resuelveTurnos(int puntosAntes) {
		int puntosDespues = jugadorActual().puntos();
		if (puntosAntes == puntosDespues) {
			siguienteJugador();
		}
	}

	private int calculaPuntosJugada(int... dados) throws MovidaInvalida {
		int puntosAntes = jugadorActual().puntos();
		Bag tirada = getBag(dados);
		validaCantidadDeDados(tirada);
		ternas(tirada);
		
		jugadorActual().add(100 * tirada.getCount(1));
		dadosParaJugar -= tirada.getCount(1);
		
		jugadorActual().add( 50 * tirada.getCount(5));
		dadosParaJugar -= tirada.getCount(5);
		
		if (dadosParaJugar == tirada.size()) {
			jugadorActual().pierdePuntos();
		}
		
		if (jugadorActual().puntos() > 10000) {
			jugadorActual().pierdePuntos();
		}
		
		if  (dadosParaJugar == 0) {
			dadosParaJugar = 5;
		}
		
		return puntosAntes;
	}

	private void validaCantidadDeDados(Bag tirada) throws MovidaInvalida {
		if (dadosParaJugar != tirada.size())
			throw new MovidaInvalida(String.format("Sólo puede jugar %d dados.", dadosParaJugar));
	}

	private void siguienteJugador() {
		tiradas = 0;
		int siguiente = jugadorActual+1;
		jugadorActual = siguiente >= jugadores().size() ? 0 : siguiente;
		dadosParaJugar = 5;
	}

	private void ternas(Bag tirada) {
		if (tirada.getCount(1) >= 3) {
			jugadorActual().add(700);
		}
		if (tirada.getCount(5) >= 3) {
			jugadorActual().add(350);
		}
		for (int i=2; i<=6; ++i) {
			if (i==5) continue;
			if (tirada.getCount(i) >= 3) {
				jugadorActual().add(i*100);
				dadosParaJugar -= 3;
			}
		}
	}
	
	private Bag getBag(int[] dados) {
		Bag result = new HashBag();
		for (int dado : dados) {
			result.add(new Integer(dado));
		}
		return result;
	}

	public Jugador jugadorActual() {
		return jugadores.get(jugadorActual);
	}

	public Jugador jugador(int orden){
		return jugadores.get(orden);
	}

	public Juego sePlanta() throws MovidaInvalida {
		requisito(tiradas>0, "No puede plantarse si no tiró al menos una vez.");
		requisito(jugadorActual().puntos()>=750, "No se puede plantar antes de tener 750");
		jugadorActual().aseguraPuntos();
		siguienteJugador();
		return this;
	}

	private void requisito(boolean condicion, String mensaje) throws MovidaInvalida {
		if (!condicion) {
			throw new MovidaInvalida(mensaje);
		}
		
	}
	
	private void requisitoApi(boolean b, String string) {
		if (!b) throw new IllegalArgumentException(string);
	}

	public boolean finalizado() {
		return ganador() != null;
	}

	public Jugador ganador() {
		for (Jugador j: jugadores()) {
			if (j.puntos() == 10000) {
				return j;
			}
		}
		return null;
	}

}
