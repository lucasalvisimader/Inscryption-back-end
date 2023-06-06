package br.senai.sc.trunfo.controller.card;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import br.senai.sc.trunfo.controller.CardController;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.senai.sc.trunfo.model.enums.SigilsType;
import br.senai.sc.trunfo.model.enums.ImageType;
import br.senai.sc.trunfo.service.CardService;
import br.senai.sc.trunfo.model.dto.CardDTO;
import br.senai.sc.trunfo.model.entity.Card;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Save {
    @WebMvcTest(CardController.class)
    public static class SaveControllerTest {
        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private CardService cardService;

        @Test
        public void saveControllerTest() throws Exception {
            List<SigilsType> sigilsTypes = new ArrayList<>();
            sigilsTypes.add(SigilsType.AIRBORNE);
            sigilsTypes.add(SigilsType.WORTHYSACRIFICE);
            sigilsTypes.add(SigilsType.TRIFURCATEDSTRIKE);
            CardDTO cardDTO = new CardDTO("Agourinho", 2, 3, sigilsTypes, ImageType.MAGPIE);
            Card card = new Card();
            BeanUtils.copyProperties(cardDTO, card);

            when(cardService.save(cardDTO)).thenReturn(card);
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(card);

            mockMvc.perform(post("/card/save")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").value(card));
        }
    }
}
