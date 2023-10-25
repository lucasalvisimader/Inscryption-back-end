package br.senai.sc.trunfo.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOnBattleDTO {
    @NotNull
    @Min(0)
    @Max(2)
    private Integer life = 2;
    @NotNull
    @Min(0)
    @Max(4)
    private Integer blood = 0;
    @NotNull
    @Min(0)
    @Max(10)
    private Integer bone = 0;
}
