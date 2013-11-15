package tdd;

public class Jugador {
	private int puntos;
	
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
}
