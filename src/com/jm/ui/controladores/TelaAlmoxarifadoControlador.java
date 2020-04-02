package com.jm.ui.controladores;

import com.jm.negocio.fachada.Fachada;
import com.jm.negocio.modelo.Almoxarifado;
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


public class TelaAlmoxarifadoControlador implements Initializable {
    
    @FXML
    private Label erro;
    
    @FXML
    private TextField pesquisa;

    @FXML
    private Button adicionar;

    @FXML
    private Button Remover;

    @FXML
    private Button pesquisar;

    @FXML
    private Button Atualizar;

    @FXML
    private ListView<Almoxarifado> items;

    @FXML
    private Button alterar;
    
    private Almoxarifado selecionado;

    @FXML
    void Buscar(ActionEvent event) throws SQLException {
        erro.setText("");
        ArrayList<Almoxarifado> todos = Fachada.getSingleton().MostrarItemValidacao();
        ArrayList<Almoxarifado> novos = new ArrayList<>();
        if(pesquisa.getText().isEmpty()==true){
            listarItems();
        }
        for(int i=0;i<todos.size();i++){
            if(todos.get(i).getNome().startsWith(pesquisa.getText())==true){
                novos.add(todos.get(i));
            }
        }
        ObservableList lista = FXCollections.observableArrayList(novos);
        items.setItems(lista);
    }
    
    public void listarItems() throws SQLException{
    ObservableList lista = FXCollections.observableArrayList(Fachada.getSingleton().MostrarItemValidacao());
    items.setItems(lista);
    
    }

    @FXML
    void Remover(ActionEvent event) throws SQLException {
        selecionado = items.getSelectionModel().getSelectedItem();
        if(selecionado != null){
            Fachada.getSingleton().RemoverItemValidacao(selecionado.getNome());
            listarItems();
            erro.setText("");
        }else{
            erro.setText("Opção Invalida!");
        }
        
    }

    @FXML
    void Atualizar(ActionEvent event) throws SQLException {
        listarItems();
        erro.setText("");
    }

    @FXML
    void Cadastrar(ActionEvent event) throws MalformedURLException, IOException, SQLException {
        erro.setText("");
        listarItems();
        Stage stagec = new Stage();
        URL url = new File("./src/com/jm/ui/TelaInsercaoAlmoxarifado.fxml").toURL();
        Scene scene = new Scene(FXMLLoader.load(url));
        scene.getStylesheets().add("file:./src/com/jm/ui/temaAzul.css");
        stagec.getIcons().add(new Image("file:./src/com/jm/ui/icons/logo.png"));
        stagec.setScene(scene);
        stagec.setResizable(false);
        stagec.show();
    }

    @FXML
    void Alterar(ActionEvent event) throws MalformedURLException, IOException, SQLException {
        erro.setText("");
        listarItems();
        Stage stagec = new Stage();
        URL url = new File("./src/com/jm/ui/TelaAtualizacaoAlmoxarifado.fxml").toURL();
        Scene scene = new Scene(FXMLLoader.load(url));
        scene.getStylesheets().add("file:./src/com/jm/ui/temaAzul.css");
        stagec.getIcons().add(new Image("file:./src/com/jm/ui/icons/logo.png"));
        stagec.setScene(scene);
        stagec.setResizable(false);
        stagec.show();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            listarItems();
        } catch (SQLException ex) {
            Logger.getLogger(TelaAlmoxarifadoControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
}
