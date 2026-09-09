package com.unifranz.proyectointegrador.infrastructure.web.controller;

import com.unifranz.proyectointegrador.application.dto.DispositivoDto;
import com.unifranz.proyectointegrador.application.service.ReporteDispositivoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dispositivos")
@RequiredArgsConstructor
public class ReporteDispositivoController {
    private final ReporteDispositivoService reporteDispositivoService;

    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<DispositivoDto>> obtenerPorMarca(@PathVariable String marca) {
        return ResponseEntity.ok(reporteDispositivoService.obtenerPorMarca(marca));
    }

    @GetMapping("/desactivados")
    public ResponseEntity<List<DispositivoDto>> obtenerDesactivados() {
        return ResponseEntity.ok(reporteDispositivoService.obtenerDesactivados());
    }
}
