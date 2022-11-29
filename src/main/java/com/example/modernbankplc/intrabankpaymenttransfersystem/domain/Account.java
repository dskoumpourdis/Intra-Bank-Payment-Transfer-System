package com.example.modernbankplc.intrabankpaymenttransfersystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity {

	@NotNull
	private String accountId;

	@NotNull
	@OneToOne
	private Balance	balance;

	@NotNull
	@OneToOne
	private Statement statement;

}
