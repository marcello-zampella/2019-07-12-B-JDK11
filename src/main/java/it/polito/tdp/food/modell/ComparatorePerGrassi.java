package it.polito.tdp.food.modell;

import java.util.Comparator;

public class ComparatorePerGrassi implements Comparator {

	public int compare (Object o1, Object o2) {
		Collegamento a1=(Collegamento) o1;
		Collegamento a2 =(Collegamento) o2;
		if(a1.getPeso()==a2.getPeso())
			return 0;
		if (a1.getPeso()>(a2.getPeso())) {
		 return 1;
		 }
		else
			return -1;
		
	}
	
}
