package br.com.startuplanches.core.model;

import br.com.startuplanches.core.promocoes.Promocao;

public class PromocaoAplicadaDTO {

	private Promocao promocao;
	private Double desconto;
	
	public PromocaoAplicadaDTO(Promocao promocao, Double desconto) {

		this.promocao = promocao;
		this.desconto = desconto;
	}

	public Promocao getPromocao() {
		return promocao;
	}
	
	public Double getDesconto() {
		return desconto;
	}
}
