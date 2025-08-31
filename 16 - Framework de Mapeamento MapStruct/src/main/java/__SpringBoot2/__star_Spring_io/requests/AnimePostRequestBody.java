package __SpringBoot2.__star_Spring_io.requests;

import lombok.Data;

/*DTO = obj de trasferencia de dados
 *  No POST, o cliente só manda name.
	No PUT, o cliente manda id + name.
	O serviço converte esses DTOs em entidades (Anime) antes de salvar no banco.
🔹 Vantagens de usar DTO
	Segurança
		Evita expor dados sensíveis da entidade (ex.: senhas, datas internas, flags do sistema).
	Separação de responsabilidades
		A entidade (Anime) representa a tabela no banco.
		O DTO representa os dados que o cliente pode enviar/receber.
		Isso mantém cada classe com uma função clara.
	Flexibilidade
		Você pode ter DTOs diferentes para entrada (request) e saída (response).
		Exemplo: resposta pode incluir campos extras (id, createdAt) que o cliente não envia no request.
	Validação
		Você pode usar anotações como @NotBlank, @Size, @Email nos DTOs para validar o que o cliente manda.*/

@Data /*→ Vem do Lombok.Ele gera automaticamente para você:
					getters e setters
					toString()
					equals() e hashCode()
					Isso evita que você tenha que escrever tudo manualmente.*/
public class AnimePostRequestBody {
	private String name;
}
