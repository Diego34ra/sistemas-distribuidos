package br.edu.ifgoiano.replicacao.sistemas_distribuidos_replicacao.controller;

import br.edu.ifgoiano.replicacao.sistemas_distribuidos_replicacao.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("api/v1/data")
public class ReplicaController {

    private final Map<Integer, Usuario> replicaStore = new ConcurrentHashMap<>();

    @PostMapping("/replica")
    public ResponseEntity<String> receiveReplica(@RequestBody Usuario usuario) {
        replicaStore.put(usuario.getId(), usuario);
        System.out.println("Recebido no secundário: " + usuario.getId() + " = " + usuario);

        return ResponseEntity.ok("Replicado com sucesso.");
    }


    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios() {
        return ResponseEntity.ok(new ArrayList<>(replicaStore.values()));
    }

}