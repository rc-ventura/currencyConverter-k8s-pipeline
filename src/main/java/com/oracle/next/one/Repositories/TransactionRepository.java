package com.oracle.next.one.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oracle.next.one.models.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository< Transaction,Long> {

//	 /**
//     * Busca todas as transações realizadas por um usuário específico.
//     * 
//     * @param userId ID do usuário que realizou as transações.
//     * @return Lista de transações realizadas pelo usuário.
//     */
//    List<Transaction> findByUserId(Long userId);
//    
//    /**
//     * Busca todas as transações realizadas em uma data específica.
//     * 
//     * @param date Data em que as transações foram realizadas.
//     * @return Lista de transações realizadas na data especificada.
//     */
//    List< Transaction> findByDate(LocalDate date);
//    
//    /**
//     * Busca todas as transações realizadas por um usuário específico em uma data específica.
//     * 
//     * @param userId ID do usuário que realizou as transações.
//     * @param date Data em que as transações foram realizadas.
//     * @return Lista de transações realizadas pelo usuário na data especificada.
//     */
//    List< Transaction> findByUserIdAndDate(Long userId, LocalDate date);

}






