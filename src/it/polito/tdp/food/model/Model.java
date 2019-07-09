package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.Condiment;
import it.polito.tdp.food.db.FoodDao;

public class Model {
	FoodDao dao;
	SimpleWeightedGraph<Condiment, DefaultWeightedEdge> grafo;
	
	public Model() {
		dao= new FoodDao();
	}
	
	public void creaGrafo(double calorie) {
		grafo= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(grafo, dao.ingredientiPerCalorie(calorie));
		dao.aggiungiListaCibi();
		
		for(Condiment c1: grafo.vertexSet()) {
			for(Condiment c2: grafo.vertexSet()) {
				if(c1.getCondiment_id()<c2.getCondiment_id()) {
				Graphs.addEdgeWithVertices(grafo, c1, c2, c1.confrontaCibi(c2));
				}
			}
		}
	}
	
	public String grafoCreato() {
		return "Grafo creato!\nNumero di verici: "+grafo.vertexSet().size()+"\nNumero di archi: "+grafo.edgeSet().size();
	}
	
	public List<Condiment> ingredientiOrdinati(){
		List<Condiment> result= new ArrayList<Condiment>(grafo.vertexSet());
		Collections.sort(result);
		return result;
	}
}
