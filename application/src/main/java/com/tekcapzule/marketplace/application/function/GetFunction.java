package com.tekcapzule.marketplace.application.function;

import com.tekcapzule.core.utils.HeaderUtil;
import com.tekcapzule.core.utils.Outcome;
import com.tekcapzule.core.utils.Stage;
import com.tekcapzule.marketplace.application.config.AppConfig;
import com.tekcapzule.marketplace.application.function.input.GetInput;
import com.tekcapzule.marketplace.domain.model.Product;
import com.tekcapzule.marketplace.domain.service.MarketplaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class GetFunction implements Function<Message<GetInput>, Message<Product>> {

    private final MarketplaceService marketplaceService;

    private final AppConfig appConfig;

    public GetFunction(final MarketplaceService marketplaceService, final AppConfig appConfig) {
        this.marketplaceService = marketplaceService;
        this.appConfig = appConfig;
    }


    @Override
    public Message<Product> apply(Message<GetInput> getInputMessage) {

        Map<String, Object> responseHeaders = new HashMap<>();
        Product product = new Product();

        String stage = appConfig.getStage().toUpperCase();

        try {
            GetInput getInput = getInputMessage.getPayload();
            log.info(String.format("Entering get product Function -Product Code:%s", getInput.getCode()));
            product = marketplaceService.findBy(getInput.getCode());
            if (product == null) {
                responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.NOT_FOUND);
                product = Product.builder().build();
            } else {
                responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.SUCCESS);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
        }
        return new GenericMessage<>(product, responseHeaders);
    }
}