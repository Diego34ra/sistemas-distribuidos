package br.edu.ifgoiano.pubSub.consumer;

import br.edu.ifgoiano.pubSub.model.Pedido;
import br.edu.ifgoiano.pubSub.model.PedidoStatus;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class ProcessadorPedidos {

    @Bean
    public Consumer<Pedido> processarPedido(StreamBridge streamBridge) {
        return pedido  -> {
            System.out.println("Mensagem recebida (processarPedido): " + pedido );
            pedido.setStatus(PedidoStatus.PROCESSADO);
            streamBridge.send("eventoProcessado-out-0", pedido);
            streamBridge.send("transportarPedido-out-0", pedido);
        };
    }

}
