package br.com.cqrstraining.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cqrstraining.domain.Product;
import br.com.cqrstraining.domain.Review;
import br.com.cqrstraining.domain.mapper.ReviewMapper;
import br.com.cqrstraining.domain.message.Message;
import br.com.cqrstraining.domain.message.ReviewMessage;
import br.com.cqrstraining.service.ProductQueryService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProducerConsumer {

    @Autowired
    private ProductQueryService productQueryService;
    @Autowired
    private ReviewMapper reviewMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "tp-query")
    public void receiver(final Message<?> message) {
        log.info("Mensagem recebida no topico tp-query. Evento: {}", message.getEvent());

        switch (message.getEvent()) {
            case CreateProduct -> {
                final var product = objectMapper.convertValue(message.getPayload(), Product.class);
                productQueryService.save(product);
                log.info("Produto salvo no Mongo. Id: {}", product.getId());
            }   

            case UpdateProduct -> {
                final var product = objectMapper.convertValue(message.getPayload(), Product.class);
                productQueryService.update(product);
                log.info("Produto atualizado no Mongo. Id: {}", product.getId());
            }
            
            case DeleteProduct -> {
                final Integer productId = objectMapper.convertValue(message.getPayload(), Integer.class);
                productQueryService.deleteById(productId);
                log.info("Produto removido do Mongo. Id: {}", productId);
            }
            
            case CreateReview -> {
                final ReviewMessage review = objectMapper.convertValue(message.getPayload(), ReviewMessage.class);
                log.info("Evento CreateReview recebido. reviewId={}, productId={}", review.getId(), review.getProduct() != null ? review.getProduct().getId() : null);
                final Review mappedReview = reviewMapper.toReview(review);
                log.info("Review mapeada. id={}, userName={}, description={}, rating={}", mappedReview.getId(), mappedReview.getUserName(), mappedReview.getDescription(), mappedReview.getRating());
                productQueryService.addReview(review.getProduct().getId(), mappedReview);
                log.info("Review adicionada ao produto no Mongo. ProductId: {}", review.getProduct().getId());
            }

            default -> log.error("Event not mapped: {}", message.getEvent());
        }
    }
}
