package com.jm.dados.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import com.jm.negocio.modelo.Almoxarifado;

public interface IMetodosAlmoxarifado {

	public void insereItem(Almoxarifado item) throws SQLException;
	public Almoxarifado buscaItem(String nome) throws SQLException;
	public boolean verificarExisteItem(String nome) throws SQLException;
	public void removeItem(String nome) throws SQLException;
	public ArrayList<Almoxarifado> listaItem() throws SQLException;
	public void atualizaItem(Almoxarifado antigo, Almoxarifado novo) throws SQLException;

}

