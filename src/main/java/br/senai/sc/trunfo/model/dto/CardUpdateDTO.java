package br.senai.sc.trunfo.model.dto;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardUpdateDTO {
    @NotEmpty
    private String name;
    @NotNull
    @PositiveOrZero
    private Integer power;
    @NotNull
    @Positive
    private Integer health;
}
