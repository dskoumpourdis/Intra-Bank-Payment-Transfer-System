package com.example.modernbankplc.intrabankpaymenttransfersystem.service;

import com.example.modernbankplc.intrabankpaymenttransfersystem.base.BaseComponent;
import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation class of the BaseService interface that can be extended by other classes to obtain the following
 * methods:
 * create()
 * get()
 * findAll()
 */
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
public abstract class BaseServiceImpl<T extends BaseEntity> extends BaseComponent implements BaseService<T, Long> {
	// Returns a repository of type T
	public abstract JpaRepository<T, Long> getRepository();

	// Saves in the database an object of type T
	@Override
	public T create(final T item) {
		T savedItem = getRepository().save(item);
		logger.debug("Created item with id:{}.", savedItem.getId());
		return savedItem;
	}

	// Retrieves from the database an object of type T by id
	@Override
	public T get(final Long id) {
		T item = getRepository().findById(id).orElseThrow();
		logger.trace("Item found matching id:{}.", id);
		return item;
	}

	// Retrieves all objects from the database of type T
	@Override
	public List<T> findAll() {
		List<T> itemsFound = getRepository().findAll();
		logger.trace("Returned {} items.", itemsFound.size());
		return itemsFound;
	}

}
