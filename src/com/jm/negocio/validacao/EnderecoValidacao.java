package com.jm.negocio.validacao;

import com.jm.negocio.fachada.Fachada;
import com.jm.negocio.modelo.Endereco;


public class EnderecoValidacao {

	public boolean ValidacaoEndereco(Endereco endereco) {
		
		if(endereco.getBairro().isEmpty() == true) {
			return false;
		}
		
		if(endereco.getRua().isEmpty() == true) {
			return false;
		}
		
		if(endereco.getNumero().matches("[0-9]*")==false){
			return false;
		}
		
		if(endereco.getCep().matches("[0-9]*")==false){
			return false;
		}
		
		return true;
		
	}
	
	
	
	
	 
	
}
