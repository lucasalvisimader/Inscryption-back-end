package br.senai.sc.trunfo.controller.card;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import br.senai.sc.trunfo.controller.CardController;
import br.senai.sc.trunfo.model.enums.SigilsType;
import br.senai.sc.trunfo.model.enums.ImageType;
import br.senai.sc.trunfo.service.CardService;
import br.senai.sc.trunfo.model.dto.CardDTO;
import br.senai.sc.trunfo.model.entity.Card;
import org.springframework.beans.BeanUtils;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Delete {
    @WebMvcTest(CardController.class)
    public static class DeleteControllerTest {
        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private CardService cardService;

        @Test
        public void deleteControllerTest() throws Exception {
            List<SigilsType> sigilsTypes = new ArrayList<>();
            sigilsTypes.add(SigilsType.AIRBORNE);
            sigilsTypes.add(SigilsType.WORTHYSACRIFICE);
            sigilsTypes.add(SigilsType.TRIFURCATEDSTRIKE);
            CardDTO cardDTO = new CardDTO("Agourinho", 2, 3, sigilsTypes, ImageType.MAGPIE);
            Card card = new Card();
            BeanUtils.copyProperties(cardDTO, card);

            mockMvc.perform(delete("/card/delete/1"))
                    .andExpect(status().isOk());
        }
    }
}
