package main;


import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Anime {
	
	@Positive
	private int id;
	
	@Size(max=100 ,min = 1, message = "nome com mais de 100 caracterers nao serao aceitos")
	private String name; 
	
}
