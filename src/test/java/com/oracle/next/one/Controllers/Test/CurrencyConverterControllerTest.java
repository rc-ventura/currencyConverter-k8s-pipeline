package com.oracle.next.one.Controllers.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.oracle.next.one.Models.CurrencyConverter;


@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyConverterControllerTest {

    @MockBean
    private CurrencyConverter currencyConverter;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCurrencyConversion() throws Exception {
        // mock para a conversão de moeda
        Mockito.when(currencyConverter.convert(Currency.getInstance("USD"), Currency.getInstance("BRL"), new BigDecimal(100)))
                .thenReturn(new BigDecimal(500));

        // requisição para o endpoint de conversão de moeda
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/convert/USD/BRL")
                .param("from", "USD")
                .param("to", "BRL")
                .param("amount", "100")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        // verificação do status da resposta
        int status = result.getResponse().getStatus();
        assertEquals(200, status);

        // verificação do resultado da conversão de moeda
        String content = result.getResponse().getContentAsString();
        BigDecimal convertedValue = new BigDecimal(content);
        assertNotNull(convertedValue);
        assertTrue(convertedValue.compareTo(BigDecimal.ZERO) > 0);
    }
}