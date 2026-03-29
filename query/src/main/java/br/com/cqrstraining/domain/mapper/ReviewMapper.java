package br.com.cqrstraining.domain.mapper;

import org.mapstruct.Mapper;

import br.com.cqrstraining.domain.Review;
import br.com.cqrstraining.domain.message.ReviewMessage;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    Review toReview(ReviewMessage reviewMessage);

}
