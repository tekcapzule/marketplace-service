package com.tekcapsule.marketplace.domain.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.tekcapsule.marketplace.domain.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class MarketplaceRepositoryImpl implements MarketplaceDynamoRepository {

    private DynamoDBMapper dynamo;

    @Autowired
    public MarketplaceRepositoryImpl(DynamoDBMapper dynamo) {
        this.dynamo = dynamo;
    }

    @Override
    public List<Product> findAll() {

        return dynamo.scan(Product.class,new DynamoDBScanExpression());
    }

    @Override
    public Product findBy(String code) {
        return dynamo.load(Product.class, code);
    }

    @Override
    public Product save(Product product) {
        dynamo.save(product);
        return product;
    }
}
