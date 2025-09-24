 package __SpringBoot2.__star_Spring_io.dominio;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data /*@Data → Vem do Lombok.Ele gera automaticamente para você:
							getters e setters
							toString()
							equals() e hashCode()
							Isso evita que você tenha que escrever tudo manualmente.*/

@AllArgsConstructor /*@AllArgsConstructor→ Lombok também.
					Cria um construtor com todos os atributos da classe (no caso, id e name).*/

@NoArgsConstructor /*@NoArgsConstructor→ Lombok.
					Cria um construtor vazio (sem parâmetros).*/

@Entity /*@Entity→ Vem do JPA (Java Persistence API).
					Diz para o Hibernate que essa classe representa uma tabela no banco de dados.
					(Aqui, a tabela provavelmente terá o nome anime, a menos que você configure outro nome com @Table).*/

@Builder /*@Builder→ Lombok de novo.
					Cria o padrão de projeto Builder, que permite criar objetos de forma fluida.*/
public class Anime {
	@Id /*→ Indica a chave primária da tabela (no caso, o campo id).*/
	@GeneratedValue(strategy = GenerationType.IDENTITY)/*→ Diz que o banco de dados vai gerar automaticamente o valor do id.
														IDENTITY significa que o banco vai usar auto_increment (no MySQL, por exemplo).
														Ou seja, cada vez que você salvar um novo Anime, o id será gerado.*/
	private Long id;
	
	
	@Column(nullable = false, length = 100)/*→ Define configurações extras da coluna no banco.
											nullable = false → não permite null (ou seja, sempre precisa ter um name).
											length = 100 → tamanho máximo de 100 caracteres para o campo.*/
	private String name;
	
}
