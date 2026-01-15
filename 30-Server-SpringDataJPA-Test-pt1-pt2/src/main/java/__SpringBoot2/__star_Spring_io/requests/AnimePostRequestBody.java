package __SpringBoot2.__star_Spring_io.requests;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AnimePostRequestBody {
	
	@NotNull(message = "id nao pede ser nullo ou vazio")
	@Min(value = 1l,message = "valor deve ser maior q 1")
	@Max(value = 999999999l ,message = "id deve ser menor q 999999999")
	@Digits(integer = 9,fraction = 0 ,message = "id nao pode ter mas de 9 digitos ")
	private Long id;
			
	@NotEmpty(message = "o nome de um anime nao pode ser vazio")
	@Size(min = 1, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
	private String name;

}