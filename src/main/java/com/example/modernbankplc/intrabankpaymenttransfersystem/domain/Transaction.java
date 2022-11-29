package com.example.modernbankplc.intrabankpaymenttransfersystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.security.Timestamp;
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
	private Long accountId;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;

	@NotNull
	private BigDecimal amount;

	@NotNull
	private Currency currency;

	@NotNull
	@CreationTimestamp
	private Timestamp timestamp;

	@ManyToOne
	@ToString.Exclude
	@JsonIgnore
	private Statement statement;
}
