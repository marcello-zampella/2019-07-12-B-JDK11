/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.polito.tdp.food.modell.Collegamento;
import it.polito.tdp.food.modell.Food;
import it.polito.tdp.food.modell.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FoodController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtPorzioni"
    private TextField txtPorzioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnGrassi"
    private Button btnGrassi; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="boxFood"
    private ComboBox<Food> boxFood; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Creazione grafo...");
    	int porzioni;
    	if(isNumeric(this.txtPorzioni.getText()))
    		 porzioni = Integer.parseInt(this.txtPorzioni.getText());
    	else {
    		this.txtResult.setText("DEVI INSERIRE UN NUMERO INTERO!");
    		return;
    	}
    	this.model.creaGrafo(porzioni);
    	this.boxFood.getItems().addAll(model.getGrafo().vertexSet());
    	this.txtResult.appendText("finito");
    }
    
    public static boolean isNumeric(String str) { 
    	  try {  
    	    Integer.parseInt(str);  
    	    return true;
    	  } catch(NumberFormatException e){  
    	    return false;  
    	  }  
    	}

    @FXML
    void doGrassi(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Analisi grassi...\n");
    	Food f=this.boxFood.getValue();
    	ArrayList<Collegamento>list=model.cercaVicini(f);
    	for(int i=0; i<5; i++) {
    		this.txtResult.appendText(model.getCibi().get(model.getCibi().indexOf(list.get(i).getCibo()))+" differenza di "+list.get(i).getPeso()+"\n");
    	}
    }

    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Simulazione...");
    	if(isNumeric(this.txtK.getText())) {
    		if(Integer.parseInt(this.txtK.getText())<1||Integer.parseInt(this.txtK.getText())>10) {
    			this.txtResult.setText("INSERISCI UN NUMERO COMPRESO TRA 1 E 10");
    			return;
    		}
    		model.simula(Integer.parseInt(this.txtK.getText()),this.boxFood.getValue());
    	}
    	else {
    		this.txtResult.setText("INSERISCI UN NUMERO INTERO");
    		return;
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtPorzioni != null : "fx:id=\"txtPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnGrassi != null : "fx:id=\"btnGrassi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxFood != null : "fx:id=\"boxFood\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
