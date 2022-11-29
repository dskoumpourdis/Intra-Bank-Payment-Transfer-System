package com.example.modernbankplc.intrabankpaymenttransfersystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Currency;

@Entity
@Setter
@Getter
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Transaction extends BaseEntity {

	@NotNull
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;

	@NotNull
	private BigDecimal amount;

	@NotNull
	private Currency currency;

	@ManyToOne
	@JoinColumn(name = "fk_statement")
	@ToString.Exclude
	@JsonIgnore
	private Statement statement;
}
