package __SpringBoot2.__star_Spring_io.repository;

// Importa a interface base do Spring Data que fornece métodos prontos (save, delete, findAll, etc)
import org.springframework.data.jpa.repository.JpaRepository;

// Importa a sua entidade que mapeamos anteriormente
import __SpringBoot2.__star_Spring_io.dominio.DevDojoUser;

/**
 * Ao estender JpaRepository, o Spring cria automaticamente uma implementação 
 * desta interface em tempo de execução, conectando-a ao seu banco de dados.
 * * <DevDojoUser, Long> indica: 
 * 1. A entidade que este repositório gerencia (DevDojoUser).
 * 2. O tipo da chave primária (@Id) dessa entidade (Long).
 */
public interface DevDojoUserRepository extends JpaRepository<DevDojoUser, Long> {

    /**
     * Este é um "Query Method" (Método de Consulta). 
     * O Spring analisa o nome do método "findBy" + "Username" e gera 
     * automaticamente o SQL: SELECT * FROM dev_dojo_user WHERE username = ?
     * * @param Username O login que o usuário digitou na tela.
     * @return O objeto DevDojoUser encontrado ou null caso não exista.
     */
    DevDojoUser findByUsername(String Username);
}