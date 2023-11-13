package com.tekcapzule.marketplace.domain.repository;

import com.tekcapzule.core.domain.CrudRepository;
import com.tekcapzule.marketplace.domain.model.Product;

public interface MarketplaceDynamoRepository extends CrudRepository<Product, String> {
}
