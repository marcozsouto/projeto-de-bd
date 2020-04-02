/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.ui.controladores;

import com.jm.negocio.fachada.Fachada;
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
import javafx.scene.control.TextField;
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
    
    @FXML
    private ListView<Servico> servicos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
        mensagem.setText("Bem-vindo, "+Fachada.getSingleton().funcionarioLogado().getNome());
        try {
            listarServicos();
        } catch (SQLException ex) {
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
    
    public void listarServicos() throws SQLException{
        ObservableList lista = FXCollections.observableArrayList(Fachada.getSingleton().MostrarServicoIncompleto());
        servicos.setItems(lista);
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
    void Buscar(ActionEvent event) throws SQLException {
        ArrayList<Servico> todos = Fachada.getSingleton().MostrarServicoIncompleto();
        ArrayList<Servico> novos = new ArrayList<>();
        if(pesquisa.getText().isEmpty()==true){
            listarServicos();
        }
        for(int i=0;i<todos.size();i++){
            if(todos.get(i).getCliente().getCpf_cnpj().startsWith(pesquisa.getText())==true){
                novos.add(todos.get(i));
            }
        }
        ObservableList lista = FXCollections.observableArrayList(novos);
        servicos.setItems(lista);
    }
}
