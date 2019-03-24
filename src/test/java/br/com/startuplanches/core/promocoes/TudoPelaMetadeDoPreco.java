package br.com.startuplanches.core.promocoes;

import br.com.startuplanches.core.model.Lanche;

public class TudoPelaMetadeDoPreco implements Promocao {

	
	private static final String NOME = "Tudo pela metade do preço";

	@Override
	public double calcularDesconto(Lanche lanche) {

		return lanche.precoBase() / 2;
	}
	
	@Override
	public String getNome() {
		
		return NOME;
	}
}
