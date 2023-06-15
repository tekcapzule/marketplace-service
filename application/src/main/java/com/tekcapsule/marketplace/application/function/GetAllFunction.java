package com.tekcapsule.marketplace.application.function;

import com.tekcapsule.core.domain.EmptyFunctionInput;
import com.tekcapsule.core.utils.HeaderUtil;
import com.tekcapsule.core.utils.Outcome;
import com.tekcapsule.core.utils.Stage;
import com.tekcapsule.marketplace.application.config.AppConfig;
import com.tekcapsule.marketplace.domain.model.Product;
import com.tekcapsule.marketplace.domain.service.MarketplaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Component
@Slf4j
public class GetAllFunction implements Function<Message<EmptyFunctionInput>, Message<List<Product>>> {

    private final MarketplaceService marketplaceService;

    private final AppConfig appConfig;

    public GetAllFunction(final MarketplaceService marketplaceService, final AppConfig appConfig) {
        this.marketplaceService = marketplaceService;
        this.appConfig = appConfig;
    }


    @Override
    public Message<List<Product>> apply(Message<EmptyFunctionInput> getAllInputMessage) {

        Map<String, Object> responseHeaders = new HashMap<>();
        List<Product> products = new ArrayList<>();
        String stage = appConfig.getStage().toUpperCase();
        try {
            log.info("Entering get all products Function");
            products = marketplaceService.findAll();
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.SUCCESS);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
        }
        return new GenericMessage<>(products, responseHeaders);
    }
}