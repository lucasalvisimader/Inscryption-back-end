package br.senai.sc.trunfo.model.dto;

import br.senai.sc.trunfo.model.enums.ImageType;
import br.senai.sc.trunfo.model.enums.PriceType;
import br.senai.sc.trunfo.model.enums.SigilsType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {
    @NotEmpty
    private String name;
    @NotNull
    @PositiveOrZero
    private Integer power;
    @NotNull
    @Positive
    private Integer health;
    @NotNull
    private List<SigilsType> sigilsTypes;
    @NotNull
    private ImageType imageType;
    @NotNull
    private PriceType priceType;
}
