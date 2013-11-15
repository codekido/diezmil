package tdd;

import java.util.ArrayList;
import java.util.List;

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
		int unos = 0;
		for (int dado : dados) {
			jugadorActual().add(dado == 1 ? 100 : 0);
			if (dado == 1) ++unos;
		}
		if (unos >= 3) jugadorActual().add(700);
		return this;
	}
	
	private Jugador jugadorActual() {
		return jugador(0);
	}

	public Jugador jugador(int orden){
		return jugadores.get(orden);
	}

}
