package com.jm.negocio;

import java.sql.SQLException;
import java.util.ArrayList;

import com.jm.dados.MetodosCliente;
import com.jm.dados.interfaces.IMetodosCliente;
import com.jm.negocio.modelo.Cliente;


public class ControleCliente {
	private IMetodosCliente cliente;
	
	
	
	public ControleCliente() {
		cliente = new MetodosCliente();
	}

	public void CadastraCliente(Cliente c) throws SQLException {
		cliente.insereCliente(c);		
	}
	
	public Cliente BuscaCliente(String cpf_cnpj) throws SQLException {
		return cliente.buscaCliente(cpf_cnpj);
	}
	
	public void RemoveCliente(String cpf_cnpj) throws SQLException{
		cliente.removeCliente(cpf_cnpj);
	}
	
	public ArrayList<Cliente> ListaCliente() throws SQLException {
		ArrayList<Cliente> ArrayCliente = new ArrayList<Cliente>();
		return ArrayCliente = cliente.listaCliente();
		
	}
	public void AtualizarCliente(Cliente antigo, Cliente atualizado) throws SQLException{
		cliente.atualizaCliente(antigo, atualizado);
	}
	
	public ArrayList<Cliente> ListaCliente(int id) throws SQLException {
		ArrayList<Cliente> ArrayCliente = new ArrayList<Cliente>();
		return ArrayCliente = cliente.listaCliente(id);
		
	}
	
	public Cliente buscaClienteId(int id) throws SQLException {
		return cliente.buscaCliente(id);
	}
	
	public boolean verificarExisteCliente(String cpf_cnpj) throws SQLException {
		return cliente.verificarExisteCliente(cpf_cnpj);
	}
}