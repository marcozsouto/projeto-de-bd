package com.jm.dados.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import com.jm.negocio.modelo.Cliente;

public interface IMetodosCliente {
	public void insereCliente(Cliente cliente) throws SQLException;
	public Cliente buscaCliente(String cpf_cnpj) throws SQLException;
	public Cliente buscaClienteId(int id) throws SQLException;
	public void removeCliente(String cpf_cnpj) throws SQLException;
	public ArrayList<Cliente> listaCliente() throws SQLException;
	public void atualizaCliente(Cliente antigo, Cliente atualizado) throws SQLException;
	public ArrayList<Cliente> listaCliente(int id) throws SQLException;
	public Cliente buscaCliente(int id) throws SQLException;
	public boolean verificarExisteCliente(String cpf_cnpj) throws SQLException;
	

}
