package com.jm.negocio.modelo;

public class Cliente {

	private int id;
	private String nome,cpf_cnpj,telefone,email,senha;
	private Endereco endereco;

	
	public Cliente() {
		super();
	}


	public Cliente(int id, String nome, String cpf_cnpj, String telefone, String email, String senha,
			Endereco endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf_cnpj = cpf_cnpj;
		this.telefone = telefone;
		this.email = email;
		this.senha = senha;
		this.endereco = endereco;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCpf_cnpj() {
		return cpf_cnpj;
	}


	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cpf_cnpj=" + cpf_cnpj + ", telefone=" + telefone + ", email="
				+ email + ", senha=" + senha + ", endereco=" + endereco + "]\n";
	}

	
}