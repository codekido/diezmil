package com.codekido.diezmil;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	static Jugador jugadorActual;

	public static void main(String[] args) throws MovidaInvalida, IOException {
		jugar();
	}

	private static void jugar() throws MovidaInvalida, IOException {
		Juego juego = new JuegoJugable(2, "Darío", "Ramiro");
		
		while (!juego.finalizado()) {
			switch (menu(juego).toUpperCase()) {
			case "P":
				juego.sePlanta();
				break;
			case "R":
				juego.tirar();
				break;
			case "S":
				System.exit(0);
			}			
		}
		
		System.out.println("GRANDE " + juego.ganador().nombre() + "!!! Ahora a ver cuándo la ponés. NEEERD!");
		
	}

	private static String menu(Juego juego) throws IOException {
		System.out.println();
		System.out.println();
		cambioJugador(juego);
		puntos(juego);
		System.out.println("Le toca a " + juego.jugadorActual() + ". Juega con " + juego.dadosParaJugar() + " dados.");
		System.out.println();
		Scanner entrada = new Scanner(System.in);
		System.out.print("[R]olar - [P]lantarse - [S]alir ");
		String result = entrada.nextLine();
		return result;
	}

	private static void cambioJugador(Juego juego) {
		if (jugadorActual == null || jugadorActual != juego.jugadorActual()) {
			System.out.println(" ===== CAMBBIO =====");
			System.out.println();
			jugadorActual = juego.jugadorActual();
		}
	}

	private static void puntos(Juego juego) {
		System.out.println("Puntos:");
		for (Jugador j : juego.jugadores()) {
			System.out.println(j.nombre() + ": " + j.puntos());
		}
		System.out.println();
	}

}
