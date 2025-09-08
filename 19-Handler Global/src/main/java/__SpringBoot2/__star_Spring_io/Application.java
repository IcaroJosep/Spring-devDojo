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
 * criaçao dos da clase BedRequestExceptionDetails em /exception 
 * tbm do pacote /handler com a classe RestExceptionHandler
 * 
 * oq ocoere e que rendler é uma espesse de comtroler q dis q toda ves e for retornado um determinada 
 * exeçao ela deve retornar o metodo indicado. 
 * */
