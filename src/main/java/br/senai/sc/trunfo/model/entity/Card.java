package br.senai.sc.trunfo.model.entity;

import br.senai.sc.trunfo.model.enums.ImageType;
import br.senai.sc.trunfo.model.enums.PriceType;
import br.senai.sc.trunfo.model.enums.SigilsType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TCard")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer power;
    private Integer health;
    private List<SigilsType> sigilsTypes;
    private ImageType imageType;
    private PriceType priceType;
    @ManyToMany(mappedBy = "cards")
    private List<User> users;
}
