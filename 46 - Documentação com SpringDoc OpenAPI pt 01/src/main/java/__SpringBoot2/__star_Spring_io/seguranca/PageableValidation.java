package __SpringBoot2.__star_Spring_io.seguranca;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

public class PageableValidation {

	private static final int MAX_PAGE_SIZE = 50;
	private static final int DEFAULT_PAGE_SIZE = 5;
	private static final int DEFAULT_PAGE_NUMBER = 0;

	// Campos permitidos para ordenação (ajuste conforme sua entidade)
	private static final List<String> ALLOWED_SORT_FIELDS = Arrays.asList("id", "name");

	/**
	 * Valida e sanitiza um Pageable recebido do cliente
	 * 
	 * @param pageable Pageable original da requisição
	 * @return Pageable seguro para uso no services
	 */
	public static Pageable validateAndSanitize(Pageable pageable) {
		// 1. Valida e ajusta o tamanho da página
		int safeSize = validatePageSize(pageable.getPageSize());

		// 2. Valida e ajusta o número da página
		int safePage = validatePageNumber(pageable.getPageNumber());

		// 3. Valida e sanitiza a ordenação
		Sort safeSort = validateAndSanitizeSort(pageable.getSort());

		// 4. Retorna Pageable seguro
		return PageRequest.of(safePage, safeSize, safeSort);
	}

	/**
	 * Valida tamanho da página (prevenção de DoS)
	 */
	private static int validatePageSize(int requestedSize) {
		if (requestedSize <= 0) {
			return DEFAULT_PAGE_SIZE;
		}
		return Math.min(requestedSize, MAX_PAGE_SIZE);
	}

	/**
	 * Valida número da página
	 */
	private static int validatePageNumber(int requestedPage) {
		return Math.max(requestedPage, DEFAULT_PAGE_NUMBER);
	}

	/**
	 * Valida e sanitiza parâmetros de ordenação
	 */
	private static Sort validateAndSanitizeSort(Sort requestedSort) {
		if (requestedSort == null || requestedSort.isEmpty()) {
			return Sort.by("id").ascending(); // Ordenação padrão segura
		}

		// Filtra apenas campos permitidos e sanitiza nomes
		List<Order> safeOrders = requestedSort.stream()
				.filter(order -> ALLOWED_SORT_FIELDS.contains(order.getProperty()))
				.map(order -> new Order(order.getDirection(), Sanatizador.saniString(order.getProperty()) // Sanitiza
																											// nome do
																											// campo
				)).collect(Collectors.toList());

		if (safeOrders.isEmpty()) {
			// Nenhum campo válido, usa padrão
			return Sort.by("id").ascending();
		}

		return Sort.by(safeOrders);
	}

	/**
	 * Método rápido para validação básica (apenas tamanho)
	 */
	public static Pageable quickValidate(Pageable pageable) {
		int safeSize = Math.min(pageable.getPageSize(), MAX_PAGE_SIZE);
		if (safeSize <= 0) {
			safeSize = DEFAULT_PAGE_SIZE;
		}

		return PageRequest.of(Math.max(pageable.getPageNumber(), 0), safeSize, pageable.getSort());
	}

	/**
	 * Valida se um campo de ordenação é permitido
	 */
	public static boolean isSortFieldAllowed(String fieldName) {
		return ALLOWED_SORT_FIELDS.contains(fieldName);
	}

	/**
	 * Obtém a lista de campos permitidos para ordenação
	 */
	public static List<String> getAllowedSortFields() {
		return ALLOWED_SORT_FIELDS;
	}

	/**
	 * Obtém o tamanho máximo permitido para páginas
	 */
	public static int getMaxPageSize() {
		return MAX_PAGE_SIZE;
	}

}
