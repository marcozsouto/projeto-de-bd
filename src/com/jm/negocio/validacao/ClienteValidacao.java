package com.jm.negocio.validacao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.jm.negocio.ControleCliente;
import com.jm.negocio.excecoes.ArrayVazioException;
import com.jm.negocio.fachada.Fachada;
import com.jm.negocio.modelo.Cliente;
import com.jm.negocio.modelo.Funcionario;

public class ClienteValidacao {
	private ControleCliente metodos;
	
	public ClienteValidacao(){
		metodos = new ControleCliente();
	}
	
	
	public void CadastroClienteValidacao(Cliente cliente) throws SQLException{
		
		if(metodos.verificarExisteCliente(cliente.getCpf_cnpj())==true){
			
		}
		
		if(cliente.getNome().matches("^[ A-Za-z]+$")==false || cliente.getNome().isEmpty() == true){
		
		}
		
		if(cliente.getCpf_cnpj().matches("[0-9]*")==false || cliente.getCpf_cnpj().isEmpty() == true){
		
		}
		
		if(cliente.getTelefone().matches("[0-9]*")==false || cliente.getTelefone().isEmpty() == true){
		}
		
		metodos.CadastraCliente(cliente);
	}

	public void AtualizarClienteValidacao(Cliente antigo, Cliente novo) throws SQLException{
		
		
		if(novo.getNome().matches("^[ A-Za-z]+$")==false|| novo.getNome().isEmpty() == true){
		
		}
		
		if(novo.getCpf_cnpj().matches("[0-9]*")==false || novo.getCpf_cnpj().isEmpty() == true){
		
		}
	
		if(metodos.verificarExisteCliente(novo.getCpf_cnpj())==true){
		
		}
		
		if(novo.getTelefone().matches("[0-9]*")==false|| novo.getTelefone().isEmpty() == true){
		}
		
		metodos.AtualizarCliente(antigo, novo);
	}
	
	public ArrayList<Cliente> MostrarClienteValidacao() throws SQLException, ArrayVazioException {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		clientes = metodos.ListaCliente();
		
		if(clientes.isEmpty() == true) {
			throw new ArrayVazioException();
		}
		
		return clientes;
	}
	
	public void RemoverClienteValidacao(Cliente cliente) throws SQLException {
		
		if(metodos.verificarExisteCliente(cliente.getCpf_cnpj())==false){
			
		}
		metodos.RemoveCliente(cliente.getCpf_cnpj());
		
	}
	
	
}