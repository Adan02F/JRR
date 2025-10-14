package com.jrr.alquiler.repository;

import com.jrr.alquiler.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> { }
