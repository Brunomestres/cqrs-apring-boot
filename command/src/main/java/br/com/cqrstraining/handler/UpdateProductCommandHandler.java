package br.com.cqrstraining.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import br.com.cqrstraining.domain.command.UpdateProductCommand;
import br.com.cqrstraining.domain.enums.Event;
import br.com.cqrstraining.domain.message.Message;
import br.com.cqrstraining.repository.ProductRepository;


@Component
public class UpdateProductCommandHandler  implements CommandHandler<UpdateProductCommand> {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private KafkaTemplate<String, Message<?>> kafkaTemplate;
    
    @Override
    public void handle(UpdateProductCommand command) {
            var savedProduct = productRepository.findById(command.getId()).orElseThrow(IllegalArgumentException::new);

            final var product = savedProduct.toBuilder().imageUrl(command.getImageUrl()).name(command.getName()).description(command.getDescription()).value(command.getValue()).build();
            productRepository.save(product);

            kafkaTemplate.send("tp-query", new Message<>(Event.UpdateProduct, product));
    }

}
