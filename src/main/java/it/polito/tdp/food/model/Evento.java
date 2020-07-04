package it.polito.tdp.food.model;

import java.time.LocalDateTime;


public class Evento<Evento> implements Comparable<Evento> {

	LocalDateTime tempo;
	private TipoEvento tipo;
	
	public enum TipoEvento {
		ARRIVO_CIBO,
		PARTENZA_CIBO
	}
	
	
	
	public Evento(LocalDateTime lt, TipoEvento tipo) {
		this.tempo=lt;
		this.tipo=tipo;
	}
	

	@Override
	public int compareTo(Evento other) {
		// TODO Auto-generated method stub
		return tempo.compareTo(other.tempo);
	}



	@Override
	public String toString() {
		return "Evento [tempo=" + tempo + ", tipo=" + tipo + "]";
	}
	


}
