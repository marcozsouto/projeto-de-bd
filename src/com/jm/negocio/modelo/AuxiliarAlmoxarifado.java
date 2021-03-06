package com.jm.negocio.modelo;

public class AuxiliarAlmoxarifado extends Funcionario{

	public AuxiliarAlmoxarifado() {
		super();
		this.cargo = "AUXILIR ALMOXARIFADO";
	}

	public AuxiliarAlmoxarifado(String nome, String cpf, String ctps, String rg, String telefone, String senha,
			double salario, Endereco endereco) {
		super(nome, cpf, ctps, rg, telefone, senha, salario, endereco);
		this.cargo = "AUXILIR ALMOXARIFADO";
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
}
