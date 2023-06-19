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
import br.senai.sc.trunfo.model.entity.Card;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class ListOne {

    @WebMvcTest(CardController.class)
    public static class ListControllerTest {
        @Autowired
        private MockMvc mockMvc;
        @MockBean
        private CardService cardService;

        @Test
        public void listControllerTest() throws Exception {
            Long id = 1L;
            List<SigilsType> sigilsTypes = new ArrayList<>();
            sigilsTypes.add(SigilsType.AIRBORNE);
            sigilsTypes.add(SigilsType.WORTHYSACRIFICE);
            sigilsTypes.add(SigilsType.TRIFURCATEDSTRIKE);
            Card card = new Card(id, "Rakin", 1, 1, sigilsTypes, ImageType.ALPHA);

            when(cardService.list(id))
                    .thenReturn(card);

            mockMvc.perform(get("/card/list/{id}", id)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").value(card));
        }
    }
}
