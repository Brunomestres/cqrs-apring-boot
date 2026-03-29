package br.com.cqrstraining.domain.message;

import br.com.cqrstraining.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewMessage {
    private Integer id;
    private String userName;
    private String description;
    private Integer rating;
    private Product product;
}
