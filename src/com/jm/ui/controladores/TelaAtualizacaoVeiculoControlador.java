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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaAtualizacaoVeiculoControlador implements Initializable {
    
    @FXML
    private TextField pesquisa;

    @FXML
    private Button Buscar;

    @FXML
    private ChoiceBox<String> tipo;

    @FXML
    private Button Atualizar;

    @FXML
    private Button Alterar;

    
    @FXML
    private ListView<Veiculo> veiculos;

    @FXML
    private TextField modelo;

    @FXML
    private TextField placa;
    
    private Veiculo selecionado;
    
    @FXML
    private Label erro;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            listarVeiculos();
        } catch (SQLException ex) {
            Logger.getLogger(TelaAtualizacaoVeiculoControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void Buscar(ActionEvent event) throws SQLException {
        ArrayList<Veiculo> todos = Fachada.getSingleton().MostrarVeiculoValidacao();
        ArrayList<Veiculo> novos = new ArrayList<>();
        if(pesquisa.getText().isEmpty()==true){
            ObservableList lista3 = FXCollections.observableArrayList(Fachada.getSingleton().MostrarVeiculoValidacao());
            veiculos.setItems(lista3);
        }
        for(int i=0;i<todos.size();i++){
            if(todos.get(i).getPlaca().startsWith(pesquisa.getText())==true){
                novos.add(todos.get(i));
            }
        }
        ObservableList lista = FXCollections.observableArrayList(novos);
        veiculos.setItems(lista);
    }
    
    @FXML
    void Alterar(ActionEvent event) {
        selecionado = veiculos.getSelectionModel().selectedItemProperty().getValue();
        modelo.setText(selecionado.getModelo());
        placa.setText(selecionado.getPlaca());
        inicializarTipos();
        if(selecionado.equals(Veiculo.CARRETA_PRANCHA)){
            tipo.setValue(Veiculo.CARRETA_PRANCHA);
        }
        if(selecionado.equals(Veiculo.CESTO_AEREO)){
            tipo.setValue(Veiculo.CESTO_AEREO);
        }
        if(selecionado.equals(Veiculo.EMPILHADEIRA)){
            tipo.setValue(Veiculo.EMPILHADEIRA);
        }
        if(selecionado.equals(Veiculo.GUINDASTE_ARTICULADO)){
            tipo.setValue(Veiculo.GUINDASTE_ARTICULADO);
        }
        if(selecionado.equals(Veiculo.GUINDASTE_HIDRAULICO)){
            tipo.setValue(Veiculo.GUINDASTE_HIDRAULICO);
        }
        if(selecionado.equals(Veiculo.REMOCAO_INDUSTRIAL)){
            tipo.setValue(Veiculo.REMOCAO_INDUSTRIAL);
        }
    }

    @FXML
    void Atualizar(ActionEvent event) throws SQLException {
        selecionado = veiculos.getSelectionModel().selectedItemProperty().getValue();
        int i = 0;
        if(selecionado == null){
            erro.setText("Opção Invalida!");
            i++;
        }
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
        Fachada.getSingleton().AtualizarVeiculoValidacao(selecionado, novo);
        Stage stages = (Stage) Atualizar.getScene().getWindow();
        stages.close();
        }
    }
    
    public void inicializarTipos(){
        ObservableList lista = FXCollections.observableArrayList();
        lista.removeAll(lista);
        lista.addAll(Veiculo.CARRETA_PRANCHA,Veiculo.CESTO_AEREO,Veiculo.EMPILHADEIRA,Veiculo.GUINDASTE_ARTICULADO,Veiculo.GUINDASTE_HIDRAULICO,Veiculo.REMOCAO_INDUSTRIAL);
        tipo.getItems().addAll(lista);
        tipo.setValue(Veiculo.CARRETA_PRANCHA);
    }
    
    public void listarVeiculos() throws SQLException{
    ObservableList lista = FXCollections.observableArrayList(Fachada.getSingleton().MostrarVeiculoValidacao());
    veiculos.setItems(lista);
    }
    
}
