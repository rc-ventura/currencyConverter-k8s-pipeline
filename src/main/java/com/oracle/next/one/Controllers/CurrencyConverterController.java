package com.oracle.next.one.Controllers;

	
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.next.one.Models.CurrencyConverter;
import com.oracle.next.one.Models.Transaction;
import com.oracle.next.one.Services.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;



	/**
	 * Controlador para a classe CurrencyConverter que faz a conversão de moedas utilizando a API da Currency Layer.
	 */

@RestController
@Tag(name = "Currency Converter", description = "Endpoint responsável pela conversão de moedas")
public class CurrencyConverterController {
	 
	private final CurrencyConverter currencyConverter;

	    public CurrencyConverterController(RestTemplateBuilder restTemplateBuilder) {
	        this.currencyConverter = new CurrencyConverter(restTemplateBuilder);
	    }
	    
	@Autowired
	TransactionService transactionService;

	
	    /**
	     * Endpoint responsável por realizar a conversão de moedas utilizando a API da Currency Layer e chamar a classe TransactionService.
	     *
	     * @param from   Moeda de origem.
	     * @param to     Moeda de destino.
	     * @param amount Valor a ser convertido.
	     * @return ResponseEntity com o resultado da conversão ou erro se a conversão não for bem sucedida.
	     */
	
	@Operation(summary = "Converte uma moeda em outra", description = "Endpoint responsável por realizar a conversão de moedas utilizando a API da Currency Layer e chamar a classe TransactionService.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Conversão realizada com sucesso", content = {
            @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BigDecimal.class)) }),
        @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")})
	
	
	    @GetMapping("/convert/{from}/{to}")
	    public ResponseEntity<BigDecimal> convertCurrency(
	    		@Parameter(description = "Moeda de origem", example = "USD") @PathVariable String from,
	            @Parameter(description = "Moeda de destino", example = "BRL") @PathVariable String to,
	            @Parameter(description = "Valor a ser convertido", example = "100") @RequestParam("amount") BigDecimal amount){
	        Currency currencyFrom = Currency.getInstance(from);
	        Currency currencyTo = Currency.getInstance(to);

	        try {
	            BigDecimal result = currencyConverter.convert(currencyFrom, currencyTo, amount);
	            
	            //Chama o o TransactionService para salvar no banco de dados a transação de conversão
	            LocalDateTime dataTransaction = LocalDateTime.now();
	            Transaction transaction = new Transaction(from, to, amount, result, dataTransaction);
	            transactionService.save(transaction);
	           
	            return ResponseEntity.ok(result);
	            
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }
	}




	   