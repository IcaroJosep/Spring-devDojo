package __SpringBoot2.__star_Spring_io;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
/*ouve a refatoraçao total do codigo para a inplementaçao da  com o banco de dados :
 * 
 * 1./dominio > Anime : bilder gemerator id , alem de dizer q o anime é uma elemento 
 * do banco de dados diz q ele tem o id geredo pelo banco.
 * 
 * 2.criaçao de /request clases Anime**RequestBody  DTO = Data Transfer Object Em português: Objeto de Transferência de Dados.
	É uma classe simples (geralmente só com atributos, getters/setters) usada para transportar 
	dados entre camadas do sistema ou entre sistemas (ex.: API ↔ cliente).
 * 
 * 3.a classe anime repository extends jpaRepository assim dando acesso aos metodos crud
 * 
 * 4.classe AnimeServices ouve a subistituiçao de list<Animes> por uma instancia de AnimeRepository 
 * ,assim conectando o banco de dados .
 * 	 tbm a refatoraçao de todos os metodos trocando Anime por clases Anime**RequestBody e o tratamento 
 * de transformando suas entradas Anime**RequestBody em anime e envidando  para o banco.
 * 
 * 5. em comtroller so ouve a troca de anime dos requerimentos dos metodos por Anime**RequestBody 
 * pertinentes.
 * 
 * */
