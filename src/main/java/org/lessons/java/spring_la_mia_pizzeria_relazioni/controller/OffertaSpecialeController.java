package org.lessons.java.spring_la_mia_pizzeria_relazioni.controller;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.OffertaSpeciale;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.repository.OffertaSpecialeRepository;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/offerte")
public class OffertaSpecialeController {

    @Autowired
    private OffertaSpecialeRepository offertaRepo;

    @Autowired
    private PizzaRepository pizzaRepo;

    // form creazione nuova offerta
    @GetMapping("/create/{pizzaId}")
    public String create(@PathVariable Long pizzaId, Model model) {
        Pizza pizza = pizzaRepo.findById(pizzaId).orElse(null);
        if (pizza == null)
            return "redirect:/";

        OffertaSpeciale offerta = new OffertaSpeciale();
        offerta.setPizza(pizza);

        model.addAttribute("offerta", offerta);
        return "offerte/create";
    }

    // salvataggio offerta
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("offerta") OffertaSpeciale offerta,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "offerte/create";
        }
        offertaRepo.save(offerta);
        return "redirect:/pizza/" + offerta.getPizza().getId();
    }

    // modifica offerta
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        OffertaSpeciale offerta = offertaRepo.findById(id).orElse(null);
        if (offerta == null)
            return "redirect:/";

        model.addAttribute("offerta", offerta);
        return "offerte/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id,
            @Valid @ModelAttribute("offerta") OffertaSpeciale formOfferta,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "offerte/edit";
        }
        formOfferta.setId(id);
        offertaRepo.save(formOfferta);
        return "redirect:/pizza/" + formOfferta.getPizza().getId();
    }

    // cancello l offerta

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        OffertaSpeciale offerta = offertaRepo.findById(id).orElse(null);
        if (offerta == null) {
            return "redirect:/";
        }
        Long pizzaId = offerta.getPizza().getId();
        offertaRepo.delete(offerta);
        return "redirect:/pizza/" + pizzaId;
    }

}
