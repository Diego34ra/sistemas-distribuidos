package br.edu.ifgoiano.websocket.controller.response;

public class TemperaturaResponse {
    private String location;
    private String temperature;
    private String description;

    public TemperaturaResponse(String location, String temperature, String description) {
        this.location = location;
        this.temperature = temperature;
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
