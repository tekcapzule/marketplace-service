package com.tekcapsule.marketplace.domain.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.core.domain.AggregateRoot;
import com.tekcapsule.core.domain.BaseDomainEntity;
import lombok.*;

import java.util.List;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@DynamoDBTable(tableName = "Product")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseDomainEntity implements AggregateRoot {

    @DynamoDBHashKey(attributeName = "code")
    private String code;
    @DynamoDBAttribute(attributeName = "title")
    private String title;
    @DynamoDBAttribute(attributeName = "category")
    private String category;
    @DynamoDBAttribute(attributeName = "productType")
    @DynamoDBTypeConvertedEnum
    private ProductType productType;
    @DynamoDBAttribute(attributeName = "prizingModel")
    @DynamoDBTypeConvertedEnum
    private PrizingModel prizingModel;
    @DynamoDBAttribute(attributeName = "summary")
    private String summary;
    @DynamoDBAttribute(attributeName = "description")
    private String description;
    @DynamoDBAttribute(attributeName = "vendor")
    private String vendor;
    @DynamoDBAttribute(attributeName = "tags")
    private List<String> tags;
    @DynamoDBAttribute(attributeName = "website")
    private String website;
    @DynamoDBAttribute(attributeName = "recommendations")
    private int recommendations;
    @DynamoDBAttribute(attributeName = "imageUrl")
    private String imageUrl;
    @DynamoDBAttribute(attributeName = "productDemo")
    private UserGuide productDemo;
    @DynamoDBAttribute(attributeName = "userGuides")
    private List<UserGuide> userGuides;
    @DynamoDBAttribute(attributeName = "status")
    @DynamoDBTypeConvertedEnum
    private Status status;
}