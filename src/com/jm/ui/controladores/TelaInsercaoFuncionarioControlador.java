package com.jm.ui.controladores;

import com.jm.negocio.excecoes.CpfInvalidoException;
import com.jm.negocio.excecoes.CtpsInvalidoException;
import com.jm.negocio.excecoes.FuncionarioExistenteException;
import com.jm.negocio.excecoes.NomeInvalidoException;
import com.jm.negocio.excecoes.RgInvalidoException;
import com.jm.negocio.excecoes.TelefoneInvalidoException;
import com.jm.negocio.fachada.Fachada;
import com.jm.negocio.modelo.Endereco;
import com.jm.negocio.modelo.Funcionario;
import static com.jm.ui.Main.stage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Marcos
 */
public class TelaInsercaoFuncionarioControlador implements Initializable {

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
    private Label label;
   
    @FXML
    private Button botao;

    @FXML
    private TextField cep;

    @FXML
    private TextField senha;

    @FXML
    private TextField rg;

    @FXML
    private TextField cpf;

    @FXML
    private TextField ctps;

    @FXML
    private ChoiceBox<String> cargo;
    
    @FXML
    private TextField complemento;

    @FXML
    private TextField rua;
    
    @FXML
    private Label erro;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    label.setText("Cadastrar");
    inicializarCargos();
    }
    
    public void inicializarCargos(){
        ObservableList lista = FXCollections.observableArrayList();
        lista.removeAll(lista);
        lista.addAll("Auxiliar Almoxarifado","Funcionario RH","Gerente","Motorista");
        cargo.getItems().addAll(lista);
        cargo.setValue("Motorista");
    }
    
    public void botao() throws SQLException, MalformedURLException, IOException, NomeInvalidoException, CpfInvalidoException, CtpsInvalidoException, TelefoneInvalidoException, RgInvalidoException, FuncionarioExistenteException{
        Funcionario novo = new Funcionario();
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
        int i = 0;
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
        
        
        novo.setNome(nome.getText());
        novo.setCpf(cpf.getText());
        novo.setCtps(ctps.getText());
        novo.setRg(rg.getText());
        novo.setSenha(senha.getText());
        novo.setTelefone(telefone.getText());
        Endereco novoe = new Endereco(0,rua.getText(),bairro.getText(),cep.getText(),numero.getText(),complemento.getText());
        novo.setEndereco(novoe);
        if(i==0){
            Fachada.getSingleton().CadastroFuncionarioValidacao(novo);
            Stage stages = (Stage) botao.getScene().getWindow();
            stages.close();
        }
    
    }
}