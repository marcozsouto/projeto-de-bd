package com.jm.negocio.validacao;
import java.sql.SQLException;
import com.jm.negocio.ControleServico;
import com.jm.negocio.fachada.Fachada;
import com.jm.negocio.modelo.Servico;
import java.util.ArrayList;

public class ServicoValidacao {
	
	private ControleServico metodos;
	
	public ServicoValidacao() {
		metodos = new ControleServico();
	}
	
	public void CadastrarServicoValidacao(Servico servico) throws SQLException  {
		
		if(servico.getFuncionario() == null) {
			
		}
		
		if(servico.getCliente() == null) {
			
		}
		
		if(servico.getEndereco() == null) {
			
		}
		
		if(servico.getVeiculo() == null) {
			
		}
		
		metodos.CadastraServico(servico);
	}
        
        public void AtualizarServicoValidacao(Servico antigo, Servico novo) throws SQLException{
                
                if(novo.getFuncionario() == null) {
			
		}
		
		if(novo.getCliente() == null) {
			
		}
		
		if(novo.getEndereco() == null) {
			
		}
		
		if(novo.getVeiculo() == null) {
			
		}
        
        
            metodos.atualizaServico(antigo, novo);
        }
	
	public void RemoverServicoValidacao(Servico servico) throws SQLException {
		
		
		
		metodos.RemoverServico(servico.getId());
	}
        
        public ArrayList<Servico> MostrarServicoIncompletoValidacao() throws SQLException{
                return metodos.listarServicosIncompletos();
        }
        
        public ArrayList<Servico> MostrarServicoValidacao() throws SQLException{
            return metodos.listaServico();
        } 
        
        public ArrayList<Servico> MostrarServicosMotorista(int id) throws SQLException{
            return metodos.listarServicosMotorista(id);
        }
        
        public void atualizaRelatorio(Servico s,String relatorio) throws SQLException {
            metodos.inserirRelatorio(s, relatorio);
        }
        
        public ArrayList<Servico> MostrarServicoClientes(int id) throws SQLException{
		return metodos.listaServicoClientes(id);
	}
	
}
