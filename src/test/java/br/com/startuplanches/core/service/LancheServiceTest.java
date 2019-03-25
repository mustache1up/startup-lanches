package br.com.startuplanches.core.service;

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
import br.com.startuplanches.core.service.LancheService;
import br.com.startuplanches.core.service.PromocaoService;

public class LancheServiceTest {

	LancheService lancheService;
	
	private static final double DELTA = 0.000_000_001;

	private Ingrediente queijo;

	private List<Promocao> promocoes;

	private Promocao promocaoMuitoQueijo;
	private Promocao promocaoTudoPelaMetadeDoPreco;
	
	@Before
	public void setUp() {
		
		queijo = new Ingrediente();
		queijo.setId(1L);
		queijo.setNome("Queijo");
		queijo.setPreco(1D);
		
		promocaoMuitoQueijo = new MuitoQueijo();
		promocaoTudoPelaMetadeDoPreco = new TudoPelaMetadeDoPreco();
		
		promocoes = Arrays.asList(promocaoTudoPelaMetadeDoPreco, promocaoMuitoQueijo);
		
		PromocaoService promocaoService = new PromocaoService(promocoes);
		lancheService = new LancheService(promocaoService, null, null);
	}

	@Test
	public void lancheValorZeroPermaneceZeroAposPromocaoPercentual() {
		
		Lanche lanche = new Lanche();
		
		DetalhesLancheDTO detalhesLanches = lancheService.computaDetalhesLanche(lanche);
		
		assertEquals(0, detalhesLanches.getPromocoesAplicadas().size());
				
		assertEquals(0, detalhesLanches.getPrecoFinal(), DELTA);
	}
	
	@Test
	public void lanchePodeCairEmUmaPromocaoENaoCairEmOutras() {
		
		Lanche lanche = new Lanche();
		lanche.adicionaIngrediente(queijo, 2);
		
		DetalhesLancheDTO detalhesLanches = lancheService.computaDetalhesLanche(lanche);
		
		assertEquals(1, detalhesLanches.getPromocoesAplicadas().size());
		assertTrue(contemPromocao(detalhesLanches, promocaoTudoPelaMetadeDoPreco));
		
		assertEquals(1, detalhesLanches.getPrecoFinal(), DELTA);
	}
	
	@Test
	public void lanchePodeCairEmMaisDeUmaPromocao() {
		
		Lanche lanche = new Lanche();
		lanche.adicionaIngrediente(queijo, 8);
		
		DetalhesLancheDTO detalhesLanches = lancheService.computaDetalhesLanche(lanche);
		
		assertEquals(2, detalhesLanches.getPromocoesAplicadas().size());
		assertTrue(contemPromocao(detalhesLanches, promocaoTudoPelaMetadeDoPreco));
		assertTrue(contemPromocao(detalhesLanches, promocaoMuitoQueijo));
		
		assertEquals(2, detalhesLanches.getPrecoFinal(), DELTA);
	}

	public boolean contemPromocao(DetalhesLancheDTO detalhesLanches, Promocao promocaoTudoPelaMetadeDoPreco) {
		
		return detalhesLanches.getPromocoesAplicadas().stream()
				.anyMatch(x -> x.getNome().equals(promocaoTudoPelaMetadeDoPreco.getNome()));
	}
}
