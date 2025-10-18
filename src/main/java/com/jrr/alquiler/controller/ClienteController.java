package com.jrr.alquiler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.jrr.alquiler.model.Cliente;
import com.jrr.alquiler.repository.ClienteRepository;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

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

}
