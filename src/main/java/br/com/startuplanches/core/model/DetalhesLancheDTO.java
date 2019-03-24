package br.com.startuplanches.core.model;

import java.util.ArrayList;
import java.util.List;

import br.com.startuplanches.core.promocoes.Promocao;

public class DetalhesLancheDTO {

	private Lanche lanche;
	private List<PromocaoAplicadaDTO> promocoesAplicadas;
	private Double precoFinal;
	
	public DetalhesLancheDTO() {
		promocoesAplicadas = new ArrayList<>();
	}
	
	public boolean contemPromocao(Promocao promocaoTudoPelaMetadeDoPreco) {
		
		return promocoesAplicadas.stream()
				.anyMatch(x -> x.getPromocao().equals(promocaoTudoPelaMetadeDoPreco));
	}
	
	// getters e setters:

	public Lanche getLanche() {
		return lanche;
	}

	public void setLanche(Lanche lanche) {
		this.lanche = lanche;
	}

	public List<PromocaoAplicadaDTO> getPromocoesAplicadas() {
		return promocoesAplicadas;
	}

	public void setPromocoesAplicadas(List<PromocaoAplicadaDTO> promocoesAplicadas) {
		this.promocoesAplicadas = promocoesAplicadas;
	}

	public Double getPrecoFinal() {
		return precoFinal;
	}

	public void setPrecoFinal(Double precoFinal) {
		this.precoFinal = precoFinal;
	}
}
