package br.com.cqrstraining.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cqrstraining.domain.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
