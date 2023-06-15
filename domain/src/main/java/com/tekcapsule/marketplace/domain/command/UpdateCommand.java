package com.tekcapsule.marketplace.domain.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.core.domain.Command;
import com.tekcapsule.marketplace.domain.model.Category;
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
    private List<Category> categories;
    private String description;
    private String imageUrl;
}
