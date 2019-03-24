package br.com.startuplanches.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.startuplanches.core.entity.Ingrediente;
import br.com.startuplanches.core.service.IngredienteService;

@RestController
public class IngredientesController {

	@Autowired
	private IngredienteService ingredienteService;
	
    @GetMapping("/api/ingredientes/")
    public List<Ingrediente> ingredientes() {
    	
		return ingredienteService.todosIngredientes();
    }
}
