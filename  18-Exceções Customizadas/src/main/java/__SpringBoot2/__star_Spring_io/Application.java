package __SpringBoot2.__star_Spring_io;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
/*
 * criaçao do pacote exception e classe BedRequestException
 * 
 * para ser usada em vez de "ResponseStatusException" 
 * assim retornando uma exeçao personalizada .
 * 
 * assim quando chama o gat id com id inesistente retorna a exeçao personalinada 
 * 
 * 
 * alteraçoes:
 * a alteraçao foi feita apenas no services .
 * */
