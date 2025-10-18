package com.jrr.alquiler.controller;

import com.jrr.alquiler.model.Vehiculo;
import com.jrr.alquiler.repository.VehiculoRepository;
import com.jrr.alquiler.repository.ReservaRepository; // Import faltante

import org.springframework.beans.factory.annotation.Autowired; // Import faltante
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehiculos")
@CrossOrigin(origins = "*")
public class VehiculoController {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @GetMapping
    public List<Vehiculo> listar() {
        return vehiculoRepository.findAll();
    }

    @PostMapping
    public Vehiculo crear(@RequestBody Vehiculo v) {
        return vehiculoRepository.save(v);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarVehiculo(@PathVariable Long id) {
        if (!vehiculoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        boolean tieneReservas = reservaRepository.existsByVehiculoId(id);
        if (tieneReservas) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar el vehiculo porque tiene reservas asociadas.");
        }

        vehiculoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
