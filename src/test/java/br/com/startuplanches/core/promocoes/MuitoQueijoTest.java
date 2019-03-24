package br.com.startuplanches.core.promocoes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.startuplanches.core.entity.Ingrediente;
import br.com.startuplanches.core.model.Lanche;

public class MuitoQueijoTest {

	private static final double DELTA = 0.000_000_001;
	
	private Ingrediente queijo;

	private Promocao promocao;
	
	@Before
	public void setUp() {
		
		promocao = new MuitoQueijo();
		
		queijo = new Ingrediente();
		queijo.setNome("Queijo");
		queijo.setPreco(1D);
	}
	
	@Test
	public void lancheComUmQueijoNaoCaiNaPromocao() {
		
		Lanche lanche = new Lanche();
		lanche.adicionaIngrediente(queijo);
		
		Double desconto = promocao.calcularDesconto(lanche);
		assertEquals(0, desconto, DELTA);
	}
	
	@Test
	public void lancheComTresQueijosTemDescontoDeUmQueijo() {
		
		Lanche lanche = new Lanche();
		lanche.adicionaIngrediente(queijo, 3);
		
		Double desconto = promocao.calcularDesconto(lanche);
		assertEquals(queijo.getPreco(), desconto, DELTA);
	}
	
	@Test
	public void lancheComSeisQueijosTemDescontoDeDoisQueijos() {
		
		Lanche lanche = new Lanche();
		lanche.adicionaIngrediente(queijo, 6);
		
		Double desconto = promocao.calcularDesconto(lanche);
		assertEquals(queijo.getPreco() * 2, desconto, DELTA);
	}
}
