package br.edu.ifgoiano.grpc.servidor;

import org.springframework.grpc.server.service.GrpcService;

import java.util.*;

@GrpcService
public class WeatherServiceImpl extends WeatherServiceGrpc.WeatherServiceImplBase {

    private final Map<String, List<Float>> cidadeTemperaturas = new HashMap<>();
    private final Random random = new Random();

    @Override
    public void cadastrarCidade(CidadeRequest request, io.grpc.stub.StreamObserver<StatusResponse> responseObserver) {
        String cidade = request.getNome();
        if (!cidadeTemperaturas.containsKey(cidade)) {
            List<Float> temps = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                temps.add(20 + random.nextFloat() * 15); // temperaturas simuladas
            }
            cidadeTemperaturas.put(cidade, temps);
            responseObserver.onNext(StatusResponse.newBuilder().setMensagem("Cidade cadastrada com sucesso").build());
        } else {
            responseObserver.onNext(StatusResponse.newBuilder().setMensagem("Cidade já existente").build());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void listarCidades(Empty request, io.grpc.stub.StreamObserver<ListaCidadesResponse> responseObserver) {
        ListaCidadesResponse response = ListaCidadesResponse.newBuilder().addAllCidades(cidadeTemperaturas.keySet()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void obterTemperaturaAtual(CidadeRequest request, io.grpc.stub.StreamObserver<TemperaturaResponse> responseObserver) {
        List<Float> temps = cidadeTemperaturas.get(request.getNome());
        float tempAtual = (temps != null && !temps.isEmpty()) ? temps.get(0) : 0;
        responseObserver.onNext(TemperaturaResponse.newBuilder().setTemperaturaAtual(tempAtual).build());
        responseObserver.onCompleted();
    }

    @Override
    public void previsaoCincoDias(CidadeRequest request, io.grpc.stub.StreamObserver<PrevisaoResponse> responseObserver) {
        List<Float> temps = cidadeTemperaturas.getOrDefault(request.getNome(), new ArrayList<>());
        responseObserver.onNext(PrevisaoResponse.newBuilder().addAllTemperaturas(temps).build());
        responseObserver.onCompleted();
    }

    @Override
    public void estatisticasClimaticas(CidadeRequest request, io.grpc.stub.StreamObserver<EstatisticasResponse> responseObserver) {
        List<Float> temps = cidadeTemperaturas.getOrDefault(request.getNome(), new ArrayList<>());
        if (temps.isEmpty()) {
            responseObserver.onNext(EstatisticasResponse.newBuilder().setMedia(0).setMinima(0).setMaxima(0).build());
        } else {
            float media = (float) temps.stream().mapToDouble(f -> f).average().orElse(0);
            float min = Collections.min(temps);
            float max = Collections.max(temps);
            responseObserver.onNext(EstatisticasResponse.newBuilder().setMedia(media).setMinima(min).setMaxima(max).build());
        }
        responseObserver.onCompleted();
    }
}
