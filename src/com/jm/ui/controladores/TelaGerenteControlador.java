/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.ui.controladores;

import com.jm.negocio.excecoes.ArrayVazioException;
import com.jm.negocio.fachada.Fachada;
import com.jm.negocio.modelo.Funcionario;
import com.jm.negocio.modelo.Servico;
import static com.jm.ui.Main.stage;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Marcos
 */
public class TelaGerenteControlador implements Initializable {
    
    @FXML
    private Button Voltar;
    
    @FXML
    private Label mensagem;

    @FXML
    private Button Almoxarifado;

    @FXML
    private Button Veiculos;

    @FXML
    private Button Funcionarios;

    @FXML
    private Button Clientes;

    @FXML
    private Button Servicos;

    @FXML
    private TextField pesquisa;
    
    //@FXML private ListView<Servico> servicos; 
    
    @FXML private TableView<Funcionario> table = new TableView<>();
    
    @FXML private TableColumn<Funcionario, String> nomeCol;
    
    @FXML private TableColumn<Funcionario, Integer> idCol;
    
    @FXML private TableColumn<Funcionario, String> cpfCol;
    
    @FXML private TableColumn<Funcionario, String> cargoCol;
    
    @FXML private TableColumn<Funcionario, String> telefoneCol;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
        mensagem.setText("Bem-vindo, "+Fachada.getSingleton().funcionarioLogado().getNome());
        try {
            //listarFuncionarios();
            createTableViwer();
            
        } catch (SQLException | ArrayVazioException ex) {
            Logger.getLogger(TelaGerenteControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public void clickFuncionario() throws MalformedURLException, IOException{
        
        URL url = new File("./src/com/jm/ui/TelaFuncionario.fxml").toURL();
        Scene scene = new Scene(FXMLLoader.load(url));
        scene.getStylesheets().add("file:./src/com/jm/ui/temaAzul.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    
    }
    
    public void clickVeiculo() throws MalformedURLException, IOException{
        URL url = new File("./src/com/jm/ui/TelaVeiculo.fxml").toURL();
        Scene scene = new Scene(FXMLLoader.load(url));
        scene.getStylesheets().add("file:./src/com/jm/ui/temaAzul.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    public void clickCliente() throws MalformedURLException, IOException{
        URL url = new File("./src/com/jm/ui/TelaCliente.fxml").toURL();
        Scene scene = new Scene(FXMLLoader.load(url));
        scene.getStylesheets().add("file:./src/com/jm/ui/temaAzul.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    public void clickAlmoxarifado() throws MalformedURLException, IOException{
        URL url = new File("./src/com/jm/ui/TelaAlmoxarifado.fxml").toURL();
        Scene scene = new Scene(FXMLLoader.load(url));
        scene.getStylesheets().add("file:./src/com/jm/ui/temaAzul.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    public void clickServico() throws MalformedURLException, IOException{
        URL url = new File("./src/com/jm/ui/TelaServico.fxml").toURL();
        Scene scene = new Scene(FXMLLoader.load(url));
        scene.getStylesheets().add("file:./src/com/jm/ui/temaAzul.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    public void listarFuncionarios() throws SQLException, ArrayVazioException{
        ObservableList lista = FXCollections.observableArrayList(Fachada.getSingleton().MostrarFuncionarioValidacao());
        //servicos.setItems(lista);
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

    @FXML
    void Buscar(ActionEvent event) throws SQLException, ArrayVazioException {
        ArrayList<Funcionario> todos = Fachada.getSingleton().MostrarFuncionarioValidacao();
        ArrayList<Funcionario> novos = new ArrayList<>();
        if(pesquisa.getText().isEmpty()==true){
            listarFuncionarios();
            
        }
        for(int i=0;i<todos.size();i++){
            if(todos.get(i).getCpf().startsWith(pesquisa.getText())==true){
                novos.add(todos.get(i));
            }
        }
        ObservableList<Funcionario> lista = FXCollections.observableArrayList(novos);
        //servicos.setItems(lista);
        table.setItems(lista);
        
    }
    
    public ObservableList<Funcionario> getFuncionarios() throws SQLException, ArrayVazioException{
    	ObservableList<Funcionario> funcionarios = FXCollections.observableArrayList(Fachada.getSingleton().MostrarFuncionarioValidacao());;
  
		return funcionarios;
    	
    }
    
    public void createTableViwer() throws SQLException, ArrayVazioException {
    	
    	//nomeCol = new TableColumn<>("NOME");
    	nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
    	
    	// = new TableColumn<>("ID");
    	idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    	
    	//cpfCol = new TableColumn<>("CPF");
    	cpfCol.setCellValueFactory(new PropertyValueFactory<>("cpf"));
    	
    	//cargoCol = new TableColumn<>("CARGO");
    	cargoCol.setCellValueFactory(new PropertyValueFactory<>("cargo"));
    	
    	//telefoneCol = new TableColumn<>("TELEFONE");
    	telefoneCol.setCellValueFactory(new PropertyValueFactory<>("telefone"));
    	
    	table.setItems(getFuncionarios());
    	//table.getColumns().addAll(nomeCol, idCol, cpfCol,  cargoCol, telefoneCol);
    	
    }
}
