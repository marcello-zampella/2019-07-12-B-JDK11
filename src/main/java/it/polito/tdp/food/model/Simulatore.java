package it.polito.tdp.food.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.PriorityQueue;

import it.polito.tdp.food.model.Evento.TipoEvento;
import javafx.util.Duration;

public class Simulatore {

	private int k; //numero di postazioni
	private int stazioniLibere;
	

	public void init(int i, ArrayList<Collegamento> lista, Food food) {
		PriorityQueue<Evento> queue= new PriorityQueue<Evento>();
		this.k=i;
		this.stazioniLibere=k;
		LocalDateTime ora=LocalDateTime.of(2000, 1, 1, 0, 0);
		queue.clear();
		//Double durata=Duration.hours(arg0)
		//Evento e=new Evento(ora.plusMinutes(durata),TipoEvento.ARRIVO_CIBO);
		for(int x=0; x<k-1;x++) {
			long durata=(long) lista.get(x).getPeso();
			Evento e=new Evento(ora.plusMinutes(durata),TipoEvento.PARTENZA_CIBO);
			queue.add(e);
		}
		
	}

	public void run() {
		// TODO Auto-generated method stub
		
	}

}
