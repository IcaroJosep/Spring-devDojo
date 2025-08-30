package __SpringBoot2.__star_Spring_io;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
/*jpa:
 * ouve a adiçao de dependencia no mevem , a instauaçao de uma coneçao e atribuiçao de uma entidade
 * 		DEPENDENCIAS :
 * 			MYSLQ
 * 			JPA
 * 	#ATEMÇAO:
 * Sobre a introduçao de uma coneçao com o mysql no arquivo resurces/ .propirties,
 * tbm a la a incerçao direta de senha e logim (embora seja usavel em progetos de estudo nao e ussual, 
 * nem em produçao nem em en deployt para o git), em produçao deve se ussar variaveis de aombiente em 
 * vez da incerçao direata"
 * 
 * istauraçao de uma entidade:
 * 		ororeu sobre a clase anime comtidade em dominio 
 * 		esta istauraçao faz com q a alase se torne uma tabela no banco de dados
 * */
