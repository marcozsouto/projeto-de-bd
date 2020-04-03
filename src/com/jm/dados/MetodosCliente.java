package com.jm.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jm.dados.interfaces.IMetodosCliente;
import com.jm.negocio.modelo.Cliente;

public class MetodosCliente implements IMetodosCliente{
	public void insereCliente(Cliente cliente) throws SQLException {
		MetodosEndereco endereco = new MetodosEndereco();
		cliente.setEndereco(endereco.insereEndereco(cliente.getEndereco()));
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "INSERT INTO Clientes (nome, cpfcnpj, telefone, email, senha, endereco) VALUES(?,?,?,?,?,?)";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setString(1, cliente.getNome());
		pstmt.setString(2, cliente.getCpf_cnpj());
		pstmt.setString(3, cliente.getTelefone());
		pstmt.setString(4, cliente.getEmail());
		pstmt.setString(5, cliente.getSenha());
		pstmt.setDouble(6, cliente.getEndereco().getId());
		pstmt.executeUpdate();
		cliente = buscaCliente(cliente.getCpf_cnpj());

	}

	public Cliente buscaCliente(String cpf_cnpj) throws SQLException {
		MetodosEndereco endereco = new MetodosEndereco();
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "SELECT id, nome, cpfcnpj, telefone, email, senha, endereco FROM Clientes WHERE cpfcnpj = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setString(1, cpf_cnpj);
		ResultSet resultado = pstmt.executeQuery();
		Cliente resultador = new Cliente();
		while (resultado.next()) {
			resultador.setId(resultado.getInt(1));
			resultador.setNome(resultado.getString(2));
			resultador.setCpf_cnpj(resultado.getString(3));
			resultador.setTelefone(resultado.getString(4));
			resultador.setEmail(resultado.getString(5));
			resultador.setSenha(resultado.getString(6));
			resultador.setEndereco(endereco.buscaEnderecoId(resultado.getInt(7)));
		}
		return resultador;
	}

	public Cliente buscaClienteId(int id) throws SQLException {
		MetodosEndereco endereco = new MetodosEndereco();
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "SELECT id, nome, cpfcnpj, telefone, email, senha, endereco FROM Clientes WHERE id = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet resultado = pstmt.executeQuery();
		Cliente resultador = new Cliente();
		while (resultado.next()) {
			resultador.setId(resultado.getInt(1));
			resultador.setNome(resultado.getString(2));
			resultador.setCpf_cnpj(resultado.getString(3));
			resultador.setTelefone(resultado.getString(4));
			resultador.setEmail(resultado.getString(5));
			resultador.setSenha(resultado.getString(6));
			resultador.setEndereco(endereco.buscaEnderecoId(resultado.getInt(7)));
		}
		return resultador;
	}
	public void removeCliente(String cpf_cnpj) throws SQLException {
		MetodosEndereco endereco = new MetodosEndereco();
		Cliente aux = buscaCliente(cpf_cnpj);
		endereco.removeEndereco(aux.getEndereco().getId());
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "DELETE FROM Clientes WHERE cpfcnpj = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setString(1, cpf_cnpj);
		pstmt.executeUpdate();

	}
	
	public ArrayList<Cliente> listaCliente() throws SQLException{
		MetodosEndereco endereco = new MetodosEndereco();
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "SELECT id, nome, cpfcnpj, telefone, email, senha, endereco FROM Clientes";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		ResultSet resultado = pstmt.executeQuery();
		ArrayList <Cliente> ArrayResultado = new ArrayList<Cliente>();
		while (resultado.next()) {
			Cliente resultador = new Cliente();
			resultador.setId(resultado.getInt(1));
			resultador.setNome(resultado.getString(2));
			resultador.setCpf_cnpj(resultado.getString(3));
			resultador.setTelefone(resultado.getString(4));
			resultador.setEmail(resultado.getString(5));
			resultador.setSenha(resultado.getString(6));
			resultador.setEndereco(endereco.buscaEnderecoId(resultado.getInt(7)));
			ArrayResultado.add(resultador);
		}
		return ArrayResultado;
	}
	
	public void atualizaCliente(Cliente antigo, Cliente atualizado) throws SQLException {
		MetodosEndereco endereco = new MetodosEndereco();
		endereco.atualizarEndereco(antigo.getEndereco(), atualizado.getEndereco());
                Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "UPDATE Clientes SET nome = ?, cpfcnpj = ?, telefone = ?, email = ?, senha = ? WHERE cpfcnpj = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setString(1, atualizado.getNome());
		pstmt.setString(2, atualizado.getCpf_cnpj());
		pstmt.setString(3, atualizado.getTelefone());
		pstmt.setString(4, atualizado.getEmail());
		pstmt.setString(5, atualizado.getSenha());
		pstmt.setString(6, antigo.getCpf_cnpj());
		pstmt.executeUpdate();
	}
	
	public ArrayList<Cliente> listaCliente(int id) throws SQLException{
		MetodosEndereco endereco = new MetodosEndereco();
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "SELECT id, nome, cpfcnpj, telefone, email, senha, endereco FROM Clientes WHERE id = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet resultado = pstmt.executeQuery();
		ArrayList <Cliente> ArrayResultado = new ArrayList<Cliente>();
		while (resultado.next()) {
			Cliente resultador = new Cliente();
			resultador.setId(resultado.getInt(1));
			resultador.setNome(resultado.getString(2));
			resultador.setCpf_cnpj(resultado.getString(3));
			resultador.setTelefone(resultado.getString(4));
			resultador.setEmail(resultado.getString(5));
			resultador.setSenha(resultado.getString(6));
			resultador.setEndereco(endereco.buscaEnderecoId(resultado.getInt(7)));
			ArrayResultado.add(resultador);
		}
		return ArrayResultado;
	}
	
	public Cliente buscaCliente(int id) throws SQLException {
		MetodosEndereco endereco = new MetodosEndereco();
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "SELECT id, nome, cpfcnpj, telefone, email, senha, endereco FROM Clientes WHERE id = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet resultado = pstmt.executeQuery();
		Cliente resultador = new Cliente();
		while (resultado.next()) {
			resultador.setId(resultado.getInt(1));
			resultador.setNome(resultado.getString(2));
			resultador.setCpf_cnpj(resultado.getString(3));
			resultador.setTelefone(resultado.getString(4));
			resultador.setEmail(resultado.getString(5));
			resultador.setSenha(resultado.getString(6));
			resultador.setEndereco(endereco.buscaEnderecoId(resultado.getInt(7)));
		}
		return resultador;
	}
	
	public boolean verificarExisteCliente(String cpf_cnpj) throws SQLException {
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "SELECT id, nome, cpfcnpj, telefone, email, senha, endereco FROM Clientes WHERE cpfcnpj = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setString(1, cpf_cnpj);
		ResultSet resultado = pstmt.executeQuery();
		Cliente resultador = new Cliente();
		MetodosEndereco endereco = new MetodosEndereco();
		while (resultado.next()) {
			resultador.setId(resultado.getInt(1));
			resultador.setNome(resultado.getString(2));
			resultador.setCpf_cnpj(resultado.getString(3));
			resultador.setTelefone(resultado.getString(4));
			resultador.setEmail(resultado.getString(5));
			resultador.setSenha(resultado.getString(6));
			resultador.setEndereco(endereco.buscaEnderecoId(resultado.getInt(7)));
		}
		if(resultador.getCpf_cnpj() == null) {
			return false;
		}
		else {
			return true;
		}
	}
}