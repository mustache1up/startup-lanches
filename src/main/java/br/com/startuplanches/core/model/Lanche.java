package br.com.startuplanches.core.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.startuplanches.core.entity.Ingrediente;

public class Lanche {

	private Long id;
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
	
	public int totalIngredientes() {
		
		return ingredientes.values().stream()
				.mapToInt(x -> x)
				.sum();
	}
	
	public LancheDTO buildDTO() {
		LancheDTO lancheDTO = new LancheDTO();
		lancheDTO.setId(id);
		lancheDTO.setNome(nome);
		
		List<IngredienteDTO> ingredientesDTO = ingredientes.entrySet().stream()
				.map(ingrediente -> convertToIngredienteDTO(ingrediente))
				.collect(Collectors.toList());
		lancheDTO.setIngredientes(ingredientesDTO);
		
		return lancheDTO;
	}

	private IngredienteDTO convertToIngredienteDTO(Entry<Ingrediente, Integer> ingredienteEntry) {

		IngredienteDTO ingredienteDTO = new IngredienteDTO();
		Ingrediente ingrediente = ingredienteEntry.getKey();
		ingredienteDTO.setNome(ingrediente.getNome());
		ingredienteDTO.setPreco(ingrediente.getPreco());
		ingredienteDTO.setQuantidade(ingredienteEntry.getValue());
		return ingredienteDTO;
	}
	
	//getters e setters

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

	public Map<Ingrediente, Integer> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(Map<Ingrediente, Integer> ingredientes) {
		this.ingredientes = ingredientes;
	}
}
