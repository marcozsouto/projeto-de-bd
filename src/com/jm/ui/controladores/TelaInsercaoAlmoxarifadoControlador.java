package com.jm.ui.controladores;

import com.jm.negocio.fachada.Fachada;
import com.jm.negocio.modelo.Almoxarifado;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class TelaInsercaoAlmoxarifadoControlador implements Initializable {
    @FXML
    private Label erro;
    @FXML
    private TextField preco;

    @FXML
    private Button menos;

    @FXML
    private TextField nome;

    @FXML
    private Button Cadastrar;

    @FXML
    private Button mais;

    @FXML
    private TextField quantidade;

    @FXML
    private TextField descricao;
    
    private int i=0;

    @FXML
    void Cadastrar(ActionEvent event) throws SQLException {
        Almoxarifado novo = new Almoxarifado();
        novo.setNome(nome.getText());
        int j = 0;
        try{
            novo.setPreco(Double.parseDouble(preco.getText()));
            novo.setQuantidade(Integer.parseInt(quantidade.getText()));
            novo.setDescricao(descricao.getText());
        }catch(NumberFormatException e){
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
            Fachada.getSingleton().CadastroItemValidacao(novo);
            Stage stages = (Stage) Cadastrar.getScene().getWindow();
            stages.close();
        }
        
        
    }

    @FXML
    void incrementar(ActionEvent event) {
        i = i+1;
        quantidade.setText(String.valueOf(i));
    }

    @FXML
    void decrementar(ActionEvent event) {
        if(i>=1){
        i = i-1;
        quantidade.setText(String.valueOf(i));
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        quantidade.setText("1");
    }    
    
}
