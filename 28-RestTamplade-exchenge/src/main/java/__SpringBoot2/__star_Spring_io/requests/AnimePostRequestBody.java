package __SpringBoot2.__star_Spring_io.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AnimePostRequestBody {

	@NotEmpty(message = "o nome de um anime nao pode ser vazio")
	@Size(min = 1, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
	private String name;

}