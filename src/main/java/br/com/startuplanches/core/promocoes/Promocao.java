package br.com.startuplanches.core.promocoes;

import br.com.startuplanches.core.model.Lanche;

public interface Promocao {

	public double calcularDesconto(Lanche lanche);
}
