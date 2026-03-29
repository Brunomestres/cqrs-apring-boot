package br.com.cqrstraining.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cqrstraining.domain.Product;
import br.com.cqrstraining.service.ProductQueryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/v1/products")
public class ProductQueryController {

    @Autowired
    private  ProductQueryService productQueryService;
    
    @GetMapping
    public List<Product> findAll() {
        return productQueryService.findAll();
    }
    
    @GetMapping("/{id}")
    public Product findById(@PathVariable("id") Integer id) {
        return productQueryService.findById(id);
    }
    
}
