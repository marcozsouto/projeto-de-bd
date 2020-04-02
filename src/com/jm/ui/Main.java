package com.jm.ui;

import com.jm.negocio.ControleCliente;
import com.jm.negocio.ControleLogin;
import com.jm.negocio.fachada.Fachada;
import com.jm.negocio.modelo.Cliente;
import com.jm.negocio.modelo.Endereco;
import com.jm.negocio.modelo.Funcionario;
import com.jm.negocio.modelo.Servico;
import com.jm.negocio.modelo.Veiculo;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
    public static Stage stage;
    @Override
    public void start(Stage primarystage) throws Exception {
        stage = primarystage;
        Parent root = FXMLLoader.load(getClass().getResource("TelaLogin.fxml"));
        stage.getIcons().add(new Image("file:./src/com/jm/ui/icons/logo.png"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("file:./src/com/jm/ui/temaAzul.css");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        launch(args);
    }
    
}
