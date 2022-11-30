package com.example.modernbankplc.intrabankpaymenttransfersystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

	@OneToMany(mappedBy = "statement", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Builder.Default
	private Set<Transaction> transactions = new HashSet<>();

	@NotNull
	@OneToOne
	@ToString.Exclude
	@JsonIgnore
	private Account account;
}
