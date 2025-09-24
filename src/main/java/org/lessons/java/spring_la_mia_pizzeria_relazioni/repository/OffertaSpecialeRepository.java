package org.lessons.java.spring_la_mia_pizzeria_relazioni.repository;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.OffertaSpeciale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OffertaSpecialeRepository extends JpaRepository<OffertaSpeciale, Long> {
    List<OffertaSpeciale> findByPizzaId(Long pizzaId);
}
