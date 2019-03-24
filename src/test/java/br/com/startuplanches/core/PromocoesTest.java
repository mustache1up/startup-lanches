package br.com.startuplanches.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.startuplanches.core.dto.DetalhesLancheDTO;
import br.com.startuplanches.core.entity.Ingrediente;
import br.com.startuplanches.core.model.Lanche;
import br.com.startuplanches.core.promocoes.MuitoQueijo;
import br.com.startuplanches.core.promocoes.Promocao;
import br.com.startuplanches.core.promocoes.TudoPelaMetadeDoPreco;
import br.com.startuplanches.core.service.PromocaoService;

/**
 * Existe uma exceção à regra para o cálculo de preço, quando o lanche pertencer
 * à uma promoção. Cada promoção tem sua regra.
 */
public class PromocoesTest {

	PromocaoService cardapioService;
	
	private static final double DELTA = 0.000_000_001;

	private Ingrediente queijo;

	private List<Promocao> promocoes;

	private Promocao promocaoMuitoQueijo;
	private Promocao promocaoTudoPelaMetadeDoPreco;
	
	@Before
	public void setUp() {
		
		queijo = new Ingrediente();
		queijo.setNome("Queijo");
		queijo.setPreco(1D);
		
		promocaoMuitoQueijo = new MuitoQueijo();
		promocaoTudoPelaMetadeDoPreco = new TudoPelaMetadeDoPreco();
		
		promocoes = Arrays.asList(promocaoTudoPelaMetadeDoPreco, promocaoMuitoQueijo);
		
		cardapioService = new PromocaoService(promocoes);
	}

	@Test
	public void lancheValorZeroPermaneceZeroAposPromocaoPercentual() {
		
		Lanche lanche = new Lanche();
		
		DetalhesLancheDTO detalhesLanches = cardapioService.computaDetalhesLanche(lanche);
		
		assertEquals(0, detalhesLanches.getPromocoesAplicadas().size());
				
		assertEquals(0, detalhesLanches.getPrecoFinal(), DELTA);
	}
	
	@Test
	public void lanchePodeCairEmUmaPromocaoENaoCairEmOutras() {
		
		Lanche lanche = new Lanche();
		lanche.adicionaIngrediente(queijo, 2);
		
		DetalhesLancheDTO detalhesLanches = cardapioService.computaDetalhesLanche(lanche);
		
		assertEquals(1, detalhesLanches.getPromocoesAplicadas().size());
		assertTrue(detalhesLanches.contemPromocao(promocaoTudoPelaMetadeDoPreco));
		
		assertEquals(1, detalhesLanches.getPrecoFinal(), DELTA);
	}
	
	@Test
	public void lanchePodeCairEmMaisDeUmaPromocao() {
		
		Lanche lanche = new Lanche();
		lanche.adicionaIngrediente(queijo, 8);
		
		DetalhesLancheDTO detalhesLanches = cardapioService.computaDetalhesLanche(lanche);
		
		assertEquals(2, detalhesLanches.getPromocoesAplicadas().size());
		assertTrue(detalhesLanches.contemPromocao(promocaoTudoPelaMetadeDoPreco));
		assertTrue(detalhesLanches.contemPromocao(promocaoMuitoQueijo));
		
		assertEquals(2, detalhesLanches.getPrecoFinal(), DELTA);
	}

}
