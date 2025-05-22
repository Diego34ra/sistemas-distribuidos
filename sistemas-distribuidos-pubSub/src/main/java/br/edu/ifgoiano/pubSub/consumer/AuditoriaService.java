package br.edu.ifgoiano.pubSub.consumer;

import br.edu.ifgoiano.pubSub.model.Pedido;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class AuditoriaService {

    @Bean
    public Consumer<Pedido> auditoriaPedidos(StreamBridge streamBridge) {
        return pedido -> {
            System.out.println("Mensagem recebida (auditoriaPedidos): " + pedido);
            streamBridge.send("notificacaoProcessado-out-0", pedido);
        };
    }
}
