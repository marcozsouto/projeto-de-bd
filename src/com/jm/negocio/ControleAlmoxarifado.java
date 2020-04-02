package com.jm.negocio;

import java.sql.SQLException;
import java.util.ArrayList;

import com.jm.dados.MetodosAlmoxarifado;
import com.jm.dados.interfaces.IMetodosAlmoxarifado;
import com.jm.negocio.modelo.Almoxarifado;;

public class ControleAlmoxarifado {
		private IMetodosAlmoxarifado item;
		
		
		
		public ControleAlmoxarifado() {
			item = new MetodosAlmoxarifado();
		}

		public void CadastraItem(Almoxarifado i) throws SQLException {
			item.insereItem(i);		
		}
		
		public Almoxarifado BuscaItem(String nome) throws SQLException {
			return item.buscaItem(nome);
		}
		
		public void RemoveItem(String nome) throws SQLException{
			item.removeItem(nome);
		}
		
		public ArrayList<Almoxarifado> ListaItem() throws SQLException {
			ArrayList<Almoxarifado> ArrayAlmoxarifado = new ArrayList<Almoxarifado>();
			return ArrayAlmoxarifado = item.listaItem();
		}
		
		public void AtualizarAlmoxarifado(Almoxarifado antigo, Almoxarifado novo) throws SQLException {
			item.atualizaItem(antigo, novo);
		}
		
		public boolean verificarExisteItem(String nome) throws SQLException{
			return item.verificarExisteItem(nome);
		}
	
}