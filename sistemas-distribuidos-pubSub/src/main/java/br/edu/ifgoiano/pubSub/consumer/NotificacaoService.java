package br.edu.ifgoiano.pubSub.consumer;

import br.edu.ifgoiano.pubSub.model.Pedido;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class NotificacaoService {

    @Bean
    public Consumer<Pedido> notificacaoProcessado(StreamBridge streamBridge) {
        return pedido -> {
            System.out.println("Mensagem recebida (notificacaoProcessado): " + pedido);
        };
    }
}