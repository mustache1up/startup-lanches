package br.com.startuplanches.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.startuplanches.core.model.DetalhesLancheDTO;
import br.com.startuplanches.core.model.Lanche;
import br.com.startuplanches.core.model.PromocaoAplicadaDTO;
import br.com.startuplanches.core.promocoes.Promocao;

@Service
public class PromocaoService {

	private	List<? extends Promocao> promocoes;
	
	@Autowired
	public PromocaoService(List<? extends Promocao> promocoes) {

		this.promocoes = promocoes;
	}

	public List<? extends Promocao> getPromocoes() {
		
		return promocoes;
	}

	public List<PromocaoAplicadaDTO> aplicaPromocoes(Lanche lanche) {

		List<PromocaoAplicadaDTO> promocoesAplicadas = new ArrayList<>();
		
		for (Promocao promocao : promocoes) {

			double desconto = promocao.calcularDesconto(lanche);
			
			if(desconto == 0) {
				continue;
			}
			
			PromocaoAplicadaDTO promocaoAplicadaDTO = new PromocaoAplicadaDTO();
			promocaoAplicadaDTO.setNome(promocao.getNome());
			promocaoAplicadaDTO.setDesconto(desconto);
			
			promocoesAplicadas.add(promocaoAplicadaDTO);
			
		}
		
		return promocoesAplicadas;
	}

	public DetalhesLancheDTO computaDetalhesLanche(Lanche lanche) {

		List<PromocaoAplicadaDTO> promocoesAplicadas = aplicaPromocoes(lanche);
		Double totalDescontoPromocoes = promocoesAplicadas.stream().mapToDouble(x -> x.getDesconto()).sum();
		double precoFinal = lanche.precoBase() - totalDescontoPromocoes;
		
		DetalhesLancheDTO detalhesLancheDTO = new DetalhesLancheDTO();
		detalhesLancheDTO.setPromocoesAplicadas(promocoesAplicadas);
		detalhesLancheDTO.setLanche(lanche.buildDTO());
		detalhesLancheDTO.setPrecoFinal(precoFinal);
		
		return detalhesLancheDTO;
	}
}
