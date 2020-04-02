package com.jm.negocio.modelo;

public class Almoxarifado {
	
	private int id,quantidade;
	private String nome,descricao;
	private double preco;
	
	public Almoxarifado() {
		super();
	}

	public Almoxarifado(int id, int quantidade, String nome, String descricao, double preco) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "Almoxarifado [id=" + id + ", quantidade=" + quantidade + ", nome=" + nome + ", descricao=" + descricao
				+ ", preco=" + preco + "]\n";
	}

	
}

