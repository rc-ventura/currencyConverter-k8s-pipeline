package com.oracle.next.one.Models.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import com.oracle.next.one.Models.CurrencyConverter;

class CurrencyConverterTest {

    @Mock
    private RestTemplate restTemplate;

    private CurrencyConverter currencyConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        currencyConverter = new CurrencyConverter(new RestTemplateBuilder().build());
    }

    @Test
    void testConvert() {
        Currency from = Currency.getInstance("BRL");
        Currency to = Currency.getInstance("USD");
        BigDecimal amount = BigDecimal.valueOf(500);
        BigDecimal expected = BigDecimal.valueOf(96.1335);

        String apiUrl = "https://api.apilayer.com/exchangerates_data/convert?from=USD&to=BRL&amount=10&apikey=rlUXvIRpBWJanbwSohjh0x7AA3Pai02M";
        CurrencyConverter.ConversionResponse response = new CurrencyConverter.ConversionResponse();
        response.setResult(expected);
        when(restTemplate.getForObject(apiUrl, CurrencyConverter.ConversionResponse.class)).thenReturn(response);

        BigDecimal actual = currencyConverter.convert(from, to, amount);
        assertEquals(expected, actual);
    }

}
