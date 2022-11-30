package com.example.modernbankplc.intrabankpaymenttransfersystem.mapper;

import com.example.modernbankplc.intrabankpaymenttransfersystem.base.BaseMapper;
import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Account;
import com.example.modernbankplc.intrabankpaymenttransfersystem.transfer.resource.AccountResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper extends BaseMapper<Account, AccountResource> {
}
