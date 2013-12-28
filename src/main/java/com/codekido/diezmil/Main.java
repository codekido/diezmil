package com.codekido.diezmil;

import java.io.IOException;
import java.util.Scanner;

public class Main {

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
		
	}

	private static String menu(Juego juego) throws IOException {
		System.out.println();
		System.out.println();
		puntos(juego);
		System.out.println("Le toca a " + juego.jugadorActual());
		System.out.println();
		Scanner entrada = new Scanner(System.in);
		System.out.print("[R]olar - [P]lantarse - [S]alir ");
		return entrada.nextLine();
	}

	private static void puntos(Juego juego) {
		System.out.println("Puntos:");
		for (Jugador j : juego.jugadores()) {
			System.out.println(j.nombre() + ": " + j.puntos());
		}
		System.out.println();
	}

}
