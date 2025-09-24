package __SpringBoot2.__star_Spring_io.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AnimePostRequestBody {
	@NotEmpty(message = "o nome de um anime nao pode ser vazio") // Empty ja faz o tratamento de null
	// @NotNull(message = "o nome de um anime nao pode ser nullo")
	private String name;
}
