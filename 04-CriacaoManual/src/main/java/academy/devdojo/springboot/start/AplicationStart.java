package academy.devdojo.springboot.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration //liga a autoconfiguração (ex.: configura Tomcat embutido, Jackson, DataSource, etc. conforme o que estiver no classpath).
@ComponentScan(basePackages = "academy.devdojo.springboot")//manda o Spring varrer o pacote academy.devdojo.springboot e todos os subpacotes atrás de componentes.
public class AplicationStart {

	public static void main(String[] args) {
		SpringApplication.run(AplicationStart.class,args);
		/*Cria o ApplicationContext do Spring.
		Executa a component scan e a autoconfiguração.
		Sobe o servidor embutido (por padrão, Tomcat na porta 8080).
		Processa os args da linha de comando (ex.: --server.port=9090).
		Retorna o contexto (se você precisasse, poderia guardar em uma variável).*/
	}

}
