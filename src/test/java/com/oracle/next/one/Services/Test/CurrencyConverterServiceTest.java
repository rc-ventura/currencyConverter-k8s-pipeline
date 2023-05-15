package com.oracle.next.one.Services.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.oracle.next.one.Services.CurrencyConverterService;
import com.oracle.next.one.models.CurrencyConverter;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;

@Epic("Currency Converter")
@Feature("Currency Converter Service")
@DisplayName("Currency Converter Service Tests")
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
    @Owner("John Doe")
    @Feature("Currency Conversion")
    @Story("Convert USD to BRL")
    @DisplayName("Test Currency Conversion")
    @Issue("JIRA-123")
    @Link(name = "GitHub", type = "url", url = "https://github.com/my/repository")
    @Description("Test the conversion of USD to BRL")
    
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
