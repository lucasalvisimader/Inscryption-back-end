package br.senai.sc.trunfo.model.dto;

import br.senai.sc.trunfo.model.entity.Card;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardPositionDTO {
    @NotNull
    Card card;
    @PositiveOrZero
    Integer position;
}
