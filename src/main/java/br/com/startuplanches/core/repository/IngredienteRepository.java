package br.com.startuplanches.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.startuplanches.core.entity.Ingrediente;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long>{

}
