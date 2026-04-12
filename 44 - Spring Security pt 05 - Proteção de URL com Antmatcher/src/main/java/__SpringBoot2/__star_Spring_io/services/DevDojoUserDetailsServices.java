package __SpringBoot2.__star_Spring_io.services;

import java.util.Optional;

// Importações do Spring Security para padronizar o retorno do usuário
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Importação do repositório que criamos para acessar o banco
import __SpringBoot2.__star_Spring_io.repository.DevDojoUserRepository;
import lombok.RequiredArgsConstructor;

/**
 * @Service: Indica que esta classe é um componente de serviço do Spring.
 * Implementar UserDetailsService é OBRIGATÓRIO para que o Spring Security 
 * saiba que este é o local onde ele deve validar logins vindos do banco.
 */
@Service
@RequiredArgsConstructor // Cria o construtor para injetar o repositório automaticamente
public class DevDojoUserDetailsServices implements UserDetailsService {
	
	// Repositório que fará a consulta SQL no banco de dados
	private final DevDojoUserRepository devDojoUserRepository;
	
	/**
	 * Este é o método principal do UserDetailsService. 
	 * O Spring Security o chama toda vez que alguém tenta fazer login.
	 * * @param username O texto digitado no campo "login" da tela ou Postman.
	 * @return Um objeto do tipo UserDetails (nossa classe DevDojoUser).
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {
		
		// 1. Chama o repositório para buscar o usuário pelo nome de login
		// 2. Optional.ofNullable trata o caso do banco retornar 'null'
		return Optional.ofNullable(devDojoUserRepository.findByUsername(username))
				
				// 3. Se o usuário existir, ele o retorna. 
				// 4. Se não existir (null), lança a exceção padrão de "Usuário não encontrado"
				.orElseThrow(() -> new UsernameNotFoundException("DevDojo User not found"));
	}

}