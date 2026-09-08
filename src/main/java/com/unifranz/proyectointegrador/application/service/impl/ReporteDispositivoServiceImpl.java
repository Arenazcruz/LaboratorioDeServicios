package com.unifranz.proyectointegrador.application.service.impl;

import com.unifranz.proyectointegrador.application.dto.DispositivoDto;
import com.unifranz.proyectointegrador.application.service.ReporteDispositivoService;
import com.unifranz.proyectointegrador.domain.Dispositivo;
import com.unifranz.proyectointegrador.infrastructure.persistence.DispositivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReporteDispositivoServiceImpl implements ReporteDispositivoService {
    private final DispositivoRepository dispositivoRepository;

    @Override
    public List<DispositivoDto> obtenerPorMarca(String marca) {
        return dispositivoRepository.findByMarcaIgnoreCaseAndEstadoTrue(marca)
                .stream()
                .map(this::convertirDto)
                .toList();
    }

    @Override
    public List<DispositivoDto> obtenerDesactivados() {
        return dispositivoRepository.findByEstadoFalse()
                .stream()
                .map(this::convertirDto)
                .toList();
    }

    private DispositivoDto convertirDto(Dispositivo dispositivo) {
        return new DispositivoDto(dispositivo.getId(), dispositivo.getNombre(),
                dispositivo.getMarca(), dispositivo.getTipo(), dispositivo.getPrecio());
    }
}
