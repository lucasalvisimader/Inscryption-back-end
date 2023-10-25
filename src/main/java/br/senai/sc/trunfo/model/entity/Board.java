package br.senai.sc.trunfo.model.entity;

import br.senai.sc.trunfo.model.enums.PhasesBoard;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TBoard")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private List<Card> playerCards;
    private List<Card> enemyCards;
    private List<Card> enemyUpComingCards;
    private PhasesBoard phasesBoard;
    @OneToMany(mappedBy = "board")
    @JsonIgnore
    private List<Card> cards;
}
