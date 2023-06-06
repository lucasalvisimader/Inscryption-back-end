package br.senai.sc.trunfo.repository;

import br.senai.sc.trunfo.model.entity.Card;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

// http://hgm.nubati.net/rules/Capablanca.html

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

}
