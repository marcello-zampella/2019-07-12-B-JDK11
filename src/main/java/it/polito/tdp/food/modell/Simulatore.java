package it.polito.tdp.food.modell;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.PriorityQueue;
import javafx.util.Duration;

public class Simulatore {

	private int k; //numero di postazioni
	private int stazioniLibere;
	private Model model;
	ArrayList<Food> preparati;
	PriorityQueue<Evento> queue;
	LocalDateTime max;
	LocalDateTime ora;

	public void init(int i, Food food, Model model) {
		preparati=new ArrayList<Food>();
		queue= new PriorityQueue<Evento>();
		ArrayList<Collegamento> lista=model.cercaVicini(food);
		this.k=i;
		this.stazioniLibere=k;
		ora=LocalDateTime.of(2000, 1, 1, 0, 0);
		max=ora;
		preparati.clear();
		queue.clear();
		System.out.println("controllo: "+preparati.size()+" "+queue.size()+" "+stazioniLibere);
		this.model=model;
		//Double durata=Duration.hours(arg0)
		//Evento e=new Evento(ora.plusMinutes(durata),TipoEvento.ARRIVO_CIBO);
		preparati.add(food);
		for(int x=0; x<lista.size() && x<k;x++) {
			if(lista.get(x)!=null) {
			long durata=(long) lista.get(x).getPeso();
			Food f=lista.get(x).getCibo();
			preparati.add(f);
			LocalDateTime temp=ora.plusHours(durata);
			Evento e=new Evento(temp, f);
			if(temp.isAfter(max)) {
				max=temp;
			}
			queue.add(e);
			stazioniLibere--;
			}
		}
	}

	public void run(){
		
		while(!queue.isEmpty()) {
			stazioniLibere++;
			Evento ev=queue.poll();
			Food f=ev.getCibo();
			ArrayList<Collegamento> coll=model.cercaVicini(f);
			//coll.removeAll(preparati);
			this.rimuoviCollegamenti(coll,preparati);
			if(coll.containsAll(preparati)) {
				System.out.println("PRIMO PROBLEMA DI DOPPIANZA");
				return;
			}
			if(!coll.isEmpty()) {
				int r=stazioniLibere;
				for (int y=0;y<r && y<coll.size();y++) {
					Collegamento c=coll.get(y);
					stazioniLibere--;
					if(preparati.contains(c.getCibo())) {
						System.out.println("SECONDO PROBLEMONE DI DOPPIANZAAAAA");
						return;
					}
					preparati.add(c.getCibo());
					LocalDateTime temp=ev.getTempo().plusHours((long) c.getPeso());
					queue.add(new Evento(temp,c.getCibo()));
					if(temp.isAfter(max)) {
						max=temp;
					}
				}
				coll.clear();
				if(stazioniLibere<0) {
					System.out.println("HOUSTON ABBIAMO UN PROBLEMA");
					return;
				}
				System.out.println(stazioniLibere);
			}
			}
		System.out.println(preparati.size());
		
		long hours = ora.until( max, ChronoUnit.HOURS );
		System.out.println(hours);
		}

	private void rimuoviCollegamenti(ArrayList<Collegamento> coll, ArrayList<Food> preparati2) {
		ArrayList<Collegamento> temp= new ArrayList<Collegamento>();
		for(Collegamento c: coll) {
			if(preparati.contains(c.getCibo())) {
				temp.add(c);
			}
		}
		coll.removeAll(temp);
		
	}
		
	}

