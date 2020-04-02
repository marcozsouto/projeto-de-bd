/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.ui.controladores;

import com.jm.negocio.excecoes.ArrayVazioException;
import com.jm.negocio.excecoes.FuncionarioExistenteException;
import com.jm.negocio.fachada.Fachada;
import com.jm.negocio.modelo.Funcionario;
import static com.jm.ui.Main.stage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TelaFuncionarioRHControlador implements Initializable {
    
    @FXML
    private TextField pesquisa;

    @FXML
    private Button Buscar;
    
    @FXML
    private Button adicionar;

    @FXML
    private Button Remover;
    
    @FXML
    private Button Atualizar;

    @FXML
    private Label mensagem;

    @FXML
    private ListView<Funcionario> funcionarios;

    @FXML
    private Button alterar;
    
    private Funcionario selecionado;
    
    @FXML
    private Label erro;

    @FXML
    void Cadastrar(ActionEvent event) throws SQLException, MalformedURLException, IOException, ArrayVazioException {
        erro.setText("");
        listarFuncionario();
        Stage stagec = new Stage();
        URL url = new File("./src/com/jm/ui/TelaInsercaoFuncionario.fxml").toURL();
        Scene scene = new Scene(FXMLLoader.load(url));
        scene.getStylesheets().add("file:./src/com/jm/ui/temaAzul.css");
        stagec.getIcons().add(new Image("file:./src/com/jm/ui/icons/logo.png"));
        stagec.setScene(scene);
        stagec.setResizable(false);
        stagec.show();
            
    }
    
    public void listarFuncionario() throws SQLException, ArrayVazioException{
    ObservableList lista = FXCollections.observableArrayList(Fachada.getSingleton().MostrarFuncionarioValidacao());
    funcionarios.setItems(lista);
    }
    
    @FXML
    void Buscar(ActionEvent event) throws SQLException, ArrayVazioException {
        erro.setText("");
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
    void Alterar(ActionEvent event) throws SQLException, MalformedURLException, IOException, ArrayVazioException {
        listarFuncionario();
        Stage stagec = new Stage();
        URL url = new File("./src/com/jm/ui/TelaAtualizacaoFuncionario.fxml").toURL();
        Scene scene = new Scene(FXMLLoader.load(url));
        scene.getStylesheets().add("file:./src/com/jm/ui/temaAzul.css");
        stagec.getIcons().add(new Image("file:./src/com/jm/ui/icons/logo.png"));
        stagec.setScene(scene);
        stagec.setResizable(false);
        stagec.show();
    }

    @FXML
    void Remover(ActionEvent event) throws SQLException, FuncionarioExistenteException, ArrayVazioException {
        selecionado = funcionarios.getSelectionModel().getSelectedItem();
        if(selecionado != null){
            Fachada.getSingleton().RemoverFuncionarioValidacao(selecionado);
            listarFuncionario();
        }else{
            erro.setText("Opção Invalida!");
        }
        
    }

    @FXML
    void Atualizar(ActionEvent event) throws SQLException, ArrayVazioException {
        listarFuncionario();
        erro.setText("");
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        mensagem.setText("Bem-vindo, "+Fachada.getSingleton().funcionarioLogado().getNome());
        try {
            listarFuncionario();
        } catch (SQLException ex) {
            Logger.getLogger(TelaFuncionarioRHControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ArrayVazioException ex) {
            Logger.getLogger(TelaFuncionarioRHControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    @FXML
    void Voltar(ActionEvent event) throws SQLException, IOException {
        URL url = new File("./src/com/jm/ui/TelaLogin.fxml").toURL();
        Scene scene = new Scene(FXMLLoader.load(url));
        scene.getStylesheets().add("file:./src/com/jm/ui/temaAzul.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    
    
    }
    
    
}
