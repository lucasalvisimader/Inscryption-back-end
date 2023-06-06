package br.senai.sc.trunfo.model.dto;

import br.senai.sc.trunfo.model.enums.SigilsType;
import br.senai.sc.trunfo.model.enums.ImageType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;
import lombok.Data;

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
}
