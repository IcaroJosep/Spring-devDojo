package __SpringBoot2.__star_Spring_io.dominio;

// Importações para manipulação de listas e fluxos de dados (Streams)
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

// Importações do Spring Security para lidar com permissões e detalhes do usuário
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// Importações do Jakarta Persistence (JPA) para mapeamento com o banco de dados
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

// Importações do Lombok para reduzir código repetitivo (Boilerplate)
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data // Gera Getter, Setter, equals, hashCode e toString automaticamente
@AllArgsConstructor // Gera um construtor com todos os campos
@NoArgsConstructor // Gera um construtor vazio (exigido pelo JPA)
@Entity // Diz ao Hibernate que esta classe é uma tabela no banco de dados
@Builder // Permite criar objetos de forma fluida: DevDojoUser.builder().name("..").build()
/**
 * Implementar UserDetails é o que permite ao Spring Security usar esta 
 * entidade para o processo de autenticação.
 */
public class DevDojoUser implements UserDetails {
	
	@Id // Define a chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // O banco de dados gera o ID (Auto-increment)
	private Long id;

	@NotBlank(message = "o nome de um anime nao pode ser vazio") // Validação de Bean Validation
	@Column(nullable = false, length = 100) // Configuração da coluna no banco (não nula, tamanho 100)
	private String name;

	private String username; // Nome de usuário usado para o login

	@ToString.Exclude // Impede que a senha apareça quando você der um log no objeto
	private String password; // Senha criptografada (Hash)

	private String authorities; // String que armazena permissões separadas por vírgula (ex: "ROLE_USER,ROLE_ADMIN")

	/**
	 * Converte a String de 'authorities' em uma lista de objetos que o Spring Security entende.
	 * Ex: "ROLE_USER,ROLE_ADMIN" vira uma lista de SimpleGrantedAuthority.
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.stream(authorities.split(",")) // Divide a string pelas vírgulas
				.map(SimpleGrantedAuthority::new) // Para cada texto, cria uma nova autoridade
				.collect(Collectors.toList()); // Transforma tudo em uma Lista
	}

	@Override
	public String getPassword() {
		return this.password; // Retorna a senha para o Spring Security validar
	}

	@Override
	public String getUsername() {
		return this.username; // Retorna o login para o Spring Security validar
	}

	/**
	 * Os 4 métodos abaixo controlam o status da conta. 
	 * Retornar 'true' significa que a conta está ativa e pronta para uso.
	 */

	@Override
	public boolean isAccountNonExpired() {
		return true; // A conta não expirou
	}

	@Override
	public boolean isAccountNonLocked() {
		return true; // A conta não está bloqueada
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true; // As credenciais (senha) não expiraram
	}

	@Override
	public boolean isEnabled() {
		return true; // O usuário está habilitado
	}
}