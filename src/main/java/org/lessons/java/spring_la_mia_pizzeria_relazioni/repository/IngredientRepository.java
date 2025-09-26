package org.lessons.java.spring_la_mia_pizzeria_relazioni.repository;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
