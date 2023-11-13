package com.tekcapzule.marketplace.domain.service;

import com.tekcapzule.marketplace.domain.command.ApproveCommand;
import com.tekcapzule.marketplace.domain.command.CreateCommand;
import com.tekcapzule.marketplace.domain.command.DisableCommand;
import com.tekcapzule.marketplace.domain.command.UpdateCommand;
import com.tekcapzule.marketplace.domain.model.Product;

import java.util.List;


public interface MarketplaceService {

    void create(CreateCommand createCommand);

    void update(UpdateCommand updateCommand);

    void disable(DisableCommand disableCommand);

    List<Product> findAll();

    Product findBy(String code);
    void approve(ApproveCommand approveCommand);
}
