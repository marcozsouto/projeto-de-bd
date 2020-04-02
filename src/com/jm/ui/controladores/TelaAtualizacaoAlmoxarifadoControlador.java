package com.jm.ui.controladores;

import com.jm.negocio.fachada.Fachada;
import com.jm.negocio.modelo.Almoxarifado;
import com.jm.negocio.modelo.Cliente;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaAtualizacaoAlmoxarifadoControlador implements Initializable {
    
    @FXML
    private TextField pesquisa;
    
    @FXML
    private TextField preco;

    @FXML
    private TextField texto;

    @FXML
    private Button Buscar;
    
    @FXML
    private Button menos;

    @FXML
    private Button Alterar;

    @FXML
    private TextField nome;

    @FXML
    private Button Atualizar;

    @FXML
    private ListView<Almoxarifado> items;

    @FXML
    private Button mais;

    @FXML
    private TextField quantidade;

    @FXML
    private TextField descricao;
    
    private int i=0;

    private Almoxarifado selecionado;
    
    @FXML
    private Label erro;
    
    @FXML
    void decrementar(ActionEvent event) {
        if(i>=1){
        i = i-1;
        quantidade.setText(String.valueOf(i));
        }
    }

    @FXML
    void incrementar(ActionEvent event) {
        i = i+1;
        quantidade.setText(String.valueOf(i));
    
    }

    @FXML
    void Atualizar(ActionEvent event) throws SQLException {
        selecionado = items.getSelectionModel().selectedItemProperty().getValue();
        Almoxarifado novo = new Almoxarifado();
        int j = 0;
        try{
            novo.setNome(nome.getText());
            novo.setPreco(Double.parseDouble(preco.getText()));
            novo.setQuantidade(Integer.parseInt(quantidade.getText()));
            novo.setDescricao(descricao.getText());
        }catch(NumberFormatException e){
            j++;
            erro.setText("Opção Invalida!");
        }
        if(selecionado == null){
            j++;
            erro.setText("Opção Invalida!");
        }
        if(nome.getText().isEmpty() == true){
            j++;
            erro.setText("Opção Invalida!");
        }
        if(Integer.parseInt(quantidade.getText()) < 1){
            j++;
            erro.setText("Opção Invalida!");
        }
        if(j==0){
            Fachada.getSingleton().AtualizarItemValidacao(selecionado, novo);
            Stage stages = (Stage) Atualizar.getScene().getWindow();
            stages.close();
        }
        
    }

    

    @FXML
    void Alterar(ActionEvent event) {
        selecionado = items.getSelectionModel().selectedItemProperty().getValue();
        nome.setText(selecionado.getNome());
        preco.setText(String.valueOf(selecionado.getPreco()));
        quantidade.setText(String.valueOf(selecionado.getQuantidade()));
        descricao.setText(selecionado.getDescricao());
        i = Integer.parseInt(quantidade.getText());
    }
    
    void listarItems() throws SQLException{
    ObservableList lista = FXCollections.observableArrayList(Fachada.getSingleton().MostrarItemValidacao());
    items.setItems(lista);
    }
    
    @FXML
    void Buscar(ActionEvent event) throws SQLException {
        ArrayList<Almoxarifado> todos = Fachada.getSingleton().MostrarItemValidacao();
        ArrayList<Almoxarifado> novos = new ArrayList<>();
        if(pesquisa.getText().isEmpty()==true){
            listarItems();
        }
        for(int i=0;i<todos.size();i++){
            if(todos.get(i).getNome().startsWith(pesquisa.getText())==true){
                novos.add(todos.get(i));
            }
        }
        ObservableList lista = FXCollections.observableArrayList(novos);
        items.setItems(lista);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        quantidade.setText("1");
        try {
            listarItems();
        } catch (SQLException ex) {
            Logger.getLogger(TelaAtualizacaoAlmoxarifadoControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
