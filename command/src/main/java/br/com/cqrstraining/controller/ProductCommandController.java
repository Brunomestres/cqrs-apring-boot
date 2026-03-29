package br.com.cqrstraining.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cqrstraining.bus.CommandBus;
import br.com.cqrstraining.domain.command.CreateProductCommand;
import br.com.cqrstraining.domain.command.DeleteProductCommand;
import br.com.cqrstraining.domain.command.UpdateProductCommand;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
public class ProductCommandController {
    @Autowired
    private CommandBus commandBus;
   

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody @Valid CreateProductCommand command) {
        commandBus.execute(command);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable("id") Integer id, @RequestBody @Valid UpdateProductCommand command) {
        command.setId(id);
        commandBus.execute(command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") Integer id) {
        var command = new DeleteProductCommand();
        command.setId(id);
        commandBus.execute(command);
    }
}
