package br.com.startuplanches.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.startuplanches.core.dto.DetalhesLancheDTO;
import br.com.startuplanches.core.entity.Ingrediente;
import br.com.startuplanches.core.model.Lanche;
import br.com.startuplanches.core.service.PromocaoService;

@RestController
public class LancheController {

	@Autowired
	PromocaoService promocaoService;
	
    @RequestMapping("/api/lanches/{lancheId}")
    public DetalhesLancheDTO opcaoCardapio(@PathVariable String lancheId) {
    	
        Lanche lanche = new Lanche();
        lanche.setId(1L);
        lanche.setNome("Id: " + lancheId);
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNome("Queijo");
        ingrediente.setPreco(1D);
		lanche.adicionaIngrediente(ingrediente, 3);
		
		return promocaoService.computaDetalhesLanche(lanche);
    }
}
