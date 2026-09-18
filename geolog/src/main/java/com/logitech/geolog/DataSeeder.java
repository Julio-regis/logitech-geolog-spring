package com.logitech.geolog;

import com.logitech.geolog.model.Motorista;
import com.logitech.geolog.model.Telemetria;
import com.logitech.geolog.model.Veiculo;
import com.logitech.geolog.repository.MotoristaRepository;
import com.logitech.geolog.repository.TelemetriaRepository;
import com.logitech.geolog.repository.VeiculoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(MotoristaRepository motoristaRepo,
                                   VeiculoRepository veiculoRepo,
                                   TelemetriaRepository telemetriaRepo,
                                   MongoTemplate mongoTemplate) {
        return args -> {
            // Limpa dados anteriores
            motoristaRepo.deleteAll();
            veiculoRepo.deleteAll();
            telemetriaRepo.deleteAll();

            // Garante que o índice 2dsphere é criado na coleção 'telemetria'
            mongoTemplate.indexOps("telemetria").ensureIndex(
                    new GeospatialIndex("location").typed(GeoSpatialIndexType.GEO_2DSPHERE)
            );

            // 1. Cadastrando Motoristas (H2)
            Motorista m1 = motoristaRepo.save(new Motorista("Carlos Andrade", "123456789", "Ativo"));
            Motorista m2 = motoristaRepo.save(new Motorista("Mariana Silva", "987654321", "Ativo"));

            // 2. Cadastrando Veículos (H2)
            Veiculo v1 = veiculoRepo.save(new Veiculo("ABC-1A23", "Volvo FH 540", m1.getId()));
            Veiculo v2 = veiculoRepo.save(new Veiculo("XYZ-9876", "Scania R450", m2.getId()));

            // 3. Cadastrando Telemetria com GeoJSON (MongoDB)
            telemetriaRepo.save(new Telemetria(
                    v1.getId(),
                    new GeoJsonPoint(-34.873, -7.115),
                    4.2,
                    65.0,
                    "2026-09-18T10:00:00Z"
            ));

            telemetriaRepo.save(new Telemetria(
                    v2.getId(),
                    new GeoJsonPoint(-34.832, -7.121),
                    -18.5,
                    85.0,
                    "2026-09-18T10:05:00Z"
            ));

            System.out.println(">>> Dados de teste e índice 2dsphere criados com sucesso!");
        };
    }
}