package com.oracle.next.one.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.oracle.next.one.Repositories.TransactionRepository;
import com.oracle.next.one.models.Transaction;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Transaction Service
 */
@Transactional
@Service
@Tag(name = "Transaction", description = "Operations related to transactions")
public class TransactionService {
	
	private final TransactionRepository transactionRepository;
	 
	/**
	 * Constructor with TransactionRepository
	 *
	 * @param transactionRepository Transaction repository
	 */
	public TransactionService(TransactionRepository transactionRepository) {
	    this.transactionRepository = transactionRepository;
	}

	/**
	 * Find all transactions
	 *
	 * @return List of transactions
	 */
	@Operation(summary = "Find all transactions", description = "Returns a list of all transactions")
	@ApiResponse(responseCode = "200", description = "List of transactions returned successfully", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Transaction.class)) })
	public List<Transaction> findAll() {
	    return transactionRepository.findAll();
	}
	
	/**
	 * Find a transaction by ID
	 *
	 * @param id Transaction ID
	 * @return Object of type Transaction
	 * @throws ResponseStatusException if transaction not found
	 */
	@Operation(summary = "Find a transaction by ID", description = "Returns a transaction based on the given ID")
	@ApiResponse(responseCode = "200", description = "Transaction found", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Transaction.class)) })
	@ApiResponse(responseCode = "404", description = "Transaction not found")
	
	public Transaction findById(@PathVariable Long id) {
	    Optional<Transaction> transaction = transactionRepository.findById(id);
	    if (transaction.isPresent()) {
	        return transaction.get();
	    }
	    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found");
	}

	/**
	 * Update a transaction
	 *
	 * @param id Transaction ID
	 * @param transaction Transaction object
	 * @return Updated transaction object
	 * @throws ResponseStatusException if transaction not found
	 */
	@Operation(summary = "Update a transaction", description = "Updates a transaction based on the given ID")
	@ApiResponse(responseCode = "200", description = "Transaction updated successfully", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Transaction.class)) })
	@ApiResponse(responseCode = "404", description = "Transaction not found")
	
	public Transaction update(@PathVariable Long id, Transaction transaction) {
	   
		Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
	    if (optionalTransaction.isPresent()) {
	       
	    	Transaction existingTransaction = optionalTransaction.get();
	        existingTransaction.setFromCurrency(transaction.getFromCurrency());
	        existingTransaction.setToCurrency(transaction.getToCurrency());
	        existingTransaction.setAmount(transaction.getAmount());
	        existingTransaction.setConvertedAmount(transaction.getConvertedAmount());
	        existingTransaction.setDataTransaction(transaction.getDataTransaction());
	        transactionRepository.save(existingTransaction);
	        return existingTransaction;
	    }
	    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found");
	}

	        /**
	         * Deleta uma transação por ID
	         *
	         * @param id Transaction ID
	         * @return Messagem com objeto Transaction deletado com ID
	         * @throws ResponseStatusException se a transação não for encontrada
	         */
	        
		@Operation(summary = "Deleta uma transação por ID", description = "Deleta uma transação por ID")
			@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Transação deletada"),
	        @ApiResponse(responseCode = "404", description = "Transação não encontrada")
	})
		
		public ResponseEntity<String> deleteById(
	        @Parameter(description = "ID da transação", example = "1", required = true)
	        @PathVariable Long id) {

	    Optional<Transaction> transaction = transactionRepository.findById(id);
	    if (transaction.isPresent()) {
	        transactionRepository.deleteById(id);
	        return ResponseEntity.ok("Transação com ID " + id + " deletada com sucesso");
	    }
	    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transação não encontrada");
	}
		/**
		 * Salva uma nova transação no banco de dados.
		 *
		 * @param transaction Objeto Transaction a ser salvo.
		 * @return Objeto Transaction salvo.
		 */
		 
		@Operation(summary = "Salvar uma nova transação", description = "Salva uma nova transação no banco de dados.")
		@ApiResponses(value = {
		        @ApiResponse(responseCode = "201", description = "Transação salva com sucesso."),
		        @ApiResponse(responseCode = "400", description = "Requisição inválida.")
		})

		public Transaction save( Transaction transaction) {
			return transactionRepository.save(transaction);
		}
		
		public long countTransactions() {
	        return transactionRepository.count();
	    }
				
}

