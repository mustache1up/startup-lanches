package br.com.startuplanches.core.promocoes;

import br.com.startuplanches.core.model.Lanche;

public class TudoPelaMetadeDoPreco implements Promocao {

	@Override
	public double calcularDesconto(Lanche lanche) {

		return lanche.precoBase() / 2;
	}
}
