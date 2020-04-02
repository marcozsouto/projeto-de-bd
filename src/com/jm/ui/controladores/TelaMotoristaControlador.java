package com.jm.ui.controladores;

import com.jm.negocio.fachada.Fachada;
import com.jm.negocio.modelo.Servico;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TelaMotoristaControlador implements Initializable {

    @FXML
    private TextField pesquisa;

    @FXML
    private Button Buscar;
    
    @FXML
    private ListView<Servico> servicos;

    @FXML
    private Button pesquisar;

    @FXML
    private Button Atualizar;

    @FXML
    private Label mensagem;

    @FXML
    private Button relatorio;

    @FXML
    void Relatorio(ActionEvent event) throws SQLException, MalformedURLException, IOException {
        listarServicos();
        Stage stagec = new Stage();
        URL url = new File("./src/com/jm/ui/TelaInsercaoRelatorio.fxml").toURL();
        Scene scene = new Scene(FXMLLoader.load(url));
        scene.getStylesheets().add("file:./src/com/jm/ui/temaAzul.css");
        stagec.getIcons().add(new Image("file:./src/com/jm/ui/icons/logo.png"));
        stagec.setScene(scene);
        stagec.setResizable(false);
        stagec.show();
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
    void Atualizar(ActionEvent event) throws SQLException {
        listarServicos();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mensagem.setText("Bem-vindo, "+Fachada.getSingleton().funcionarioLogado().getNome());
        try {
            listarServicos();
        } catch (SQLException ex) {
            Logger.getLogger(TelaMotoristaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void listarServicos() throws SQLException{
        int id = Fachada.getSingleton().funcionarioLogado().getId();
        ObservableList lista = FXCollections.observableArrayList(Fachada.getSingleton().MostrarServicosMotorista(id));
        servicos.setItems(lista);
    }
    
    @FXML
    void Buscar(ActionEvent event) throws SQLException {
        int id = Fachada.getSingleton().funcionarioLogado().getId();
        ArrayList<Servico> todos = Fachada.getSingleton().MostrarServicosMotorista(id);
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
