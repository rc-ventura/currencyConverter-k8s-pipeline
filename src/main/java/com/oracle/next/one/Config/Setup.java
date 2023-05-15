package com.oracle.next.one.config;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.oracle.next.one.Repositories.TransactionRepository;
import com.oracle.next.one.models.Transaction;

@Configuration
@Profile("test")
public class Setup implements CommandLineRunner {	
	    
	    @Autowired
	    private TransactionRepository transactionRepository;

	    @Override
	    public void run(String... args) throws Exception {
	        // Cria uma instância da classe Random para gerar valores aleatórios
	        Random random = new Random();
	        
	        // Cria um array de moedas para ser usado na geração das transações
	        String[] currencies = {"USD", "EUR", "GBP", "BRL", "JPY"};
	        
	        // Gera 10 transações aleatórias e as salva no banco de dados
	        for (int i = 0; i < 10; i++) {
	        	Transaction transaction = new Transaction();
	            // Define uma moeda de origem aleatória a partir do array de moedas
	        	transaction.setFromCurrency(currencies[random.nextInt(currencies.length)]);
	            // Define uma moeda de destino aleatória a partir do array de moedas
	        	transaction.setToCurrency(currencies[random.nextInt(currencies.length)]);
	            // Define um valor aleatório entre 1 e 1000 para a quantidade de moeda
	        	transaction.setAmount(BigDecimal.valueOf(random.nextInt(1000) + 1));
	            // Define um valor aleatório entre 1 e 1000 para o valor convertido
	        	transaction.setConvertedAmount(BigDecimal.valueOf(random.nextInt(1000) + 1));
	            // Define uma data aleatória entre 01/01/2020 e 01/01/2023
	        	transaction.setDataTransaction(LocalDateTime.of(
	                    2020 + random.nextInt(3), // ano entre 2020 e 2022
	                    random.nextInt(12) + 1,  // mês entre 1 e 12
	                    random.nextInt(28) + 1,  // dia entre 1 e 28 (considerando todos os meses têm até 28 dias)
	                    random.nextInt(24),      // hora entre 0 e 23
	                    random.nextInt(60)       // minuto entre 0 e 59
	            ));
	            // Salva a transação no banco de dados
	        	transactionRepository.save(transaction);
	        }
	    }
	}



