package com.tekcapsule.marketplace.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserGuide {
    private String title;
    private int durationInMinutes;
    private String summary;
    private String description;
    private String videoUrl;
    private String imageUrl;
}