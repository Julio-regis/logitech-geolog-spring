package com.logitech.geolog.service;

import com.logitech.geolog.dto.RelatorioUnificadoDTO;
import com.logitech.geolog.model.Motorista;
import com.logitech.geolog.model.Telemetria;
import com.logitech.geolog.model.Veiculo;
import com.logitech.geolog.repository.MotoristaRepository;
import com.logitech.geolog.repository.TelemetriaRepository;
import com.logitech.geolog.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GeologService {

    private final MotoristaRepository motoristaRepo;
    private final VeiculoRepository veiculoRepo;
    private final TelemetriaRepository telemetriaRepo;

    public GeologService(MotoristaRepository motoristaRepo, VeiculoRepository veiculoRepo, TelemetriaRepository telemetriaRepo) {
        this.motoristaRepo = motoristaRepo;
        this.veiculoRepo = veiculoRepo;
        this.telemetriaRepo = telemetriaRepo;
    }

    // Realiza o cruzamento de dados (Join Poliglota em Memória) entre relacional e NoSQL
    public List<RelatorioUnificadoDTO> obterVisaoUnificada() {
        List<Telemetria> telemetrias = telemetriaRepo.findAll();
        List<RelatorioUnificadoDTO> relatorio = new ArrayList<>();

        for (Telemetria t : telemetrias) {
            Optional<Veiculo> veiculoOpt = veiculoRepo.findById(t.getVeiculoId());
            if (veiculoOpt.isPresent()) {
                Veiculo v = veiculoOpt.get();

                Optional<Motorista> motoristaOpt = motoristaRepo.findById(v.getMotoristaId());
                String nomeMotorista = motoristaOpt.map(Motorista::getNome).orElse("Desconhecido");

                relatorio.add(new RelatorioUnificadoDTO(
                        nomeMotorista,
                        v.getPlaca(),
                        t.getTemperatura(),
                        t.getVelocidade(),
                        t.getTimestamp()
                ));
            }
        }
        return relatorio;
    }
}