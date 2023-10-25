package br.senai.sc.trunfo.model.dto;

import br.senai.sc.trunfo.model.enums.PhasesBoard;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    @NotNull
    private PhasesBoard phasesBoard;
}
