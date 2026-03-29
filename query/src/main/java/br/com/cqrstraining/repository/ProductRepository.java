package br.com.cqrstraining.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.cqrstraining.domain.Product;


@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {

}
