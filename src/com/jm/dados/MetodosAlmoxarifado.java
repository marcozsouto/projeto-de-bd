package com.jm.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.jm.dados.interfaces.IMetodosAlmoxarifado;
import com.jm.negocio.modelo.Almoxarifado;

public class MetodosAlmoxarifado implements IMetodosAlmoxarifado{
	public void insereItem(Almoxarifado item) throws SQLException {

		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "INSERT INTO Almoxarifado (nome, preco, quantidade, descricao) VALUES(?,?,?,?)";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setString(1, item.getNome());
		pstmt.setDouble(2, item.getPreco());
		pstmt.setInt(3, item.getQuantidade());
		pstmt.setString(4, item.getDescricao());
		pstmt.executeUpdate();
		item = buscaItem(item.getNome());
	}

	public Almoxarifado buscaItem(String nome) throws SQLException {
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "SELECT id, nome, preco, quantidade, descricao FROM Almoxarifado WHERE nome = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setString(1, nome);
		ResultSet resultado = pstmt.executeQuery();
		Almoxarifado resultador = new Almoxarifado();
		while (resultado.next()) {
			resultador.setId(resultado.getInt(1));
			resultador.setNome(resultado.getString(2));
			resultador.setPreco(resultado.getDouble(3));
			resultador.setQuantidade(resultado.getInt(4));
			resultador.setDescricao(resultado.getString(5));
		}
		return resultador;
	}
	
	public boolean verificarExisteItem(String nome) throws SQLException {
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "SELECT id, nome, preco, quantidade, descricao FROM Almoxarifado WHERE nome = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setString(1, nome);
		ResultSet resultado = pstmt.executeQuery();
		Almoxarifado resultador = new Almoxarifado();
		while (resultado.next()) {
			resultador.setId(resultado.getInt(1));
			resultador.setNome(resultado.getString(2));
			resultador.setPreco(resultado.getDouble(3));
			resultador.setQuantidade(resultado.getInt(4));
			resultador.setDescricao(resultado.getString(5));
		}
		if(resultador.getNome() == null) {
			return false;
		}
		else {
			return true;
		}
	}

	public void removeItem(String nome) throws SQLException {
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "DELETE FROM Almoxarifado WHERE nome = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setString(1, nome);
		pstmt.executeUpdate();

	}
	
	public ArrayList<Almoxarifado> listaItem() throws SQLException{
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "SELECT id, nome, preco, quantidade, descricao FROM Almoxarifado";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		ResultSet resultado = pstmt.executeQuery();
		ArrayList <Almoxarifado> ArrayResultado = new ArrayList<Almoxarifado>();
		while (resultado.next()) {
			Almoxarifado resultador = new Almoxarifado();
			resultador.setId(resultado.getInt(1));
			resultador.setNome(resultado.getString(2));
			resultador.setPreco(resultado.getDouble(3));
			resultador.setQuantidade(resultado.getInt(4));
			resultador.setDescricao(resultado.getString(5));
			ArrayResultado.add(resultador);
		}
		return ArrayResultado;
	}
	
	public void atualizaItem(Almoxarifado antigo, Almoxarifado novo) throws SQLException {
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "UPDATE Almoxarifado SET nome = ?, preco = ?, quantidade = ?, descricao = ? WHERE nome = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setString(1, novo.getNome());
		pstmt.setDouble(2, novo.getPreco());
		pstmt.setInt(3, novo.getQuantidade());
		pstmt.setString(4, novo.getDescricao());
		pstmt.setString(5, antigo.getNome());
		pstmt.executeUpdate();
		
	}
}
