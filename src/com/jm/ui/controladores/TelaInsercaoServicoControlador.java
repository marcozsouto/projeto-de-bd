package com.jm.ui.controladores;

import com.jm.negocio.excecoes.ArrayVazioException;
import com.jm.negocio.fachada.Fachada;
import com.jm.negocio.modelo.Cliente;
import com.jm.negocio.modelo.Endereco;
import com.jm.negocio.modelo.Funcionario;
import com.jm.negocio.modelo.Servico;
import com.jm.negocio.modelo.Veiculo;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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

public class TelaInsercaoServicoControlador implements Initializable {
    
    @FXML
    private Label erro;
    
    @FXML
    private TextField numero;

    @FXML
    private TextField bairro;

    @FXML
    private Button cadastrar;

    @FXML
    private TextField cep;

    
    @FXML
    private TextField preco;

    @FXML
    private ListView<Veiculo> veiculos;

    @FXML
    private TextField complemento;


    @FXML
    private ListView<Funcionario> funcionarios;

    @FXML
    private ListView<Cliente> clientes;

    @FXML
    private TextField rua;

    @FXML
    private ChoiceBox<String> status;
    
    @FXML
    private Button BuscarF;

    @FXML
    private TextField pesquisaf;
    
    
    @FXML
    private TextField pesquisac;

    @FXML
    private Button BuscarV;

    @FXML
    private TextField pesquisav;

    @FXML
    private Button BuscarC;
    
    

    @FXML
    void Cadastrar(ActionEvent event) throws SQLException {
        int i = 0;
        Calendar c = Calendar.getInstance();
        Servico novo = new Servico();
        Funcionario fselecionado  = funcionarios.getSelectionModel().selectedItemProperty().getValue();
        Cliente cselecionado = clientes.getSelectionModel().selectedItemProperty().getValue();
        Veiculo vselecionado = veiculos.getSelectionModel().selectedItemProperty().getValue();
        
        if(fselecionado == null || cselecionado == null || vselecionado == null){
            erro.setText("Opção Invalida!");
            i++;
        }
        try{
        novo.setPreco(Double.parseDouble(preco.getText()));
        }catch(NumberFormatException e){
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
        if(i==0){
        novo.setStatus(status.getValue());
        novo.setCliente(cselecionado);
        novo.setFuncionario(fselecionado);
        novo.setVeiculo(vselecionado);
        novo.setData(String.valueOf(c.get(Calendar.DAY_OF_MONTH))+'-'+String.valueOf(c.get(Calendar.MONTH)+1)+'-'+String.valueOf(c.get(Calendar.YEAR)));
        Endereco e = new Endereco();
        e.setBairro(bairro.getText());
        e.setCep(cep.getText());
        e.setRua(rua.getText());
        e.setNumero(numero.getText());
        e.setComplemento(complemento.getText());
        novo.setEndereco(e);
        Fachada.getSingleton().CadastrarServicoValidacao(novo);
        Stage stages = (Stage) cadastrar.getScene().getWindow();
        stages.close();
        erro.setText("");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            inicializarOpcoes();
        } catch (SQLException ex) {
            Logger.getLogger(TelaInsercaoServicoControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ArrayVazioException ex) {
            Logger.getLogger(TelaInsercaoServicoControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        inicializarStatus();
    }
    
    public void inicializarOpcoes() throws SQLException, ArrayVazioException{
    ObservableList lista1 = FXCollections.observableArrayList(Fachada.getSingleton().MostrarFuncionarioValidacao());
    funcionarios.setItems(lista1);
    ObservableList lista2 = FXCollections.observableArrayList(Fachada.getSingleton().MostrarClienteValidacao());
    clientes.setItems(lista2);
    ObservableList lista3 = FXCollections.observableArrayList(Fachada.getSingleton().MostrarVeiculoValidacao());
    veiculos.setItems(lista3);
    }
    
    public void inicializarStatus(){
        ObservableList lista = FXCollections.observableArrayList();
        lista.removeAll(lista);
        lista.addAll(Servico.st1,Servico.st2,Servico.st3,Servico.st4);
        status.getItems().addAll(lista);
        status.setValue(Servico.st1);
    }
    
    @FXML
    void BuscarC(ActionEvent event) throws SQLException, ArrayVazioException {
        ArrayList<Cliente> todos = Fachada.getSingleton().MostrarClienteValidacao();
        ArrayList<Cliente> novos = new ArrayList<>();
        if(pesquisac.getText().isEmpty()==true){
            ObservableList lista2 = FXCollections.observableArrayList(Fachada.getSingleton().MostrarClienteValidacao());
            clientes.setItems(lista2);
        }
        for(int i=0;i<todos.size();i++){
            if(todos.get(i).getCpf_cnpj().startsWith(pesquisac.getText())==true){
                novos.add(todos.get(i));
            }
        }
        ObservableList lista = FXCollections.observableArrayList(novos);
        clientes.setItems(lista);
    }

    @FXML
    void BuscarV(ActionEvent event) throws SQLException {
        ArrayList<Veiculo> todos = Fachada.getSingleton().MostrarVeiculoValidacao();
        ArrayList<Veiculo> novos = new ArrayList<>();
        if(pesquisav.getText().isEmpty()==true){
            ObservableList lista3 = FXCollections.observableArrayList(Fachada.getSingleton().MostrarVeiculoValidacao());
            veiculos.setItems(lista3);
        }
        for(int i=0;i<todos.size();i++){
            if(todos.get(i).getPlaca().startsWith(pesquisav.getText())==true){
                novos.add(todos.get(i));
            }
        }
        ObservableList lista = FXCollections.observableArrayList(novos);
        veiculos.setItems(lista);
    }
   
    @FXML
    void BuscarF(ActionEvent event) throws SQLException, ArrayVazioException {
        ArrayList<Funcionario> todos = Fachada.getSingleton().MostrarFuncionarioValidacao();
        ArrayList<Funcionario> novos = new ArrayList<>();
        if(pesquisaf.getText().isEmpty()==true){
            ObservableList lista1 = FXCollections.observableArrayList(Fachada.getSingleton().MostrarFuncionarioValidacao());
            funcionarios.setItems(lista1);
        }
        for(int i=0;i<todos.size();i++){
            if(todos.get(i).getCpf().startsWith(pesquisaf.getText())==true){
                novos.add(todos.get(i));
            }
        }
        ObservableList lista = FXCollections.observableArrayList(novos);
        funcionarios.setItems(lista);
    }
    
}
