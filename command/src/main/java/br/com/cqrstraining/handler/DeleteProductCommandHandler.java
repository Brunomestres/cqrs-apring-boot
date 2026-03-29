package br.com.cqrstraining.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import br.com.cqrstraining.domain.command.DeleteProductCommand;
import br.com.cqrstraining.domain.enums.Event;
import br.com.cqrstraining.domain.message.Message;
import br.com.cqrstraining.repository.ProductRepository;


@Component
public class DeleteProductCommandHandler  implements CommandHandler<DeleteProductCommand> {
    
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private KafkaTemplate<String, Message<?>> kafkaTemplate;
    @Override
    public void handle(DeleteProductCommand command) {
            productRepository.findById(command.getId()).orElseThrow(IllegalArgumentException::new);

            productRepository.deleteById(command.getId());


            kafkaTemplate.send("tp-query", new Message<>(Event.DeleteProduct, command.getId()));

    }

}
