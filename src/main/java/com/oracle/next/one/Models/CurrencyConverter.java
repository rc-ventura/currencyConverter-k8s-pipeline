package com.oracle.next.one.Models;


import java.math.BigDecimal;
import java.net.URI;
import java.util.Currency;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


/**
	 * Classe responsável por fazer a conversão de moedas utilizando a API da Currency Layer.
	 */

@Component
@Tag(name = "Currency Converter", description = "Classe para conversão de moedas")
public class CurrencyConverter {
	    

	private final RestTemplate restTemplate;
	private static final String API_URL = "https://api.apilayer.com/exchangerates_data/convert";


	/**
	 * Construtor da classe que recebe um RestTemplateBuilder para criar um RestTemplate.
	 *
	 * @param build RestTemplateBuilder para criar um RestTemplate.
	 */
	
	public CurrencyConverter(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
	
	public CurrencyConverter(RestTemplate build) {
		this.restTemplate = new RestTemplate();
	}
	
	public CurrencyConverter() {
		this.restTemplate = new RestTemplate();
		
	}

	/**
	 * Método responsável por realizar a conversão de moedas utilizando a API da Currency Layer.
	 *
	 * @param from   Moeda de origem.
	 * @param to     Moeda de destino.
	 * @param amount Valor a ser convertido.
	 * @return Valor convertido.
	 */
	
	@Operation(summary = "Realiza a conversão de moedas",
			description = "Método responsável por realizar a conversão de moedas utilizando a API da Currency Layer.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Conversão realizada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Não autorizado"),
			@ApiResponse(responseCode = "403", description = "Proibido"),
			@ApiResponse(responseCode = "404", description = "Não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	
	/**
	* Constrói um objeto URI a partir da URL base e parâmetros de consulta fornecidos.
	* Os parâmetros de consulta são adicionados à URL base como pares chave-valor.
	*
	* @param API_URL a URL base para a API da Currency Layer
	*
	* @return um objeto URI contendo a URL completa com parâmetros de consulta adicionados
	*/
	
	
	public BigDecimal convert(
			
			@Parameter(description = "Moeda de origem", required = true, schema = @Schema(type = "string", example = "USD"))
			Currency from,
			@Parameter(description = "Moeda de destino", required = true, schema = @Schema(type = "string", example = "BRL"))
			Currency to,
			@Parameter(description = "Valor a ser convertido", required = true, schema = @Schema(type = "number", example = "100.00"))
			BigDecimal amount) {
			
try  {
				
		   URI uri = UriComponentsBuilder.fromUriString(API_URL)
	            .queryParam("from", from.getCurrencyCode())
	            .queryParam("to", to.getCurrencyCode())
	            .queryParam("amount", amount)
	            .queryParam("apikey", "rlUXvIRpBWJanbwSohjh0x7AA3Pai02M")
	            .build()
	            .toUri();

		 	ConversionResponse response = restTemplate.getForObject(uri, ConversionResponse.class);
		 	System.out.println(response.getResult());
		 	return response.getResult();
	}catch(RestClientException e) {
		throw new RuntimeException("Failed to convert currency", e);
	}
	}

	/**
	 * Classe interna responsável por representar a resposta da conversão de moedas da API.
	 */
	public static class ConversionResponse {
	    
		 
	    /**
	     * Resultado da conversão de moedas.
	     */
		
	    @Schema(description = "Valor convertido.", example = "125.23", requiredMode = RequiredMode.REQUIRED  )
	    @JsonProperty("result")
		private BigDecimal result;
	    
	    /**
	     * Retorna o resultado da conversão de moedas.
	     * 
	     * @return Resultado da conversão de moedas.
	     */

	    @JsonGetter("result")
	    public BigDecimal getResult() {
	        return result;
	    }
	    
	    /**
	     * Define o resultado da conversão de moedas.
	     * 
	     * @param result Resultado da conversão de moedas.
	     */
	    
	    @JsonSetter("result")
	    public void setResult(BigDecimal result) {
	        this.result = result;
	    }
	}

}

