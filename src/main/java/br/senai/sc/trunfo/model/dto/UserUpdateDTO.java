package br.senai.sc.trunfo.model.dto;

import br.senai.sc.trunfo.model.entity.Card;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {
    private List<Card> cards;
}
