package com.tekcapsule.marketplace.domain.repository;

import com.tekcapsule.core.domain.CrudRepository;
import com.tekcapsule.marketplace.domain.model.Product;

public interface MarketplaceDynamoRepository extends CrudRepository<Product, String> {
}
