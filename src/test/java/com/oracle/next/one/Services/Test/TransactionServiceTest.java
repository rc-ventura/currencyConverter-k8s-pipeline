package com.oracle.next.one.Services.Test;

	

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.oracle.next.one.Repositories.TransactionRepository;
import com.oracle.next.one.Services.TransactionService;
import com.oracle.next.one.models.Transaction;

import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;

	/**
	 * Classe de teste para TransactionService.
	 */
@Epic("Currency Conversion Tests")
@Feature("Transaction Service")
@Story("Transaction Management")
@Transactional
public class TransactionServiceTest {

	    @Mock
	    private TransactionRepository transactionRepository;

	    @InjectMocks
	    private TransactionService transactionService;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    /**
	     * Testa o método findAll() de TransactionService.
	     */
	    @Test
	    @AllureId("1012")
	    @Severity(SeverityLevel.NORMAL)
	    @Description("Teste do método findAll() de TransactionService")
	    @Story("Get all transactions")
	    
	    public void testFindAll() {
	        // Cria lista de transações simulando dados do banco de dados.
	        List<Transaction> transactions = new ArrayList<>();
	        transactions.add(new Transaction( "USD", "BRL", new BigDecimal(100.0), new BigDecimal(520.0), LocalDateTime.now()));
	        transactions.add(new Transaction("EUR", "BRL", new BigDecimal(200.0), new BigDecimal(1240.0), LocalDateTime.now()));

	        // Configura comportamento do mock do repositório.
	        when(transactionRepository.findAll()).thenReturn(transactions);

	        // Executa método a ser testado.
	        List<Transaction> result = transactionService.findAll();

	        // Verifica se o resultado foi o esperado.
	        assertThat(result).isEqualTo(transactions);

	        // Verifica se o método do repositório foi chamado uma vez.
	        verify(transactionRepository, times(1)).findAll();
	    }

	    /**
	     * Testa o método findById() de TransactionService com um ID existente.
	     */
	    @Test
	    @AllureId("1013")
	    @Severity(SeverityLevel.CRITICAL)
	    @Description("Teste do método findById() de TransactionService com um ID existente")
	    @Story("Get transaction by ID")
	    @Issue("NEXT-123")
	    @TmsLink("NEXT-456")
	    
	    public void testFindByIdExistingId() {
	        // Cria transação simulando dado do banco de dados.
	        Transaction transaction = new Transaction("USD", "BRL", new BigDecimal(100.0), new BigDecimal(520.0), LocalDateTime.now());

	        // Configura comportamento do mock do repositório.
	        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));

	        // Executa método a ser testado.
	        Transaction result = transactionService.findById(1L);

	        // Verifica se o resultado foi o esperado.
	        assertThat(result).isEqualTo(transaction);

	        // Verifica se o método do repositório foi chamado uma vez.
	        verify(transactionRepository, times(1)).findById(1L);
	    }

	    /**
	     * Testa o método findById() de TransactionService com um ID inexistente.
	     */
	    @Test
	    public void testFindByIdNonexistentId() {
	        // Configura comportamento do mock do repositório.
	        when(transactionRepository.findById(1L)).thenReturn(Optional.empty());

	        // Executa método a ser testado e verifica se uma exceção foi lançada.
	        assertThatThrownBy(() -> transactionService.findById(1L))
	                .isInstanceOf(ResponseStatusException.class)
	                .hasMessageContaining("Transaction not found")
	                .extracting("status")
	                .isEqualTo(HttpStatus.NOT_FOUND);

	        // Verifica se o método do repositório foi chamado uma vez.
	        verify(transactionRepository, times(1)).findById(1L);
	    }



}
