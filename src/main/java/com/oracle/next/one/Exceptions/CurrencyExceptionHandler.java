package com.oracle.next.one.Exceptions;

import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;



/**
 * Classe responsável por controlar exceções lançadas pelas requisições da aplicação.
 */

@Tag(name  = "CurrencyExceptionHandler", description = "API para controle de exceções da aplicação")
@ControllerAdvice
public class CurrencyExceptionHandler  extends ResponseEntityExceptionHandler {
	

	    /**
	     * Método responsável por tratar exceções genéricas.
	     * @param ex exceção lançada
	     * @param request requisição recebida
	     * @return objeto ResponseEntity contendo informações sobre a exceção
	     */
	
		@Operation(summary = "Handle generic exceptions", responses = {
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
		})
	
	    @ExceptionHandler(value = { Exception.class })
	    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
	        String errorMessage = "An error occurred while processing your request.";
	        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

	        return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), status, request);
	    }

	    /**
	     * Método responsável por tratar exceções de resposta com status.
	     * @param ex exceção lançada
	     * @param request requisição recebida
	     * @return objeto ResponseEntity contendo informações sobre a exceção
	     */
		
		@Operation(summary = "Handle response status exceptions", responses = {
	        @ApiResponse(responseCode = "400", description = "Bad Request"),
	        @ApiResponse(responseCode = "404", description = "Not Found")
	    })
		
	    @ExceptionHandler(value = { ResponseStatusException.class })
	    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
	        String errorMessage = ex.getReason();
	        HttpStatus status = (HttpStatus) ex.getStatusCode();

	        return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), status, request);
	    }

	    /**
	     * Método responsável por tratar exceções de argumentos inválidos.
	     * @param ex exceção lançada
	     * @param request requisição recebida
	     * @return objeto ResponseEntity contendo informações sobre a exceção
	     */
		
		 @Operation(summary = "Handle illegal argument exceptions", responses = {
		        @ApiResponse(responseCode = "400", description = "Bad Request")
		    })
		 
	    @ExceptionHandler(value = { IllegalArgumentException.class })
	    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
	        String errorMessage = "Invalid request parameter.";
	        HttpStatus status = HttpStatus.BAD_REQUEST;

	        return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), status, request);
	    }

	    /**
	     * Método responsável por tratar exceções de recursos não encontrados.
	     * @param ex exceção lançada
	     * @param request requisição recebida
	     * @return objeto ResponseEntity contendo informações sobre a exceção
	     */
	    @ExceptionHandler(value = { NoSuchElementException.class })
	    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {
	        String errorMessage = "The requested resource was not found.";
	        HttpStatus status = HttpStatus.NOT_FOUND;

	        return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), status, request);
	    }

	    /**
	     * Método responsável por tratar exceções de violação de integridade de dados.
	     * @param ex exceção lançada
	     * @param request requisição recebida
	     * @return objeto ResponseEntity contendo informações sobre a exceção
	     */
	    
	    @Operation(summary = "Trata exceções de argumentos inválidos", responses = {
	            @ApiResponse(responseCode = "400", description = "Parâmetro de solicitação inválido")
	    })
	    
	    @ExceptionHandler(value = { DataIntegrityViolationException.class })
	    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
	        String errorMessage = "The requested operation cannot be completed due to data integrity violation.";
	        HttpStatus status = HttpStatus.CONFLICT;

	        return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), status, request);
	    }
	}
