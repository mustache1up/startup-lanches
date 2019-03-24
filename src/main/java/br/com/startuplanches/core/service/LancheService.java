package br.com.startuplanches.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.startuplanches.core.dto.DetalhesLancheDTO;
import br.com.startuplanches.core.dto.LancheDTO;
import br.com.startuplanches.core.dto.PromocaoAplicadaDTO;
import br.com.startuplanches.core.entity.Ingrediente;
import br.com.startuplanches.core.model.Lanche;

@Service
public class LancheService {
	
	private PromocaoService promocaoService;
	private IngredienteService ingredienteService;

	@Autowired
	public LancheService(PromocaoService promocaoService, IngredienteService ingredienteService) {
		
		this.promocaoService = promocaoService;
		this.ingredienteService = ingredienteService;
	}

	public List<LancheDTO> todasOpcoesCardapio() {
		
		List<LancheDTO> todasOpcoesCardapio = new ArrayList<>();
		
		LancheDTO lanche1 = new LancheDTO();
		lanche1.setId(1L);
		lanche1.setNome("X-Bacon");
		todasOpcoesCardapio.add(lanche1);
		
		LancheDTO lanche2 = new LancheDTO();
		lanche2.setId(2L);
		lanche2.setNome("X-Salada");
		todasOpcoesCardapio.add(lanche2);
		
		return todasOpcoesCardapio;
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

		Lanche lanche = parseFromDTO(detalhesLancheDTO);
		
		Ingrediente ingrediente = ingredienteService.getIngredienteById(ingredienteId);
		
		lanche.adicionaIngrediente(ingrediente);
		
		return computaDetalhesLanche(lanche);
	}

	public DetalhesLancheDTO computaDetalhesLancheById(String lancheId) {
		
    	Lanche lanche = new Lanche();
    	lanche.setId(1L);
    	lanche.setNome("Id: " + lancheId);
    	Ingrediente ingrediente = ingredienteService.getIngredienteById(1L);
    	lanche.adicionaIngrediente(ingrediente, 3);
    	
    	return computaDetalhesLanche(lanche);
	}
	
	
	public Lanche parseFromDTO(DetalhesLancheDTO detalhesLancheDTO) {

		LancheDTO lancheDTO = detalhesLancheDTO.getLanche();
		
		Lanche lanche = new Lanche();
		lanche.setId(lancheDTO.getId());
		lanche.setNome(lancheDTO.getNome());
		
		Map<Ingrediente, Integer> ingredientes = lancheDTO.getIngredientes().stream()
			.collect(Collectors.toMap(x -> ingredienteService.getIngredienteById(x.getId()), x -> x.getQuantidade()));
		
		lanche.setIngredientes(ingredientes);
		
		return lanche;
	}
}
