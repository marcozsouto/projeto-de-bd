package com.jm.negocio;

import java.sql.SQLException;
import java.util.ArrayList;

import com.jm.dados.MetodosVeiculo;
import com.jm.dados.interfaces.IMetodosVeiculo;
import com.jm.negocio.modelo.Veiculo;

public class ControleVeiculo {
	private IMetodosVeiculo veiculo;
	
	
	
	public ControleVeiculo() {
		veiculo = new MetodosVeiculo();
	}

	public void CadastraVeiculo(Veiculo v) throws SQLException {
		veiculo.insereVeiculo(v);		
	}
	
	public Veiculo BuscaVeiculo(String placa) throws SQLException {
		return veiculo.buscaVeiculo(placa);
	}
	
	public void RemoveVeiculo(String placa) throws SQLException{
		veiculo.removeVeiculo(placa);
	}

	public ArrayList<Veiculo> ListaVeiculo() throws SQLException {
		ArrayList<Veiculo> ArrayVeiculo = new ArrayList<Veiculo>();
		return ArrayVeiculo = veiculo.listaVeiculo();
		
	}
	
	public void atualizarVeiculo(Veiculo antigo, Veiculo atualizado) throws SQLException{
		veiculo.atualizarVeiculo(antigo, atualizado);
	}
	
	public Veiculo buscaVeiculoId(int id) throws SQLException {
		return veiculo.buscaVeiculoId(id);
	}
	
	public boolean VerificarExisteVeiculo(String placa) throws SQLException {
		return veiculo.VerificarExisteVeiculo(placa);
	}
}

