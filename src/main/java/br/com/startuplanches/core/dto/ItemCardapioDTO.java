package br.com.startuplanches.core.dto;

import br.com.startuplanches.core.entity.ItemCardapio;

public class ItemCardapioDTO {

	private Long id;
	private String nome;
	
	public static ItemCardapioDTO build(ItemCardapio itemCardapio) {

		ItemCardapioDTO itemCardapioDTO = new ItemCardapioDTO();
		
		itemCardapioDTO.setId(itemCardapio.getId());
		itemCardapioDTO.setNome(itemCardapio.getNome());
		
		return itemCardapioDTO;
	}
	
	// getters e setters

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
}
