package br.com.cqrstraining.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cqrstraining.domain.Product;
import br.com.cqrstraining.domain.command.CreateProductCommand;
import br.com.cqrstraining.repository.ProductRepository;


@Component
public class CreateProductCommandHandler  implements CommandHandler<CreateProductCommand> {
    
    @Autowired
    private ProductRepository productRepository;
    @Override
    public void handle(CreateProductCommand command) {

            final var product = Product.builder().id(null).imageUrl(command.getImageUrl()).name(command.getName()).description(command.getDescription()).value(command.getValue()).build();
            productRepository.save(product);
    }

}
