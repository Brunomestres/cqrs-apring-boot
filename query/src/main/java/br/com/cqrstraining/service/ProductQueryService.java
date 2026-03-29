package br.com.cqrstraining.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import br.com.cqrstraining.domain.Product;
import br.com.cqrstraining.domain.Review;
import br.com.cqrstraining.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductQueryService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MongoTemplate mongoTemplate;


    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(final Integer id) {
        return productRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public void save(final Product product) {
        final var savedProduct = productRepository.save(product);
        final var totalProducts = productRepository.count();

        log.info(
            "Produto persistido. database={}, collection=products, id={}, totalProducts={}",
            mongoTemplate.getDb().getName(),
            savedProduct.getId(),
            totalProducts
        );
    }

    public void deleteById(final Integer id) {
        productRepository.deleteById(id);
    }

    public void update(final Product product) {
       var existingProduct = findById(product.getId());
       var savedProduct = existingProduct.toBuilder()
            .name(product.getName())
            .description(product.getDescription())
            .imageUrl(product.getImageUrl())
            .value(product.getValue())
            .build();
       productRepository.save(savedProduct);
    }

    public void  addReview(final Integer productId, final Review review) {
        final var product = findById(productId);
        final var reviews = product.getReviews() == null ? new ArrayList<Review>() : product.getReviews();
        reviews.add(review);

        final var savedProduct = product.toBuilder()
            .reviews(reviews)
            .build();

        productRepository.save(savedProduct);
        log.info("Review persistida no produto. database={}, productId={}, totalReviews={}", mongoTemplate.getDb().getName(), productId, reviews.size());
    }
}
