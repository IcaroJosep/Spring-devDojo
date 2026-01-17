	package __SpringBoot2.__star_Spring_io.dominio;
	
	import java.util.Set;
	
	import org.assertj.core.api.Assertions;
	import org.junit.jupiter.api.DisplayName;
	import org.junit.jupiter.api.Test;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.boot.test.context.SpringBootTest;
	
	import jakarta.validation.ConstraintViolation;
	import jakarta.validation.constraints.NotBlank;
	import jakarta.validation.constraints.NotEmpty;
	import lombok.extern.java.Log;
	import lombok.extern.log4j.Log4j2;
	
	@SpringBootTest
	@Log4j2
	class AnimeTest {
		
			@Autowired
			private jakarta.validation.Validator validator;
	
			@Test
			@DisplayName("It should return a validation error when the name is blank, empty, or null.")
			void ValidationAnimeName_returnValidationError_WhenNameIsBlank() {
				
				Anime anime = new Anime().builder().name(" ").build();
				
				Set<ConstraintViolation<Anime>> violations = validator.validate(anime);
				
				
				
				violations.forEach(v->{
					log.info("Campo violado : {}",v.getPropertyPath());
					log.info("Mensagem :{}",v.getMessage());
					log.info("Template :{}",v.getMessageTemplate());
					log.info("Valor invalido: [{}]",v.getInvalidValue());
					log.info("classe : {}",v.getRootBeanClass().getSimpleName());
					log.info("Anotacao : {}",v.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName());
					log.info("--------------------------------");
					});
				
				Assertions.assertThat(violations).isNotEmpty();
				
				Assertions.assertThat(violations)
			    .anyMatch(v ->
			        v.getPropertyPath().toString().equals("name") 
			        && 
			        v.getConstraintDescriptor().getAnnotation().annotationType().equals(NotBlank.class)
			    );
				
				
				
			}
			
		
	
	}
