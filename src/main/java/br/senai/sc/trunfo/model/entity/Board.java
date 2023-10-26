package br.senai.sc.trunfo.model.entity;

import br.senai.sc.trunfo.model.enums.PhasesBoard;
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
    @OneToMany
    private List<CardBoardPosition> playerCards;
    @OneToMany
    private List<CardBoardPosition> enemyCards;
    @OneToMany
    private List<CardBoardPosition> enemyUpComingCards;
    private PhasesBoard phasesBoard;
}
