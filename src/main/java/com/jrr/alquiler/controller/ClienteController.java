package com.jrr.alquiler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.jrr.alquiler.model.Cliente;
import com.jrr.alquiler.repository.ClienteRepository;
import com.jrr.alquiler.repository.ReservaRepository;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;
	
	@Autowired
	private ReservaRepository reservaRepository;

    @PostMapping
    public Cliente registrarCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }
	
	@GetMapping("/{id}")
	public Cliente obtenerPorId(@PathVariable Long id) {
		return clienteRepository.findById(id).orElse(null);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarCliente(@PathVariable Long id) {
		if (!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		// Verificar si el cliente tiene reservas
		boolean tieneReservas = reservaRepository.existsByClienteId(id);
		if (tieneReservas) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("No se puede eliminar el cliente porque tiene reservas asociadas.");
		}

		clienteRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}



}
