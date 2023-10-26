package br.senai.sc.trunfo.repository;

import br.senai.sc.trunfo.model.entity.CardBoardPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardBoardPositionRepository extends JpaRepository<CardBoardPosition, Long> {
}
