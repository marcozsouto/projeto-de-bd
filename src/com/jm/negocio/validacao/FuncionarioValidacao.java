package com.jm.negocio.validacao;


import java.sql.SQLException;
import java.util.ArrayList;
import com.jm.negocio.ControleFuncionario;
import com.jm.negocio.excecoes.ArrayVazioException;
import com.jm.negocio.excecoes.CpfInvalidoException;
import com.jm.negocio.excecoes.CtpsInvalidoException;
import com.jm.negocio.excecoes.FuncionarioExistenteException;
import com.jm.negocio.excecoes.NomeInvalidoException;
import com.jm.negocio.excecoes.RgInvalidoException;
import com.jm.negocio.excecoes.TelefoneInvalidoException;
import com.jm.negocio.fachada.Fachada;
import com.jm.negocio.modelo.Funcionario;

public class FuncionarioValidacao {
	
	private ControleFuncionario metodos;
	
	public FuncionarioValidacao() {
		metodos = new ControleFuncionario();
	}
	
	
	public void CadastroFuncionarioValidacao(Funcionario funcionario) throws NomeInvalidoException, SQLException, CpfInvalidoException, CtpsInvalidoException, FuncionarioExistenteException, RgInvalidoException, TelefoneInvalidoException{
		
		if(funcionario.getNome().matches("^[ A-Za-z]+$")==false || funcionario.getNome().isEmpty() == true) {
			throw new NomeInvalidoException();
		}
		
		if(funcionario.getCpf().matches("[0-9]*")==false || funcionario.getCpf().isEmpty() == true) {
			throw new CpfInvalidoException();
		}
		
		if(metodos.verificarExisteFuncionario(funcionario.getCpf()) == true) {
                    throw new FuncionarioExistenteException();
		}
		
		if(funcionario.getCtps().matches("[0-9]*")==false || funcionario.getCtps().isEmpty() == true) {
                    throw new CtpsInvalidoException();
		}
		
		if(funcionario.getRg().matches("[0-9]*")==false || funcionario.getRg().isEmpty() == true) {
                    throw new RgInvalidoException(); 
		}
		
		if(funcionario.getTelefone().matches("[0-9]*")==false || funcionario.getTelefone().isEmpty() == true) {
		throw new TelefoneInvalidoException();
		}
		
		
		metodos.CadastraFuncionario(funcionario);
	}
	
	
	public void AtualizarFuncionarioValidacao(Funcionario antigo, Funcionario novo) throws SQLException, NomeInvalidoException, CpfInvalidoException, FuncionarioExistenteException, CtpsInvalidoException, RgInvalidoException, TelefoneInvalidoException {
		
		if(novo.getNome().matches("^[ A-Za-z]+$")==false || novo.getNome().isEmpty() == true) {
			throw new NomeInvalidoException();
		}
		
		if(novo.getCpf().matches("[0-9]*")==false || novo.getCpf().isEmpty() == true) {
                    throw new CpfInvalidoException();
		}
		
		if(metodos.verificarExisteFuncionario(novo.getCpf()) == true) {
			throw new FuncionarioExistenteException();
		}
		
		if(novo.getCtps().matches("[0-9]*")==false || novo.getCtps().isEmpty() == true) {
			throw new CtpsInvalidoException();
		}
		
		if(novo.getRg().matches("[0-9]*")==false || novo.getRg().isEmpty() == true) {
			throw new RgInvalidoException(); 
		}
		
		if(novo.getTelefone().matches("[0-9]*")==false || novo.getTelefone().isEmpty() == true) {
			throw new TelefoneInvalidoException();
		}
		
		
		metodos.atualizarFuncionario(antigo, novo);
	}
	
	public ArrayList<Funcionario> MostrarFuncionarioValidacao() throws SQLException, ArrayVazioException {
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		funcionarios = metodos.ListaFuncionario();
		
		if(funcionarios.isEmpty() == true) {
			throw new ArrayVazioException();
		}
		
		return funcionarios;
	}
	
	public void RemoverFuncionarioValidacao(Funcionario funcionario) throws SQLException, FuncionarioExistenteException {
		
		if(metodos.verificarExisteFuncionario(funcionario.getCpf())==false) {
			throw new FuncionarioExistenteException();
		}
		metodos.RemoveFuncionario(funcionario.getCpf());
	}
}