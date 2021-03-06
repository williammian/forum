package br.com.alura.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Profile("basic-security")
@EnableWebSecurity
@Configuration
@Order(2) //esta classe será carregada depois da ActuatorSecurityConfigurations
public class BasicSecurityConfigurations extends WebSecurityConfigurerAdapter {
	
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
//----------------------------------------------------------------------------------------------
//		Autenticação Basic
//
		http
		.authorizeRequests()
		.anyRequest().authenticated()
		.and()
		.httpBasic()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.csrf().disable();
//		
//		https://www.base64encode.org/   encode   aluno@email.com:123456   YWx1bm9AZW1haWwuY29tOjEyMzQ1Ng==
//
//		Exemplo requisição:
//		curl -H "Authorization: Basic YWx1bm9AZW1haWwuY29tOjEyMzQ1Ng==" http://localhost:8080/topicos
//
//		Obtendo usuário logado
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String username = ((Usuario)principal).getEmail();
//		
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
