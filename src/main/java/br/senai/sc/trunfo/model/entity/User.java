package br.senai.sc.trunfo.model.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.List;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TUser")
//@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private Integer victories = 0;
    private Integer losses = 0;
    private Integer life = 2;
    private Integer blood = 0; //max 99(reposto caso sacrifique uma carta
    private Integer bone = 0; // max 99(reposto caso uma carta é morta)
    private Integer energy = 6; // max 6(reposto a cada turno)
    @ManyToMany
    @JoinColumn(name = "id_player")
    private List<Card> cards;
}
