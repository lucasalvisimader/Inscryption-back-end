package br.senai.sc.trunfo.repository;

import br.senai.sc.trunfo.model.entity.Card;
import br.senai.sc.trunfo.model.enums.ImageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByImageType(ImageType imageType);
}
