package br.senai.sc.trunfo.controller.card;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import br.senai.sc.trunfo.controller.CardController;
import br.senai.sc.trunfo.model.enums.SigilsType;
import br.senai.sc.trunfo.model.enums.ImageType;
import br.senai.sc.trunfo.service.CardService;
import static org.hamcrest.Matchers.hasItems;
import br.senai.sc.trunfo.model.entity.Card;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class ListAll {

    @WebMvcTest(CardController.class)
    public static class ListAllControllerTest {
        @Autowired
        private MockMvc mockMvc;
        @MockBean
        private CardService cardService;

        @Test
        public void listAllControllerTest() throws Exception {
            List<SigilsType> sigilsTypes = new ArrayList<>();

            ImageType imageType = ImageType.ALPHA;
            Card card1 = new Card(1L, "Rakin", 1, 1, sigilsTypes, imageType);
            Card card2 = new Card(2L, "Rakin2", 2, 5, sigilsTypes, imageType);
            Card card3 = new Card(3L, "Rakin3", 3, 4, sigilsTypes, imageType);

            List<Card> cards = List.of(card1, card2, card3);

            sigilsTypes.add(SigilsType.AIRBORNE);
            sigilsTypes.add(SigilsType.WORTHYSACRIFICE);
            sigilsTypes.add(SigilsType.TRIFURCATEDSTRIKE);

            when(cardService.listAll())
                    .thenReturn(cards);

            mockMvc.perform(get("/card/listAll")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0]").value(card1))
                    .andExpect(jsonPath("$[1]").value(card2))
                    .andExpect(jsonPath("$[2]").value(card3));
        }
    }
}

