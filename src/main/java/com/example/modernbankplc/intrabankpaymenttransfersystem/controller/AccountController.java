package com.example.modernbankplc.intrabankpaymenttransfersystem.controller;

import com.example.modernbankplc.intrabankpaymenttransfersystem.base.BaseMapper;
import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Account;
import com.example.modernbankplc.intrabankpaymenttransfersystem.mapper.AccountMapper;
import com.example.modernbankplc.intrabankpaymenttransfersystem.service.AccountService;
import com.example.modernbankplc.intrabankpaymenttransfersystem.service.BaseService;
import com.example.modernbankplc.intrabankpaymenttransfersystem.transfer.ApiResponse;
import com.example.modernbankplc.intrabankpaymenttransfersystem.transfer.resource.AccountResource;
import com.example.modernbankplc.intrabankpaymenttransfersystem.transfer.resource.BalanceResource;
import com.example.modernbankplc.intrabankpaymenttransfersystem.transfer.resource.StatementResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController extends BaseController<Account, AccountResource> {

	private final AccountService accountService;
	private final AccountMapper accountMapper;

	@Override
	protected BaseService<Account, Long> getBaseService() {
		return accountService;
	}

	@Override
	protected BaseMapper<Account, AccountResource> getMapper() {
		return accountMapper;
	}

	@GetMapping("/{id}/balance")
	public ResponseEntity<ApiResponse<BalanceResource>> getBalance(@PathVariable("id") final Long id) {
		return ResponseEntity.ok(
				ApiResponse.<BalanceResource>builder().data(getMapper().toResource(getBaseService().get(id)).getBalance()).build());
	}

	@GetMapping("/{id}/statements")
	public ResponseEntity<ApiResponse<StatementResource>> getStatement(@PathVariable("id") final Long id) {
		return ResponseEntity.ok(
				ApiResponse.<StatementResource>builder().data(getMapper().toResource(getBaseService().get(id)).getStatement()).build());
	}
}
