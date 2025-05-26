# Sistema Distribuído de Pedidos com Filas e Eventos

## 📦 Descrição

Este projeto simula o ciclo de vida de um pedido em um sistema de e-commerce, utilizando **Spring Boot**, **RabbitMQ** e **Spring Cloud Stream**. O sistema implementa um fluxo distribuído de pedidos, passando por etapas de **processamento**, **transporte/entrega**, além de gerar eventos para serviços de **notificação** e **auditoria**.

## 🎯 Objetivo da Aplicação

- Simular o fluxo de um pedido desde sua criação até a entrega.
- Trabalhar com filas e eventos (pub/sub) para comunicação entre serviços.
- Demonstrar conceitos de sistemas distribuídos, desacoplamento e comunicação assíncrona.

## 🏗️ Arquitetura

### 🛒 Fluxo do Pedido

1. **Criação do Pedido:**
   - O cliente envia um pedido via endpoint REST.
   - O pedido é enviado para a fila `fila-processamento-pedidos`.

2. **Processamento do Pedido:**
   - O pedido é consumido da fila `fila-processamento-pedidos`.
   - O status do pedido é alterado para `PROCESSADO`.
   - Gera dois eventos:
     - `pedido-processado` → Enviado para auditoria e notificação.
     - Encaminhamento do pedido para a fila `fila-transporte-pedidos`.

3. **Transporte/Entrega do Pedido:**
   - O pedido é consumido da fila `fila-transporte-pedidos`.
   - O status do pedido é alterado para `ENTREGUE`.
   - Gera o evento `pedido-entregue` → Também enviado para auditoria.

4. **Eventos de Auditoria e Notificação:**
   - Os eventos `pedido-processado` e `pedido-entregue` são consumidos pelos serviços de auditoria.
   - A auditoria registra o evento e encaminha uma notificação para o cliente.

## 🔗 Tecnologias Utilizadas

- ✅ Java + Spring Boot
- ✅ Spring Cloud Stream
- ✅ RabbitMQ (mensageria)
- ✅ Spring Web (REST API)

## 🛠️ Como Funciona (Principais Componentes)

| Componente              | Função                                                                                                           |
|-------------------------|------------------------------------------------------------------------------------------------------------------|
| **PedidoController**    | Camada REST. Recebe requisições HTTP e delega a lógica de negócio para o `PedidoService`.                        |
| **PedidoService**       | Responsável pela lógica de criação de pedidos. Envia o pedido para a fila `fila-processamento-pedidos`.          |
| **ProcessadorPedidos**  | Consome pedidos da fila de processamento. Altera o status para `PROCESSADO`. Gera o evento `pedido-processado` e encaminha para a fila de transporte (`fila-transporte-pedidos`). |
| **TransportePedidos**   | Consome pedidos da fila de transporte. Altera o status para `ENTREGUE`. Gera o evento `pedido-entregue`.        |
| **EventosPedidos**      | Responsável por encaminhar os eventos `pedido-processado` e `pedido-entregue` para o serviço de auditoria.      |
| **AuditoriaService**    | Registra os eventos recebidos e, após a auditoria, envia notificações aos clientes.                             |
| **NotificacaoService**  | Recebe os eventos de auditoria e realiza a notificação final para o cliente.    


## 📜 Observações Importantes

- As filas e os bindings são definidos automaticamente via Spring Cloud Stream com as configurações no `application.yml`.
- Cada evento gera logs no console, simulando os serviços de notificação e auditoria.
