package br.com.startuplanches.core.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import br.com.startuplanches.core.entity.Ingrediente;

public class Lanche {

	private String nome;
	private Map<Ingrediente, Integer> ingredientes;
	
	public Lanche() {
		
		ingredientes = new HashMap<>();
	}
	
	public Double precoBase() {
		
		return ingredientes.entrySet().stream()
				.mapToDouble(x -> x.getKey().getPreco() * x.getValue())
				.sum();
	}
	
	public void adicionaIngrediente(Ingrediente ingrediente) {
		
		adicionaIngrediente(ingrediente, 1);
	}
	
	public void adicionaIngrediente(Ingrediente ingrediente, Integer quantidadeAdicionada) {
		
		Integer quantidadeAtual = ingredientes.getOrDefault(ingrediente, 0);
		ingredientes.put(ingrediente, quantidadeAtual + quantidadeAdicionada);
	}

	public Integer getQuantidadeIngrediente(Ingrediente ingrediente) {
		
		return ingredientes.getOrDefault(ingrediente, 0);
	}

	public Optional<Ingrediente> ingredientePorNome(String nomeDoIngrediente) {
		
		return ingredientes.keySet().stream()
			.filter(x -> x.getNome().equalsIgnoreCase(nomeDoIngrediente))
			.findFirst();
	}
}
