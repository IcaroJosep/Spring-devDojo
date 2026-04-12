package __SpringBoot2.__star_Spring_io.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import __SpringBoot2.__star_Spring_io.services.DevDojoUserDetailsServices;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration // Indica que esta classe contém definições de Beans e configurações do Spring
@EnableWebSecurity // Ativa a segurança web no projeto (filtro de segurança do Spring)
@Log4j2 // Anotação do Lombok para permitir o uso do 'log.info' ou 'log.error' no console

/*anotaçao depreciada */
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableMethodSecurity //prePostEnabled = true. Na @EnableMethodSecurity, isso já é o comportamento padrão.

@RequiredArgsConstructor
public class SecurityConfig {
	// Injeção do serviço que busca usuários no banco de dados (JPA)
	private final DevDojoUserDetailsServices devDojoUserDetailsServices;

    /**
     * Este Bean configura a "corrente de filtros" de segurança. 
     * Ele define QUEM pode acessar o QUÊ e COMO (Basic, Form, etc).
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Desabilita a proteção contra CSRF (comum em APIs REST e ambientes de teste/estudo)	
            .csrf(csrf -> csrf.disable()) 
            
            // Inicia a configuração das regras de autorização das requisições HTTP
            .authorizeHttpRequests(auth -> auth
                // Define que QUALQUER requisição (.anyRequest()) 
                // precisa estar autenticada (.authenticated()) para ser respondida
                .anyRequest().authenticated()
            )
            
            .formLogin(withDefaults()) //atribui pagina http padrao de login
            
            // Ativa a autenticação do tipo HTTP Basic (aquela janelinha do navegador ou Postman)
            // withDefaults() aplica as configurações padrão do Spring para o login básico
            .httpBasic(withDefaults());
        
        // Finaliza a montagem do objeto e o retorna para o Spring gerenciar
        return http.build();
    }

    /**
     * Este Bean cria e gerencia os usuários que ficarão salvos na memória RAM.
     * Substitui o antigo AuthenticationManagerBuilder.
     */
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        // Cria um codificador de senhas padrão (atualmente usa BCrypt por baixo dos panos)
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        
        // Apenas para fins didáticos: imprime no console a senha "academy" já criptografada
        log.info("Password encoded {}", passwordEncoder.encode("academy"));

        // Cria a definição do primeiro usuário (icaro)
        UserDetails user1 = User.withUsername("icaro")
            .password(passwordEncoder.encode("academy")) // Define a senha (precisa estar criptografada)
            .roles("USER", "ADMIN") // Atribui papéis/cargos ao usuário
            .build(); // Constrói o objeto do usuário

        // Cria a definição do segundo usuário (DevDojo)
        UserDetails user2 = User.withUsername("devdojo")
            .password(passwordEncoder.encode("academy"))
            .roles("USER") // Este usuário não é ADMIN, apenas USER
            .build();

        // Retorna o gerenciador de usuários em memória contendo os dois perfis criados
        return new InMemoryUserDetailsManager(user1, user2);
    }
    
    /**
     * O AuthenticationManager é o "Maestro" da autenticação.
     * Aqui configuramos ele para aceitar múltiplas fontes (Banco e Memória).
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder, InMemoryUserDetailsManager memManager) throws Exception {
    	
    	// Obtém o construtor de gerenciamento de autenticação do contexto do Spring
    	AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        
    	// CONFIGURAÇÃO 1: Diz ao Spring para usar o seu Service customizado (Banco de Dados)
        // Ele usará o repositório para buscar o usuário e o passwordEncoder para validar a senha
        authBuilder.userDetailsService(devDojoUserDetailsServices)
                   .passwordEncoder(passwordEncoder);
        
        // CONFIGURAÇÃO 2: Adiciona também o gerenciador de memória que criamos acima
        // Isso permite que o login funcione tanto para quem está no DB quanto para o 'icaro' da memória
        authBuilder.userDetailsService(memManager)
                   .passwordEncoder(passwordEncoder);

        return authBuilder.build();
    }
    /**
     * Bean responsável por definir o algoritmo de criptografia de senhas.
     * O DelegatingPasswordEncoder decide o hash (BCrypt, Argon2, etc) baseado no prefixo da senha.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
}
	// abaixo temos a implementaçao da aula que se tornol obsoleta nas versao 3.x do spring

/* import lombok.extern.log4j.Log4j2;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity

@Log4j2

public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override

    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()

                .anyRequest()

                .authenticated()

                .and()

                .httpBasic();

    }


    @Override

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        log.info("Password encoded {}", passwordEncoder.encode("test"));

        auth.inMemoryAuthentication()

                .withUser("william")

                .password(passwordEncoder.encode("academy"))

                .roles("USER", "ADMIN")

                .and()

                .withUser("devdojo")

                .password(passwordEncoder.encode("academy"))

                .roles("USER"); 
                
       auth.userDetailsServices(devDojoUserDetailsServices))
       		.passwordEncoder(passwordEncoder);
       
 */
