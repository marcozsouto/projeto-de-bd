package com.jm.negocio.modelo;

public class Veiculo {
	
	public static final String GUINDASTE_ARTICULADO = "GUINDASTE ARTICULADO";
	public static final String GUINDASTE_HIDRAULICO = "GUINDASTE HIDRÁULICO";
	public static final String EMPILHADEIRA = "EMPILHADEIRA";
	public static final String CARRETA_PRANCHA = "CARRETA PRANCHA";
	public static final String CESTO_AEREO = "CESTO AÉREO";
	public static final String REMOCAO_INDUSTRIAL = "REMOÇÃO INDUSTRIAL";
	
	private int id;
	private String placa,modelo,tipo;
	
	public Veiculo() {
		super();
	}

	public Veiculo(int id, String placa, String modelo, String tipo) {
		super();
		this.id = id;
		this.placa = placa;
		this.modelo = modelo;
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Veiculo [id=" + id + ", placa=" + placa + ", modelo=" + modelo + ", tipo=" + tipo + "]\n";
	}
	
	
}