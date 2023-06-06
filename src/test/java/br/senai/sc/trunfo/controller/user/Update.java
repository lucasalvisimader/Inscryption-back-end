package br.senai.sc.trunfo.controller.user;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import br.senai.sc.trunfo.controller.UserController;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.senai.sc.trunfo.model.dto.UserUpdateDTO;
import br.senai.sc.trunfo.model.enums.SigilsType;
import br.senai.sc.trunfo.model.enums.ImageType;
import br.senai.sc.trunfo.service.UserService;
import br.senai.sc.trunfo.model.entity.Card;
import br.senai.sc.trunfo.model.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import java.util.List;

public class Update {
    @WebMvcTest(UserController.class)
    public static class UpdateControllerTest {
        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private UserService userService;

        @Test
        public void updateControllerTest() throws Exception {
            UserUpdateDTO userUpdateDTO = new UserUpdateDTO(List.of(new Card(1L, "aaaa", 1,
                    1, List.of(SigilsType.NONE), ImageType.ALPHA)));
            User user = new User();
            BeanUtils.copyProperties(userUpdateDTO, user);

            when(userService.update(1L, userUpdateDTO)).thenReturn(user);
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(user);

            mockMvc.perform(put("/user/update/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").value(user));
        }
    }
}
