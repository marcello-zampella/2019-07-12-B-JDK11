package it.polito.tdp.food.model;

public class Collegamento {
	private double peso;
	private Food cibo;
	public Collegamento(double peso, Food cibo) {
		super();
		this.peso = peso;
		this.cibo = cibo;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public Food getCibo() {
		return cibo;
	}
	public void setCibo(Food cibo) {
		this.cibo = cibo;
	}
	@Override
	public String toString() {
		return "Collegamento [peso=" + peso + ", cibo=" + cibo + "]";
	}
	
	
	

}
