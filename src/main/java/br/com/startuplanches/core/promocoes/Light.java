package br.com.startuplanches.core.promocoes;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.startuplanches.core.entity.Ingrediente;
import br.com.startuplanches.core.model.Lanche;

@Component
public class Light implements Promocao {

	private static final String NOME = "Light"; 
	private static final String ALFACE = "Alface";
	private static final String BACON = "Bacon";

	@Override
	public double calcularDesconto(Lanche lanche) {

		if (entraNaPromocao(lanche)) {
			return lanche.precoBase() * 10.0 / 100.0;
		}
		
		return 0;
	}

	private boolean entraNaPromocao(Lanche lanche) {
		
		Optional<Ingrediente> alface = lanche.ingredienteContendoPalavra(ALFACE);
		Optional<Ingrediente> bacon = lanche.ingredienteContendoPalavra(BACON);
		
		return alface.isPresent() && !bacon.isPresent();
	}
	
	@Override
	public String getNome() {
		
		return NOME;
	}
}
