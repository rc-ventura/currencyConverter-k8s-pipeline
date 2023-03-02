package com.oracle.next.one.Models;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Classe que representa uma transação de conversão de moeda.
 * A transação é registrada no banco de dados para fins de histórico.
 */
@Entity
@Table(name = "historico_transacoes")
public class  Transaction implements Serializable {
	 private static final long serialVersionUID = 1L;
	
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "moeda_origem")
	    private String fromCurrency;

	    @Column(name = "moeda_destino")
	    private String toCurrency;

	    @Column(name = "valor_origem") 	
	    private BigDecimal amount;

	    @Column(name = "valor_destino")
	    private BigDecimal convertedAmount;

	    @Column(name = "data_transacao")
	    private LocalDateTime dataTransaction;

	    /**
	     * Construtor vazio necessário para o JPA.
	     */
	    public  Transaction() {}

	    /**
	     * Construtor da classe.
	     * @param fromCurrency a moeda de origem da transação
	     * @param toCurrency a moeda de destino da transação
	     * @param amount o valor original da transação
	     * @param convertedAmount o valor convertido da transação
	     * @param dataTransaction a data e hora da transação
	     */
		public  Transaction(String fromCurrency, String toCurrency, BigDecimal amount, BigDecimal convertedAmount,
				LocalDateTime dataTransaction) {
			this.fromCurrency = fromCurrency;
			this.toCurrency = toCurrency;
			this.amount = amount;
			this.convertedAmount = convertedAmount;
			this.dataTransaction = dataTransaction;
		}

		/**
		 * Retorna o ID da transação.
		 * @return o ID da transação
		 */
		public Long getId() {
			return id;
		}

		/**
		 * Define o ID da transação.
		 * @param id o ID da transação
		 */
		public void setId(Long id) {
			this.id = id;
		}

		/**
		 * Retorna a moeda de origem da transação.
		 * @return a moeda de origem da transação
		 */
		public String getFromCurrency() {
			return fromCurrency;
		}

		/**
		 * Define a moeda de origem da transação.
		 * @param fromCurrency a moeda de origem da transação
		 */
		public void setFromCurrency(String fromCurrency) {
			this.fromCurrency = fromCurrency;
		}

		/**
		 * Retorna a moeda de destino da transação.
		 * @return a moeda de destino da transação
		 */
		public String getToCurrency() {
			return toCurrency;
		}

		/**
		 * Define a moeda de destino da transação.
		 * @param toCurrency a moeda de destino da transação
		 */
		public void setToCurrency(String toCurrency) {
			this.toCurrency = toCurrency;
		}

		/**
		 * Retorna o valor original da transação.
		 * @return o valor original da transação
		 */
		public BigDecimal getAmount() {
			return amount;
		}

		/**
		 * Define o valor original da transação.
		 * @param amount o valor original da transação
		 */
		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}

		/**
		 * Retorna o valor convertido da transação.
		 * @return o valor convertido da transação
		 */
		public BigDecimal getConvertedAmount() {
			return convertedAmount;
		}

		/**
		 * Define o valor convertido da transação.
		 * @param convertedAmount o valor convertido da transação
		 */
		public void setConvertedAmount(BigDecimal convertedAmount) {
			this.convertedAmount = convertedAmount;
		}

		public LocalDateTime getDataTransaction() {
			return dataTransaction;
		}

		public void setDataTransaction(LocalDateTime dataTransaction) {
			this.dataTransaction = dataTransaction;
		}

		
		


}
