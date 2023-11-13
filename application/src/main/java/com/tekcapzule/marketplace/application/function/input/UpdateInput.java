package com.tekcapzule.marketplace.application.function.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapzule.marketplace.domain.model.PrizingModel;
import com.tekcapzule.marketplace.domain.model.ProductType;
import com.tekcapzule.marketplace.domain.model.UserGuide;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class UpdateInput {
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
