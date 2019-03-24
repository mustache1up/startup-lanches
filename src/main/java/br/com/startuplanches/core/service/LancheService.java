package br.com.startuplanches.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.startuplanches.core.dto.DetalhesLancheDTO;
import br.com.startuplanches.core.dto.LancheDTO;
import br.com.startuplanches.core.dto.PromocaoAplicadaDTO;
import br.com.startuplanches.core.entity.Ingrediente;
import br.com.startuplanches.core.model.Lanche;

@Service
public class LancheService {
	
	PromocaoService promocaoService;

	@Autowired
	public LancheService(PromocaoService promocaoService) {
		
		this.promocaoService = promocaoService;
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
		detalhesLancheDTO.setLanche(lanche.buildDTO());
		detalhesLancheDTO.setPrecoFinal(precoFinal);
		
		return detalhesLancheDTO;
	}

	public DetalhesLancheDTO adicionarIngrediente(DetalhesLancheDTO detalhesLancheDTO, Long ingredienteId) {

		Lanche lanche = Lanche.parseFromDTO(detalhesLancheDTO);
		
		Ingrediente ingrediente = getIngredienteById(ingredienteId);
		
		lanche.adicionaIngrediente(ingrediente);
		
		return computaDetalhesLanche(lanche);
	}

	private Ingrediente getIngredienteById(Long ingredienteId) {

		Ingrediente ingrediente = new Ingrediente();
    	ingrediente.setId(1L);
    	ingrediente.setNome("Queijo");
    	ingrediente.setPreco(1D);
		return ingrediente;
	}

	public DetalhesLancheDTO computaDetalhesLancheById(String lancheId) {
		
    	Lanche lanche = new Lanche();
    	lanche.setId(1L);
    	lanche.setNome("Id: " + lancheId);
    	Ingrediente ingrediente = new Ingrediente();
    	ingrediente.setId(1L);
    	ingrediente.setNome("Queijo");
    	ingrediente.setPreco(1D);
    	lanche.adicionaIngrediente(ingrediente, 3);
    	
    	return computaDetalhesLanche(lanche);
	}

}
