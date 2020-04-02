package com.jm.negocio.validacao;

import java.sql.SQLException;
import com.jm.negocio.ControleLogin;
import com.jm.negocio.modelo.Cliente;
import com.jm.negocio.modelo.Funcionario;


public class LoginValidacao {
	private ControleLogin controle;
	
	public LoginValidacao() {
		controle = new ControleLogin();
	}

	public Cliente RealizarLoginClienteValidacao(String login, String senha) throws SQLException {
		
		if(login.isEmpty() == true || senha.isEmpty() == true) {
			
		}
		return controle.RealizarLoginCliente(login, senha);
	}
	
	public Funcionario RealizarLoginFuncionarioValidacao(String login, String senha) throws SQLException {
		
		if(login.isEmpty() == true || senha.isEmpty() == true) {
			
		}
		
		return controle.RealizarLoginFuncionario(login, senha);
	}
}
