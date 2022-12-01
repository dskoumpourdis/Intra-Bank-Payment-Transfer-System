package com.example.modernbankplc.intrabankpaymenttransfersystem.controller;

import com.example.modernbankplc.intrabankpaymenttransfersystem.base.BaseComponent;
import com.example.modernbankplc.intrabankpaymenttransfersystem.base.BaseMapper;
import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.BaseEntity;
import com.example.modernbankplc.intrabankpaymenttransfersystem.service.BaseService;
import com.example.modernbankplc.intrabankpaymenttransfersystem.transfer.ApiResponse;
import com.example.modernbankplc.intrabankpaymenttransfersystem.transfer.resource.BaseResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class BaseController<T extends BaseEntity, R extends BaseResource> extends BaseComponent {
	protected abstract BaseService<T, Long> getBaseService();

	protected abstract BaseMapper<T, R> getMapper();

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<R>> find(@PathVariable("id") final Long id) {
		return ResponseEntity.ok(
				ApiResponse.<R>builder().data(getMapper().toResource(getBaseService().get(id))).build());
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<R>>> findAll() {
		return ResponseEntity.ok(ApiResponse.<List<R>>builder().data(getMapper().toResources(getBaseService().findAll())).build());
	}

	@PostMapping
	public ResponseEntity<ApiResponse<R>> create(@Valid @RequestBody final R resource) {
		//@formatter:off
		return new ResponseEntity<>(
				ApiResponse.<R>builder().data(
						getMapper().toResource(
								getBaseService().create(getMapper().toDomain(resource))
											  )
											 ).build(),
				getNoCacheHeaders(),
				HttpStatus.CREATED);
		//@formatter:on
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") final Long id) {
		getBaseService().deleteById(id);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@Valid @RequestBody final R resource) {
		if (getBaseService().exists(getMapper().toDomain(resource))) {
			getBaseService().delete(getMapper().toDomain(resource));
		}
	}

	protected CacheControl getCacheHeaders(int cacheDuration) {
		// https://www.imperva.com/learn/performance/cache-control/
		// Note: Header "Expires" is ignored in max-age is set.
		// response.addHeader("Cache-Control", "max-age=60, must-revalidate, no-transform");
		return CacheControl.maxAge(cacheDuration, TimeUnit.SECONDS).noTransform().mustRevalidate();
	}

	protected HttpHeaders getNoCacheHeaders() {
		final HttpHeaders headers = new HttpHeaders();
		// HTTP 1.1 cache control header
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		// HTTP 1.0 cache control header
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		return headers;
	}

	protected HttpHeaders getDownloadHeaders(final String filename) {
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=" + filename);
		return headers;
	}
}
