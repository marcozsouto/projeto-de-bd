package com.jm.ui.controladores;

import com.jm.dados.MetodosCliente;
import com.jm.negocio.excecoes.ArrayVazioException;
import com.jm.negocio.fachada.Fachada;
import com.jm.negocio.modelo.Cliente;
import com.jm.negocio.modelo.Endereco;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class TelaAtualizacaoClienteControlador implements Initializable {
    
    @FXML
    private Label erro;
    
    @FXML
    private TextField pesquisa;

    @FXML
    private Button Buscar;
    
    
    @FXML
    private TextField telefone;

    @FXML
    private Button botao;
    
    @FXML
    private TextField numero;

    @FXML
    private TextField bairro;

    @FXML
    private TextField nome;

    @FXML
    private TextField cpf_cnpj;

    @FXML
    private TextField cep;

    @FXML
    private TextField texto;

    @FXML
    private TextField senha;

    @FXML
    private TextField complemento;

    @FXML
    private Button pesquisar;

    @FXML
    private Button Alterar;

    @FXML
    private ListView<Cliente> clientes;

    @FXML
    private TextField email;

    @FXML
    private TextField rua;

    @FXML
    private Cliente selecionado;
    
    @FXML
    public void listarClientes() throws SQLException, ArrayVazioException{
    ObservableList lista = FXCollections.observableArrayList(Fachada.getSingleton().MostrarClienteValidacao());
    clientes.setItems(lista);
    
    }
    
    @FXML
    void Buscar(ActionEvent event) throws SQLException, ArrayVazioException {
        ArrayList<Cliente> todos = Fachada.getSingleton().MostrarClienteValidacao();
        ArrayList<Cliente> novos = new ArrayList<>();
        if(pesquisa.getText().isEmpty()==true){
            listarClientes();
        }
        for(int i=0;i<todos.size();i++){
            if(todos.get(i).getCpf_cnpj().startsWith(pesquisa.getText())==true){
                novos.add(todos.get(i));
            }
        }
        ObservableList lista = FXCollections.observableArrayList(novos);
        clientes.setItems(lista);
    }
    
    @FXML
    void Botao() throws SQLException{
        selecionado = clientes.getSelectionModel().selectedItemProperty().getValue();
        Cliente novo = new Cliente();
        int i = 0;
        if(selecionado == null){
            erro.setText("Opção Invalida!");
            i++;
        }
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
            endereco.setId(selecionado.getEndereco().getId());
            endereco.setRua(rua.getText());
            endereco.setBairro(bairro.getText());
            endereco.setCep(cep.getText());
            endereco.setNumero(numero.getText());
            endereco.setComplemento(complemento.getText());
            novo.setEndereco(endereco);
            Fachada.getSingleton().AtualizarClienteValidacao(selecionado, novo);
            Stage stages = (Stage) botao.getScene().getWindow();
            stages.close();
        }
    }
    @FXML
    void Alterar(ActionEvent event) {
        selecionado = clientes.getSelectionModel().selectedItemProperty().getValue();
        nome.setText(selecionado.getNome());
        cpf_cnpj.setText(selecionado.getCpf_cnpj());
        telefone.setText(selecionado.getTelefone());
        email.setText(selecionado.getEmail());
        senha.setText(selecionado.getSenha());
        rua.setText(selecionado.getEndereco().getRua());
        bairro.setText(selecionado.getEndereco().getBairro());
        cep.setText(selecionado.getEndereco().getCep());
        numero.setText(selecionado.getEndereco().getNumero());
        complemento.setText(selecionado.getEndereco().getComplemento());
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            listarClientes();
        } catch (SQLException ex) {
            Logger.getLogger(TelaAtualizacaoClienteControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ArrayVazioException ex) {
            Logger.getLogger(TelaAtualizacaoClienteControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
