package com.jm.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jm.dados.MetodosServico;
import com.jm.dados.interfaces.IMetodosServico;
import com.jm.negocio.modelo.Servico;

public class ControleServico {
	private IMetodosServico servico;
	
	
	
	public ControleServico() {
		servico = new MetodosServico();
	}

	public void CadastraServico(Servico s) throws SQLException {
		servico.insereServico(s);
	}
	
	public ArrayList<Servico> listaServico() throws SQLException{
		ArrayList<Servico> ArrayServico = new ArrayList<Servico>();
		return ArrayServico = servico.listaServico();
	}
	
	public ArrayList<Servico> listaServico(int id) throws SQLException{
		ArrayList<Servico> ArrayServico = new ArrayList<Servico>();
		return ArrayServico = servico.listaServico(id);
	}
	
	public void RemoverServico(int id) throws SQLException{
		servico.removeServico(id);
	}
	
	public Servico BuscarServico(int id) throws SQLException {
		return servico.buscaServico(id);
	}
	
	public ArrayList<Servico> listaServicoClientes(int id) throws SQLException{
		return servico.listaServicoClientes(id);
	}
	
	public void atualizaServico(Servico antigo, Servico atualizado) throws SQLException{
		servico.atualizaServico(antigo, atualizado);
	}
	
	public ArrayList<Servico> listarServicosMotorista(int id) throws SQLException{
		return servico.listarServicosMotorista(id);
	}
	
	public void inserirRelatorio(Servico s, String r) throws SQLException {
			servico.inserirRelatorio(s,r);
	}
        
        public ArrayList<Servico> listarServicosIncompletos() throws SQLException{
		return servico.listaServicoIncompletos();
	}
        
        public void atualizaRelatorio(Servico s,String relatorio) throws SQLException {
            servico.inserirRelatorio(s, relatorio);
        }
	
}