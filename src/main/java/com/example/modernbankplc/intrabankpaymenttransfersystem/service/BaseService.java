package com.example.modernbankplc.intrabankpaymenttransfersystem.service;

import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.BaseEntity;

import java.util.List;

public interface BaseService<T extends BaseEntity, ID> {
	T create(final T item);

	List<T> createAll(final List<T> items);

	void delete(T item);

	void deleteById(ID id);

	T get(ID id);

	List<T> findAll();

	boolean exists(T item);
}
