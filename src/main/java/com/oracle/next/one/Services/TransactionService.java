package com.oracle.next.one.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.oracle.next.one.Models.Transaction;
import com.oracle.next.one.Repositories.TransactionRepository;

@Transactional
@Service
public class TransactionService {
	
	 private final TransactionRepository transactionRepository;
	    
	    public TransactionService(TransactionRepository transactionRepository) {
	        this.transactionRepository = transactionRepository;
	    }
	 

	        public List<Transaction> findAll() {
	         return transactionRepository.findAll();
	        }

	        public Transaction findById(Long id) {
	        	        Optional<Transaction> transaction = transactionRepository.findById(id);
	        	        if (transaction.isPresent()) {
	        	            return transaction.get();
	        	        }
	        	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found");
	        	    }

	        public Transaction update(Long id, Transaction transaction) {
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
	    

	        public String deleteById(Long id) {
	            Optional<Transaction> transaction = transactionRepository.findById(id);
	            if (transaction.isPresent()) {
	                transactionRepository.deleteById(id);
	                return "Transaction with ID " + id + " deleted successfully";
	            }
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found");
	        }


	        public Transaction save(Transaction transaction) {
	            return transactionRepository.save(transaction);
	        }
	    }
