package com.jrr.alquiler.controller;

import com.jrr.alquiler.model.Reserva;
import com.jrr.alquiler.repository.ReservaRepository;
import com.jrr.alquiler.repository.ClienteRepository;
import com.jrr.alquiler.repository.VehiculoRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservas")
@CrossOrigin(origins = "*")
public class ReservaController {

    @Autowired
    private ReservaRepository repo;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    // Listar todas las reservas
    @GetMapping
    public List<Reserva> listar() {
        return repo.findAll();
    }

    // Crear una reserva (POST)
    @PostMapping
    public Reserva crear(@RequestBody Reserva r) {
        // Asegura que cliente y vehiculo existan antes de guardar
        if (r.getCliente() != null && r.getCliente().getId() != null) {
            r.setCliente(clienteRepository.findById(r.getCliente().getId()).orElse(null));
        }
        if (r.getVehiculo() != null && r.getVehiculo().getId() != null) {
            r.setVehiculo(vehiculoRepository.findById(r.getVehiculo().getId()).orElse(null));
        }
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
	
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Long id) {
		if (repo.existsById(id)) {
			repo.deleteById(id);
		}
	}
}
