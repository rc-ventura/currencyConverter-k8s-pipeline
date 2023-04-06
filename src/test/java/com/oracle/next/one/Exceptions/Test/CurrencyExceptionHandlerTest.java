package com.oracle.next.one.Exceptions.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import com.oracle.next.one.Exceptions.CurrencyExceptionHandler;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Exceptions")
@Feature("CurrencyExceptionHandler")
@Owner("Oracle")
public class CurrencyExceptionHandlerTest {
	
	@Mock
	private WebRequest request;

	@InjectMocks
	private CurrencyExceptionHandler currencyExceptionHandler = new CurrencyExceptionHandler();

	/**
	 * Testa o tratamento de exceção genérica, que retorna um erro interno do servidor (500).
	 */
	@Test
    @DisplayName("Handle Generic Exception - Returns Internal Server Error")
    @Description("Testa o tratamento de exceção genérica, que retorna um erro interno do servidor (500).")
    @Severity(SeverityLevel.NORMAL)
    @Story("Testando o tratamento de exceções genéricas")
	
	void handleGenericException_ReturnInternalServerError() {
	    Exception ex = new Exception();
	    

	    request = mock(WebRequest.class);
	    when(request.getDescription(false)).thenReturn("http://localhost:8080/test");

	    ResponseEntity<Object> response = currencyExceptionHandler.handleGenericException(ex, request);

	    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	    assertEquals("An error occurred while processing your request.", response.getBody());
	}

	/**
	 * Testa o tratamento de exceção de status de resposta, que retorna um erro HTTP personalizado com a mensagem fornecida.
	 */
	@Test
	@DisplayName("Handle Response Status Exception - Returns Custom HTTP Error")
	@Description("Testa o tratamento de exceção de status de resposta, que retorna um erro HTTP personalizado com a mensagem fornecida.")
	@Severity(SeverityLevel.NORMAL)
	@Story("Testando o tratamento de exceções de status de resposta")
	
	void handleResponseStatusException_ReturnResponseExceptionStatus() {
	    ResponseStatusException ex = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request parameter.");

	    ResponseEntity<Object> response = currencyExceptionHandler.handleResponseStatusException(ex, request);

	    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	    assertEquals("Invalid request parameter.", response.getBody());
	}

	/**
	 * Testa o tratamento de exceção de argumento ilegal, que retorna um erro de solicitação inválido (400).
	 */
	@Test
	@Description("Testa o tratamento de exceção de argumento ilegal, que retorna um erro de solicitação inválido (400).")
    @Severity(SeverityLevel.NORMAL)
    
	void handleIllegalArgumentException_ReturnBadRequest() {
	    IllegalArgumentException ex = new IllegalArgumentException();

	    ResponseEntity<Object> response = currencyExceptionHandler.handleIllegalArgumentException(ex, request);

	    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	    assertEquals("Invalid request parameter.", response.getBody());
	}

	/**
	 * Testa o tratamento de exceção de elemento não encontrado, que retorna um erro 404 (não encontrado) com a mensagem fornecida.
	 */
	@Test
	@Description("Testa o tratamento de exceção de elemento não encontrado, que retorna um erro 404 (não encontrado) com a mensagem fornecida.")
    @Severity(SeverityLevel.NORMAL)
	
	void handleNoSuchElementException_ReturnNotFound() {
	    NoSuchElementException ex = new NoSuchElementException();

	    ResponseEntity<Object> response = currencyExceptionHandler.handleNoSuchElementException(ex, request);

	    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	    assertEquals("The requested resource was not found.", response.getBody());
	}

	/**
	 * Testa o tratamento de exceção de violação de integridade de dados, que retorna um erro de conflito (409) com a mensagem fornecida.
	 */
	@Test
	@Description("Testa o tratamento de exceção de violação de integridade de dados, que retorna um erro de conflito (409) com a mensagem fornecida.")
    @Severity(SeverityLevel.NORMAL)
	
	void handleDataIntegrityViolationException_ReturnConflict() {
	    DataIntegrityViolationException ex = new DataIntegrityViolationException("The requested operation cannot be completed due to data integrity violation.");

	    ResponseEntity<Object> response = currencyExceptionHandler.handleDataIntegrityViolationException(ex, request);

	    assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
	    assertEquals("The requested operation cannot be completed due to data integrity violation.", response.getBody());
	}


}
