package tdd;

import java.util.ArrayList;
import java.util.List;

public class Juego {

	private ArrayList<Jugador> jugadores;
	
	public List<Jugador> jugadores() {
		jugadores = new ArrayList<Jugador>();
		return jugadores;
	}

	public Juego forzar(int... dados) {
		return this;
	}
	
	public Jugador jugador(int orden){
		return new Jugador();
	}

}
