package br.com.cqrstraining.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.cqrstraining.domain.Product;
import br.com.cqrstraining.domain.command.CreateProductCommand;
import br.com.cqrstraining.domain.enums.Event;
import br.com.cqrstraining.domain.message.Message;
import br.com.cqrstraining.repository.ProductRepository;


@Component
public class CreateProductCommandHandler  implements CommandHandler<CreateProductCommand> {
    
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private KafkaTemplate<String, Message<?>> kafkaTemplate;
    @Override
    @Transactional
    public void handle(CreateProductCommand command) {

            final var product = Product.builder().id(null).imageUrl(command.getImageUrl()).name(command.getName()).description(command.getDescription()).value(command.getValue()).build();
            final var productSaved = productRepository.save(product);

            kafkaTemplate.send("tp-query", new Message<>(Event.CreateProduct, productSaved));
    }

}
