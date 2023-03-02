package com.oracle.next.one.Controllers.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.oracle.next.one.Models.Transaction;

public class TransactionControllerTest {
	
	// Definindo a URL base da API
	private static final String BASE_URL = "/transactions";

	// Teste para verificar se é possível obter todas as transações
	@Test
	public void getAllTransactionsTest() {
	    // Chamada HTTP GET na URL base da API
	    given()
	        .when()
	            .get(BASE_URL)
	        .then()
	            // Verifica se a resposta tem status 200 OK
	            .statusCode(200)
	            // Verifica se o tamanho do corpo da resposta é maior que zero
	            .body("size()", greaterThan(0));
	}

	// Teste para verificar se é possível obter uma transação pelo ID
	@Test
	public void getTransactionByIdTest() {
	    // Cria uma transação para testar
	    Transaction transaction = new Transaction("BRL", "USD", new BigDecimal("100.00"), new BigDecimal("20.00"), LocalDateTime.now());
	    // Faz uma chamada HTTP POST para criar a transação
	    transaction = given()
	                    .contentType("application/json")
	                    .body(transaction)
	                .when()
	                    .post(BASE_URL)
	                // Verifica se a resposta tem status 201 Created
	                .then()
	                    .statusCode(201)
	                    // Extrai a transação criada do corpo da resposta
	                    .extract().as(Transaction.class);

	    // Faz uma chamada HTTP GET com o ID da transação para buscá-la
	    given()
	        .when()
	            .get(BASE_URL + "/{id}", transaction.getId())
	        .then()
	            // Verifica se a resposta tem status 200 OK
	            .statusCode(200)
	            // Verifica se o ID da transação na resposta é igual ao ID da transação criada
	            .body("id", equalTo(transaction.getId().intValue()))
	            // Verifica se a moeda de origem na resposta é igual à moeda de origem da transação criada
	            .body("fromCurrency", equalTo("BRL"))
	            // Verifica se a moeda de destino na resposta é igual à moeda de destino da transação criada
	            .body("toCurrency", equalTo("USD"))
	            // Verifica se o valor na resposta é igual ao valor da transação criada
	            .body("amount", equalTo(100.00f))
	            // Verifica se o valor convertido na resposta é igual ao valor convertido da transação criada
	            .body("convertedAmount", equalTo(20.00f))
	            // Verifica se a data de transação na resposta não é nula
	            .body("dataTransaction", notNullValue());
	}

	// Teste para verificar se é possível adicionar uma transação
	@Test
	public void addTransactionTest() {
	    // Cria uma nova transação para adicionar
	    Transaction transaction = new Transaction("USD", "BRL", new BigDecimal("200.00"), new BigDecimal("400.00"), LocalDateTime.now());
	    // Faz uma chamada HTTP POST para adicionar a transação
	    given()
	        .contentType("application/json")
	        .body(transaction)
	        .when()
	            .post(BASE_URL)
	        // Verifica se a resposta tem status 201 Created
	        .then()
	            // Verifica se o ID da transação na resposta não é nulo
	            .body("id", notNullValue())
	            // Verifica se a moeda de origem na resposta é igual à moeda de origem da transação criada
	            .body("fromCurrency", equalTo("USD"));
	            // Verifica se a moeda de destino na resposta é igual à moeda de destino

	}
	 // Teste para verificar se é possível excluir uma transação
	    @Test
	    public void deleteTransactionTest() {
	        // Cria uma transação para testar
	        Transaction transaction = new Transaction("BRL", "USD", new BigDecimal("100.00"), new BigDecimal("20.00"), LocalDateTime.now());
	        // Faz uma chamada HTTP POST para criar a transação
	        transaction = given()
	                        .contentType("application/json")
	                        .body(transaction)
	                    .when()
	                        .post(BASE_URL)
	                    // Verifica se a resposta tem status 201 Created
	                        .then()
	                        .statusCode(201)
	                        // Extrai a transação criada do corpo da resposta
	                        .extract().as(Transaction.class);

	        // Faz uma chamada HTTP DELETE com o ID da transação para excluí-la
	        given()
	            .when()
	                .delete(BASE_URL + "/{id}", transaction.getId())
	            .then()
	                // Verifica se a resposta tem status 204 No Content
	                .statusCode(204);

	        // Faz uma chamada HTTP GET com o ID da transação excluída para verificar se ela foi removida
	        given()
	            .when()
	                .get(BASE_URL + "/{id}", transaction.getId())
	            .then()
	                // Verifica se a resposta tem status 404 Not Found
	                .statusCode(404);
	    }


}

