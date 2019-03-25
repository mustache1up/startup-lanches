package br.com.startuplanches.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.startuplanches.core.dto.DetalhesLancheDTO;
import br.com.startuplanches.core.dto.ItemCardapioDTO;
import br.com.startuplanches.core.service.LancheService;

@RestController
public class LancheController {

	@Autowired
	private LancheService lancheService;
	
    @GetMapping("/api/lanches")
    public List<ItemCardapioDTO> todosItensCardapio() {
    	
		return lancheService.todosItensCardapio();
    }
    
    @GetMapping("/api/lanches/criar")
    public DetalhesLancheDTO criar() {
    	
    	return lancheService.computaDetalhesLancheVazio();
    }
    
    @PostMapping("/api/lanches/adicionar-ingrediente/{ingredienteId}")
    public DetalhesLancheDTO adicionarIngrediente(@RequestBody DetalhesLancheDTO detalhesLancheDTO, @PathVariable Long ingredienteId) {
    	
    	return lancheService.adicionarIngrediente(detalhesLancheDTO, ingredienteId);
    }
    
    @GetMapping("/api/lanches/{itemCardapioId}")
    public DetalhesLancheDTO itemCardapio(@PathVariable Long itemCardapioId) {
    	
    	return lancheService.computaDetalhesLancheById(itemCardapioId);
    }
}
