package br.edu.ifgoiano.grpc.client.dto;

public class EstatisticasDTO {
    private float media;
    private float minima;
    private float maxima;

    public EstatisticasDTO(float media, float minima, float maxima) {
        this.media = media;
        this.minima = minima;
        this.maxima = maxima;
    }

    public float getMedia() {
        return media;
    }

    public void setMedia(float media) {
        this.media = media;
    }

    public float getMinima() {
        return minima;
    }

    public void setMinima(float minima) {
        this.minima = minima;
    }

    public float getMaxima() {
        return maxima;
    }

    public void setMaxima(float maxima) {
        this.maxima = maxima;
    }
}
