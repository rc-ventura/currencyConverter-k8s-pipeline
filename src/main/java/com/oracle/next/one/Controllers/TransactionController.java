package com.oracle.next.one.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.next.one.Services.TransactionService;
import com.oracle.next.one.models.Transaction;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/transactions")
@Tag(name = "Transactions", description = "Endpoint responsável por manipulações no banco de dados (CRUD)")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Operation(summary = "Get all transactions", description = "Returns all transactions.")
    
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.findAll();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @Operation(summary = "Get transaction by ID", description = "Returns a single transaction.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved transaction"),
            @ApiResponse(responseCode = "404", description = " The transaction you were trying to reach is not found")
    })
    
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Transaction transaction = transactionService.findById(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }
    
    @Operation(summary = "Add a new transaction", description = "Adds a new transaction.")
    @PostMapping
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
        Transaction savedTransaction = transactionService.save(transaction);
        return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
    }
    
    @Operation(summary = "Update an existing transaction", description = "Updates an existing transaction.")
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        Transaction updatedTransaction = transactionService.update(id, transaction);
        return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);
    }
    
    @Operation(summary = "Delete a transaction by ID", description = "Delete a transaction by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted transaction"),
            @ApiResponse(responseCode = "404", description = "The transaction you were trying to delete is not found")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(
            @Parameter(description = "ID da transação a ser deletada.", required = true)
            @PathVariable Long id) {
        transactionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
