package com.oracle.next.one.models;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Currency;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.oracle.next.one.config.MetricsConfig;

import io.micrometer.core.annotation.Timed;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Classe responsável por fazer a conversão de moedas utilizando a API da
 * Currency Layer.
 */

@Component
@Tag(name = "Currency Converter", description = "Classe para conversão de moedas")
public class CurrencyConverter {

	private RestTemplate restTemplate;
	
	private static final String API_URL = "https://api.apilayer.com/exchangerates_data/convert";

	
	@Autowired
	MetricsConfig metrics;
	
	@Autowired
	PrometheusMeterRegistry prometheusRegistry;
	
	/**
	 * Construtor da classe que recebe um RestTemplateBuilder para criar um
	 * RestTemplate.
	 *
	 * @param build RestTemplateBuilder para criar um RestTemplate.
	 */

	public CurrencyConverter(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public CurrencyConverter(RestTemplate restTemplate) {
		this.restTemplate = new RestTemplate();
	}

	public CurrencyConverter() {
		this.restTemplate = new RestTemplate();

	}

	

	/**
	 * Método responsável por realizar a conversão de moedas utilizando a API da
	 * Currency Layer.
	 *
	 * @param from   Moeda de origem.
	 * @param to     Moeda de destino.
	 * @param amount Valor a ser convertido.
	 * @return Valor convertido.
	 */

	@Operation(summary = "Realiza a conversão de moedas", description = "Método responsável por realizar a conversão de moedas utilizando a API da Currency Layer.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Conversão realizada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Não autorizado"),
			@ApiResponse(responseCode = "403", description = "Proibido"),
			@ApiResponse(responseCode = "404", description = "Não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor") })

	/**
	 * Constrói um objeto URI a partir da URL base e parâmetros de consulta
	 * fornecidos. Os parâmetros de consulta são adicionados à URL base como pares
	 * chave-valor.
	 *
	 * @param API_URL a URL base para a API da Currency Layer
	 *
	 * @return um objeto URI contendo a URL completa com parâmetros de consulta
	 *         adicionados
	 */

	@Timed(value = "latency.timer", description = "Tempo de resposta da API")
	public BigDecimal convert(
			@Parameter(description = "Moeda de origem", required = true, schema = @Schema(type = "string", example = "USD")) Currency from,
			@Parameter(description = "Moeda de destino", required = true, schema = @Schema(type = "string", example = "BRL")) Currency to,
			@Parameter(description = "Valor a ser convertido", required = true, schema = @Schema(type = "number", example = "100.00")) BigDecimal amount) {

		try {

			URI uri = UriComponentsBuilder.fromUriString(API_URL).queryParam("from", from.getCurrencyCode())
					.queryParam("to", to.getCurrencyCode()).queryParam("amount", amount)
					.queryParam("apikey", "03Fm6p9gDKvpobp5mWiZ5PcoBVoAeb3Z").build().toUri();

			// timer criado antes da requisição
			long startTime = System.nanoTime();

			// requisição
			ConversionResponse response = restTemplate.getForObject(uri, ConversionResponse.class);

			// tempo de latência da resposta
			metrics.myTimerLatencyCalls(prometheusRegistry).record(System.nanoTime() - startTime, TimeUnit.NANOSECONDS);

			System.out.println(response.getResult());

			// Incremente o contador de chamadas bem sucedidas
		   // metrics.myCounterSuccessCalls(prometheusRegistry).increment();

			return response.getResult();

		} catch (Exception e) {

			// Incrementa o contador de chamadas com erro
			//metrics.myCounterErrorCalls(prometheusRegistry).increment();

			throw new RuntimeException("Failed to convert currency", e);
		}
	}

	/**
	 * Classe interna responsável por representar a resposta da conversão de moedas
	 * da API.
	 */
	public static class ConversionResponse {

		/**
		 * Resultado da conversão de moedas.
		 */

		@Schema(description = "Valor convertido.", example = "125.23", requiredMode = RequiredMode.REQUIRED)
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
