package br.senai.sc.trunfo.controller.card;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import br.senai.sc.trunfo.controller.CardController;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.senai.sc.trunfo.model.dto.CardUpdateDTO;
import br.senai.sc.trunfo.model.enums.SigilsType;
import br.senai.sc.trunfo.service.CardService;
import br.senai.sc.trunfo.model.entity.Card;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Update {
    @WebMvcTest(CardController.class)
    public static class UpdateControllerTest {
        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private CardService cardService;

        @Test
        public void updateControllerTest() throws Exception {
            List<SigilsType> sigilsTypes = new ArrayList<>();
            sigilsTypes.add(SigilsType.AIRBORNE);
            sigilsTypes.add(SigilsType.WORTHYSACRIFICE);
            sigilsTypes.add(SigilsType.TRIFURCATEDSTRIKE);
            CardUpdateDTO cardUpdateDTO = new CardUpdateDTO("Agourinho", 2, 3);
            Card card = new Card();
            BeanUtils.copyProperties(cardUpdateDTO, card);

            when(cardService.update(1L, cardUpdateDTO)).thenReturn(card);
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(card);

            mockMvc.perform(put("/card/update/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").value(card));
        }
    }
}
