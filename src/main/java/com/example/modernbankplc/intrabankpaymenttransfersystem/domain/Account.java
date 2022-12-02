package com.example.modernbankplc.intrabankpaymenttransfersystem.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * Domain class that represents an account.
 */
@Entity
@Setter
@Getter
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity {

	@NotNull
	private Long accountNum;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	private Balance	balance;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	private Statement statement;

}
