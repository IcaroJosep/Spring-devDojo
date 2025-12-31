package main;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnimePost {

	@NotBlank(message = "nome nao pode ser vazio ou null")
	@Size(max = 100, min = 1, message = "nome deve conter de 1 a 100 caracteres")
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
