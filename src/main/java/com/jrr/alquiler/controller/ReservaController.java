package com.jrr.alquiler.controller;

import com.jrr.alquiler.model.Reserva;
import com.jrr.alquiler.repository.ReservaRepository;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservas")
@CrossOrigin(origins = "*")
public class ReservaController {

    private final ReservaRepository repo;

    public ReservaController(ReservaRepository repo) {
        this.repo = repo;
    }

    // Listar todas las reservas
    @GetMapping
    public List<Reserva> listar() {
        return repo.findAll();
    }

    // Crear una reserva (POST)
    @PostMapping
    public Reserva crear(@RequestBody Reserva r) {
        return repo.save(r);
    }

    // Buscar reservas por vehículo
    @GetMapping("/vehiculo/{vehiculoId}")
    public List<Reserva> buscarPorVehiculo(@PathVariable Long vehiculoId) {
        return repo.findByVehiculoId(vehiculoId);
    }

    // Buscar reservas por cliente
    @GetMapping("/cliente/{clienteId}")
    public List<Reserva> buscarPorCliente(@PathVariable Long clienteId) {
        return repo.findByClienteId(clienteId);
    }

    // Buscar reservas dentro de un rango de fechas
    @GetMapping("/fecha")
    public List<Reserva> buscarPorFecha(
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fin) {
        return repo.findByFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(inicio, fin);
    }
}
