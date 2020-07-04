package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.Collections;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {
	private FoodDao dao;
	private SimpleDirectedWeightedGraph<Food,DefaultWeightedEdge> grafo;
private ArrayList<Food> cibi;
	
	public Model() {
		dao=new FoodDao();
	}

	public void creaGrafo(int porzioni) {
		this.grafo=new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		cibi= new ArrayList<Food>(dao.numberPortion(porzioni));
		for(Food f: cibi) {
			grafo.addVertex(f);
		}
		ArrayList<CollegamentoCibi> list= dao.getPorzioneCibo();
		for(CollegamentoCibi temp: list) {
			Food primo=temp.getF1();
			Food secondo=temp.getF2();
			if(cibi.contains(primo) && cibi.contains(secondo)) {
				double peso=temp.getPeso();
				if(peso>0) {
					Graphs.addEdge(grafo, primo, secondo, peso);
				} 
				if(peso<0) {
					Graphs.addEdge(grafo, secondo, primo, -peso);
				}
			}
			
		}
				}

	public SimpleDirectedWeightedGraph<Food, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}
	private ArrayList<Collegamento> lista;
	public ArrayList<Collegamento> cercaVicini(Food f) {
		ArrayList<Collegamento> lista= new ArrayList<Collegamento>();
		for( DefaultWeightedEdge arco: grafo.outgoingEdgesOf(f)) {
			lista.add(new Collegamento(grafo.getEdgeWeight(arco),grafo.getEdgeTarget(arco)));
		}
		Collections.sort(lista,new ComparatorePerGrassi());
		System.out.println(lista);
		return lista;
	}
	

	public ArrayList<Collegamento> getLista() {
		return lista;
	}

	public ArrayList<Food> getCibi() {
		return cibi;
	}

	public void simula(int i, Food food) {
		Simulatore sim= new Simulatore();
		sim.init(i,lista,food);
		sim.run();
		
	}
	
	
	
	

}
