package com.jrr.alquiler.controller;

import com.jrr.alquiler.model.Vehiculo;
import com.jrr.alquiler.repository.VehiculoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    private final VehiculoRepository repo;

    public VehiculoController(VehiculoRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Vehiculo> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Vehiculo crear(@RequestBody Vehiculo v) {
        return repo.save(v);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repo.deleteById(id);
    }
	
	@GetMapping("/{id}")
	public Vehiculo obtenerPorId(@PathVariable Long id) {
		return repo.findById(id).orElse(null);
	}

}
