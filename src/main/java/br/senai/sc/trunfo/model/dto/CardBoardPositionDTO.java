package br.senai.sc.trunfo.model.dto;

import br.senai.sc.trunfo.model.entity.Card;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardBoardPositionDTO {
    @NotNull
    Card card;
    @Positive
    short position;
}
