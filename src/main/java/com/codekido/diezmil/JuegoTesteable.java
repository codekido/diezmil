package com.codekido.diezmil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.Bag;
import org.apache.commons.collections.bag.HashBag;

public class JuegoTesteable implements Juego {

	private ArrayList<JugadorImpl> jugadores;
	private int jugadorActual;
	private int dadosParaJugar = 5;
	protected int tiradas;
	
	public JuegoTesteable(int cantJugadores, String... nombres) {
		requisitoApi(cantJugadores>0, "Debe haber al menos un jugador");
		jugadores = new ArrayList<JugadorImpl>();
		for (int i=0; i<cantJugadores; ++i) {
			String nombre = nombres.length > i ? nombres[i] : ("Jugador " + i); 
			jugadores.add(new JugadorImpl(nombre));
		}
	}

	public List<? extends Jugador> jugadores() {
		return Collections.unmodifiableList(jugadores);
	}

	protected void resuelveTurnos(int puntosAntes) {
		int puntosDespues = jugadorActual().puntos();
		if (puntosAntes == puntosDespues) {
			siguienteJugador();
		}
	}

	protected int calculaPuntosJugada(int... dados) throws MovidaInvalida {
		int puntosAntes = jugadorActual().puntosSeguros();
		Bag tirada = getBag(dados);
		validaCantidadDeDados(tirada);
		ternas(tirada);
		
		jugadorActualImpl().add(100 * tirada.getCount(1));
		dadosParaJugar -= tirada.getCount(1);
		
		jugadorActualImpl().add( 50 * tirada.getCount(5));
		dadosParaJugar -= tirada.getCount(5);
		
		if (dadosParaJugar == tirada.size()) {
			jugadorActualImpl().pierdePuntos();
		}
		
		if (jugadorActual().puntos() > 10000) {
			jugadorActualImpl().pierdePuntos();
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
			jugadorActualImpl().add(700);
		}
		if (tirada.getCount(5) >= 3) {
			jugadorActualImpl().add(350);
		}
		for (int i=2; i<=6; ++i) {
			if (i==5) continue;
			if (tirada.getCount(i) >= 3) {
				jugadorActualImpl().add(i*100);
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
		return jugadorActualImpl();
	}

	public Jugador jugador(int orden){
		return jugadores.get(orden);
	}

	public Juego sePlanta() throws MovidaInvalida {
		requisito(tiradas>0, "No puede plantarse si no tiró al menos una vez.");
		requisito(jugadorActual().puntos()>=750, "No se puede plantar antes de tener 750");
		jugadorActualImpl().aseguraPuntos();
		siguienteJugador();
		return this;
	}

	private JugadorImpl jugadorActualImpl() {
		return jugadores.get(jugadorActual);
	}

	private void requisito(boolean condicion, String mensaje) throws MovidaInvalida {
		if (!condicion) {
			throw new MovidaInvalida(mensaje);
		}
		
	}
	
	protected void requisitoApi(boolean b, String string) {
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

	protected boolean tiradaValida(int[] dados) {
		boolean valid = dados.length > 0 && dados.length <= 5;
		for (int dado : dados) {
			valid = valid && (dado>=1 && dado<=6);
		}
		return valid;
	}

	public JuegoTesteable forzar(int... dados) throws MovidaInvalida {
		requisitoApi(tiradaValida(dados), "Dónde compraste esos dados? Esto no es un juego de rol");
		tiradas++;
		int puntosAntes = calculaPuntosJugada(dados);
		resuelveTurnos(puntosAntes);
		
		return this;
	}

	@Override
	public void tirar() throws MovidaInvalida {
		int[] dados = new int[dadosParaJugar];
		for (int i=0; i<dadosParaJugar; ++i) {
			dados[i] = (int) Math.ceil(Math.random()*6);
			System.out.println("dado roló " + dados[i]);
		}
		forzar(dados);
	}

	@Override
	public int dadosParaJugar() {
		return dadosParaJugar;
	}

}
