package br.edu.ifgoiano.pubSub.controller;

import br.edu.ifgoiano.pubSub.model.Pedido;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/pedidos")
public class PedidoController {

    private final StreamBridge streamBridge;

    public PedidoController(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @PostMapping
    public ResponseEntity<String> criarPedido(@RequestBody Pedido pedido) {
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
