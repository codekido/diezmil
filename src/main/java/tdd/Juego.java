package tdd;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.Bag;
import org.apache.commons.collections.bag.HashBag;

public class Juego {

	private ArrayList<Jugador> jugadores;
	
	public Juego(int cantJugadores) {
		jugadores = new ArrayList<Jugador>();
		for (int i=0; i<cantJugadores; ++i) {
			jugadores.add(new Jugador());
		}
	}
	
	public List<Jugador> jugadores() {
		return jugadores;
	}

	public Juego forzar(int... dados) {
		
		Bag tirada = getBag(dados);
		if (tirada.getCount(1) >= 3) {
			jugadorActual().add(700);
		}
		if (tirada.getCount(2) >= 3) {
			jugadorActual().add(200);
		}
		if (tirada.getCount(3) >= 3) {
			jugadorActual().add(300);
		}
		
		
		jugadorActual().add(100 * tirada.getCount(1));
		
		return this;
	}
	
	private Bag getBag(int[] dados) {
		Bag result = new HashBag();
		for (int dado : dados) {
			result.add(new Integer(dado));
		}
		return result;
	}

	private Jugador jugadorActual() {
		return jugador(0);
	}

	public Jugador jugador(int orden){
		return jugadores.get(orden);
	}

}
