package com.jm.dados;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RepositorioBanco {

	private Connection conexao;
	private final String url = "jdbc:sqlite:./bd/banco.db";
	private static RepositorioBanco singleton = null;
	
	private RepositorioBanco() throws SQLException {
		this.conectar();
		this.criarBanco();
	}
	
	public Connection conectar() throws SQLException {
		
                this.conexao = DriverManager.getConnection(url);
		return conexao;
	}
	
	private void criarBanco() throws SQLException {
		String funcionario = "CREATE TABLE IF NOT EXISTS Funcionarios (" + "id integer PRIMARY KEY AUTOINCREMENT, "
				+ "nome TEXT,cargo TEXT, cpf TEXT, ctps TEXT, rg TEXT, telefone TEXT, salario double, senha TEXT, endereco integer "
				+ ");";
		
		
		String veiculo = "CREATE TABLE IF NOT EXISTS Veiculos (" + "id integer PRIMARY KEY AUTOINCREMENT, "
				+ "placa TEXT, modelo TEXT, tipo TEXT " + ");";
		
		String servico = "CREATE TABLE IF NOT EXISTS Servicos (" + "id integer PRIMARY KEY AUTOINCREMENT, "
				+ "cliente integer, endereco integer, veiculo integer, funcionario integer, status TEXT, preco double, data TEXT, relatorio TEXT "
				+ ");";
		
		String endereco = "CREATE TABLE IF NOT EXISTS Enderecos (" + "id integer PRIMARY KEY AUTOINCREMENT, "
				+ "rua TEXT, bairro TEXT, cep TEXT, numero TEXT, complemento TEXT " + ");";
		
		String cliente = "CREATE TABLE IF NOT EXISTS Clientes (" + "id integer PRIMARY KEY AUTOINCREMENT, "
				+ "nome TEXT, cpfcnpj TEXT, telefone TEXT, email TEXT, senha TEXT, endereco integer " + ");";
		
		String almoxarifado = "CREATE TABLE IF NOT EXISTS Almoxarifado (" + "id integer PRIMARY KEY AUTOINCREMENT, "
				+ "nome TEXT, preco double, quantidade integer, descricao TEXT " + ");";
		
		Statement stmt = conexao.createStatement();
		stmt.execute(cliente);
		stmt.execute(endereco);
		stmt.execute(servico);
		stmt.execute(veiculo);
		stmt.execute(funcionario);
		stmt.execute(almoxarifado);
		
	}
	
	public Connection getConnection() {
		return this.conexao;
	}
	
	public static RepositorioBanco getSingleton() throws SQLException {
		if (singleton == null) {
			singleton = new RepositorioBanco();
		}
		return singleton;
	}
	
	
	
	
}
