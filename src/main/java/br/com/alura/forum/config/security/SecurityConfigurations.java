package br.com.alura.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	//Configuracoes de autenticacao (login, controle de acesso)
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//Configuracoes de autorizacao (urls, quem pode acessar url, perfil de acesso)
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers(HttpMethod.GET , "/topicos").permitAll()
		.antMatchers(HttpMethod.GET , "/topicos/*").permitAll()
		.anyRequest().authenticated()
		.and().formLogin();

//----------------------------------------------------------------------------------------------
//		Autenticação Basic
//
//		http
//		.authorizeRequests()
//		.anyRequest().authenticated()
//		.and()
//		.httpBasic()
//		.and()
//		.sessionManagement()
//		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		.and()
//		.csrf().disable();
//		
//		https://www.base64encode.org/   encode   aluno@email.com:123456   YWx1bm9AZW1haWwuY29tOjEyMzQ1Ng==
//
//		Exemplo requisição:
//		curl -H "Authorization: Basic YWx1bm9AZW1haWwuY29tOjEyMzQ1Ng==" http://localhost:8080/topicos
//---------------------------------------------------------------------------------------------------------
		
	}
	
	//Configuracoes de recursos estaticos (arquivos css, js, imagens, etc) se view integrado com spring (jsp, thymeleaf, etc)
	@Override
	public void configure(WebSecurity web) throws Exception {
	}

	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("123456"));
	}
	
}
