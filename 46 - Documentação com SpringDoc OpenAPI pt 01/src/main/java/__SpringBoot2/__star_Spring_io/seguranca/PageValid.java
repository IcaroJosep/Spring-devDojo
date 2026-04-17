package __SpringBoot2.__star_Spring_io.seguranca;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import __SpringBoot2.__star_Spring_io.dominio.Anime;
import __SpringBoot2.__star_Spring_io.exception.BedRequestException;
import __SpringBoot2.__star_Spring_io.exception.BedRequestExceptionDetails;

public class PageValid {
	private static final int MAX_PAGE_SIZE = 50;
	
	
	public static Page<Anime> ValidaSanitizaPageAnime (Page<Anime> page) {
		
		
		if (page==null) {
			throw new BedRequestException(
					"page null!!"
					);
		};
		
		int pageSize=page.getSize();
		if (pageSize <=0 ||pageSize>MAX_PAGE_SIZE) {
			throw new BedRequestException(
	                String.format("Tamanho da página inválido: %d. Deve estar entre 1 e %d", 
	                    pageSize, MAX_PAGE_SIZE)
	            );
		}
		
		if (page.getNumber()<0) {
			throw new BedRequestException(
					String.format("Número da página inválido: %d. Não pode ser negativo", 
		                    page.getNumber())
	            );
		}
		
		
		// 4. Se não tem conteúdo, retorna como está
        if (!page.hasContent()) {
            return page;
        }
        
        // 5. Cria NOVA página com conteúdo sanitizado (sem modificar a original)
        List<Anime> sanitizedContent = page.getContent().stream()
            .map(Sanatizador::saniAnime)
            .collect(Collectors.toList());
     
        
        Page<Anime> saniPage = new PageImpl<>(
            sanitizedContent,
            page.getPageable(),      // Mantém configuração de paginação
            page.getTotalElements()  // Mantém total de elementos
        );
		
		
		return saniPage;
	}
	
	
}
