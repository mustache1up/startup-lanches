package br.com.startuplanches.core.promocoes;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.startuplanches.core.entity.Ingrediente;
import br.com.startuplanches.core.model.Lanche;

@Component
public class MuitoQueijo implements Promocao {

	private static final String NOME = "Muito Queijo"; 
	private static final String QUEIJO = "Queijo";

	@Override
	public double calcularDesconto(Lanche lanche) {

		Optional<Ingrediente> optionalIngrediente = lanche.ingredienteContendoPalavra(QUEIJO);
		
		if (!optionalIngrediente.isPresent()) {
			return 0;
		}
		
		Ingrediente ingrediente = optionalIngrediente.get();
		
		long quantidade = lanche.getQuantidadeIngrediente(ingrediente);
		double desconto = Math.floor(quantidade / 3) * ingrediente.getPreco();
		
		return desconto;
	}
	
	@Override
	public String getNome() {
		
		return NOME;
	}
}
