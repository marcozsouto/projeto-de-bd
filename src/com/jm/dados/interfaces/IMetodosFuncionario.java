package com.jm.dados.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import com.jm.negocio.modelo.Funcionario;

public interface IMetodosFuncionario{
	public void insereFuncionario(Funcionario funcionario) throws SQLException;
	public Funcionario buscaFuncionario(String cpf) throws SQLException;
	public Funcionario buscaFuncionarioId(int id) throws SQLException;
	public void removeFuncionario(String cpf) throws SQLException;
	public ArrayList<Funcionario> listaFuncionario() throws SQLException;
	public void atualizarFuncionario(Funcionario antigo, Funcionario atualizado) throws SQLException;
	public boolean verificarExisteFuncionario(String cpf) throws SQLException;
	
}