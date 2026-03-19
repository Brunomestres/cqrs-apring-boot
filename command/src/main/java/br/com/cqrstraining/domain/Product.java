package br.com.cqrstraining.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private Integer id;
    private String  imageUrl;
    private String name;
    private String description;
    private BigDecimal value;
    private List<Review> reviews = new ArrayList<>();


}
