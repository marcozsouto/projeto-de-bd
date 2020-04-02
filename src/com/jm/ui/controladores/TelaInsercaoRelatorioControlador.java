package com.jm.ui.controladores;

import com.jm.negocio.fachada.Fachada;
import com.jm.negocio.modelo.Servico;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class TelaInsercaoRelatorioControlador implements Initializable {
    
    @FXML
    private Label erro;
      
    @FXML
    private TextField pesquisa;

    @FXML
    private Button Buscar;
    
    @FXML
    private ListView<Servico> servicos;

    @FXML
    private Button inserir;

    @FXML
    private Button Alterar;

    @FXML
    private TextArea relatorio;

    private Servico selecionado;
    
    public void listarServicos() throws SQLException{
        int id = Fachada.getSingleton().funcionarioLogado().getId();
        ObservableList lista = FXCollections.observableArrayList(Fachada.getSingleton().MostrarServicosMotorista(id));
        servicos.setItems(lista);
    }
    
    @FXML
    void Alterar(ActionEvent event) {
        selecionado = servicos.getSelectionModel().selectedItemProperty().getValue();
        
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

    @FXML
    void Inserir(ActionEvent event) throws SQLException {
        int i = 0;
        if(selecionado == null){
            erro.setText("Opção Invalida!");
            i++;
        }
        if(relatorio.getText() == null){
            erro.setText("Opção Invalida");
            i++;
        }
        if(i==0){
            Fachada.getSingleton().atualizaRelatorio(selecionado,relatorio.getText());
            Stage stages = (Stage) inserir.getScene().getWindow();
            stages.close();
        }
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            listarServicos();
        } catch (SQLException ex) {
            Logger.getLogger(TelaInsercaoRelatorioControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
