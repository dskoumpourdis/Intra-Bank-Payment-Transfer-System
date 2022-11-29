package com.example.modernbankplc.intrabankpaymenttransfersystem.service;

import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.BaseEntity;

import java.util.List;

public interface BaseService<T extends BaseEntity, I> {
	T create(final T item);

	List<T> createAll(final List<T> items);

	void delete(T item);

	void deleteById(I id);

	T get(I id);

	List<T> findAll();

	boolean exists(T item);
}
