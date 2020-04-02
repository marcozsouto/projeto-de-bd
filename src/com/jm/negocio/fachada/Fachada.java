package com.jm.negocio.fachada;

import com.jm.negocio.excecoes.ArrayVazioException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jm.negocio.excecoes.CpfInvalidoException;
import com.jm.negocio.excecoes.CtpsInvalidoException;
import com.jm.negocio.excecoes.FuncionarioExistenteException;
import com.jm.negocio.excecoes.NomeInvalidoException;
import com.jm.negocio.excecoes.RgInvalidoException;
import com.jm.negocio.excecoes.TelefoneInvalidoException;
import com.jm.negocio.modelo.Almoxarifado;
import com.jm.negocio.modelo.AuxiliarAlmoxarifado;
import com.jm.negocio.modelo.Cliente;
import com.jm.negocio.modelo.Funcionario;
import com.jm.negocio.modelo.Servico;
import com.jm.negocio.modelo.Veiculo;
import com.jm.negocio.validacao.*;

public class Fachada {
	private AlmoxarifadoValidacao almoxarifado;
	private ClienteValidacao cliente;
	private FuncionarioValidacao funcionario;
	private ServicoValidacao servico;
	private VeiculoValidacao veiculo;
	private LoginValidacao login;
        private Funcionario flogado;
        private Cliente clogado;
	private static Fachada singleton = null;
	
	public static Fachada getSingleton() {
		if (singleton == null) {
			singleton = new Fachada();
		}
		return singleton;
	}
	
	private Fachada () {
		almoxarifado = new AlmoxarifadoValidacao();
		cliente = new ClienteValidacao();
		funcionario = new FuncionarioValidacao();
		servico = new ServicoValidacao();
		veiculo = new VeiculoValidacao();
		login = new LoginValidacao();
                flogado = new Funcionario();
                clogado = new Cliente();
	}
	
	public void CadastroFuncionarioValidacao(Funcionario funcionario) throws SQLException, NomeInvalidoException, CpfInvalidoException, CtpsInvalidoException, TelefoneInvalidoException, RgInvalidoException, FuncionarioExistenteException{
		this.funcionario.CadastroFuncionarioValidacao(funcionario);
	}

	public void AtualizarFuncionarioValidacao(Funcionario antigo, Funcionario novo) throws SQLException, NomeInvalidoException, CpfInvalidoException, FuncionarioExistenteException, CtpsInvalidoException, RgInvalidoException, TelefoneInvalidoException {
		this.funcionario.AtualizarFuncionarioValidacao(antigo, novo);
	}
	
	public ArrayList<Funcionario> MostrarFuncionarioValidacao() throws SQLException, ArrayVazioException {
		return this.funcionario.MostrarFuncionarioValidacao();
	}
	
	public void RemoverFuncionarioValidacao(Funcionario funcionario) throws SQLException, FuncionarioExistenteException {
		this.funcionario.RemoverFuncionarioValidacao(funcionario);
	}	
	
	public Funcionario RealizarLoginFuncionarioValidacao(String login, String senha) throws SQLException {
		return this.login.RealizarLoginFuncionarioValidacao(login, senha);
	}
	
	public Cliente RealizarLoginClienteValidacao(String login, String senha) throws SQLException {
                return this.login.RealizarLoginClienteValidacao(login, senha);
	}
        
        public ArrayList<Servico> MostrarServicoIncompleto() throws SQLException{
            return this.servico.MostrarServicoIncompletoValidacao();
        }
        
        public void CadastroClienteValidacao(Cliente cliente) throws SQLException{
            this.cliente.CadastroClienteValidacao(cliente);
        }
        
        public void CadastrarVeiculoValidacao(Veiculo veiculo) throws SQLException {
            this.veiculo.CadastrarVeiculoValidacao(veiculo);
        }
        
        public void CadastrarServicoValidacao(Servico servico) throws SQLException  {
            this.servico.CadastrarServicoValidacao(servico);
        }
        
        public ArrayList<Veiculo> MostrarVeiculoValidacao() throws SQLException{
            return this.veiculo.MostrarVeiculoValidacao();
        }
        
        public void RemoverVeiculoValidacao(Veiculo veiculo) throws SQLException {
            this.veiculo.RemoverVeiculoValidacao(veiculo);
        }
        
        public void AtualizarVeiculoValidacao(Veiculo antigo, Veiculo novo) throws SQLException {
            veiculo.AtualizarVeiculoValidacao(antigo, novo);
        }
        
        public ArrayList<Cliente> MostrarClienteValidacao() throws SQLException, ArrayVazioException {
            return this.cliente.MostrarClienteValidacao();
        }
        
        public void RemoverClienteValidacao(Cliente cliente) throws SQLException {
            this.cliente.RemoverClienteValidacao(cliente);
        }
        
        public void AtualizarClienteValidacao(Cliente antigo, Cliente novo) throws SQLException{
            this.cliente.AtualizarClienteValidacao(antigo, novo);
        }
        
        public ArrayList<Almoxarifado> MostrarItemValidacao() throws SQLException{
            return this.almoxarifado.MostrarItemValidacao();
        }
        
        public void AtualizarItemValidacao(Almoxarifado antigo, Almoxarifado novo) throws SQLException {
            this.almoxarifado.AtualizarItemValidacao(antigo, novo);
        }
        
        public void CadastroItemValidacao(Almoxarifado item) throws SQLException {
            this.almoxarifado.CadastroItemValidacao(item);
        }
        
        public void RemoverItemValidacao(String nome) throws SQLException{
            this.almoxarifado.RemoverItemValidacao(nome);
        }
        
        public void RemoverServicoValidacao(Servico servico) throws SQLException {
            this.servico.RemoverServicoValidacao(servico);
        }
        
        public ArrayList<Servico> MostrarServicoValidacao() throws SQLException{
            return this.servico.MostrarServicoValidacao();
        }
        
        public void AtualizarServicoValidacao(Servico antigo, Servico novo) throws SQLException{
            this.servico.AtualizarServicoValidacao(antigo, novo);
        }
        
        public void  funcionarioLogin(Funcionario f){
            this.flogado = f;
        }
        
        public void  clienteLogin(Cliente c){
            this.clogado = c;
        }
        
        public Funcionario funcionarioLogado(){
            return this.flogado;
        }
        
        public Cliente clienteLogado(){
            return this.clogado;
        }
        
         public ArrayList<Servico> MostrarServicosMotorista(int id) throws SQLException{
             return this.servico.MostrarServicosMotorista(id);
        }
         
        public void atualizaRelatorio(Servico s,String relatorio) throws SQLException {
            this.servico.atualizaRelatorio(s, relatorio);
        }
        
        public ArrayList<Servico> MostrarServicoClientes(int id) throws SQLException{
		return servico.MostrarServicoClientes(id);
	}
}