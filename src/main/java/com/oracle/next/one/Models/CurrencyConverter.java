package com.oracle.next.one.Models;


import java.math.BigDecimal;
import java.net.URI;
import java.util.Currency;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


/**
	 * Classe responsável por fazer a conversão de moedas utilizando a API da Currency Layer.
	 */

@Component
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
	
	/**
	* Constrói um objeto URI a partir da URL base e parâmetros de consulta fornecidos.
	* Os parâmetros de consulta são adicionados à URL base como pares chave-valor.
	*
	* @param API_URL a URL base para a API da Currency Layer
	*
	* @return um objeto URI contendo a URL completa com parâmetros de consulta adicionados
	*/
	
	public BigDecimal convert(Currency from, Currency to, BigDecimal amount) {
	 try {
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
	    private BigDecimal result;

	    public BigDecimal getResult() {
	        return result;
	    }

	    public void setResult(BigDecimal result) {
	        this.result = result;
	    }
	}

}

