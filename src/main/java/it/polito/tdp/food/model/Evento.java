package it.polito.tdp.food.model;

import java.time.LocalDateTime;


public class Evento implements Comparable<Evento> {

	private LocalDateTime tempo;
	private Food cibo;
	
	
	
	
	public Evento(LocalDateTime lt, Food f) {
		this.tempo=lt;
		this.cibo=f;
	}
	

	@Override
	public int compareTo(Evento other) {
		// TODO Auto-generated method stub
		return tempo.compareTo(other.tempo);
	}



	@Override
	public String toString() {
		return "Evento [tempo=" + tempo + ", Cibo=" + cibo + "]";
	}


	public LocalDateTime getTempo() {
		return tempo;
	}


	public Food getCibo() {
		return cibo;
	}


	


}
