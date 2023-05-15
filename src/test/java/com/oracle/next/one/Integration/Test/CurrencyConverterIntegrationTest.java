package com.oracle.next.one.Integration.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.next.one.Repositories.TransactionRepository;
import com.oracle.next.one.Services.CurrencyConverterService;
import com.oracle.next.one.models.Transaction;

import groovy.lang.Category;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@ActiveProfiles("dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Epic("Conversão de Moeda")
@Feature("Testes de Integração")
@Category(CurrencyConverterIntegrationTest.class)
public class CurrencyConverterIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private CurrencyConverterService currencyConverterService;

	/**
	 * Testa a conversão de moedas e a persistência de transações no banco de dados.
	 * Verifica se uma nova transação foi criada e se os dados da transação estão
	 * corretos.
	 */

	@Story("Conversão de moeda com sucesso")
	@Severity(SeverityLevel.CRITICAL)
	@Test

	public void shouldConvertCurrencyAndSaveTransaction() {

		// Define os parâmetros da conversão

		Currency fromCurrency = Currency.getInstance("USD");
		Currency toCurrency = Currency.getInstance("BRL");
		BigDecimal amount = new BigDecimal("100.00");
		String url = "http://localhost:8080/convert/USD/BRL?amount=100";

		// Realiza a conversão
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, fromCurrency, toCurrency,
				amount);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());

		// Obtém o número de transações antes da conversão
		List<Transaction> transactionsBefore = transactionRepository.findAll();
		int transactionsBeforeCount = transactionsBefore.size();

		// Obtém o número de transações após a conversão
		List<Transaction> transactionsAfter = transactionRepository.findAll();
		int transactionsAfterCount = transactionsAfter.size();

		// Verifica se uma nova transação foi criada
		assertEquals(transactionsBeforeCount, transactionsAfterCount);

		// Obtém a última transação adicionada ao banco de dados
		Transaction transaction = transactionsAfter.get(transactionsAfterCount - 1);

		// Parse de String fromCurrency para Currency fromCurrency
		Currency actualFromCurrency = Currency.getInstance(transaction.getFromCurrency());

		// Parse de String toCurrency para Currency toCurrency
		Currency actualToCurrency = Currency.getInstance(transaction.getToCurrency());

		BigDecimal actualAmount = transaction.getAmount();
		String actualAmountConverted = actualAmount.toString();

		String amountConverted = amount.toString();

		// Verifica se os dados da transação estão corretos
		assertEquals(fromCurrency, actualFromCurrency);
		assertEquals(toCurrency, actualToCurrency);
		assertEquals(amountConverted, actualAmountConverted);

		// Verifica se a conversão está correta
		BigDecimal expectedConversion = currencyConverterService.convertCurrency(fromCurrency, toCurrency, amount);

		// Faz a conversão do resultado esperado
		String expectedConversionConverted = expectedConversion.toString();

		BigDecimal actualConversion = new BigDecimal(response.getBody());

		// Faz a conversão do resultado obtido
		String actualConversionConverted = actualConversion.toString();

		// Verifica se os três primeiros caracteres de cada resultado são iguais
		assertEquals(expectedConversionConverted.substring(0, 3), actualConversionConverted.substring(0, 3));

		// Adiciona anotação Allure para gerar informações de passo
		Allure.step("Conversão realizada com sucesso");

		// Adiciona anotação Allure para gerar informações de resultado
		if (expectedConversion.compareTo(actualConversion) == 0) {
			Allure.addAttachment("Resultado da Conversão",
					"As conversões esperada e atual são iguais: " + actualConversionConverted);
		} else {
			Allure.addAttachment("Resultado da Conversão", "A conversão esperada é " + expectedConversionConverted
					+ ", mas a conversão atual é " + actualConversionConverted);
		}

	}

}
