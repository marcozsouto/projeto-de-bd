/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.ui.controladores;

import com.jm.negocio.fachada.Fachada;
import com.jm.negocio.modelo.Veiculo;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaInsercaoVeiculoControlador implements Initializable {

    @FXML
    private ChoiceBox<String> tipo;

    @FXML
    private Button botao;

    @FXML
    private TextField modelo;

    @FXML
    private TextField placa;

    @FXML
    private Label erro;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTipos();
    }    
    
    public void inicializarTipos(){
        ObservableList lista = FXCollections.observableArrayList();
        lista.removeAll(lista);
        lista.addAll(Veiculo.CARRETA_PRANCHA,Veiculo.CESTO_AEREO,Veiculo.EMPILHADEIRA,Veiculo.GUINDASTE_ARTICULADO,Veiculo.GUINDASTE_HIDRAULICO,Veiculo.REMOCAO_INDUSTRIAL);
        tipo.getItems().addAll(lista);
        tipo.setValue(Veiculo.CARRETA_PRANCHA);
    }
    
    
    @FXML
    void botao(ActionEvent event) throws SQLException {
        int i=0;
        if(modelo.getText().isEmpty() == true || modelo.getText() == null ){
            erro.setText("Opção Invalida!");
            i++;
        }
        if(placa.getText().isEmpty() == true || placa.getText() == null ){
            erro.setText("Opção Invalida!");
            i++;
        }
        if(i==0){
            Veiculo novo = new Veiculo();
            String opc = tipo.getValue();
            if(opc.equals(Veiculo.CARRETA_PRANCHA)){
                novo.setTipo(Veiculo.CARRETA_PRANCHA);
            }
            if(opc.equals(Veiculo.CESTO_AEREO)){
                novo.setTipo(Veiculo.CESTO_AEREO);
            }
            if(opc.equals(Veiculo.EMPILHADEIRA)){
                novo.setTipo(Veiculo.EMPILHADEIRA);
            }
            if(opc.equals(Veiculo.GUINDASTE_ARTICULADO)){
                novo.setTipo(Veiculo.GUINDASTE_ARTICULADO);
            }
            if(opc.equals(Veiculo.GUINDASTE_HIDRAULICO)){
                novo.setTipo(Veiculo.GUINDASTE_HIDRAULICO);
            }
            if(opc.equals(Veiculo.REMOCAO_INDUSTRIAL)){
                novo.setTipo(Veiculo.REMOCAO_INDUSTRIAL);
            }
            novo.setModelo(modelo.getText());
            novo.setPlaca(placa.getText());
            Fachada.getSingleton().CadastrarVeiculoValidacao(novo);
            Stage stages = (Stage) botao.getScene().getWindow();
            stages.close();
        }
        
    }
}
