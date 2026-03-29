package br.com.cqrstraining.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Document(collection = "reviews")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @MongoId
    private Integer id;

    private String userName;

    private String description;

    private Integer rating;

}
