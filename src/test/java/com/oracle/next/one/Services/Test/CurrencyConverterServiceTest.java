package com.oracle.next.one.Services.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.oracle.next.one.Models.CurrencyConverter;
import com.oracle.next.one.Services.CurrencyConverterService;

public class CurrencyConverterServiceTest {

    private CurrencyConverterService currencyConverterService;

    @Mock
    private CurrencyConverter currencyConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        currencyConverterService = new CurrencyConverterService(currencyConverter);
    }

    @Test
    void testConvertCurrency() {
        Currency from = Currency.getInstance("USD");
        Currency to = Currency.getInstance("BRL");
        BigDecimal amount = BigDecimal.valueOf(100);

        BigDecimal conversionRate = BigDecimal.valueOf(5.0);
        when(currencyConverter.convert(from, to, amount)).thenReturn(conversionRate);

        BigDecimal expectedResult = BigDecimal.valueOf(500.0);
        BigDecimal actualResult = currencyConverterService.convertCurrency(from, to, amount);

        assertEquals(expectedResult, actualResult);
    }
}
