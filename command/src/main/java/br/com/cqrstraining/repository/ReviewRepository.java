package br.com.cqrstraining.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cqrstraining.domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
