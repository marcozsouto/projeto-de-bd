package com.jm.dados;
import com.jm.dados.MetodosEndereco;
import com.jm.dados.RepositorioBanco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jm.dados.interfaces.IMetodosFuncionario;
import com.jm.negocio.modelo.Funcionario;
import java.sql.Connection;

public class MetodosFuncionario implements IMetodosFuncionario{

	public void insereFuncionario(Funcionario funcionario) throws SQLException {
		MetodosEndereco endereco = new MetodosEndereco();
		funcionario.setEndereco(endereco.insereEndereco(funcionario.getEndereco()));
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "INSERT INTO Funcionarios (nome, cargo, cpf, ctps, rg, telefone, salario,senha, endereco) VALUES(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setString(1, funcionario.getNome());
		pstmt.setString(2, funcionario.getCargo());
		pstmt.setString(3, funcionario.getCpf());
		pstmt.setString(4, funcionario.getCtps());
		pstmt.setString(5, funcionario.getRg());
		pstmt.setString(6, funcionario.getTelefone());
		pstmt.setDouble(7, funcionario.getSalario());
		pstmt.setString(8, funcionario.getSenha());
		pstmt.setInt(9, funcionario.getEndereco().getId());
		pstmt.executeUpdate();
		funcionario = buscaFuncionario(funcionario.getCpf());
	}

	public Funcionario buscaFuncionario(String cpf) throws SQLException {
		MetodosEndereco endereco = new MetodosEndereco();
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "SELECT id, nome, cargo, cpf, ctps, rg, telefone, salario, senha, endereco FROM Funcionarios WHERE cpf = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setString(1, cpf);
		ResultSet resultado = pstmt.executeQuery();
		Funcionario resultador = new Funcionario();
		while (resultado.next()) {
			resultador.setId(resultado.getInt(1));
			resultador.setNome(resultado.getString(2));
			resultador.setCargo(resultado.getString(3));
			resultador.setCpf(resultado.getString(4));
			resultador.setCtps(resultado.getString(5));
			resultador.setRg(resultado.getString(6));
			resultador.setTelefone(resultado.getString(7));
			resultador.setSalario(resultado.getDouble(8));
			resultador.setSenha(resultado.getString(9));
			resultador.setEndereco(endereco.buscaEnderecoId(resultado.getInt(10)));
		}
		return resultador;
	}

	public Funcionario buscaFuncionarioId(int id) throws SQLException {
		MetodosEndereco endereco = new MetodosEndereco();
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "SELECT id, nome, cargo, cpf, ctps, rg, telefone, salario, senha, endereco FROM Funcionarios WHERE id = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet resultado = pstmt.executeQuery();
		Funcionario resultador = new Funcionario();
		while (resultado.next()) {
			resultador.setId(resultado.getInt(1));
			resultador.setNome(resultado.getString(2));
			resultador.setCargo(resultado.getString(3));
			resultador.setCpf(resultado.getString(4));
			resultador.setCtps(resultado.getString(5));
			resultador.setRg(resultado.getString(6));
			resultador.setTelefone(resultado.getString(7));
			resultador.setSalario(resultado.getDouble(8));
			resultador.setSenha(resultado.getString(9));
			resultador.setEndereco(endereco.buscaEnderecoId(resultado.getInt(10)));
		}
		return resultador;
	}
	public void removeFuncionario(String cpf) throws SQLException {
		MetodosEndereco endereco = new MetodosEndereco();
		Funcionario aux = buscaFuncionario(cpf);
		endereco.removeEndereco(aux.getEndereco().getId());
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "DELETE FROM Funcionarios WHERE cpf = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setString(1, cpf);
		pstmt.executeUpdate();

	}
	
	public ArrayList<Funcionario> listaFuncionario() throws SQLException{
		MetodosEndereco endereco = new MetodosEndereco();
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "SELECT id, nome, cargo, cpf, ctps, rg, telefone, salario, senha, endereco FROM Funcionarios";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		ResultSet resultado = pstmt.executeQuery();
		ArrayList <Funcionario> ArrayResultado = new ArrayList<Funcionario>();
		while (resultado.next()) {
			Funcionario resultador = new Funcionario();
			resultador.setId(resultado.getInt(1));
			resultador.setNome(resultado.getString(2));
			resultador.setCargo(resultado.getString(3));
			resultador.setCpf(resultado.getString(4));
			resultador.setCtps(resultado.getString(5));
			resultador.setRg(resultado.getString(6));
			resultador.setTelefone(resultado.getString(7));
			resultador.setSalario(resultado.getDouble(8));
			resultador.setSenha(resultado.getString(9));
			resultador.setEndereco(endereco.buscaEnderecoId(resultado.getInt(10)));
			ArrayResultado.add(resultador);		
		}
		return ArrayResultado;
	}
	public void atualizarFuncionario(Funcionario antigo, Funcionario atualizado) throws SQLException {
		MetodosEndereco endereco = new MetodosEndereco();
		endereco.atualizarEndereco(antigo.getEndereco(), atualizado.getEndereco());
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "UPDATE Funcionarios SET nome = ?, cargo = ?, cpf = ?, ctps = ?, rg = ?, telefone = ?, salario = ?, senha = ?  WHERE cpf = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setString(1, atualizado.getNome());
		pstmt.setString(2, atualizado.getCargo());
		pstmt.setString(3, atualizado.getCpf());
		pstmt.setString(4, atualizado.getCtps());
		pstmt.setString(5, atualizado.getRg());
		pstmt.setString(6, atualizado.getTelefone());
		pstmt.setDouble(7, atualizado.getSalario());
		pstmt.setString(8, atualizado.getSenha());
		pstmt.setString(9, antigo.getCpf());
		pstmt.executeUpdate();
		
	} 
	
	public boolean verificarExisteFuncionario(String cpf) throws SQLException {
		MetodosEndereco endereco = new MetodosEndereco();
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "SELECT id, nome, cargo, cpf, ctps, rg, telefone, salario, senha, endereco FROM Funcionarios WHERE cpf = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setString(1, cpf);
		ResultSet resultado = pstmt.executeQuery();
		Funcionario resultador = new Funcionario();
		while (resultado.next()) {
			resultador.setId(resultado.getInt(1));
			resultador.setNome(resultado.getString(2));
			resultador.setCargo(resultado.getString(3));
			resultador.setCpf(resultado.getString(4));
			resultador.setCtps(resultado.getString(5));
			resultador.setRg(resultado.getString(6));
			resultador.setTelefone(resultado.getString(7));
			resultador.setSalario(resultado.getDouble(8));
			resultador.setSenha(resultado.getString(9));
			resultador.setEndereco(endereco.buscaEnderecoId(resultado.getInt(10)));
		}
	
		if(resultador.getCpf() == null) {
			return false;
		}
		else {
			return true;
		}
	}

}
