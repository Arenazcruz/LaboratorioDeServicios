package com.unifranz.proyectointegrador.application.service;

import com.unifranz.proyectointegrador.application.dto.DispositivoDto;

import java.util.List;

public interface ReporteDispositivoService {
    List<DispositivoDto> obtenerPorMarca(String marca);

    List<DispositivoDto> obtenerDesactivados();
}
