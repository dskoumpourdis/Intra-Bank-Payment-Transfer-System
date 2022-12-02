package com.example.modernbankplc.intrabankpaymenttransfersystem.base;

import java.util.List;

/**
 * Handles mapping from D to R and vice versa.
 * Works both with single objects and Lists.
 *
 * @param <D> Domain class
 * @param <R> Resource class (DTO)
 */

public interface BaseMapper<D, R> {
    R toResource(D domain);

    List<R> toResources(List<D> domains);

    D toDomain(R resource);

    List<D> toDomains(List<R> resources);
}
