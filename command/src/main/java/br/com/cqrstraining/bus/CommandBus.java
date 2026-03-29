package br.com.cqrstraining.bus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import br.com.cqrstraining.domain.command.Command;
import br.com.cqrstraining.handler.CommandHandler;
import ch.qos.logback.core.util.StringUtil;

@Component
public class CommandBus {

    @Autowired
    private ApplicationContext applicationContext;
    public void execute(final Command command) {
        try{ 
            String handlerBeanName = StringUtil.lowercaseFirstLetter(command.getClass().getSimpleName()) + "Handler";
            CommandHandler handler = (CommandHandler) applicationContext.getBean(handlerBeanName);
            handler.handle(command);
        }catch(Exception e){
            throw new RuntimeException("Error executing command", e);
        }
    }
}
