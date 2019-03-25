package br.com.startuplanches.core.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import br.com.startuplanches.core.entity.Ingrediente;

public class Lanche {

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

	public Optional<Ingrediente> ingredienteContendoPalavra(String nomeDoIngrediente) {
		
		return ingredientes.keySet().stream()
			.filter(x -> x.getNome().toLowerCase().contains(nomeDoIngrediente.toLowerCase()))
			.findFirst();
	}
	
	public int totalIngredientes() {
		
		return ingredientes.values().stream()
			.mapToInt(x -> x)
			.sum();
	}
	
	// getters e setters	

	public Map<Ingrediente, Integer> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(Map<Ingrediente, Integer> ingredientes) {
		this.ingredientes = ingredientes;
	}

}
