package com.logitech.geolog.repository;

import com.logitech.geolog.model.Telemetria;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelemetriaRepository extends MongoRepository<Telemetria, String> {

    // Busca veículos próximos a um ponto geográfico (longitude, latitude) dentro de uma distância máxima
    List<Telemetria> findByLocationNear(Point location, Distance distance);
}