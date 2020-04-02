package com.jm.negocio.validacao;

import java.sql.SQLException;
import java.util.ArrayList;
import com.jm.negocio.ControleVeiculo;
import com.jm.negocio.fachada.Fachada;
import com.jm.negocio.modelo.Almoxarifado;
import com.jm.negocio.modelo.Funcionario;
import com.jm.negocio.modelo.Veiculo;


public class VeiculoValidacao {

	private ControleVeiculo metodos;
	
	public VeiculoValidacao() {
		metodos = new ControleVeiculo();
	}
	
	public void CadastrarVeiculoValidacao(Veiculo veiculo) throws SQLException {
		
		if(metodos.VerificarExisteVeiculo(veiculo.getPlaca()) == true) {
			
		}	
	
		if(veiculo.getPlaca().isEmpty() == true || veiculo.getModelo().isEmpty() == true) {
			
		}
		
		if(veiculo.getTipo().equals(veiculo.CARRETA_PRANCHA)==false || veiculo.getTipo().equals(veiculo.CESTO_AEREO)==false){
			
		}
		
		if(veiculo.getTipo().equals(veiculo.EMPILHADEIRA)==false || veiculo.getTipo().equals(veiculo.GUINDASTE_ARTICULADO)==false) {
			
		}
		
		if(veiculo.getTipo().equals(veiculo.GUINDASTE_HIDRAULICO)==false || veiculo.getTipo().equals(veiculo.REMOCAO_INDUSTRIAL)==false) {
			
		}
		
		metodos.CadastraVeiculo(veiculo);
	}
	
	public void RemoverVeiculoValidacao(Veiculo veiculo) throws SQLException {
		
		if(metodos.VerificarExisteVeiculo(veiculo.getPlaca())==false){
			
		}
		metodos.RemoveVeiculo(veiculo.getPlaca());
		
	}
	
	public void AtualizarVeiculoValidacao(Veiculo antigo, Veiculo novo) throws SQLException {
		
		if(metodos.VerificarExisteVeiculo(novo.getPlaca()) == true) {
			
		}	
	
		if(novo.getPlaca().isEmpty() == true || novo.getModelo().isEmpty() == true) {
			
		}
		
		if(novo.getTipo().equals(novo.CARRETA_PRANCHA)==false || novo.getTipo().equals(novo.CESTO_AEREO)==false){
			
		}
		
		if(novo.getTipo().equals(novo.EMPILHADEIRA)==false || novo.getTipo().equals(novo.GUINDASTE_ARTICULADO)==false) {
			
		}
		
		if(novo.getTipo().equals(novo.GUINDASTE_HIDRAULICO)==false || novo.getTipo().equals(novo.REMOCAO_INDUSTRIAL)==false) {
			
		}
		
		
		metodos.atualizarVeiculo(antigo, novo);
	}
	
	public ArrayList<Veiculo> MostrarVeiculoValidacao() throws SQLException{
		ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
		veiculos = metodos.ListaVeiculo();
		
		if(veiculos.isEmpty() == true) {
			
		}
		return veiculos;
	}
	
	
}
