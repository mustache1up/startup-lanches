package br.com.startuplanches.core.dto;

import br.com.startuplanches.core.entity.Ingrediente;

public class IngredienteDTO {

	private Long id;
	private String nome;
	private Double preco;
	private Integer quantidade;

	public static IngredienteDTO build(Ingrediente ingrediente, Integer quantidade) {

		IngredienteDTO ingredienteDTO = new IngredienteDTO();
		ingredienteDTO.setId(ingrediente.getId());
		ingredienteDTO.setNome(ingrediente.getNome());
		ingredienteDTO.setPreco(ingrediente.getPreco());
		ingredienteDTO.setQuantidade(quantidade);
		return ingredienteDTO;
	}

	// getters e setters:

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
