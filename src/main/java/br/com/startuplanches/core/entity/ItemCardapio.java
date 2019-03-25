package br.com.startuplanches.core.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ItemCardapio {

	@Id
	private Long id;
	private String nome;

	@OneToMany(mappedBy = "itemCardapio", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<IngredienteItemCardapio> ingredientesItemCardapio;

	public ItemCardapio() {

		ingredientesItemCardapio = new ArrayList<>();
	}

	// getters e setters

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<IngredienteItemCardapio> getIngredientesItemCardapio() {
		return ingredientesItemCardapio;
	}

	public void setIngredientesItemCardapio(List<IngredienteItemCardapio> ingredientesItemCardapio) {
		this.ingredientesItemCardapio = ingredientesItemCardapio;
	}
}
