package br.com.alura.forum.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {
	
	//Configuracoes de autenticacao (login, controle de acesso)
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	}
	
	//Configuracoes de autorizacao (urls, quem pode acessar url, perfil de acesso)
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET , "/topicos").permitAll()
		.antMatchers(HttpMethod.GET , "/topicos/*").permitAll();
	}
	
	//Configuracoes de recursos estaticos (arquivos css, js, imagens, etc) se view integrado com spring (jsp, thymeleaf, etc)
	@Override
	public void configure(WebSecurity web) throws Exception {
	}

}
