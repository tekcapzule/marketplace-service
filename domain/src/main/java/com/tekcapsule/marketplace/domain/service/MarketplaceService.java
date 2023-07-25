package com.tekcapsule.marketplace.domain.service;

import com.tekcapsule.marketplace.domain.command.ApproveCommand;
import com.tekcapsule.marketplace.domain.command.CreateCommand;
import com.tekcapsule.marketplace.domain.command.DisableCommand;
import com.tekcapsule.marketplace.domain.command.UpdateCommand;
import com.tekcapsule.marketplace.domain.model.Product;

import java.util.List;


public interface MarketplaceService {

    void create(CreateCommand createCommand);

    void update(UpdateCommand updateCommand);

    void disable(DisableCommand disableCommand);

    List<Product> findAll();

    Product findBy(String code);
    void approve(ApproveCommand approveCommand);
}
