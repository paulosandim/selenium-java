package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {
	
	private LoginPage paginaDeLogin; 
	
	@BeforeEach
	public void beforeEach() {
		this.paginaDeLogin = new LoginPage();
	}
	
	@AfterEach
	public void afterEach() {
		this.paginaDeLogin.fechar();
	}
	
	@Test
	public void loginComDadosValidos() {
		paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
		paginaDeLogin.efetuaLogin();
		
		Assert.assertFalse(paginaDeLogin.isPaginaDeLogin());
		Assert.assertEquals("fulano", paginaDeLogin.getNomeUsuarioLogado());
	}
	
	@Test
	public void tentativaLoginComDadosInvalidos() {
		paginaDeLogin.preencheFormularioDeLogin("invalido", "123");
		paginaDeLogin.efetuaLogin();
		
		Assert.assertTrue(paginaDeLogin.isPaginaDeLoginInvalida());
		Assert.assertNull(paginaDeLogin.getNomeUsuarioLogado());
		Assert.assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos"));
	}
	
	@Test
	public void naoAcessaPaginaRestrita() {
		paginaDeLogin.navegaParaPaginaDeLances();

		Assert.assertTrue(paginaDeLogin.isPaginaDeLogin());
		Assert.assertFalse(paginaDeLogin.contemTexto("Dados dos Leilão"));
	}

}





