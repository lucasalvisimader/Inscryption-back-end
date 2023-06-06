package br.senai.sc.trunfo.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRankingUpdateDTO {
    @NotNull
    @Min(0)
    private Integer victories;
    @NotNull
    @Min(0)
    private Integer losses;
}
