package com.jm.negocio.modelo;

public class Servico {
	
	public static String st1 = "RECEBIDO"; 
	public static String st2 = "ACEITO"; 
	public static String st3 = "EM ANDAMENTO";
	public static String st4 = "FINALIZADO";
	
	private int id;
	private Cliente cliente;
	private Endereco endereco;
	private Veiculo veiculo;
	private Funcionario funcionario;
	private String status;
	private double preco;
	private String data;
	private String relatorio;
	
	public Servico() {
		super();
	}

	public Servico(int id, Cliente cliente, Endereco endereco, Veiculo veiculo, Funcionario funcionario, String status,
			double preco, String data, String relatorio) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.endereco = endereco;
		this.veiculo = veiculo;
		this.funcionario = funcionario;
		this.status = status;
		this.preco = preco;
		this.data = data;
		this.relatorio = relatorio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(String relatorio) {
		this.relatorio = relatorio;
	}

	@Override
	public String toString() {
		return "Servico [id=" + id + ", cliente=" + cliente + ", endereco=" + endereco + ", veiculo=" + veiculo
				+ ", funcionario=" + funcionario + ", status=" + status + ", preco=" + preco + ", data=" + data
				+ ", relatorio=" + relatorio + "]\n";
	}

	
}
