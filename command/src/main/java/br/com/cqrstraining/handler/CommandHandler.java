package br.com.cqrstraining.handler;

import br.com.cqrstraining.domain.command.Command;

public interface CommandHandler<C extends Command> {
    void handle(C command);
}
