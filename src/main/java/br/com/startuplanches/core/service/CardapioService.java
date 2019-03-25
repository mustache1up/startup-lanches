package br.com.startuplanches.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.startuplanches.core.entity.ItemCardapio;
import br.com.startuplanches.core.repository.ItemCardapioRepository;

@Service
public class CardapioService {

	private ItemCardapioRepository itemCardapioRepository;

	@Autowired
	public CardapioService(ItemCardapioRepository itemCardapioRepository) {
		
		this.itemCardapioRepository = itemCardapioRepository;
	}

	public List<ItemCardapio> todosItensCardapio() {

		return itemCardapioRepository.findAll();
	}

	public ItemCardapio getItemCardapioById(Long itemCardapioId) {
		
		return itemCardapioRepository.findById(itemCardapioId).get();
	}
}
