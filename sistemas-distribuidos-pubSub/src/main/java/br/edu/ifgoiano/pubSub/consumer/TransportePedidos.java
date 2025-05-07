package br.edu.ifgoiano.pubSub.consumer;

import br.edu.ifgoiano.pubSub.model.Pedido;
import br.edu.ifgoiano.pubSub.model.PedidoStatus;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class TransportePedidos {

    @Bean
    public Consumer<Pedido> transportarPedido(StreamBridge streamBridge) {
        return pedido -> {
            System.out.println("Mensagem recebida (transportarPedido): " + pedido );
            pedido.setStatus(PedidoStatus.ENTREGUE);
            streamBridge.send("eventoEntregue-out-0", pedido);
        };
    }
}
