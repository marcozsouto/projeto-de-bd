package com.jm.negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import com.jm.dados.MetodosFuncionario;
import com.jm.dados.interfaces.IMetodosFuncionario;
import com.jm.negocio.modelo.Funcionario;

public class ControleFuncionario {
	
	private IMetodosFuncionario funcionario;
	
	public ControleFuncionario() {
		funcionario = new MetodosFuncionario();
	}

	public void CadastraFuncionario(Funcionario f) throws SQLException {
		funcionario.insereFuncionario(f);		
	}
	
	public Funcionario BuscaFuncionario(String cpf) throws SQLException {
		return funcionario.buscaFuncionario(cpf);
	}
	
	public void RemoveFuncionario(String cpf) throws SQLException{
		funcionario.removeFuncionario(cpf);
	}
	
	public ArrayList<Funcionario> ListaFuncionario() throws SQLException {
		ArrayList<Funcionario> ArrayFuncionario = new ArrayList<Funcionario>();
		return ArrayFuncionario = funcionario.listaFuncionario();
		
	}
	
	public void atualizarFuncionario(Funcionario antigo, Funcionario atualizado) throws SQLException{
		funcionario.atualizarFuncionario(antigo, atualizado);
	}
	
	public Funcionario buscaFuncionarioId(int id) throws SQLException {
		return funcionario.buscaFuncionarioId(id);
	}

	public boolean verificarExisteFuncionario(String cpf) throws SQLException {
		return funcionario.verificarExisteFuncionario(cpf);
	}
	
	
}
	
