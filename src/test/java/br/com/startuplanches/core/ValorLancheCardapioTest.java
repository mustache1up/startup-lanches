package br.com.startuplanches.core;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.startuplanches.core.entity.Ingrediente;
import br.com.startuplanches.core.model.Lanche;

/**
 * O valor de cada opção do cardápio é dado pela soma dos ingredientes que compõe o lanche.
 */
public class ValorLancheCardapioTest {

	private static final double DELTA = 0.000_000_001;

	@Test
	public void lancheSemIngredientesTemValorZero() {

		Lanche lanche = new Lanche();
		
		assertEquals(0, lanche.precoBase(), DELTA);
	}
	
	@Test
	public void lancheCom1IngredienteTemMesmoValorDoIngrediente() {
		
		Lanche lanche = new Lanche();
		Ingrediente ingrediente = new Ingrediente();
		ingrediente.setPreco(12.34);
		lanche.adicionaIngrediente(ingrediente);
		
		assertEquals(12.34, lanche.precoBase(), DELTA);
	}
	
	@Test
	public void lancheCom3IngredienteTemOValorDaSomaDosPrecosDosIngredientes() {
		
		Lanche lanche = new Lanche();
		
		Ingrediente ingrediente1 = new Ingrediente();
		ingrediente1.setPreco(12.34);
		lanche.adicionaIngrediente(ingrediente1);
		
		Ingrediente ingrediente2 = new Ingrediente();
		ingrediente2.setPreco(01.23);
		lanche.adicionaIngrediente(ingrediente2);
		
		Ingrediente ingrediente3 = new Ingrediente();
		ingrediente3.setPreco(23.45);
		lanche.adicionaIngrediente(ingrediente3);
		
		assertEquals(37.02, lanche.precoBase(), DELTA);
	}

}
