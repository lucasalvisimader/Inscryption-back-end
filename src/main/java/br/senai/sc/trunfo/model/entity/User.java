package br.senai.sc.trunfo.model.entity;

import br.senai.sc.trunfo.security.enums.Profile;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
    private Integer blood = 0; //max 99(reposto caso sacrifique uma carta
    private Integer bone = 0; // max 99(reposto caso uma carta é morta)
    private Integer energy = 6; // max 6(reposto a cada turno)
    @ManyToMany
    @JoinColumn(name = "id_player")
    private List<Card> cards;

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
    }
}
