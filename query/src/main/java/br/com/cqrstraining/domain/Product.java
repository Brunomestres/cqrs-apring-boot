package br.com.cqrstraining.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Document(collection = "products")
public class Product {
    @MongoId
    private Integer id;

    private String name;

    private String imageUrl;

    private String description;

    private BigDecimal value;

    private List<Review> reviews = new ArrayList<>();
}
