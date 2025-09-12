package __SpringBoot2.__star_Spring_io.requests;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*DTO = obj de trasferencia de dados
 *  No POST, o cliente só manda name.
	No PUT, o cliente manda id + name.
	O serviço converte esses DTOs em entidades (Anime) antes de salvar no banco.
*/

@Data
public class AnimePostRequestBody {
	@NotEmpty(message = "o nome de um anime nao pode ser vazio")
	@NotNull(message = "o nome de um anime nao pode ser nullo")
	private String name;
}
