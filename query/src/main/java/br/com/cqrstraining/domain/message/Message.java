package br.com.cqrstraining.domain.message;


import br.com.cqrstraining.domain.enums.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Message<T> {

    private Event event;

    private T payload;

}
