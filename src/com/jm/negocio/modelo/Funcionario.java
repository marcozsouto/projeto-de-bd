package com.jm.negocio.modelo;

public class Funcionario {
	
	protected int id;
	protected String nome,cpf,ctps,rg,telefone,senha;
	protected double salario;
	protected Endereco endereco;
	protected String cargo;
	
	public Funcionario() {
		super();
	}

	public Funcionario(String nome, String cpf, String ctps, String rg, String telefone, String senha,
			double salario, Endereco endereco) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.ctps = ctps;
		this.rg = rg;
		this.telefone = telefone;
		this.senha = senha;
		this.salario = salario;
		this.endereco = endereco;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCtps() {
		return ctps;
	}

	public void setCtps(String ctps) {
		this.ctps = ctps;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public String getCargo() {
		return cargo;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", ctps=" + ctps + ", rg=" + rg
				+ ", telefone=" + telefone + ", senha=" + senha + ", salario=" + salario + ", endereco=" + endereco
				+ ", cargo=" + cargo + "]";
	}


	
	
	
}

