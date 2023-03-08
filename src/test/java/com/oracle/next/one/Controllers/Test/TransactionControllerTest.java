package com.oracle.next.one.Controllers.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.oracle.next.one.Models.Transaction;

/**

Classe de testes para TransactionController.
*/

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionControllerTest {


@LocalServerPort
private int port;

/**
 * Testa se todas as transações são retornadas corretamente.
 */
@Test
public void testGetAllTransactions() {
    given().port(port)
        .when().get("/transactions")
        .then().statusCode(200)
               .body("$", hasSize(10));
}

/**
 * Testa se a transação com o id especificado é retornada corretamente.
 */
@Test
public void testGetTransactionById() {
    given().port(port)
        .when().get("/transactions/1")
        .then().statusCode(200)
               .body("id", equalTo(1))
               .body("amount", equalTo(150.0f));
}

/**
 * Testa se uma nova transação pode ser adicionada corretamente.
 */
@Test
public void testAddTransaction() {
	Transaction transaction = new Transaction("USD", "BRL", new BigDecimal(100), new BigDecimal(200), LocalDateTime.now());
	given().port(port)
	.contentType("application/json")
	.body(transaction)
	.when().post("/transactions")
	.then().statusCode(201)
	.body("fromCurrency", equalTo("USD"))
	.body("toCurrency", equalTo("BRL"))
	.body("amount", equalTo(100))
	.body("convertedAmount", equalTo(200))
	.body("dataTransaction", notNullValue());
}

/**
 * Testa se uma transação existente pode ser atualizada corretamente.
 */
@Test
public void testUpdateTransaction() {
    Transaction transaction = new Transaction("USD", "BRL", BigDecimal.valueOf(150.0), BigDecimal.valueOf(750.0), LocalDateTime.now());
    transaction.setId(1L);
    given().port(port)
        .contentType("application/json")
        .body(transaction)
        .when().put("/transactions/1")
        .then().statusCode(200)
               .body("id", equalTo(1))
               .body("fromCurrency", equalTo("USD"))
               .body("toCurrency", equalTo("BRL"))
               .body("amount", equalTo(150.0f))
               .body("convertedAmount", equalTo(750.0f))
               .body("dataTransaction", notNullValue());
}

/**
 * Testa se uma transação pode ser excluída corretamente.
 */
@Test
public void testDeleteTransaction() {
    given().port(port)
        .when().delete("/transactions/2")
        .then().statusCode(204);
    
    // Verifica que a transação com id 2 foi excluída
    List<Transaction> transactions = Arrays.asList(
        given().port(port).when().get("/transactions").as(Transaction[].class)
    );
    assertThat(transactions, hasSize(9));
}


}

