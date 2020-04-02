package com.jm.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jm.dados.interfaces.IMetodosServico;
import com.jm.negocio.modelo.Servico;

public class MetodosServico implements IMetodosServico{
	
	public void insereServico(Servico servico) throws SQLException {
		MetodosEndereco endereco = new MetodosEndereco();
		servico.setEndereco(endereco.insereEndereco(servico.getEndereco()));
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "INSERT INTO Servicos (cliente, endereco, veiculo, funcionario, status, preco, data) VALUES(?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setInt(1,servico.getCliente().getId());
		pstmt.setInt(2,servico.getEndereco().getId());
		pstmt.setInt(3,servico.getVeiculo().getId());
		pstmt.setInt(4,servico.getFuncionario().getId());
		pstmt.setString(5,servico.getStatus());
		pstmt.setDouble(6,servico.getPreco());
		pstmt.setString(7,servico.getData());
		pstmt.executeUpdate();
		
	}
	
	public Servico buscaServico(int id) throws SQLException{
		MetodosEndereco endereco = new MetodosEndereco();
		MetodosCliente cliente = new MetodosCliente();
		MetodosFuncionario funcionario = new MetodosFuncionario();
		MetodosVeiculo veiculo = new MetodosVeiculo();
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "SELECT id, cliente, endereco, veiculo, funcionario, status, preco, data, relatorio FROM Servicos WHERE id = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet resultado = pstmt.executeQuery();
		Servico resultador = new Servico();
		while (resultado.next()) {
			resultador.setId(resultado.getInt(1));
			resultador.setCliente(cliente.buscaClienteId(resultado.getInt(2)));
			resultador.setEndereco(endereco.buscaEnderecoId(resultado.getInt(3)));
			resultador.setVeiculo(veiculo.buscaVeiculoId(resultado.getInt(4)));
			resultador.setFuncionario(funcionario.buscaFuncionarioId(resultado.getInt(5)));
			resultador.setStatus(resultado.getString(6));
			resultador.setPreco(resultado.getDouble(7));
			resultador.setData(resultado.getString(8));		
		}
		return resultador;
	}
	public ArrayList<Servico> listaServico() throws SQLException{
		MetodosEndereco endereco = new MetodosEndereco();
		MetodosCliente cliente = new MetodosCliente();
		MetodosFuncionario funcionario = new MetodosFuncionario();
		MetodosVeiculo veiculo = new MetodosVeiculo();
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "SELECT id, cliente, endereco, veiculo, funcionario, status, preco, data, relatorio FROM Servicos";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		ResultSet resultado = pstmt.executeQuery();
		ArrayList <Servico> ArrayResultado = new ArrayList<Servico>();
		while (resultado.next()) {
			Servico resultador = new Servico();
			resultador.setId(resultado.getInt(1));
			resultador.setCliente(cliente.buscaClienteId(resultado.getInt(2)));
			resultador.setEndereco(endereco.buscaEnderecoId(resultado.getInt(3)));
			resultador.setVeiculo(veiculo.buscaVeiculoId(resultado.getInt(4)));
			resultador.setFuncionario(funcionario.buscaFuncionarioId(resultado.getInt(5)));
			resultador.setStatus(resultado.getString(6));
			resultador.setPreco(resultado.getDouble(7));
			resultador.setData(resultado.getString(8));
			ArrayResultado.add(resultador);			
		}
		return ArrayResultado;
	}
	
	public ArrayList<Servico> listaServico(int id) throws SQLException{
		MetodosEndereco endereco = new MetodosEndereco();
		MetodosCliente cliente = new MetodosCliente();
		MetodosFuncionario funcionario = new MetodosFuncionario();
		MetodosVeiculo veiculo = new MetodosVeiculo();
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "SELECT id, cliente, endereco, veiculo, funcionario, status, preco, data, relatorio FROM Servicos WHERE id = ? ";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setInt(1,id);
		ResultSet resultado = pstmt.executeQuery();
		ArrayList <Servico> ArrayResultado = new ArrayList<Servico>();
		while (resultado.next()) {
			Servico resultador = new Servico();
			resultador.setId(resultado.getInt(1));
			resultador.setCliente(cliente.buscaClienteId(resultado.getInt(2)));
			resultador.setEndereco(endereco.buscaEnderecoId(resultado.getInt(3)));
			resultador.setVeiculo(veiculo.buscaVeiculoId(resultado.getInt(4)));
			resultador.setFuncionario(funcionario.buscaFuncionarioId(resultado.getInt(5)));
			resultador.setStatus(resultado.getString(6));
			resultador.setPreco(resultado.getDouble(7));
			resultador.setData(resultado.getString(8));
			ArrayResultado.add(resultador);		
		}
		return ArrayResultado;
	}
	
	public void removeServico(int id) throws SQLException {
		MetodosEndereco endereco = new MetodosEndereco();
		MetodosServico servico = new MetodosServico();
		Servico aux = new Servico();
		aux = servico.buscaServico(id);
		endereco.removeEndereco(aux.getEndereco().getId());
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "DELETE FROM Servicos WHERE id = ? ";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.executeUpdate();

		}
	

	public ArrayList<Servico> listaServicoClientes(int id) throws SQLException{
		MetodosEndereco endereco = new MetodosEndereco();
		MetodosCliente cliente = new MetodosCliente();
		MetodosFuncionario funcionario = new MetodosFuncionario();
		MetodosVeiculo veiculo = new MetodosVeiculo();
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "SELECT id, cliente, endereco, veiculo, funcionario, status, preco, data, relatorio FROM Servicos WHERE cliente = ? ";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setInt(1,id);
		ResultSet resultado = pstmt.executeQuery();
		ArrayList <Servico> ArrayResultado = new ArrayList<Servico>();
		while (resultado.next()) {
			Servico resultador = new Servico();
			resultador.setId(resultado.getInt(1));
			resultador.setCliente(cliente.buscaClienteId(resultado.getInt(2)));
			resultador.setEndereco(endereco.buscaEnderecoId(resultado.getInt(3)));
			resultador.setVeiculo(veiculo.buscaVeiculoId(resultado.getInt(4)));
			resultador.setFuncionario(funcionario.buscaFuncionarioId(resultado.getInt(5)));
			resultador.setStatus(resultado.getString(6));
			resultador.setPreco(resultado.getDouble(7));
			resultador.setData(resultado.getString(8));
			ArrayResultado.add(resultador);		
		}
		return ArrayResultado;

}
	
	public void atualizaServico(Servico antigo, Servico atualizado) throws SQLException {
		MetodosEndereco endereco = new MetodosEndereco();
		endereco.atualizarEndereco(antigo.getEndereco(), atualizado.getEndereco());
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "UPDATE Servicos SET cliente = ?, endereco = ?, veiculo = ?, funcionario = ?, status = ?, preco = ?, data = ? WHERE id = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setInt(1,atualizado.getCliente().getId());
		pstmt.setInt(2,atualizado.getEndereco().getId());
		pstmt.setInt(3,atualizado.getVeiculo().getId());
		pstmt.setInt(4,atualizado.getFuncionario().getId());
		pstmt.setString(5,atualizado.getStatus());
		pstmt.setDouble(6,atualizado.getPreco());
		pstmt.setString(7,atualizado.getData());
		pstmt.setInt(8, antigo.getId());
		pstmt.executeUpdate();
		
	}
        
        
	
	public void inserirRelatorio(Servico servico, String relatorio) throws SQLException {
		MetodosEndereco endereco = new MetodosEndereco();
		endereco.atualizarEndereco(servico.getEndereco(),servico.getEndereco());
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "UPDATE Servicos SET cliente = ?, endereco = ?, veiculo = ?, funcionario = ?, status = ?, preco = ?, data = ?,relatorio = ? WHERE id = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setInt(1,servico.getCliente().getId());
		pstmt.setInt(2,servico.getEndereco().getId());
		pstmt.setInt(3,servico.getVeiculo().getId());
		pstmt.setInt(4,servico.getFuncionario().getId());
		pstmt.setString(5,Servico.st4);
		pstmt.setDouble(6,servico.getPreco());
		pstmt.setString(7,servico.getData());
                pstmt.setString(8,relatorio);
		pstmt.setInt(9,servico.getId());
		pstmt.executeUpdate();
	}
	
	public ArrayList<Servico> listarServicosMotorista(int id) throws SQLException{
		MetodosEndereco endereco = new MetodosEndereco();
		MetodosCliente cliente = new MetodosCliente();
		MetodosFuncionario funcionario = new MetodosFuncionario();
		MetodosVeiculo veiculo = new MetodosVeiculo();
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "SELECT id, cliente, endereco, veiculo, funcionario, status, preco, data, relatorio FROM Servicos WHERE funcionario = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
		pstmt.setInt(1,id);
		ResultSet resultado = pstmt.executeQuery();
		ArrayList <Servico> ArrayResultado = new ArrayList<Servico>();
		while (resultado.next()) {
			if(resultado.getString(9)==null) {
				Servico resultador = new Servico();
				resultador.setId(resultado.getInt(1));
				resultador.setCliente(cliente.buscaClienteId(resultado.getInt(2)));
				resultador.setEndereco(endereco.buscaEnderecoId(resultado.getInt(3)));
				resultador.setVeiculo(veiculo.buscaVeiculoId(resultado.getInt(4)));
				resultador.setFuncionario(funcionario.buscaFuncionarioId(resultado.getInt(5)));
				resultador.setStatus(resultado.getString(6));
				resultador.setPreco(resultado.getDouble(7));
				resultador.setData(resultado.getString(8));
				ArrayResultado.add(resultador);		
		}
			}
		return ArrayResultado;

		
	}
        
        public ArrayList<Servico> listaServicoIncompletos() throws SQLException{
		MetodosEndereco endereco = new MetodosEndereco();
		MetodosCliente cliente = new MetodosCliente();
		MetodosFuncionario funcionario = new MetodosFuncionario();
		MetodosVeiculo veiculo = new MetodosVeiculo();
		Connection conexao = RepositorioBanco.getSingleton().conectar();
		String sql = "SELECT id, cliente, endereco, veiculo, funcionario, status, preco, data, relatorio FROM Servicos WHERE status = ? OR status = ? OR status = ?";
		PreparedStatement pstmt = conexao.prepareStatement(sql);
                pstmt.setString(1, Servico.st1);
                pstmt.setString(2, Servico.st2);
                pstmt.setString(3, Servico.st3);
                ResultSet resultado = pstmt.executeQuery();
		ArrayList <Servico> ArrayResultado = new ArrayList<Servico>();
		while (resultado.next()) {
			Servico resultador = new Servico();
			resultador.setId(resultado.getInt(1));
			resultador.setCliente(cliente.buscaClienteId(resultado.getInt(2)));
			resultador.setEndereco(endereco.buscaEnderecoId(resultado.getInt(3)));
			resultador.setVeiculo(veiculo.buscaVeiculoId(resultado.getInt(4)));
			resultador.setFuncionario(funcionario.buscaFuncionarioId(resultado.getInt(5)));
			resultador.setStatus(resultado.getString(6));
			resultador.setPreco(resultado.getDouble(7));
			resultador.setData(resultado.getString(8));
			ArrayResultado.add(resultador);		
		}
                
		return ArrayResultado;

}
}