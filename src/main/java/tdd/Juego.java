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
		ternas(tirada);
		jugadorActual().add(100 * tirada.getCount(1));
		
		return this;
	}

	private void ternas(Bag tirada) {
		if (tirada.getCount(1) >= 3) {
			jugadorActual().add(700);
		}
		for (int i=2; i<=5; ++i) {
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

	private Jugador jugadorActual() {
		return jugador(0);
	}

	public Jugador jugador(int orden){
		return jugadores.get(orden);
	}

}
