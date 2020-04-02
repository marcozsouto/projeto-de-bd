package com.jm.dados.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import com.jm.negocio.modelo.Servico;

public interface IMetodosServico {
	public void insereServico(Servico servico) throws SQLException;
	public Servico buscaServico(int id) throws SQLException;
	public ArrayList<Servico> listaServico() throws SQLException;
	public ArrayList<Servico> listaServico(int id) throws SQLException;
	public void removeServico(int id) throws SQLException;
	public void atualizaServico(Servico antigo, Servico atualizado) throws SQLException;
	public ArrayList<Servico> listaServicoClientes(int id) throws SQLException;
	public ArrayList<Servico> listarServicosMotorista(int id) throws SQLException;
	public void inserirRelatorio(Servico servico, String relatorio) throws SQLException;
	public ArrayList<Servico> listaServicoIncompletos() throws SQLException;
	
	
}
