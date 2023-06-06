package br.senai.sc.trunfo.model.entity;

import br.senai.sc.trunfo.model.enums.SigilsType;
import br.senai.sc.trunfo.model.enums.ImageType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.List;
import lombok.Data;

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
}
