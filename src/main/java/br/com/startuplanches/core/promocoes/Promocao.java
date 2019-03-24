package br.com.startuplanches.core.promocoes;

import br.com.startuplanches.core.model.Lanche;

public interface Promocao {

	public String getNome();
	public double calcularDesconto(Lanche lanche);
}
