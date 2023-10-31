package br.senai.sc.trunfo.model.dto;

import br.senai.sc.trunfo.model.entity.Card;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CanPutCardOnBoardDTO {
    @NotNull
    private Card card;
    private int qtdBlood;
    private int qtdBone;
}
