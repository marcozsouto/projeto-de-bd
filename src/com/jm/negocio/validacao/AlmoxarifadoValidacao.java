package com.jm.negocio.validacao;
import java.sql.SQLException;
import java.util.ArrayList;
import com.jm.negocio.ControleAlmoxarifado;
import com.jm.negocio.fachada.Fachada;
import com.jm.negocio.modelo.Almoxarifado;
import com.jm.negocio.modelo.Veiculo;

public class AlmoxarifadoValidacao {

	private ControleAlmoxarifado metodos;
	
	
	public AlmoxarifadoValidacao() {
		metodos = new ControleAlmoxarifado();
	}

	public void CadastroItemValidacao(Almoxarifado item) throws SQLException {
		
		if(metodos.verificarExisteItem(item.getNome())==true) {
			
		}
		
		if(item.getNome().isEmpty() == true) {
			
		}
		
		if(item.getQuantidade() < 1 ) {
			
		}
		
		if(item.getDescricao().isEmpty() == true ) {
			
		}
		
		if(item.getPreco() < 1) {
			
		}
		
		metodos.CadastraItem(item);
	}
	
	public void AtualizarItemValidacao(Almoxarifado antigo, Almoxarifado novo) throws SQLException {
		
		if(metodos.verificarExisteItem(novo.getNome())==true) {
			
		}
		
		if(novo.getNome().isEmpty() == true) {
			
		}
		
		if(novo.getQuantidade() < 1 ) {
			
		}
		
		if(novo.getDescricao().isEmpty() == true ) {
			
		}
		
		if(novo.getPreco() < 1) {
			
		}
		
		metodos.AtualizarAlmoxarifado(antigo, novo);
	}
	
	public ArrayList<Almoxarifado> MostrarItemValidacao() throws SQLException{
		ArrayList<Almoxarifado> items = new ArrayList<Almoxarifado>();
		items = metodos.ListaItem();
		
		if(items.isEmpty() == true) {
			
		}
		return items;
	}
	
        public void RemoverItemValidacao(String nome) throws SQLException{
        
            metodos.RemoveItem(nome);
        }
	
}
