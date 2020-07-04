package it.polito.tdp.food.model;

public class TestModel {

	public static void main(String[] args) {
		Model a= new Model();
		a.creaGrafo(6);
		Food c=new Food(1,"pe");
		Food b=new Food(2,"pia");
		System.out.println(a.getGrafo().getEdge(c,b));
		

	}

}
