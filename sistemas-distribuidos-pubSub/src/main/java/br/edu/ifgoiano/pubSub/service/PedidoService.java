package br.edu.ifgoiano.pubSub.service;

import br.edu.ifgoiano.pubSub.model.Pedido;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
    private final StreamBridge streamBridge;

    public PedidoService(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public ResponseEntity<String> criar(Pedido pedido) {
        boolean enviado = streamBridge.send(
                "pedidosOutput-out-0",
                MessageBuilder
                        .withPayload(pedido)
                        .setHeader("contentType", MediaType.APPLICATION_JSON_VALUE)
                        .build()
        );

        return enviado
                ? ResponseEntity.ok("Pedido enviado para processamento.")
                : ResponseEntity.internalServerError().body("Erro ao enviar pedido.");
    }
}
