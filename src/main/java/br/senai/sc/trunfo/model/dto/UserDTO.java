package br.senai.sc.trunfo.model.dto;

import br.senai.sc.trunfo.model.entity.Card;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private List<Card> cards;
}
