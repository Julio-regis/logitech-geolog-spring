package com.logitech.geolog.dto;

public class RelatorioUnificadoDTO {
    private String nomeMotorista;
    private String placaVeiculo;
    private Double temperatura;
    private Double velocidade;
    private String timestamp;

    public RelatorioUnificadoDTO(String nomeMotorista, String placaVeiculo, Double temperatura, Double velocidade, String timestamp) {
        this.nomeMotorista = nomeMotorista;
        this.placaVeiculo = placaVeiculo;
        this.temperatura = temperatura;
        this.velocidade = velocidade;
        this.timestamp = timestamp;
    }

    // Getters e Setters
    public String getNomeMotorista() { return nomeMotorista; }
    public void setNomeMotorista(String nomeMotorista) { this.nomeMotorista = nomeMotorista; }

    public String getPlacaVeiculo() { return placaVeiculo; }
    public void setPlacaVeiculo(String placaVeiculo) { this.placaVeiculo = placaVeiculo; }

    public Double getTemperatura() { return temperatura; }
    public void setTemperatura(Double temperatura) { this.temperatura = temperatura; }

    public Double getVelocidade() { return velocidade; }
    public void setVelocidade(Double velocidade) { this.velocidade = velocidade; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}