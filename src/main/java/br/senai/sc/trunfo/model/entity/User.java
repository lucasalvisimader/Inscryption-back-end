package br.senai.sc.trunfo.model.entity;

import br.senai.sc.trunfo.model.enums.PhasesBoard;
import br.senai.sc.trunfo.security.enums.Profile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@Table(name = "TUser")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private Integer victories = 0;
    private Integer losses = 0;
    private Integer life = 2;
    private Integer blood = 0; //max 4(reposto caso sacrifique uma carta
    private Integer bone = 0; // max 10(reposto caso uma carta é morta)
    @ManyToMany
    @JoinColumn(name = "id_player")
    private List<Card> cards;
    @OneToOne(cascade = CascadeType.ALL)
    private Board board;

    @Enumerated(EnumType.STRING)
    private List<Profile> authorities;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    public User() {
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isCredentialsNonExpired = true;
        this.isEnabled = true;
        this.board = new Board();
        this.board.setPhasesBoard(PhasesBoard.DEFAULT);
    }
}
