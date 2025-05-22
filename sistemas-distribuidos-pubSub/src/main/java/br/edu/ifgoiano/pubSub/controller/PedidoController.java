package br.edu.ifgoiano.pubSub.controller;

import br.edu.ifgoiano.pubSub.model.Pedido;
import br.edu.ifgoiano.pubSub.service.PedidoService;
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
    private PedidoService pedidoService;

    public PedidoController(StreamBridge streamBridge,
                            PedidoService pedidoService) {
        this.streamBridge = streamBridge;
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<String> criarPedido(@RequestBody Pedido pedido) {
        return pedidoService.criar(pedido);
    }
}
