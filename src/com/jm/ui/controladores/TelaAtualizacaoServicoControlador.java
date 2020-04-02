package com.jm.ui.controladores;

import com.jm.negocio.excecoes.ArrayVazioException;
import com.jm.negocio.fachada.Fachada;
import com.jm.negocio.modelo.Cliente;
import com.jm.negocio.modelo.Endereco;
import com.jm.negocio.modelo.Funcionario;
import com.jm.negocio.modelo.Servico;
import com.jm.negocio.modelo.Veiculo;
import java.net.URL;
import static java.nio.channels.spi.AsynchronousChannelProvider.provider;
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

public class TelaAtualizacaoServicoControlador implements Initializable {

    @FXML
    private Button BuscarC;
    
    @FXML
    private Button BuscarV;
    
    @FXML
    private Button BuscarS;
    
    @FXML
    private Button BuscarF;
    
    @FXML
    private TextField pesquisac;
    
    @FXML
    private TextField pesquisav;
     
    @FXML
    private TextField pesquisas;
      
    @FXML
    private TextField pesquisaf;
    
    @FXML
    private ListView<Servico> servicos;

    @FXML
    private TextField numero;

    @FXML
    private TextField bairro;

    @FXML
    private TextField cep;

    @FXML
    private TextField preco;

    @FXML
    private ListView<Veiculo> veiculos;

    @FXML
    private TextField complemento;

    @FXML
    private Button Alterar;

    @FXML
    private Button atualizar;

    @FXML
    private ListView<Funcionario> funcionarios;

    @FXML
    private ListView<Cliente> clientes;

    @FXML
    private ChoiceBox<String> status;

    @FXML
    private TextField rua;
    
    private Funcionario fselecionado;
    
    private Cliente cselecionado;
    
    private Veiculo vselecionado;
    
    private Servico sselecionado;
    
    @FXML
    private Label erro;
    
    @FXML
    void BuscarS(ActionEvent event) throws SQLException {
        ArrayList<Servico> todos = Fachada.getSingleton().MostrarServicoValidacao();
        ArrayList<Servico> novos = new ArrayList<>();
        if(pesquisas.getText().isEmpty()==true){
            ObservableList lista4 = FXCollections.observableArrayList(Fachada.getSingleton().MostrarServicoValidacao());
            servicos.setItems(lista4);
        }
        for(int i=0;i<todos.size();i++){
            if(todos.get(i).getCliente().getCpf_cnpj().startsWith(pesquisas.getText())==true){
                novos.add(todos.get(i));
            }
        }
        ObservableList lista = FXCollections.observableArrayList(novos);
        servicos.setItems(lista);
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
    
    @FXML
    void Atualizar(ActionEvent event) throws SQLException {
        sselecionado = servicos.getSelectionModel().selectedItemProperty().getValue();
        fselecionado = funcionarios.getSelectionModel().selectedItemProperty().getValue();
        cselecionado = clientes.getSelectionModel().selectedItemProperty().getValue();
        vselecionado = veiculos.getSelectionModel().selectedItemProperty().getValue();
        Servico novo = new Servico();
        int i =0;
        if(sselecionado  == null || fselecionado == null || cselecionado == null || vselecionado == null){
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
        }try{
        novo.setPreco(Double.parseDouble(preco.getText()));
        }catch(NumberFormatException e){
            erro.setText("Opção Invalida!");
            i++;
        }
        if(i==0){
            novo.setStatus(status.getValue());
            Endereco enovo = new Endereco();
            enovo.setId(sselecionado.getEndereco().getId());
            enovo.setRua(rua.getText());
            enovo.setBairro(bairro.getText());
            enovo.setCep(cep.getText());
            enovo.setComplemento(complemento.getText());
            enovo.setNumero(numero.getText());
            novo.setEndereco(enovo);
            novo.setFuncionario(fselecionado);
            novo.setCliente(cselecionado);
            novo.setVeiculo(vselecionado);
            Fachada.getSingleton().AtualizarServicoValidacao(sselecionado, novo);
            Stage stages = (Stage) atualizar.getScene().getWindow();
            stages.close();
            }
        }

    @FXML
    void Alterar(ActionEvent event) throws SQLException, ArrayVazioException {
        sselecionado  = servicos.getSelectionModel().selectedItemProperty().getValue();
        Endereco sendereco = sselecionado.getEndereco();
        status.setValue(sselecionado.getStatus());
        preco.setText(String.valueOf(sselecionado.getPreco()));
        rua.setText(sendereco.getRua());
        bairro.setText(sendereco.getBairro());
        cep.setText(sendereco.getCep());
        numero.setText(sendereco.getNumero());
        complemento.setText(sendereco.getComplemento());
        fselecionado = sselecionado.getFuncionario();
        cselecionado = sselecionado.getCliente();
        vselecionado = sselecionado.getVeiculo();
        ArrayList<Funcionario> af = Fachada.getSingleton().MostrarFuncionarioValidacao();
        ArrayList<Cliente> ac = Fachada.getSingleton().MostrarClienteValidacao();
        ArrayList<Veiculo> av = Fachada.getSingleton().MostrarVeiculoValidacao();
        int f = 0,c = 0,v = 0;
        for(int i= 0; i<af.size();i++){
            if(fselecionado.getId() == af.get(i).getId()){
                f = i;
                break;
            }
        }
        for(int i= 0; i<ac.size();i++){
            if(cselecionado.getId() == ac.get(i).getId()){
                c = i;
                break;
            }
        }
        for(int i= 0; i<av.size();i++){
            if(vselecionado.getId() == av.get(i).getId()){
                v = i;
                break;
            }
        }
        
        funcionarios.getSelectionModel().select(f);
        clientes.getSelectionModel().select(c);
        veiculos.getSelectionModel().select(v);
        fselecionado = af.get(f);
        cselecionado = ac.get(c);
        vselecionado = av.get(v);
        
    }
    
    void inicializarOpcoes() throws SQLException, ArrayVazioException{
        ObservableList lista1 = FXCollections.observableArrayList(Fachada.getSingleton().MostrarFuncionarioValidacao());
        funcionarios.setItems(lista1);
        ObservableList lista2 = FXCollections.observableArrayList(Fachada.getSingleton().MostrarClienteValidacao());
        clientes.setItems(lista2);
        ObservableList lista3 = FXCollections.observableArrayList(Fachada.getSingleton().MostrarVeiculoValidacao());
        veiculos.setItems(lista3);
        ObservableList lista4 = FXCollections.observableArrayList(Fachada.getSingleton().MostrarServicoValidacao());
        servicos.setItems(lista4);
    }
    
    void inicializarStatus(){
        ObservableList lista = FXCollections.observableArrayList();
        lista.removeAll(lista);
        lista.addAll(Servico.st1,Servico.st2,Servico.st3,Servico.st4);
        status.getItems().addAll(lista);
        status.setValue(Servico.st1);
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            inicializarOpcoes();
        } catch (SQLException ex) {
            Logger.getLogger(TelaAtualizacaoServicoControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ArrayVazioException ex) {
            Logger.getLogger(TelaAtualizacaoServicoControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        inicializarStatus();
    
    }
    
    
}
