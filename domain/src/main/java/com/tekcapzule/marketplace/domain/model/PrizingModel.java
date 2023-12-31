package com.tekcapzule.marketplace.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
public enum PrizingModel {
    FREE("Free"),
    PAID("Paid"),
    FREEMIUM("Freemium");

    @Getter
    private String value;
}