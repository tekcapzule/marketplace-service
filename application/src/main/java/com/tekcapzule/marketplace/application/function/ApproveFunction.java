package com.tekcapzule.marketplace.application.function;

import com.tekcapzule.core.domain.Origin;
import com.tekcapzule.core.utils.HeaderUtil;
import com.tekcapzule.core.utils.Outcome;
import com.tekcapzule.core.utils.PayloadUtil;
import com.tekcapzule.core.utils.Stage;
import com.tekcapzule.marketplace.application.config.AppConfig;
import com.tekcapzule.marketplace.application.function.input.ApproveProductInput;
import com.tekcapzule.marketplace.application.mapper.InputOutputMapper;
import com.tekcapzule.marketplace.domain.command.ApproveCommand;
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
public class ApproveFunction implements Function<Message<ApproveProductInput>, Message<Void>> {

    private final MarketplaceService marketplaceService;

    private final AppConfig appConfig;

    public ApproveFunction(final MarketplaceService marketplaceService, final AppConfig appConfig) {
        this.marketplaceService = marketplaceService;
        this.appConfig = appConfig;
    }

    @Override
    public Message<Void> apply(Message<ApproveProductInput> approveMarketplaceInputMessage) {
        Map<String, Object> responseHeaders = new HashMap<>();
        Map<String, Object> payload = new HashMap<>();
        String stage = appConfig.getStage().toUpperCase();
        try {
            ApproveProductInput approveProductInput = approveMarketplaceInputMessage.getPayload();
            log.info(String.format("Entering approve product Function -  product Id:%s", approveProductInput.getCode()));
            Origin origin = HeaderUtil.buildOriginFromHeaders(approveMarketplaceInputMessage.getHeaders());
            ApproveCommand approveCommand = InputOutputMapper.buildApproveCommandFromApproveProductInput.apply(approveProductInput, origin);
            marketplaceService.approve(approveCommand);
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.SUCCESS);
            payload = PayloadUtil.composePayload(Outcome.SUCCESS);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
            payload = PayloadUtil.composePayload(Outcome.ERROR);
        }
        return new GenericMessage(payload, responseHeaders);

    }
}