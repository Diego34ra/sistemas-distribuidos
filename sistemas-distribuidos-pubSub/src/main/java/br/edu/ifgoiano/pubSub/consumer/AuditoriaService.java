package br.edu.ifgoiano.pubSub.consumer;

import br.edu.ifgoiano.pubSub.model.Pedido;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class AuditoriaService {

    @Bean
    public Consumer<Pedido> auditoriaPedidos() {
        return pedido -> {
            System.out.println("Mensagem recebida (auditoriaPedidos): " + pedido);
        };
    }
}
