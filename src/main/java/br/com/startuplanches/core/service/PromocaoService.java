package br.com.startuplanches.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.startuplanches.core.dto.PromocaoAplicadaDTO;
import br.com.startuplanches.core.model.Lanche;
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
}
