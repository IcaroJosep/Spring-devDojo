package __SpringBoot2.__star_Spring_io;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
/*foi implementado um metodo get ussando request Param q captura da url;
 * demendendo do que for imcluido na url ele retorna uma lista com
 * todos os nomes q comtem a sequencia de caracteres 
 * ou so os comtem ezatamente os caracteres.
 * 
 * inplementaçao no utimo get da classe Comtroler seguindo em cascata ate Repository.
 * */
