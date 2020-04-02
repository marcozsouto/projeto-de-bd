package com.jm.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.jm.dados.interfaces.IMetodosVeiculo;
import com.jm.negocio.modelo.Veiculo;

public class MetodosVeiculo implements IMetodosVeiculo{
	
	public void insereVeiculo(Veiculo veiculo) throws SQLException {
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "INSERT INTO Veiculos (placa, modelo, tipo) VALUES(?,?,?)";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setString(1, veiculo.getPlaca());
		pstmt.setString(2, veiculo.getModelo());
		pstmt.setString(3, veiculo.getTipo());
		pstmt.executeUpdate();
		veiculo = buscaVeiculo(veiculo.getPlaca());

	}

	public Veiculo buscaVeiculo(String placa) throws SQLException {
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "SELECT id, placa, modelo, tipo FROM Veiculos WHERE placa = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setString(1, placa);
		ResultSet resultado = pstmt.executeQuery();
		Veiculo resultador = new Veiculo();
		while (resultado.next()) {
			resultador.setId(resultado.getInt(1));
			resultador.setPlaca(resultado.getString(2));
			resultador.setModelo(resultado.getString(3));
			resultador.setTipo(resultado.getString(4));
		}
		return resultador;
	}

	public Veiculo buscaVeiculoId(int id) throws SQLException {
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "SELECT id, placa, modelo, tipo FROM Veiculos WHERE id = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet resultado = pstmt.executeQuery();
		Veiculo resultador = new Veiculo();
		while (resultado.next()) {
			resultador.setId(resultado.getInt(1));
			resultador.setPlaca(resultado.getString(2));
			resultador.setModelo(resultado.getString(3));
			resultador.setTipo(resultado.getString(4));
		}
		return resultador;
	}

	public void removeVeiculo(String placa) throws SQLException {
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "DELETE FROM Veiculos WHERE placa = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setString(1, placa);
		pstmt.executeUpdate();

	}
	
	public ArrayList<Veiculo> listaVeiculo() throws SQLException{
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "SELECT id, placa, modelo, tipo FROM Veiculos";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		ResultSet resultado = pstmt.executeQuery();
		ArrayList <Veiculo> ArrayResultado = new ArrayList<Veiculo>();
		while (resultado.next()) {
			Veiculo resultador = new Veiculo();
			resultador.setId(resultado.getInt(1));
			resultador.setPlaca(resultado.getString(2));
			resultador.setModelo(resultado.getString(3));
			resultador.setTipo(resultado.getString(4));
			ArrayResultado.add(resultador);		
		}
		return ArrayResultado;
}
	public void atualizarVeiculo(Veiculo antigo, Veiculo atualizado) throws SQLException {
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "UPDATE Veiculos SET placa = ?, modelo = ?, tipo = ? WHERE placa = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setString(1, atualizado.getPlaca());
		pstmt.setString(2, atualizado.getModelo());
		pstmt.setString(3, atualizado.getTipo());
		pstmt.setString(4, antigo.getPlaca());
		pstmt.executeUpdate();
	
	}
	
	public boolean VerificarExisteVeiculo(String placa) throws SQLException {
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "SELECT id, placa, modelo, tipo FROM Veiculos WHERE placa = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setString(1, placa);
		ResultSet resultado = pstmt.executeQuery();
		Veiculo resultador = new Veiculo();
		while (resultado.next()) {
			resultador.setId(resultado.getInt(1));
			resultador.setPlaca(resultado.getString(2));
			resultador.setModelo(resultado.getString(3));
			resultador.setTipo(resultado.getString(4));
		}
		
		if(resultador.getPlaca()==null) {
			return false;
		}
		else {
			return true;
		}

	}
	
}