package br.com.cqrstraining.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cqrstraining.domain.Review;
import br.com.cqrstraining.domain.command.CreateReviewCommand;
import br.com.cqrstraining.repository.ProductRepository;
import br.com.cqrstraining.repository.ReviewRepository;

@Component
public class CreateReviewCommandHandler implements CommandHandler<CreateReviewCommand> {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public void handle(CreateReviewCommand command) {   
        final var product = productRepository.findById(command.getProductId()).orElseThrow(IllegalArgumentException::new);
        final var review = Review.builder().id(null).description(command.getDescription()).rating(command.getRating()).userName(command.getUserName()).product(product).build();
        reviewRepository.save(review);
        
    }   

}
