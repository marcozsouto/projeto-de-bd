package com.jm.negocio;

import com.jm.dados.MetodosCliente;
import java.sql.SQLException;

import com.jm.dados.MetodosFuncionario;
import com.jm.dados.interfaces.IMetodosCliente;
import com.jm.dados.interfaces.IMetodosFuncionario;
import com.jm.negocio.modelo.Cliente;
import com.jm.negocio.modelo.Funcionario;

public class ControleLogin {
	
	private IMetodosFuncionario ifuncionario;
	private IMetodosCliente icliente;
	
	public ControleLogin() {
		ifuncionario = new MetodosFuncionario();
                icliente = new MetodosCliente();
	}

	public Cliente RealizarLoginCliente(String login, String senha) throws SQLException {
		Cliente c = new Cliente();
		if(icliente.verificarExisteCliente(login)==false){
                return null;
                }
                c = icliente.buscaCliente(login);
		
                if(senha.equals(c.getSenha()) || login.equals(c.getCpf_cnpj())) {
			return c;	
		}
		return null;
		
	}
	
	public Funcionario RealizarLoginFuncionario(String login, String senha) throws SQLException {
		Funcionario f = new Funcionario();
                if(ifuncionario.verificarExisteFuncionario(login)==false){
                return null;
                }
		f = ifuncionario.buscaFuncionario(login);
		if(senha.equals(f.getSenha())) {
			return f;	
		}
		return null;
	}
	
}