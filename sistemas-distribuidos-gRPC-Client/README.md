# 🌦️ Sistema de Previsão Meteorológica Distribuído com gRPC e Spring Boot

Este projeto implementa um sistema para fornecer informações meteorológicas de diferentes regiões utilizando **gRPC** como protocolo de comunicação entre serviços e **Spring Boot** para expor uma API REST que atua como ponte com os serviços gRPC.

## 📌 Objetivo

O principal objetivo do projeto é desenvolver uma arquitetura cliente-servidor distribuída, onde:

- O **servidor gRPC** oferece 5 serviços meteorológicos simulados.
- O **cliente Spring Boot** expõe uma API REST, consumindo os serviços gRPC internamente.

Essa arquitetura permite que ferramentas como o Postman acessem dados meteorológicos de maneira simples via HTTP, enquanto os dados são processados eficientemente por gRPC no back-end.

## 🧱 Estrutura do Projeto

O projeto é dividido em dois módulos:

- **`sistemas-distribuidos-gRPC`**: Contém a implementação do servidor gRPC com os cinco serviços:
  - Cadastrar uma cidade
  - Listar cidades cadastradas
  - Obter temperatura atual
  - Previsão para cinco dias
  - Estatísticas climáticas (média, mínima e máxima)

- **`sistemas-distribuidos-gRPC-Client`**: Implementa uma aplicação REST usando Spring Boot que age como cliente gRPC. Essa API REST pode ser testada com ferramentas como Postman, e cada endpoint corresponde a um serviço do servidor gRPC.

## 🔧 Tecnologias Utilizadas

- Java 21  
- Spring Boot  
- gRPC + Protobuf  
- Maven  

## 📬 Endpoints da API REST (exemplos)

- `POST /cidade` – Cadastra uma nova cidade  
- `GET /cidades` – Lista todas as cidades cadastradas  
- `GET /temperatura-atual?cidade=NomeDaCidade` – Retorna a temperatura atual da cidade  
- `GET /previsao?cidade=NomeDaCidade` – Retorna previsão para cinco dias  
- `GET /estatisticas?cidade=NomeDaCidade` – Retorna média, mínima e máxima
