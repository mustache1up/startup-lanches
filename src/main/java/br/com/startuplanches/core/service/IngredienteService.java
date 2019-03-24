package br.com.startuplanches.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.startuplanches.core.entity.Ingrediente;
import br.com.startuplanches.core.repository.IngredienteRepository;

@Service
public class IngredienteService {
	
	IngredienteRepository ingredienteRepository;

	@Autowired
	public IngredienteService(IngredienteRepository ingredienteRepository) {
		
		this.ingredienteRepository = ingredienteRepository;
	}

	public List<Ingrediente> todosIngredientes() {

		return ingredienteRepository.findAll();
	}

	public Ingrediente getIngredienteById(Long ingredienteId) {
		
		return ingredienteRepository.findById(ingredienteId).get();
	}

}
