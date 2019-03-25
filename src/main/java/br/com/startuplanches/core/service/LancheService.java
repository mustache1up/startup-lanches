package br.com.startuplanches.core.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.startuplanches.core.dto.DetalhesLancheDTO;
import br.com.startuplanches.core.dto.ItemCardapioDTO;
import br.com.startuplanches.core.dto.LancheDTO;
import br.com.startuplanches.core.dto.PromocaoAplicadaDTO;
import br.com.startuplanches.core.entity.Ingrediente;
import br.com.startuplanches.core.entity.ItemCardapio;
import br.com.startuplanches.core.model.Lanche;

@Service
public class LancheService {
	
	private PromocaoService promocaoService;
	private IngredienteService ingredienteService;
	private CardapioService cardapioService;

	@Autowired
	public LancheService(PromocaoService promocaoService, IngredienteService ingredienteService, CardapioService cardapioService) {
		
		this.promocaoService = promocaoService;
		this.ingredienteService = ingredienteService;
		this.cardapioService = cardapioService;
	}

	public List<ItemCardapioDTO> todosItensCardapio() {
		
		List<ItemCardapio> todosItensCardapio = cardapioService.todosItensCardapio();
		
		List<ItemCardapioDTO> todosItensCardapioDTO = todosItensCardapio.stream()
			.map(x -> ItemCardapioDTO.build(x))
			.collect(Collectors.toList());
		
		return todosItensCardapioDTO;
	}

	public DetalhesLancheDTO computaDetalhesLanche(Lanche lanche) {

		List<PromocaoAplicadaDTO> promocoesAplicadas = promocaoService.aplicaPromocoes(lanche);
		Double totalDescontoPromocoes = promocoesAplicadas.stream().mapToDouble(x -> x.getDesconto()).sum();
		double precoFinal = lanche.precoBase() - totalDescontoPromocoes;
		
		DetalhesLancheDTO detalhesLancheDTO = new DetalhesLancheDTO();
		detalhesLancheDTO.setPromocoesAplicadas(promocoesAplicadas);
		detalhesLancheDTO.setLanche(LancheDTO.build(lanche));
		detalhesLancheDTO.setPrecoFinal(precoFinal);
		
		return detalhesLancheDTO;
	}

	public DetalhesLancheDTO adicionarIngrediente(DetalhesLancheDTO detalhesLancheDTO, Long ingredienteId) {

		Lanche lanche = parseLancheFromDTO(detalhesLancheDTO);
		
		Ingrediente ingrediente = ingredienteService.getIngredienteById(ingredienteId);
		
		lanche.adicionaIngrediente(ingrediente);
		
		return computaDetalhesLanche(lanche);
	}

	public DetalhesLancheDTO computaDetalhesLancheById(Long itemCardapioId) {
		
    	ItemCardapio itemCardapio = cardapioService.getItemCardapioById(itemCardapioId);
    	
    	Lanche lanche = parseLancheFromItemCardapio(itemCardapio);
		
    	return computaDetalhesLanche(lanche);
	}

	public DetalhesLancheDTO computaDetalhesLancheVazio() {
		
		return computaDetalhesLanche(new Lanche());
	}
	
	private Lanche parseLancheFromItemCardapio(ItemCardapio itemCardapio) {
		
		Lanche lanche = new Lanche();
		
		Map<Ingrediente, Integer> ingredientes;
		ingredientes = itemCardapio.getIngredientesItemCardapio().stream()
				.collect(Collectors.toMap(x -> ingredienteService.getIngredienteById(x.getIngrediente().getId()), x -> x.getQuantidade()));
		
		lanche.setIngredientes(ingredientes);
		
		return lanche;
	}

	//TODO merge com metodo parseLancheFromItemCardapio
	public Lanche parseLancheFromDTO(DetalhesLancheDTO detalhesLancheDTO) {

		LancheDTO lancheDTO = detalhesLancheDTO.getLanche();
		
		Lanche lanche = new Lanche();
		
		Map<Ingrediente, Integer> ingredientes = lancheDTO.getIngredientes().stream()
			.collect(Collectors.toMap(x -> ingredienteService.getIngredienteById(x.getId()), x -> x.getQuantidade()));
		
		lanche.setIngredientes(ingredientes);
		
		return lanche;
	}
}
