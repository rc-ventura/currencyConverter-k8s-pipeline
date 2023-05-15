package com.oracle.next.one.Models.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import com.oracle.next.one.models.CurrencyConverter;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;

/**
 * Classe de testes para a classe CurrencyConverter.
 */
@Epic("Currency Conversion")
@Feature("Conversion Rates")
@Story("Test currency conversion for different currencies")

class CurrencyConverterTest {

    /**
     * Mock do RestTemplate usado na conversão de moeda.
     */
    @Mock
    private RestTemplate restTemplate;

    /**
     * Instância do CurrencyConverter para ser testada.
     */
    private CurrencyConverter currencyConverter;

    /**
     * Configuração dos mocks e instâncias antes de cada teste.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        currencyConverter = new CurrencyConverter(new RestTemplateBuilder().build());
    }

    /**
     * Teste para a conversão de moeda.
     * Verifica se o resultado é um valor não nulo e positivo.
     */
    @Test
    @Description("Test currency conversion for a given amount from one currency to another")
    @Link(name = "API documentation", url = "https://api.apilayer.com/exchangerates_data/convert")
    @TmsLink("TC001")
    
    void testConvert() {
        Currency from = Currency.getInstance("BRL");
        Currency to = Currency.getInstance("USD");
        BigDecimal amount = BigDecimal.valueOf(500);

        String apiUrl = "https://api.apilayer.com/exchangerates_data/convert?from=USD&to=BRL&amount=10&apikey=rlUXvIRpBWJanbwSohjh0x7AA3Pai02M";
        CurrencyConverter.ConversionResponse response = new CurrencyConverter.ConversionResponse();
        response.setResult(BigDecimal.valueOf(0.5)); // valor arbitrário para simular a conversão
        when(restTemplate.getForObject(apiUrl, CurrencyConverter.ConversionResponse.class)).thenReturn(response);

        BigDecimal actual = currencyConverter.convert(from, to, amount);
        assertTrue(actual != null && actual.compareTo(BigDecimal.ZERO) > 0);
    }

}
