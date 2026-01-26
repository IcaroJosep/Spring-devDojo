// Define o pacote onde a classe de teste reside
package __SpringBoot2.__star_Spring_io.controller;

// Importações estáticas permitem usar métodos como assertEquals() ou any() sem digitar o nome da classe antes
import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

// Importações de bibliotecas de teste e utilitários
import org.assertj.core.api.Assertions; // Biblioteca para asserções mais fluidas e legíveis
import org.junit.jupiter.api.BeforeEach; // Roda antes de cada método de teste
import org.junit.jupiter.api.DisplayName; // Permite dar um nome amigável ao teste
import org.junit.jupiter.api.Test; // Marca o método como um caso de teste
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers; // Usado para passar argumentos genéricos nos mocks
import org.mockito.BDDMockito; // Estilo de escrita de testes focado em Comportamento (Given, When, Then)
import org.mockito.InjectMocks; // Injeta os @Mock dentro da classe alvo
import org.mockito.Mock; // Cria um objeto falso (dublê) de uma classe
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// Classes do seu próprio projeto
import __SpringBoot2.__star_Spring_io.dominio.Anime;
import __SpringBoot2.__star_Spring_io.services.AnimeServices;
import __SpringBoot2.__star_Spring_io.util.AnimeCreator;
import __SpringBoot2.__star_Spring_io.util.DateUtil;

//roda o teste com o comtesto do spring
//@ExtendWith(SpringExtension.class)
// A anotação abaixo integraria o JUnit com o Mockito (está comentada, mas é uma alternativa ao openMocks)
//@ExtendWith(MockitoExtension.class)
class AnimeComtrollerTest {

    // @Mock cria um "dublê" da classe. Ele não executa o código real do Service.
    @Mock
    private AnimeServices animeServices;

    // Criamos um mock para o utilitário de data para que o teste não dependa do relógio do sistema
    @Mock
    private DateUtil dateUtil;
    
    // @InjectMocks cria uma instância REAL do Controller e "coloca" os mocks acima dentro dele
    @InjectMocks
    private AnimeComtroller animeComtroller;
    
    // Este método roda ANTES de cada teste individual (@Test)
    @BeforeEach
    void setUp() {
        // Inicializa os campos anotados com @Mock nesta classe
        MockitoAnnotations.openMocks(this);

        // Criamos uma página fake (PageImpl) contendo um anime gerado pelo AnimeCreator para simular o banco de dados
        PageImpl<Anime> animePage = new PageImpl<Anime>(List.of(AnimeCreator.createValidAnime()));
        
        // COMPORTAMENTO: "Quando o listAll do service for chamado com QUALQUER argumento, responda com a nossa página fake"
        BDDMockito.when(animeServices.listAll(ArgumentMatchers.any()))
                    .thenReturn(animePage);    
        
        // COMPORTAMENTO: "Quando o formatador de data for chamado, retorne uma String de data fixa/formatada"
        BDDMockito.when(dateUtil.formatLocalDataTimeToDatabaseStyle(ArgumentMatchers.any()))
                      .thenReturn(
                              DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now())
                       );
    } 
    
    @Test
    @DisplayName("list returne Liste Of Animes Inside Page Object when successful")
    void list_returneListeOfAnimesInsidePageObject_whensuccessful() {
        // 1. Preparação (Given): Qual nome esperamos encontrar?
        String expectedName = AnimeCreator.createAnimeToBeSaved().getName();
        
        // 2. Ação (When): Chamamos o método do Controller que queremos testar
        // .getBody() pega o conteúdo de dentro do ResponseEntity
        Page<Anime> animepages = animeComtroller.list(null).getBody();
        
        // 3. Verificação (Then): Usamos o AssertJ para garantir que o resultado é o que esperamos
        Assertions.assertThat(animepages).isNotNull(); // A página não pode ser nula
        
        Assertions.assertThat(animepages.toList())
                                    .isNotNull()
                                    .hasSize(1); // Esperamos exatamente 1 anime na lista
                                    
        // Verifica se o nome do anime retornado é igual ao nome que esperávamos
        Assertions.assertThat(animepages.toList().get(0).getName()).isEqualTo(expectedName);
    }
    
    
    
    
    
    
    
    
    
}