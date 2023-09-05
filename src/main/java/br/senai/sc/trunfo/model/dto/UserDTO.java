package br.senai.sc.trunfo.model.dto;

import br.senai.sc.trunfo.security.enums.Profile;
import jakarta.validation.constraints.NotEmpty;
import br.senai.sc.trunfo.model.entity.Card;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
    private List<Card> cards;
    private List<Profile> authorities;
}
