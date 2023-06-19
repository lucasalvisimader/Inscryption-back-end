package br.senai.sc.trunfo.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOnBattleDTO {
    @NotNull
    @Min(0) @Max(2)
    private Integer life = 2;
    @NotNull
    @Min(0) @Max(12)
    private Integer blood = 0;
    @NotNull
    @Min(0) @Max(99)
    private Integer bone = 0;
    @NotNull
    @Min(0) @Max(6)
    private Integer energy = 6;
}
