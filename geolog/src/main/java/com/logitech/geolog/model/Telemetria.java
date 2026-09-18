package com.logitech.geolog.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "telemetria")
public class Telemetria {

    @Id
    private String id;

    private Long veiculoId;

    // Índice geoespacial 2dsphere obrigatório para buscas por proximidade ($near)
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint location; // [longitude, latitude]

    private Double temperatura;
    private Double velocidade;
    private String timestamp;

    public Telemetria() {}

    public Telemetria(Long veiculoId, GeoJsonPoint location, Double temperatura, Double velocidade, String timestamp) {
        this.veiculoId = veiculoId;
        this.location = location;
        this.temperatura = temperatura;
        this.velocidade = velocidade;
        this.timestamp = timestamp;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Long getVeiculoId() { return veiculoId; }
    public void setVeiculoId(Long veiculoId) { this.veiculoId = veiculoId; }

    public GeoJsonPoint getLocation() { return location; }
    public void setLocation(GeoJsonPoint location) { this.location = location; }

    public Double getTemperatura() { return temperatura; }
    public void setTemperatura(Double temperatura) { this.temperatura = temperatura; }

    public Double getVelocidade() { return velocidade; }
    public void setVelocidade(Double velocidade) { this.velocidade = velocidade; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}