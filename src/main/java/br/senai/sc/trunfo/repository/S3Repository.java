package br.senai.sc.trunfo.repository;

import br.senai.sc.trunfo.model.entity.Card;
import br.senai.sc.trunfo.model.entity.ImageCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface S3Repository extends JpaRepository<ImageCard, Long> {
    ImageCard findByCard(Card card);
}
