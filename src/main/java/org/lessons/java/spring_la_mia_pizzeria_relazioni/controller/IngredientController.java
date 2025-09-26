package org.lessons.java.spring_la_mia_pizzeria_relazioni.controller;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    // Lista ingredienti
    @GetMapping
    public String index(Model model) {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        model.addAttribute("ingredients", ingredients);
        return "ingredients/index";
    }

    // Form creazione
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "ingredients/create";
    }

    // Salvataggio nuovo ingrediente
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingredient") Ingredient ingredient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ingredients/create";
        }
        ingredientRepository.save(ingredient);
        return "redirect:/ingredients";
    }

    // Form modifica ingrediente
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ingrediente non trovato: " + id));
        model.addAttribute("ingredient", ingredient);
        return "ingredients/edit";
    }

    // Salvataggio modifica ingrediente
    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute("ingredient") Ingredient ingredient,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ingredients/edit";
        }
        ingredient.setId(id); // assicuriamoci di aggiornare l'id corretto
        ingredientRepository.save(ingredient);
        return "redirect:/ingredients";
    }

    // Eliminazione ingrediente
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        ingredientRepository.deleteById(id);
        return "redirect:/ingredients";
    }
}
