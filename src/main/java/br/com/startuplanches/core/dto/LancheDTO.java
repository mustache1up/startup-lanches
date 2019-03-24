package br.com.startuplanches.core.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.startuplanches.core.model.Lanche;

public class LancheDTO {

	private Long id;
	private String nome;
	private List<IngredienteDTO> ingredientes;
	
	public static LancheDTO build(Lanche lanche) {
		
		LancheDTO lancheDTO = new LancheDTO();
		lancheDTO.setId(lanche.getId());
		lancheDTO.setNome(lanche.getNome());
		
		List<IngredienteDTO> ingredientesDTO = lanche.getIngredientes().entrySet().stream()
				.map(x -> IngredienteDTO.build(x.getKey(), x.getValue()))
				.collect(Collectors.toList());
		lancheDTO.setIngredientes(ingredientesDTO);
		
		return lancheDTO;
	}
	
	//getters and setters

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

	public List<IngredienteDTO> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<IngredienteDTO> ingredientes) {
		this.ingredientes = ingredientes;
	}


}
