package com.example.modernbankplc.intrabankpaymenttransfersystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor

public class Statement extends BaseEntity {

	@Builder.Default
	@OneToMany(mappedBy = "statement")
	@ToString.Exclude
	private Set<Transaction> transactions = new HashSet<>();

	@NotNull
	@OneToOne
	@ToString.Exclude
	@JsonIgnore
	private Account account;
}
