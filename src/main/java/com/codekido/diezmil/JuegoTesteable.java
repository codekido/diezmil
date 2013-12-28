package com.codekido.diezmil;

public class JuegoTesteable extends Juego {
	
	public JuegoTesteable(int cantJugadores) {
		super(cantJugadores);
	}

	public JuegoTesteable forzar(int... dados) throws MovidaInvalida {
		requisitoApi(tiradaValida(dados), "Dónde compraste esos dados? Esto no es un juego de rol");
		tiradas++;
		int puntosAntes = calculaPuntosJugada(dados);
		resuelveTurnos(puntosAntes);
		
		return this;
	}

}
