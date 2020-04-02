package com.jm.ui.controladores;

import com.jm.negocio.fachada.Fachada;
import com.jm.negocio.modelo.Cliente;
import com.jm.negocio.modelo.Funcionario;
import static com.jm.ui.Main.stage;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Observable;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class TelaLoginControlador implements Initializable {
    public static Funcionario funcionariologado = new Funcionario();
    public static Cliente clientelogado = new Cliente();
    
    
    @FXML
    private ImageView icon1;
    @FXML
    private ImageView icon2;
    @FXML
    private Label invalido;
    @FXML
    private Button Entrar;
    @FXML
    private TextField login;
    @FXML
    private PasswordField senha;
    @FXML
    private ChoiceBox<String> opcoes;
    
    ObservableList lista = FXCollections.observableArrayList();
   
    @FXML
    void BotaoLogin(ActionEvent event) throws SQLException, IOException {
        String opcao = opcoes.getValue();
        
        if(opcao.equals("Funcionario")){
            if(Fachada.getSingleton().RealizarLoginFuncionarioValidacao(login.getText(),senha.getText())!=null){
                loginFuncionario(Fachada.getSingleton().RealizarLoginFuncionarioValidacao(login.getText(),senha.getText()));
            }else{
            invalido.setText("Nome de usuário ou senha inválida!");
            }
        }
        if(opcao.equals("Cliente")){
            if(Fachada.getSingleton().RealizarLoginClienteValidacao(login.getText(),senha.getText())!=null){
                loginCliente(Fachada.getSingleton().RealizarLoginClienteValidacao(login.getText(),senha.getText()));
            }else{
            invalido.setText("Nome de usuário ou senha inválida!");
            }
            
        }
        
     
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarOpcoes(); 
    }
    
    public void inicializarOpcoes(){
        lista.removeAll(lista);
        lista.addAll("Funcionario","Cliente");
        opcoes.getItems().addAll(lista);
        opcoes.setValue("Funcionario");
    }
    
    public void loginFuncionario(Funcionario funcionario) throws IOException{
        Fachada.getSingleton().funcionarioLogin(funcionario);
        if(funcionario.getCargo().equals("GERENTE")){
            URL url = new File("./src/com/jm/ui/TelaGerente.fxml").toURL();
            Scene scene = new Scene(FXMLLoader.load(url));
            scene.getStylesheets().add("file:./src/com/jm/ui/temaAzul.css");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
        if(funcionario.getCargo().equals("MOTORISTA")){
            URL url = new File("./src/com/jm/ui/TelaMotorista.fxml").toURL();
            Scene scene = new Scene(FXMLLoader.load(url));
            scene.getStylesheets().add("file:./src/com/jm/ui/temaAzul.css");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
        if(funcionario.getCargo().equals("RH")){
            URL url = new File("./src/com/jm/ui/TelaFuncionarioRH.fxml").toURL();
            Scene scene = new Scene(FXMLLoader.load(url));
            scene.getStylesheets().add("file:./src/com/jm/ui/temaAzul.css");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
        if(funcionario.getCargo().equals("AUXILIR ALMOXARIFADO")){
            URL url = new File("./src/com/jm/ui/TelaAuxiliarAlmoxarifado.fxml").toURL();
            Scene scene = new Scene(FXMLLoader.load(url));
            scene.getStylesheets().add("file:./src/com/jm/ui/temaAzul.css");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
        
        
        
    }
    
    public void loginCliente(Cliente cliente) throws MalformedURLException, IOException{
        Fachada.getSingleton().clienteLogin(cliente);
        URL url = new File("./src/com/jm/ui/TelaClienteUsuario.fxml").toURL();
        Scene scene = new Scene(FXMLLoader.load(url));
        scene.getStylesheets().add("file:./src/com/jm/ui/temaAzul.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    
    }
    
}
