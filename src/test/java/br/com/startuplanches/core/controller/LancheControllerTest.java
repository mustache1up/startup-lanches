package br.com.startuplanches.core.controller;

import static org.junit.Assert.assertEquals;

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
import br.com.startuplanches.core.dto.LancheDTO;

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
	}

	@Test
	public void endpointLanchesComIdDeveRetornarDetalhesDoLancheDeMesmoId() throws Exception {

		String lancheId = "1";
		
		ResponseEntity<DetalhesLancheDTO> entity = this.testRestTemplate
				.getForEntity("http://localhost:" + this.port + "/api/lanches/" + lancheId , DetalhesLancheDTO.class);

		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}
}