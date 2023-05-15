package com.oracle.next.one.Services;

import java.math.BigDecimal;
import java.util.Currency;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.oracle.next.one.models.CurrencyConverter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Service
@Transactional
@Tag(name = "CurrencyConverterService", description = "Serviço para realizar a conversão de moedas utilizando a API da Currency Layer")
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
    
    @Operation(summary = "Converte uma moeda em outra", responses = {
        @ApiResponse(responseCode = "200", description = "Conversão realizada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    
    public BigDecimal convertCurrency(
    	@Parameter(description = "Moeda de origem", required = true, schema = @Schema(implementation = Currency.class)) @RequestParam Currency from,
    	@Parameter(description = "Moeda de destino", required = true, schema = @Schema(implementation = Currency.class)) @RequestParam Currency to,
    	@Parameter(description = "Valor a ser convertido", required = true, schema = @Schema(type = "number")) BigDecimal amount) {
        
    	BigDecimal conversionRate = currencyConverter.convert(from, to, amount);
        
        return amount.multiply(conversionRate);
    }

}
