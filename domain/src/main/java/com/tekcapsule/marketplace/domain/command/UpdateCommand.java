package com.tekcapsule.marketplace.domain.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.core.domain.Command;
import com.tekcapsule.marketplace.domain.model.PrizingModel;
import com.tekcapsule.marketplace.domain.model.ProductType;
import com.tekcapsule.marketplace.domain.model.UserGuide;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class UpdateCommand extends Command {
    private String code;
    private String title;
    private String summary;
    private String category;
    private ProductType productType;
    private PrizingModel prizingModel;
    private String description;
    private String vendor;
    private List<String> tags;
    private String website;
    private int recommendations;
    private String imageUrl;
    private UserGuide productDemo;
    private List<UserGuide> userGuides;
}
