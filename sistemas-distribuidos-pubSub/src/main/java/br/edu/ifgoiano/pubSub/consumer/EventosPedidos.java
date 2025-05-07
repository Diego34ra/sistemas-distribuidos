package br.edu.ifgoiano.pubSub.consumer;

import br.edu.ifgoiano.pubSub.model.Pedido;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class EventosPedidos {

    @Bean
    public Consumer<Pedido> eventoProcessado(StreamBridge streamBridge) {
        return pedido -> {
            System.out.println("Mensagem recebida (eventoProcessado): " + pedido);
            streamBridge.send("auditoriaPedidos-out-0", pedido);
            streamBridge.send("notificacaoProcessado-out-0", pedido);
        };
    }

    @Bean
    public Consumer<Pedido> eventoEntregue(StreamBridge streamBridge) {
        return pedido -> {
            System.out.println("Mensagem recebida (eventoEntregue): " + pedido);
            streamBridge.send("auditoriaPedidos-out-0", pedido);
            streamBridge.send("notificacaoProcessado-out-0", pedido);
        };
    }
}
