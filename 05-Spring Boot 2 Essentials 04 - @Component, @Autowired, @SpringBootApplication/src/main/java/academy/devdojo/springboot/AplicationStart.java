package academy.devdojo.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import academy.devdojo.springboot.util.DateUtil;

@EnableAutoConfiguration
@ComponentScan
public class AplicationStart {

	@Autowired
		public DateUtil dateUtil;
	
	public static void main(String[] args) {
		SpringApplication.run(AplicationStart.class,args);
	}

}
