package com.jm.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jm.negocio.modelo.Endereco;

public class MetodosEndereco {

	public Endereco insereEndereco(Endereco endereco) throws SQLException {
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "INSERT INTO Enderecos (rua, bairro, cep, numero, complemento) VALUES (?,?,?,?,?)";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setString(1, endereco.getRua());
		pstmt.setString(2, endereco.getBairro());
		pstmt.setString(3, endereco.getCep());
		pstmt.setString(4, endereco.getNumero());
		pstmt.setString(5, endereco.getComplemento());
		pstmt.executeUpdate();
		return buscaEndereco(endereco);

	}

	public Endereco buscaEndereco(Endereco endereco) throws SQLException {
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "SELECT id, rua, bairro, cep, numero, complemento FROM Enderecos WHERE rua = ? AND bairro = ? AND cep = ? AND numero = ? AND complemento = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);

		pstmt.setString(1, endereco.getRua());
		pstmt.setString(2, endereco.getBairro());
		pstmt.setString(3, endereco.getCep());
		pstmt.setString(4, endereco.getNumero());
		pstmt.setString(5, endereco.getComplemento());
		ResultSet resultado = pstmt.executeQuery();
		Endereco resultador = new Endereco();
		while (resultado.next()) {
			resultador.setId(resultado.getInt(1));
			resultador.setRua(resultado.getString(2));
			resultador.setBairro(resultado.getString(3));
			resultador.setCep(resultado.getString(4));
			resultador.setNumero(resultado.getString(5));
			resultador.setComplemento(resultado.getString(6));
		}
		return resultador;
	}

	public Endereco buscaEnderecoId(int id) throws SQLException {
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "SELECT id, rua, bairro, cep, numero, complemento FROM Enderecos WHERE id = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);

		pstmt.setInt(1, id);
		ResultSet resultado = pstmt.executeQuery();
		Endereco resultador = new Endereco();
		while (resultado.next()) {
			resultador.setId(resultado.getInt(1));
			resultador.setRua(resultado.getString(2));
			resultador.setBairro(resultado.getString(3));
			resultador.setCep(resultado.getString(4));
			resultador.setNumero(resultado.getString(5));
			resultador.setComplemento(resultado.getString(6));
		}
		return resultador;
	}

	public void removeEndereco(int id) throws SQLException {
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "DELETE FROM Enderecos WHERE id = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.executeUpdate();
	}
	
	public void atualizarEndereco(Endereco antigo, Endereco atualizado) throws SQLException {
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "UPDATE Enderecos SET rua = ?, bairro = ?, cep = ?, numero = ?, complemento = ? WHERE id = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setString(1, atualizado.getRua());
		pstmt.setString(2, atualizado.getBairro());
		pstmt.setString(3, atualizado.getCep());
		pstmt.setString(4, atualizado.getNumero());
		pstmt.setString(5, atualizado.getComplemento());
		pstmt.setInt(6, antigo.getId());
		pstmt.executeUpdate();
		
	} 
}
