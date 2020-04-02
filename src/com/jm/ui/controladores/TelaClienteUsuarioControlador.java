package com.jm.ui.controladores;

import com.jm.negocio.fachada.Fachada;
import com.jm.negocio.modelo.Servico;
import static com.jm.ui.Main.stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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


public class TelaClienteUsuarioControlador implements Initializable {
    @FXML
    private ListView<Servico> servicos;

    @FXML
    private Button Atualizar;

    @FXML
    private Label mensagem;
    

    @FXML
    void Atualizar(ActionEvent event) throws SQLException {
        ListarServicosCliente();
    }
    
    void ListarServicosCliente() throws SQLException{
        int id = Fachada.getSingleton().clienteLogado().getId();
        ObservableList lista = FXCollections.observableArrayList(Fachada.getSingleton().MostrarServicoClientes(id));
        servicos.setItems(lista);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mensagem.setText("Bem-vindo, "+Fachada.getSingleton().clienteLogado().getNome());
        try {
            ListarServicosCliente();
        } catch (SQLException ex) {
            Logger.getLogger(TelaClienteUsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
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
