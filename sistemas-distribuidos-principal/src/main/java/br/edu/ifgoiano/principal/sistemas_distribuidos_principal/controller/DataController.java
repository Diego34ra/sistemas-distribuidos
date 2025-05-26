package br.edu.ifgoiano.principal.sistemas_distribuidos_principal.controller;

import br.edu.ifgoiano.principal.sistemas_distribuidos_principal.model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/v1/data")
public class DataController {

    private final List<String> replicas = List.of("http://localhost:8081", "http://localhost:8082");

    private final RestTemplate restTemplate;

    public DataController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final Map<Integer, Usuario> dataStore = new ConcurrentHashMap<>();

    @PostMapping
    public ResponseEntity<Usuario> write(@RequestBody Usuario usuario) {
        for (String replica : replicas) {
            try {
                dataStore.put(usuario.getId(), usuario);
                replica = replica + "/api/v1/data/replica";
                restTemplate.postForEntity(replica , usuario, String.class);
            } catch (Exception e) {
                dataStore.remove(usuario.getId());
                throw new RuntimeException("Erro ao enviar requisicao para " + replica, e);
            }
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios() {
        return ResponseEntity.ok(new ArrayList<>(dataStore.values()));
    }
}
