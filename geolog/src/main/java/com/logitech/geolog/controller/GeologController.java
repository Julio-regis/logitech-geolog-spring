package com.logitech.geolog.controller;

import com.logitech.geolog.dto.RelatorioUnificadoDTO;
import com.logitech.geolog.model.Motorista;
import com.logitech.geolog.model.Telemetria;
import com.logitech.geolog.model.Veiculo;
import com.logitech.geolog.repository.MotoristaRepository;
import com.logitech.geolog.repository.TelemetriaRepository;
import com.logitech.geolog.repository.VeiculoRepository;
import com.logitech.geolog.service.GeologService;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/geolog")
public class GeologController {

    private final MotoristaRepository motoristaRepo;
    private final VeiculoRepository veiculoRepo;
    private final TelemetriaRepository telemetriaRepo;
    private final GeologService geologService;

    public GeologController(MotoristaRepository motoristaRepo,
                            VeiculoRepository veiculoRepo,
                            TelemetriaRepository telemetriaRepo,
                            GeologService geologService) {
        this.motoristaRepo = motoristaRepo;
        this.veiculoRepo = veiculoRepo;
        this.telemetriaRepo = telemetriaRepo;
        this.geologService = geologService;
    }

    @GetMapping("/motoristas")
    public List<Motorista> listarMotoristas() {
        return motoristaRepo.findAll();
    }

    @GetMapping("/veiculos")
    public List<Veiculo> listarVeiculos() {
        return veiculoRepo.findAll();
    }

    @GetMapping("/telemetria")
    public List<Telemetria> listarTelemetria() {
        return telemetriaRepo.findAll();
    }

    // Endpoint para a Visão Unificada (Join Poliglota)
    @GetMapping("/visao-unificada")
    public List<RelatorioUnificadoDTO> obterVisaoUnificada() {
        return geologService.obterVisaoUnificada();
    }

    // Endpoint para busca geoespacial por proximidade ($near)
    @GetMapping("/proximidade")
    public List<Telemetria> buscarPorProximidade(
            @RequestParam Double lng,
            @RequestParam Double lat,
            @RequestParam Double distanciaKm) {

        Point pontoReferencia = new Point(lng, lat);
        Distance distancia = new Distance(distanciaKm, Metrics.KILOMETERS);

        return telemetriaRepo.findByLocationNear(pontoReferencia, distancia);
    }
}