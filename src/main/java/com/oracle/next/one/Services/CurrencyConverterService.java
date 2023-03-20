package com.oracle.next.one.Services;

import java.math.BigDecimal;
import java.util.Currency;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.next.one.Models.CurrencyConverter;

@Service
@Transactional
public class CurrencyConverterService {
	
	private final CurrencyConverter currencyConverter;

    public CurrencyConverterService(CurrencyConverter currencyConverter) {
        this.currencyConverter = currencyConverter;
    }
    
    

    /**
     * Método responsável por realizar a conversão de moedas utilizando a API da Currency Layer e salvar no banco de dados.
     * 
     *
     * @param from   Moeda de origem.
     * @param to     Moeda de destino.
     * @param amount Valor a ser convertido.
     * @return Valor convertido.
     */
    public BigDecimal convertCurrency(Currency from, Currency to, BigDecimal amount) {
        BigDecimal conversionRate = currencyConverter.convert(from, to, amount);
        
       
        
        return amount.multiply(conversionRate);
    }

}
