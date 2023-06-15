package com.tekcapsule.marketplace.domain.service;

import com.tekcapsule.marketplace.domain.command.CreateCommand;
import com.tekcapsule.marketplace.domain.command.DisableCommand;
import com.tekcapsule.marketplace.domain.command.UpdateCommand;
import com.tekcapsule.marketplace.domain.model.Product;
import com.tekcapsule.marketplace.domain.repository.MarketplaceDynamoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MarketplaceServiceImpl implements MarketplaceService {
    private MarketplaceDynamoRepository marketplaceDynamoRepository;

    @Autowired
    public MarketplaceServiceImpl(MarketplaceDynamoRepository marketplaceDynamoRepository) {
        this.marketplaceDynamoRepository = marketplaceDynamoRepository;
    }

    @Override
    public void create(CreateCommand createCommand) {

        log.info(String.format("Entering create product service - Product Code :%s", createCommand.getCode()));

        Product product = Product.builder()
                .code(createCommand.getCode())
                .title(createCommand.getTitle())
                .summary(createCommand.getSummary())
                .description(createCommand.getDescription())
                .imageUrl(createCommand.getImageUrl())
                .categories(createCommand.getCategories())
                .status("ACTIVE")
                .build();

        product.setAddedOn(createCommand.getExecOn());
        product.setUpdatedOn(createCommand.getExecOn());
        product.setAddedBy(createCommand.getExecBy().getUserId());

        marketplaceDynamoRepository.save(product);
    }

    @Override
    public void update(UpdateCommand updateCommand) {

        log.info(String.format("Entering update product service - Product Code:%s", updateCommand.getCode()));

        Product product = marketplaceDynamoRepository.findBy(updateCommand.getCode());
        if (product != null) {
            product.setTitle(updateCommand.getTitle());
            product.setStatus("ACTIVE");
            product.setSummary(updateCommand.getSummary());
            product.setCategories(updateCommand.getCategories());
            product.setImageUrl(updateCommand.getImageUrl());
            product.setDescription(updateCommand.getDescription());
            product.setUpdatedOn(updateCommand.getExecOn());
            product.setUpdatedBy(updateCommand.getExecBy().getUserId());
            marketplaceDynamoRepository.save(product);
        }
    }

    @Override
    public void disable(DisableCommand disableCommand) {

        log.info(String.format("Entering disable product service - Product Code:%s", disableCommand.getCode()));

        marketplaceDynamoRepository.findBy(disableCommand.getCode());
        Product product = marketplaceDynamoRepository.findBy(disableCommand.getCode());
        if (product != null) {
            product.setStatus("INACTIVE");
            product.setUpdatedOn(disableCommand.getExecOn());
            product.setUpdatedBy(disableCommand.getExecBy().getUserId());
            marketplaceDynamoRepository.save(product);
        }
    }

    @Override
    public List<Product> findAll() {

        log.info("Entering findAll Product service");

        return marketplaceDynamoRepository.findAll();
    }

    @Override
    public Product findBy(String code) {

        log.info(String.format("Entering findBy Product service - Product code:%s", code));

        return marketplaceDynamoRepository.findBy(code);
    }


}
