package tdd;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.Bag;
import org.apache.commons.collections.bag.HashBag;

// TODO: Refactory: que una clase maneje turnos (y use una lista circular?)
public class Juego {

	private ArrayList<Jugador> jugadores;
	private int jugadorActual;
	
	public Juego(int cantJugadores) {
		jugadores = new ArrayList<Jugador>();
		for (int i=0; i<cantJugadores; ++i) {
			jugadores.add(new Jugador());
		}
		jugadorActual = 0;
	}
	
	public List<Jugador> jugadores() {
		return jugadores;
	}

	// TODO: Refactory: separar el cálculo de puntos del cálculo de puntos.
	public Juego forzar(int... dados) {
		int puntosAntes = jugadorActual().puntos();
		Bag tirada = getBag(dados);
		ternas(tirada);
		jugadorActual().add(100 * tirada.getCount(1));
		jugadorActual().add( 50 * tirada.getCount(5));
		int puntosDespues = jugadorActual().puntos();
		if (puntosAntes == puntosDespues) {
			siguienteJugador();
		}
		return this;
	}

	private void siguienteJugador() {
		jugadorActual = jugadorActual+1 % jugadores().size();
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

}
