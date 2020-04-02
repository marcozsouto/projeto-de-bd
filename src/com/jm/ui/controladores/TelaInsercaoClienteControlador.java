/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.ui.controladores;

import com.jm.negocio.fachada.Fachada;
import com.jm.negocio.modelo.Cliente;
import com.jm.negocio.modelo.Endereco;
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

/**
 * FXML Controller class
 *
 * @author Marcos
 */
public class TelaInsercaoClienteControlador implements Initializable {
    
    @FXML
    private TextField senha;

    @FXML
    private TextField telefone;

    @FXML
    private TextField complemento;

    @FXML
    private TextField numero;

    @FXML
    private TextField bairro;

    @FXML
    private TextField nome;

    @FXML
    private Label label;

    @FXML
    private Button botao;

    @FXML
    private TextField email;

    @FXML
    private TextField cpf_cnpj;

    @FXML
    private TextField cep;

    @FXML
    private TextField rua;
    
    @FXML
    private Label erro;

    @FXML
    void botao(ActionEvent event) throws SQLException {
        Cliente novo = new Cliente();
        int i = 0;
        if(nome.getText().matches("^[ A-Za-z]+$")==false || nome.getText().isEmpty() == true){
            erro.setText("Opção Invalida!");
            i++;
        }
		
        if(cpf_cnpj.getText().matches("[0-9]*")==false || cpf_cnpj.getText().isEmpty() == true){
            erro.setText("Opção Invalida!");
            i++;
        }
		
        if(telefone.getText().matches("[0-9]*")==false || telefone.getText().isEmpty() == true){
            erro.setText("Opção Invalida!");
            i++;
        }
        
        if(cep.getText().matches("[0-9]*")==false){
            erro.setText("Opção Invalida!");
            i++;
        }
        if(numero.getText().matches("[0-9]*")==false){
            erro.setText("Opção Invalida!");
            i++;
        }
        if(rua.getText().isEmpty()==true){
            erro.setText("Opção Invalida!");
            i++;
        }
        if(bairro.getText().isEmpty()==true){
            erro.setText("Opção Invalida!");
            i++;    
        }
        if(senha.getText().isEmpty()==true){
            erro.setText("Opção Invalida!");
            i++;    
        }
        if(i==0){
            novo.setNome(nome.getText());
            novo.setCpf_cnpj(cpf_cnpj.getText());
            novo.setEmail(email.getText());
            novo.setTelefone(telefone.getText());
            novo.setSenha(senha.getText());
            Endereco endereco = new Endereco();
            endereco.setRua(rua.getText());
            endereco.setBairro(bairro.getText());
            endereco.setCep(cep.getText());
            endereco.setNumero(numero.getText());
            endereco.setComplemento(complemento.getText());
            novo.setEndereco(endereco);
            Fachada.getSingleton().CadastroClienteValidacao(novo);
            Stage stages = (Stage) botao.getScene().getWindow();
            stages.close();
        }
    }
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
