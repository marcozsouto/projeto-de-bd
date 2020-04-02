package com.jm.dados.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import com.jm.negocio.modelo.Veiculo;

public interface IMetodosVeiculo {
	public void insereVeiculo(Veiculo veiculo) throws SQLException;
	public Veiculo buscaVeiculo(String placa) throws SQLException;
	public Veiculo buscaVeiculoId(int id) throws SQLException;
	public void removeVeiculo(String placa) throws SQLException;
	public ArrayList<Veiculo> listaVeiculo() throws SQLException;
	public void atualizarVeiculo(Veiculo antigo, Veiculo atualizado) throws SQLException;
	public boolean VerificarExisteVeiculo(String placa) throws SQLException;
	
}
