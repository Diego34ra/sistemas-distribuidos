package br.edu.ifgoiano.websocket.controller;

import br.edu.ifgoiano.websocket.controller.response.TemperaturaResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class TemperaturaController {


    private final SimpMessagingTemplate messagingTemplate;
    private final RestTemplate restTemplate = new RestTemplate();

    private final String[] locations = {"New York", "London", "Paris", "Tokyo", "Berlin", "Sydney", "Rio de Janeiro", "Moscow", "Delhi", "Cape Town"};

    @Value("${openweather.api.key}")
    private String apiKey;

    public TemperaturaController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Scheduled(fixedRate = 5000)
    public void sendTemperature() {
        String location = locations[(int) (Math.random() * locations.length)];

        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + apiKey + "&units=metric";  // Obtendo a temperatura em Celsius

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String body = response.getBody();

            String temperature = body.split("\"temp\":")[1].split(",")[0]; // Pegando o valor da temperatura
            String description = body.split("\"description\":\"")[1].split("\"")[0]; // Pegando a descrição do clima

            TemperaturaResponse message = new TemperaturaResponse(location, temperature, description);
            System.out.println("Enviando temperatura real: " + message.getLocation() + " - " + message.getTemperature() + "°C");

            messagingTemplate.convertAndSend("/topic/temperatura", message);
        } else {
            System.out.println("Erro ao buscar temperatura para: " + location);
        }
    }
}
