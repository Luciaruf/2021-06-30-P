package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	
	GenesDao dao;
	Graph<String, DefaultWeightedEdge> graph;
	
	public Model() {
		this.dao = new GenesDao();
		this.graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
	}
	
	public Graph creaGrafo() {
		this.graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.graph, this.dao.getLocalization());
		
		for(String s : this.graph.vertexSet()) {
			for(String s1 : this.graph.vertexSet()) {
				
				if(s.compareTo(s1)!=0) {
					
					int peso = this.dao.getPeso(s, s1);
					
					if(peso!=0) {
						Graphs.addEdge(this.graph, s, s1, peso);
					}
				}
			}
		}
		
		return this.graph;
	}
	
	public List<String> getLocalization(){
		return this.dao.getLocalization();
	}
	
	public List<Adiacenti> getAdiacenti(String localization){
		List<String> successori = Graphs.successorListOf(this.graph, localization);
		
		List<Adiacenti> risultato = new ArrayList<>();
		
		for(String a: successori) {
			risultato.add(new Adiacenti(a, (int)this.graph.getEdgeWeight(this.graph.getEdge(localization, a))));
		}
		
		
		return risultato;
	}
	
	

}