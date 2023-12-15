package com.restapiwithmongodb.getproduct.models.entities;


import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//Entity class
@Document(collection = "product")
@Data
@Getter
@Setter
@RequiredArgsConstructor
public class Product {

    @Id
    private ObjectId productId;
    private String productCategory;
    private String productName;
    private Integer price;
    private String description;
    private boolean isAvailable;

    public Product(
            ObjectId productId,
            String productCategory,
            String productName,
            Integer price,
            String description,
            boolean isAvailable
    ) {
        this.productId = productId;
        this.productCategory = productCategory;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.isAvailable = isAvailable;
    }
}
