	package __SpringBoot2.__star_Spring_io.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AnimePutRequestBody {
	@NotNull
	@Min(1)
	private Long id;
	@NotEmpty(message = "nao permitido nome null ou vazio")
	@Schema(description = "nome do anime", example = "demon slayer")
	private String name;
}
