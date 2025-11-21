package __SpringBoot2.__star_Spring_io.configurer;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



/*observe o pacote client ele executa uma classe main qu faz requisicoes a uma api
 * 
 * obs a clase clientSppring faz requisicoes a uma api quee nao é a comtida eexatameente neste projeto 
 * pos no comtroler abaiço nao eeesta nem um metoddo gatmaping com /anime/{id}
 * 
 * eu usei o projeto 14 pos nele teem o mapinng 
 * ou seja (foi eexecutado o rogeto 14 ara qq as quequisicoes no client funcionacem) 
 * 
 * o cliente reresenta um  programa externo a api q faz as requisiçoees para ela .
 * 
 * ele ode faciomente ser separado do projeto apenas umplementando nele as clases utilisadas por ele
 *  
 */




@Configuration
public class DavdojoWebMvcConfigurer implements WebMvcConfigurer {
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		PageableHandlerMethodArgumentResolver pageHandler = new PageableHandlerMethodArgumentResolver();
		pageHandler.setFallbackPageable(PageRequest.of(0, 5));
		resolvers.add(pageHandler);
	}

}
