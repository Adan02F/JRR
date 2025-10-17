package com.jrr.alquiler.repository;

import com.jrr.alquiler.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByVehiculoId(Long vehiculoId);
    List<Reserva> findByClienteId(Long clienteId);
    List<Reserva> findByFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(LocalDate inicio, LocalDate fin);
}
