package br.edu.ifgoiano.grpc.servidor;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class WeatherController {

    @GrpcClient("weather")
    private WeatherServiceGrpc.WeatherServiceBlockingStub grpcStub;

    @PostMapping("/cidade")
    public ResponseEntity<String> cadastrarCidade(@RequestBody Map<String, String> body) {
        CidadeRequest req = CidadeRequest.newBuilder().setNome(body.get("nome")).build();
        StatusResponse res = grpcStub.cadastrarCidade(req);
        return ResponseEntity.ok(res.getMensagem());
    }

    @GetMapping("/cidades")
    public ResponseEntity<List<String>> listarCidades() {
        ListaCidadesResponse res = grpcStub.listarCidades(Empty.newBuilder().build());
        return ResponseEntity.ok(res.getCidadesList());
    }

    @GetMapping("/temperatura")
    public ResponseEntity<Float> obterTemperatura(@RequestParam String cidade) {
        TemperaturaResponse res = grpcStub.obterTemperaturaAtual(CidadeRequest.newBuilder().setNome(cidade).build());
        return ResponseEntity.ok(res.getTemperaturaAtual());
    }

    @GetMapping("/previsao")
    public ResponseEntity<List<Float>> obterPrevisao(@RequestParam String cidade) {
        PrevisaoResponse res = grpcStub.previsaoCincoDias(CidadeRequest.newBuilder().setNome(cidade).build());
        return ResponseEntity.ok(res.getTemperaturasList());
    }

    @GetMapping("/estatisticas")
    public ResponseEntity<EstatisticasResponse> obterEstatisticas(@RequestParam String cidade) {
        EstatisticasResponse res = grpcStub.estatisticasClimaticas(CidadeRequest.newBuilder().setNome(cidade).build());
        return ResponseEntity.ok(res);
    }
}

