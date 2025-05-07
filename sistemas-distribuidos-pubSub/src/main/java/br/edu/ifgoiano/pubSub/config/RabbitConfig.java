package br.edu.ifgoiano.pubSub.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

//    // Nome da exchange e fila
//    private static final String EXCHANGE_PROCESSAMENTO = "fila-processamento-pedidos";
//    private static final String FILA_PROCESSAMENTO = "fila-processamento-pedidos";
//
//    @Bean
//    public Declarables declararFilas() {
//        // Cria a exchange
//        DirectExchange exchange = new DirectExchange(EXCHANGE_PROCESSAMENTO, true, false); // durable, autoDelete
//
//        // Cria a fila
//        Queue filaProcessamento = new Queue(FILA_PROCESSAMENTO, true); // durable
//
//        // Faz o binding entre exchange e fila
//        Binding binding = BindingBuilder
//                .bind(filaProcessamento)
//                .to(exchange)
//                .with(""); // routing key (vazia para direct exchange)
//
//        return new Declarables(exchange, filaProcessamento, binding);
//    }
}
