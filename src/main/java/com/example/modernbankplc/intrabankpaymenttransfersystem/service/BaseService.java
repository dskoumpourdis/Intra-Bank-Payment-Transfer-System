package com.example.modernbankplc.intrabankpaymenttransfersystem.service;

import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.BaseEntity;

import java.util.List;

public interface BaseService<T extends BaseEntity, ID> {
	T create(final T item);

	T get(ID id);

	List<T> findAll();

}
