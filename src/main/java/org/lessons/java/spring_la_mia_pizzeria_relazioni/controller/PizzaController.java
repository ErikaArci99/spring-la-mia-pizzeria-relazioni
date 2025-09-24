package org.lessons.java.spring_la_mia_pizzeria_relazioni.controller;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import java.util.List;

@Controller
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    // Mostra tutte le pizze
    @GetMapping("/")
    public String index(Model model) {
        List<Pizza> pizze = pizzaRepository.findAll();
        model.addAttribute("pizze", pizze);
        return "pizze/index";
    }

    // Mostra una pizza specifica
    @GetMapping("/pizza/{id}")
    public String showPizza(@PathVariable("id") Long id, Model model) {
        Pizza pizza = pizzaRepository.findById(id).orElse(null);
        if (pizza == null) {
            return "redirect:/"; // pizza non trovata
        }
        model.addAttribute("pizza", pizza);
        return "pizze/show";
    }

    // Ricerca pizze per nome o descrizione
    @GetMapping("/search")
    public String searchByNameOrDescrizione(@RequestParam("keyword") String keyword, Model model) {
        List<Pizza> pizze = pizzaRepository.findByNomeContainingIgnoreCaseOrDescrizioneContainingIgnoreCase(keyword,
                keyword);
        model.addAttribute("pizze", pizze);
        return "pizze/index";
    }

    // Creazione nuova pizza - form
    @GetMapping("/pizza/create")
    public String createForm(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "pizze/create";
    }

    // Salvataggio nuova pizza
    @PostMapping("/pizza/create")
    public String storePizza(@Valid @ModelAttribute("pizza") Pizza pizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "pizze/create";
        }
        pizzaRepository.save(pizza);
        return "redirect:/";
    }

    // Modifica pizza - mostra form
    @GetMapping("/pizza/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Pizza pizza = pizzaRepository.findById(id).orElse(null);
        if (pizza == null) {
            return "redirect:/";
        }
        model.addAttribute("pizza", pizza);
        return "pizze/edit";
    }

    // Salvataggio modifica pizza
    @PostMapping("/pizza/edit/{id}")
    public String update(@PathVariable Long id,
            @Valid @ModelAttribute("pizza") Pizza formPizza,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "pizze/edit";
        }
        formPizza.setId(id);
        pizzaRepository.save(formPizza);
        return "redirect:/pizza/" + id;
    }

    // Eliminazione pizza
    @PostMapping("/pizza/delete/{id}")
    public String delete(@PathVariable Long id) {
        pizzaRepository.deleteById(id);
        return "redirect:/";
    }
}
