package br.com.startuplanches.core.controller;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.startuplanches.core.dto.DetalhesLancheDTO;
import br.com.startuplanches.core.dto.IngredienteDTO;
import br.com.startuplanches.core.dto.LancheDTO;
import br.com.startuplanches.core.dto.PromocaoAplicadaDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "management.port=0" })
public class LancheControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	public void endpointLanchesSemIdDeveRetornarListaDeOpcoesDoCardapio() throws Exception {
		
		ResponseEntity<LancheDTO[]> entity = this.testRestTemplate
				.getForEntity("http://localhost:" + this.port + "/api/lanches/", LancheDTO[].class);
		
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		List<LancheDTO> lanches = Arrays.asList(entity.getBody());
		
		lanches.sort((x, y) -> x.getNome().compareToIgnoreCase(y.getNome()));
		
		assertEquals(2, lanches.size());
		assertEquals("X-Bacon", lanches.get(0).getNome());
		assertEquals("X-Salada", lanches.get(1).getNome());
	}

	@Test
	public void endpointLanchesComIdDeveRetornarDetalhesDoLancheDeMesmoId() throws Exception {

		String lancheId = "1";
		
		ResponseEntity<DetalhesLancheDTO> entity = this.testRestTemplate
				.getForEntity("http://localhost:" + this.port + "/api/lanches/" + lancheId , DetalhesLancheDTO.class);

		assertEquals(HttpStatus.OK, entity.getStatusCode());
        DetalhesLancheDTO detalhesLancheDTO = entity.getBody();
        
        assertEquals(1, detalhesLancheDTO.getLanche().getId().longValue());
        assertEquals("Id: 1", detalhesLancheDTO.getLanche().getNome());
        
        List<IngredienteDTO> ingredientes = detalhesLancheDTO.getLanche().getIngredientes();
        ingredientes.sort((x, y) -> x.getNome().compareToIgnoreCase(y.getNome()));
		
        assertEquals(1, ingredientes.size());
        assertEquals("Queijo", ingredientes.get(0).getNome());
        assertEquals(3, ingredientes.get(0).getQuantidade().intValue());
        
        List<PromocaoAplicadaDTO> promocoesAplicadas = detalhesLancheDTO.getPromocoesAplicadas();
        promocoesAplicadas.sort((x, y) -> x.getNome().compareToIgnoreCase(y.getNome()));

        assertEquals(1, promocoesAplicadas.size());
        assertEquals("Muito Queijo", promocoesAplicadas.get(0).getNome());
        assertEquals(1.5, promocoesAplicadas.get(0).getDesconto().doubleValue(), 0);
        
        assertEquals(3, detalhesLancheDTO.getPrecoFinal().doubleValue(), 0);
	}
}