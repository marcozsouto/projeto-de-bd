package com.jm.ui.controladores;

import com.jm.negocio.fachada.Fachada;
import com.jm.negocio.modelo.Servico;
import com.jm.negocio.modelo.Veiculo;
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

public class TelaVeiculoControlador implements Initializable {

    @FXML
    private TextField pesquisa;

    @FXML
    private Button Buscar;

    @FXML
    private Label erro;
    
    @FXML
    private Button adicionar;

    @FXML
    private Button Remover;

    @FXML
    private ListView<Veiculo> veiculos;

    @FXML
    private Button pesquisar;

    @FXML
    private Button Atualizar;

    @FXML
    private Button alterar;
    
    private Veiculo selecionado;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            erro.setText("");
            listarVeiculos();
        } catch (SQLException ex) {
            Logger.getLogger(TelaVeiculoControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void listarVeiculos() throws SQLException{
    ObservableList lista = FXCollections.observableArrayList(Fachada.getSingleton().MostrarVeiculoValidacao());
    veiculos.setItems(lista);
    }
    
    @FXML
    void Remover(ActionEvent event) throws SQLException {
        selecionado = veiculos.getSelectionModel().getSelectedItem();
        if(selecionado != null){
            Fachada.getSingleton().RemoverVeiculoValidacao(selecionado);
            listarVeiculos();
            erro.setText("");
        }else{
            erro.setText("Opção Invalida!");
        }
    }

    @FXML
    void Atualizar(ActionEvent event) throws SQLException {
        listarVeiculos();
        erro.setText("");
    }

    @FXML
    void Cadastrar(ActionEvent event) throws MalformedURLException, IOException, SQLException {
        erro.setText("");
        listarVeiculos();
        Stage stagev = new Stage();
        URL url = new File("./src/com/jm/ui/TelaInsercaoVeiculo.fxml").toURL();
            Scene scene = new Scene(FXMLLoader.load(url));
            scene.getStylesheets().add("file:./src/com/jm/ui/temaAzul.css");
            stagev.getIcons().add(new Image("file:./src/com/jm/ui/icons/logo.png"));
            stagev.setScene(scene);
            stagev.setResizable(false);
            stagev.show();
    }

    @FXML
    void Alterar(ActionEvent event) throws SQLException, MalformedURLException, IOException {
        erro.setText("");
        listarVeiculos();
        Stage stagev = new Stage();
        URL url = new File("./src/com/jm/ui/TelaAtualizacaoVeiculo.fxml").toURL();
        Scene scene = new Scene(FXMLLoader.load(url));
        scene.getStylesheets().add("file:./src/com/jm/ui/temaAzul.css");
        stagev.getIcons().add(new Image("file:./src/com/jm/ui/icons/logo.png"));
        stagev.setScene(scene);
        stagev.setResizable(false);
        stagev.show();
    }
    
    @FXML
    void Voltar(ActionEvent event) throws MalformedURLException, IOException{
        erro.setText("");
        URL url = new File("./src/com/jm/ui/TelaGerente.fxml").toURL();
        Scene scene = new Scene(FXMLLoader.load(url));
        scene.getStylesheets().add("file:./src/com/jm/ui/temaAzul.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    @FXML
    void Buscar(ActionEvent event) throws SQLException {
        erro.setText("");
        ArrayList<Veiculo> todos = Fachada.getSingleton().MostrarVeiculoValidacao();
        ArrayList<Veiculo> novos = new ArrayList<>();
        if(pesquisa.getText().isEmpty()==true){
            listarVeiculos();
        }
        for(int i=0;i<todos.size();i++){
            if(todos.get(i).getPlaca().startsWith(pesquisa.getText())==true){
                novos.add(todos.get(i));
            }
        }
        ObservableList lista = FXCollections.observableArrayList(novos);
        veiculos.setItems(lista);
    }
    
}
