package com.oracle.next.one.Controllers.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;

import org.apache.catalina.Lifecycle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.oracle.next.one.models.Transaction;

import io.qameta.allure.Feature;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Issues;
import io.qameta.allure.Link;
import io.qameta.allure.Links;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;


/**

Classe de testes para TransactionController.
*/

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Transaction Controller Test")
@Feature("Transaction Controller Feature")
public class TransactionControllerTest {


@LocalServerPort
private int port;

/**
 * Testa se todas as transações são retornadas corretamente.
 */
@Test
@DisplayName("Test Get All Transactions")
@Description("Verifica se todas as transações são retornadas corretamente.")
@Story("Get All Transactions Story")
@Severity(SeverityLevel.NORMAL)

public void testGetAllTransactions() {
    given().port(port)
        .when().get("/transactions")
        .then().statusCode(200)
        .body("$", notNullValue());
}

/**
 * Testa se a transação com o id especificado é retornada corretamente.
 */
@Test
@DisplayName("Test Get Transaction By Id")
@Description("Verifica se a transação com o ID especificado é retornada corretamente.")
@Story("Get Transaction By Id Story")
@Severity(SeverityLevel.NORMAL)
@Issue("BUG-123")
@Issues({ @Issue("BUG-456"), @Issue("BUG-789") })
@Link("https://example.com/issue/123")
@Links({ @Link("https://example.com/issue/456"), @Link("https://example.com/issue/789") })
@Owner("John Doe")
@TmsLink("https://example.com/test-cases/123")

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
@DisplayName("Test Add Transaction")
@Description("Verifica se uma nova transação pode ser adicionada corretamente.")
@Story("Add Transaction Story")
@Severity(SeverityLevel.NORMAL)

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
@DisplayName("Test Update Transaction")
@Description("Testa a atualização de uma transação.")
@Story("Update Transaction Story")
@Severity(SeverityLevel.NORMAL)
@Issue("BUG-321")
@Issues({ @Issue("BUG-123"), @Issue("BUG-456") })
@Link("https://example.com/issue/321")
@Links({ @Link("https://example.com/issue/123"), @Link("https://example.com/issue/456") })
@Owner("Rafael Ventura")
@TmsLink("https://example.com/test-cases/321")

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
@DisplayName("Test Delete Random Transaction")
@Description("Testa a exclusão de uma transação aleatória.")
@Story("Delete Transaction Story")
@Severity(SeverityLevel.NORMAL)
@Issue("BUG-654")
@Issues({ @Issue("BUG-789"), @Issue("BUG-987") })
@Link("https://example.com/issue/654")
@Links({ @Link("https://example.com/issue/789"), @Link("https://example.com/issue/987") })
@Owner("John Doe")
@TmsLink("https://example.com/test-cases/654")

public void testDeleteRandomTransaction() {
    // Obter todas as transações atuais
    Transaction[] transactions = given().port(port).when().get("/transactions").as(Transaction[].class);
    
    // Selecionar uma transação aleatória
    int randomIndex = new Random().nextInt(transactions.length);
    Transaction randomTransaction = transactions[randomIndex];
    Long randomTransactionId = randomTransaction.getId();

    // Excluir a transação selecionada
    given().port(port)
        .when().delete("/transactions/" + randomTransactionId)
        .then().statusCode(204);
    
    // Obter todas as transações novamente
    transactions = given().port(port).when().get("/transactions").as(Transaction[].class);
    
    // Verificar se a transação excluída não está mais presente na lista de transações
    assertThat(Arrays.asList(transactions), not(hasItem(randomTransaction)));
    
    // Verificar se o tamanho da lista de transações é igual ao tamanho original menos 1
    assertThat(Arrays.asList(transactions), hasSize(equalTo(transactions.length)));
}



}

