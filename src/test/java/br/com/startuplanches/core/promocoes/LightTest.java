package br.com.startuplanches.core.promocoes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.startuplanches.core.entity.Ingrediente;
import br.com.startuplanches.core.model.Lanche;

public class LightTest {

	private static final double DELTA = 0.000_000_001;
	
	private Ingrediente alface;
	private Ingrediente bacon;
	private Ingrediente carne;

	private Promocao promocao;
	
	@Before
	public void setUp() {
		
		promocao = new Light();
		
		alface = new Ingrediente();
		alface.setId(1L);
		alface.setNome("Alface");
		alface.setPreco(0.5);
		
		carne = new Ingrediente();
		carne.setId(2L);
		carne.setNome("Hamburguer de Carne");
		carne.setPreco(1.0);
		
		bacon = new Ingrediente();
		bacon.setId(3L);
		bacon.setNome("Bacon");
		bacon.setPreco(1.2);
	}
	
	@Test
	public void lancheSemAlfaceNemBaconNaoCaiNaPromocao() {
		
		Lanche lanche = new Lanche();
		lanche.adicionaIngrediente(carne);
		
		Double desconto = promocao.calcularDesconto(lanche);
		assertEquals(0, desconto, DELTA);
	}
	
	@Test
	public void lancheComAlfaceEComBaconNaoCaiNaPromocao() {
		
		Lanche lanche = new Lanche();
		lanche.adicionaIngrediente(carne);
		lanche.adicionaIngrediente(alface);
		lanche.adicionaIngrediente(bacon);
		
		Double desconto = promocao.calcularDesconto(lanche);
		assertEquals(0, desconto, DELTA);
	}
	
	@Test
	public void lancheComAlfaceESemBaconTemDescontoDeDezPorcento() {
		
		Lanche lanche = new Lanche();
		lanche.adicionaIngrediente(carne);
		lanche.adicionaIngrediente(alface);
		
		double descontoEsperado = lanche.precoBase() * 10.0 / 100.0;
		Double desconto = promocao.calcularDesconto(lanche);
		
		assertEquals(descontoEsperado, desconto, DELTA);
	}
}
