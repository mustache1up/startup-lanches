package br.com.startuplanches.core.model;

public class PromocaoAplicadaDTO {

	private String nome;
	private Double desconto;
	
	//getters e setters:

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}
}
