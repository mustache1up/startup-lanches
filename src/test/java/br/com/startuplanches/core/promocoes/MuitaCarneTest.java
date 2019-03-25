package br.com.startuplanches.core.promocoes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.startuplanches.core.entity.Ingrediente;
import br.com.startuplanches.core.model.Lanche;

public class MuitaCarneTest {

	private static final double DELTA = 0.000_000_001;
	
	private Ingrediente carne;

	private Promocao promocao;
	
	@Before
	public void setUp() {
		
		promocao = new MuitaCarne();
		
		carne = new Ingrediente();
		carne.setNome("Hamburguer de Carne");
		carne.setPreco(1D);
	}
	
	@Test
	public void lancheComUmQueijoNaoCaiNaPromocao() {
		
		Lanche lanche = new Lanche();
		lanche.adicionaIngrediente(carne);
		
		Double desconto = promocao.calcularDesconto(lanche);
		assertEquals(0, desconto, DELTA);
	}
	
	@Test
	public void lancheComTresQueijosTemDescontoDeUmQueijo() {
		
		Lanche lanche = new Lanche();
		lanche.adicionaIngrediente(carne, 3);
		
		Double desconto = promocao.calcularDesconto(lanche);
		assertEquals(carne.getPreco(), desconto, DELTA);
	}
	
	@Test
	public void lancheComSeisQueijosTemDescontoDeDoisQueijos() {
		
		Lanche lanche = new Lanche();
		lanche.adicionaIngrediente(carne, 6);
		
		Double desconto = promocao.calcularDesconto(lanche);
		assertEquals(carne.getPreco() * 2, desconto, DELTA);
	}
}
