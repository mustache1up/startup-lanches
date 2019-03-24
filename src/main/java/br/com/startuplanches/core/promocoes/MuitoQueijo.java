package br.com.startuplanches.core.promocoes;

import java.util.Optional;

import br.com.startuplanches.core.entity.Ingrediente;
import br.com.startuplanches.core.model.Lanche;

public class MuitoQueijo implements Promocao {

	private static final String QUEIJO = "Queijo";

	@Override
	public double calcularDesconto(Lanche lanche) {

		Optional<Ingrediente> optionalIngrediente = lanche.ingredientePorNome(QUEIJO);
		
		if (!optionalIngrediente.isPresent()) {
			return 0;
		}
		
		Ingrediente ingrediente = optionalIngrediente.get();
		
		long quantidade = lanche.getQuantidadeIngrediente(ingrediente);
		double desconto = Math.floor(quantidade / 3) * ingrediente.getPreco();
		
		return desconto;
	}
}
