package com.jrr.alquiler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jrr.alquiler.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}

