package com.tekcapsule.marketplace.domain.service;

import com.tekcapsule.marketplace.domain.command.CreateCommand;
import com.tekcapsule.marketplace.domain.command.DisableCommand;
import com.tekcapsule.marketplace.domain.command.UpdateCommand;
import com.tekcapsule.marketplace.domain.model.Product;
import com.tekcapsule.marketplace.domain.model.Status;
import com.tekcapsule.marketplace.domain.repository.MarketplaceDynamoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

        log.info(String.format("Entering create product service - Product Name :%s", createCommand.getTitle()));
        String code= UUID.randomUUID().toString();
        Product product = Product.builder()
                .code(code)
                .title(createCommand.getTitle())
                .summary(createCommand.getSummary())
                .description(createCommand.getDescription())
                .category(createCommand.getCategory())
                .productType(createCommand.getProductType())
                .prizingModel(createCommand.getPrizingModel())
                .description(createCommand.getDescription())
                .vendor(createCommand.getVendor())
                .tags(createCommand.getTags())
                .website(createCommand.getWebsite())
                .imageUrl(createCommand.getImageUrl())
                .productDemo(createCommand.getProductDemo())
                .userGuides(createCommand.getUserGuides())
                .recommendations(0)
                .status(Status.ACTIVE)
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
            product.setSummary(updateCommand.getSummary());
            product.setCategory(updateCommand.getCategory());
            product.setProductType(updateCommand.getProductType());
            product.setPrizingModel(updateCommand.getPrizingModel());
            product.setImageUrl(updateCommand.getImageUrl());
            product.setDescription(updateCommand.getDescription());
            product.setVendor(updateCommand.getVendor());
            product.setTags(updateCommand.getTags());
            product.setWebsite(updateCommand.getWebsite());
            product.setRecommendations(updateCommand.getRecommendations());
            product.setProductDemo(updateCommand.getProductDemo());
            product.setUserGuides(updateCommand.getUserGuides());
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
            product.setStatus(Status.INACTIVE);
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
