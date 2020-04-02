package com.jm.ui.controladores;

import com.jm.negocio.excecoes.ArrayVazioException;
import com.jm.negocio.excecoes.CpfInvalidoException;
import com.jm.negocio.excecoes.CtpsInvalidoException;
import com.jm.negocio.excecoes.FuncionarioExistenteException;
import com.jm.negocio.excecoes.NomeInvalidoException;
import com.jm.negocio.excecoes.RgInvalidoException;
import com.jm.negocio.excecoes.TelefoneInvalidoException;
import com.jm.negocio.fachada.Fachada;
import com.jm.negocio.modelo.Endereco;
import com.jm.negocio.modelo.Funcionario;
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

public class TelaAtualizacaoFuncionarioControlador implements Initializable {

    @FXML
    private TextField pesquisa;

    @FXML
    private Button Buscar;
    
    @FXML
    private Button botao;
    
    @FXML
    private Button Alterar;
    
    @FXML
    private TextField telefone;

    @FXML
    private TextField numero;

    @FXML
    private TextField bairro;

    @FXML
    private TextField salario;

    @FXML
    private TextField nome;
    
    @FXML
    private TextField cep;

    @FXML
    private TextField senha;

    @FXML
    private TextField complemento;

    @FXML
    private TextField rg;

    @FXML
    private TextField cpf;

    @FXML
    private TextField ctps;

    @FXML
    private ListView<Funcionario> funcionarios;

    @FXML
    private ChoiceBox<String> cargo;

    @FXML
    private TextField rua;
    
    public Funcionario selecionado;
    
    @FXML
    private Label erro;
    
    @Override 
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            listarFuncionario();
            
        } catch (SQLException ex) {
            Logger.getLogger(TelaAtualizacaoFuncionarioControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ArrayVazioException ex) {
            Logger.getLogger(TelaAtualizacaoFuncionarioControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    @FXML
    void Buscar(ActionEvent event) throws SQLException, ArrayVazioException {
        ArrayList<Funcionario> todos = Fachada.getSingleton().MostrarFuncionarioValidacao();
        ArrayList<Funcionario> novos = new ArrayList<>();
        if(pesquisa.getText().isEmpty()==true){
            listarFuncionario();
        }
        for(int i=0;i<todos.size();i++){
            if(todos.get(i).getCpf().startsWith(pesquisa.getText())==true){
                novos.add(todos.get(i));
            }
        }
        ObservableList lista = FXCollections.observableArrayList(novos);
        funcionarios.setItems(lista);
    }
    
    
    @FXML  
    public void listarFuncionario() throws SQLException, ArrayVazioException{
    ObservableList lista = FXCollections.observableArrayList(Fachada.getSingleton().MostrarFuncionarioValidacao());
    funcionarios.setItems(lista);
    }
    @FXML  
    public void inicializarCargos(){
        ObservableList lista = FXCollections.observableArrayList();
        lista.removeAll(lista);
        lista.addAll("Auxiliar Almoxarifado","Funcionario RH","Gerente","Motorista");
        cargo.getItems().addAll(lista);
        
        
    }
    
    
    @FXML  
    public void Alterar(){
        selecionado = funcionarios.getSelectionModel().selectedItemProperty().getValue();
        telefone.setText((selecionado.getTelefone()));
        bairro.setText(selecionado.getEndereco().getBairro());
        salario.setText(Double.toString(selecionado.getSalario()));
        nome.setText(selecionado.getNome());
        cep.setText(selecionado.getEndereco().getCep());
        senha.setText(selecionado.getSenha());
        complemento.setText(selecionado.getEndereco().getComplemento());
        rg.setText(selecionado.getRg());
        cpf.setText(selecionado.getCpf());
        ctps.setText(selecionado.getCtps());
        numero.setText(selecionado.getEndereco().getNumero());
        rua.setText(selecionado.getEndereco().getRua());
        inicializarCargos();
        if(selecionado.getCargo().equals("AUXILIR ALMOXARIFADO")){
            cargo.setValue("Auxiliar Almoxarifado");
        }
        if(selecionado.getCargo().equals("RH")){
            cargo.setValue("Funcionario RH");
        }
        if(selecionado.getCargo().equals("GERENTE")){
            cargo.setValue("Gerente");
        }
        if(selecionado.getCargo().equals("MOTORISTA")){
            cargo.setValue("Motorista");
        }
        
        
    }
    
    @FXML
    void Botao(ActionEvent event) throws SQLException, NomeInvalidoException, CpfInvalidoException, FuncionarioExistenteException, CtpsInvalidoException, RgInvalidoException, TelefoneInvalidoException {
        selecionado = funcionarios.getSelectionModel().selectedItemProperty().getValue();
        Funcionario novo = new Funcionario();
        int i = 0;
        if(selecionado == null){
            erro.setText("Opção Invalida!");
            i++;
        }
        if(nome.getText().matches("^[ A-Za-z]+$")==false || nome.getText().isEmpty() == true) {
            erro.setText("Opção Invalida!");
            i++;
            }
		
        if(cpf.getText().matches("[0-9]*")==false || cpf.getText().isEmpty() == true) {
            erro.setText("Opção Invalida!");
            i++;	
	}
	
	if(ctps.getText().matches("[0-9]*")==false || ctps.getText().isEmpty() == true) {
            erro.setText("Opção Invalida!");
            i++;	
        }
		
	if(rg.getText().matches("[0-9]*")==false || rg.getText().isEmpty() == true) {
            erro.setText("Opção Invalida!");
            i++;	
	}
		
	if(telefone.getText().matches("[0-9]*")==false || telefone.getText().isEmpty() == true) {
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
        novo.setSalario(Double.parseDouble(salario.getText()));
        }catch(NumberFormatException e){
            erro.setText("Opção Invalida!");
            i++;
        }
        if(i==0){
                String opc = cargo.getValue();
            if(opc.equals("Auxiliar Almoxarifado")){
                novo.setCargo("AUXILIR ALMOXARIFADO");
            }
            if(opc.equals("Funcionario RH")){
                novo.setCargo("RH");
            }
            if(opc.equals("Gerente")){
                novo.setCargo("GERENTE");
            }
            if(opc.equals("Motorista")){
                novo.setCargo("MOTORISTA");
            }
            novo.setNome(nome.getText());
            novo.setCpf(cpf.getText());
            novo.setCtps(ctps.getText());
            novo.setRg(rg.getText());
            novo.setSalario(Double.parseDouble(salario.getText()));
            novo.setSenha(senha.getText());
            novo.setTelefone(telefone.getText());
            Endereco novoe = new Endereco(selecionado.getEndereco().getId(),rua.getText(),bairro.getText(),cep.getText(),numero.getText(),complemento.getText());
            novo.setEndereco(novoe);
            Fachada.getSingleton().AtualizarFuncionarioValidacao(selecionado, novo);
            Stage stages = (Stage) botao.getScene().getWindow();
            stages.close();
        }
    }
    

    
}
