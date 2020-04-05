/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.ui.controladores;

import com.jm.negocio.excecoes.ArrayVazioException;
import com.jm.negocio.excecoes.FuncionarioExistenteException;
import com.jm.negocio.fachada.Fachada;
import com.jm.negocio.modelo.Endereco;
import com.jm.negocio.modelo.Funcionario;
import static com.jm.ui.Main.stage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Marcos
 */
public final class TelaFuncionarioControlador implements Initializable {
    
    @FXML
    private TextField pesquisa;

    @FXML
    private Button Buscar;
    
    @FXML
    private Button Remover;

    @FXML
    private Button Atualizar;
    
    @FXML
    private Button adicionar;

    @FXML
    private Button alterar;
    
     @FXML
    private Label erro;
    
    @FXML private ListView<Funcionario> funcionarios;
     
     @FXML private TableView<Funcionario> table = new TableView<>();
  
     @FXML private TableColumn<Funcionario, String> nomeCol;
     @FXML private TableColumn<Funcionario, Integer> idCol;
     @FXML private TableColumn<Funcionario, String> cpfCol;
     @FXML private TableColumn<Funcionario, String> cargoCol;
     @FXML private TableColumn<Funcionario, String> telefoneCol;
     @FXML private TableColumn<Funcionario, String> rgCol;
     @FXML private TableColumn<Funcionario, String> ctpsCol;
     @FXML private TableColumn<Funcionario, Double> salarioCol;
     
     @FXML private TableColumn<Funcionario, Endereco> ruaCol;
     @FXML private TableColumn<Funcionario, Endereco> numCol;
     @FXML private TableColumn<Funcionario, Endereco> bairroCol;
     @FXML private TableColumn<Funcionario, Endereco> cepCol;
     @FXML private TableColumn<Funcionario, Endereco> complementoCol;
     
    private Funcionario selecionado;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //listarFuncionario();
        	createTableViwer();
        	
        } catch (SQLException ex) {
            Logger.getLogger(TelaFuncionarioControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ArrayVazioException ex) {
            Logger.getLogger(TelaFuncionarioControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        	createTableViwer();
        }
        for(int i=0;i<todos.size();i++){
            if(todos.get(i).getCpf().startsWith(pesquisa.getText())==true){
                novos.add(todos.get(i));
            }
        }
        ObservableList lista = FXCollections.observableArrayList(novos);
        table.setItems(lista);
    }
    
    public void Cadastrar(ActionEvent event) throws IOException, SQLException, ArrayVazioException{
        erro.setText("");
        createTableViwer();;
        Stage stagec = new Stage();
        URL url = new File("./src/com/jm/ui/TelaInsercaoFuncionario.fxml").toURL();
        Scene scene = new Scene(FXMLLoader.load(url));
        scene.getStylesheets().add("file:./src/com/jm/ui/temaAzul.css");
        stagec.setScene(scene);
        stagec.getIcons().add(new Image("file:./src/com/jm/ui/icons/logo.png"));
        stagec.setResizable(false);
        stagec.show();
            
            
    }
    
    public void Remover(ActionEvent event) throws SQLException, FuncionarioExistenteException, ArrayVazioException{
        selecionado = table.getSelectionModel().getSelectedItem();
        if(selecionado != null){
            Fachada.getSingleton().RemoverFuncionarioValidacao(selecionado);
            createTableViwer();
        }else{
            erro.setText("Operação Inválida!");
        }
    }
    
    public void Atualizar(ActionEvent event) throws SQLException, MalformedURLException, IOException, ArrayVazioException{
        erro.setText("");
        createTableViwer();
    }
    
    public void Alterar(ActionEvent event) throws SQLException, IOException, ArrayVazioException{
        erro.setText("");
        createTableViwer();
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
    void Voltar(ActionEvent event) throws SQLException, IOException {
        URL url = new File("./src/com/jm/ui/TelaGerente.fxml").toURL();
        Scene scene = new Scene(FXMLLoader.load(url));
        scene.getStylesheets().add("file:./src/com/jm/ui/temaAzul.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    
    
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
    	
    	rgCol.setCellValueFactory(new PropertyValueFactory<>("rg"));
    	
    	ctpsCol.setCellValueFactory(new PropertyValueFactory<>("ctps"));
    	
    	salarioCol.setCellValueFactory(new PropertyValueFactory<>("salario"));    
    	
    	//endcCol.setCellValueFactory(new PropertyValueFactory<>("rua"));
    	
    	table.setItems(getFuncionarios());
    	//table.getColumns().addAll(nomeCol, idCol, cpfCol,  cargoCol, telefoneCol);
    	
    	ruaCol.setCellValueFactory(new PropertyValueFactory<>("endereco"));
    	numCol.setCellValueFactory(new PropertyValueFactory<>("numero"));
    	bairroCol.setCellValueFactory(new PropertyValueFactory<>("bairro"));
    	cepCol.setCellValueFactory(new PropertyValueFactory<>("cep"));
    	complementoCol.setCellValueFactory(new PropertyValueFactory<>("complemento"));
    	
    	//tableEndereco.setItems(getEnderecos());
    	
    	table.setItems(getFuncionarios());
    	
    }
    
    public ObservableList<Funcionario> getFuncionarios() throws SQLException, ArrayVazioException{
    	ObservableList<Funcionario> funcs = FXCollections.observableArrayList(Fachada.getSingleton().MostrarFuncionarioValidacao());
    	
		return funcs;
    	
    }
    
    public ObservableList<Endereco> getEnderecos() throws SQLException, ArrayVazioException{
    	ArrayList<Funcionario> func = Fachada.getSingleton().MostrarFuncionarioValidacao();
    	ArrayList<Endereco> endc = new ArrayList<Endereco>();
    	
    	for (int i = 0; i < func.size(); i++) {
    		endc.add(func.get(i).getEndereco());
    	}
    	
    	ObservableList<Endereco> endcs = FXCollections.observableArrayList(endc);
    	
		return endcs;
    	
    }
    
    
    
   
}
