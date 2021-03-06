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
import javafx.stage.Stage;


public class TelaServicoControlador implements Initializable {

    @FXML
    private Label erro;
    
    @FXML
    private TextField pesquisa;
    
    @FXML
    private Button Buscar;

    @FXML
    private Button adicionar;

    @FXML
    private ListView<Servico> servicos;

    @FXML
    private Button Remover;

    @FXML
    private Button pesquisar;

    @FXML
    private Button Atualizar;

    @FXML
    private Button alterar;

    @FXML
    private Servico selecionado;
    
    
    public void listarServicos() throws SQLException{
    ObservableList lista = FXCollections.observableArrayList(Fachada.getSingleton().MostrarServicoValidacao());
    servicos.setItems(lista);
    }
    @FXML
    void Cadastrar(ActionEvent event) throws SQLException, MalformedURLException, IOException {
        erro.setText("");
        listarServicos();
        Stage stagec = new Stage();
        URL url = new File("./src/com/jm/ui/TelaInsercaoServico.fxml").toURL();
        Scene scene = new Scene(FXMLLoader.load(url));
        scene.getStylesheets().add("file:./src/com/jm/ui/temaAzul.css");
        stagec.setScene(scene);
        stagec.setResizable(false);
        stagec.show();
    }

    @FXML
    void Alterar(ActionEvent event) throws SQLException, MalformedURLException, IOException {
        erro.setText("");
        listarServicos();
        Stage stagec = new Stage();
        URL url = new File("./src/com/jm/ui/TelaAtualizacaoServico.fxml").toURL();
        Scene scene = new Scene(FXMLLoader.load(url));
        scene.getStylesheets().add("file:./src/com/jm/ui/temaAzul.css");
        stagec.setScene(scene);
        stagec.setResizable(false);
        stagec.show();
    }

    @FXML
    void Remover(ActionEvent event) throws SQLException {
        selecionado = servicos.getSelectionModel().getSelectedItem();
        if(selecionado != null){
            Fachada.getSingleton().RemoverServicoValidacao(selecionado);
            listarServicos();
            erro.setText("");
        }else{
            erro.setText("Opção Invalida!");
        }
    }

    @FXML
    void Atualizar(ActionEvent event) throws SQLException {
        erro.setText("");
        listarServicos();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            erro.setText("");
            listarServicos();
        } catch (SQLException ex) {
            Logger.getLogger(TelaServicoControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    @FXML
    void Voltar(ActionEvent event) throws SQLException, MalformedURLException, IOException {
        URL url = new File("./src/com/jm/ui/TelaGerente.fxml").toURL();
        Scene scene = new Scene(FXMLLoader.load(url));
        scene.getStylesheets().add("file:./src/com/jm/ui/temaAzul.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    
    }
    
    @FXML
    void Buscar(ActionEvent event) throws SQLException {
        ArrayList<Servico> todos = Fachada.getSingleton().MostrarServicoValidacao();
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
