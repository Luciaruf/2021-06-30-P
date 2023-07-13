package it.polito.tdp.genes;

import java.net.URL;
import java.util.ResourceBundle;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.genes.model.Adiacenti;
import it.polito.tdp.genes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Model model ;
	Graph<String, DefaultWeightedEdge> graph;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnStatistiche;

    @FXML
    private Button btnRicerca;

    @FXML
    private ComboBox<String> boxLocalizzazione;

    @FXML
    private TextArea txtResult;

    @FXML
    void doRicerca(ActionEvent event) {

    	
    }

    @FXML
    void doStatistiche(ActionEvent event) {

    	txtResult.clear();
    	
    	String localization = this.boxLocalizzazione.getValue();
    	
    	for(Adiacenti a : this.model.getAdiacenti(localization)) {
    		txtResult.appendText(localization +"\n");
    		txtResult.appendText(a.getLocalization() + " "+a.getPeso()+"\n");
    	}
    	
    }

    @FXML
    void initialize() {
        assert btnStatistiche != null : "fx:id=\"btnStatistiche\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnRicerca != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxLocalizzazione != null : "fx:id=\"boxLocalizzazione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		
		this.graph = this.model.creaGrafo();
		txtResult.appendText("#VERTICI: "+this.graph.vertexSet().size()+"\n"+"#ARCHI: "+this.graph.edgeSet().size()+"\n");
	

		this.boxLocalizzazione.getItems().addAll(this.graph.vertexSet());
	}
}
