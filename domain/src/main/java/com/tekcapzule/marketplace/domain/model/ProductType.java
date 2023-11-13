package com.tekcapzule.marketplace.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ProductType {
    SOFTWARE("Software"),
    LIBRARY("Library"),
    DATASET("Dataset"),
    LLM("LLM"),
    GADGET("Gadget");

    @Getter
    private String value;
}