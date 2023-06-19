package br.senai.sc.trunfo.controller.user;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import br.senai.sc.trunfo.controller.UserController;
import br.senai.sc.trunfo.model.entity.User;
import br.senai.sc.trunfo.service.UserService;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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

public class ListLogin {

    @WebMvcTest(UserController.class)
    public static class ListControllerTest {
        @Autowired
        private MockMvc mockMvc;
        @MockBean
        private UserService userService;

        @Test
        public void listControllerTest() throws Exception {
            String name = "Rakin";
            String password = "123";
            User user = new User(1L, name, password, 0,
                    0, 2, 0, 0, 6, null);

            when(userService.listLogin(name, password))
                    .thenReturn(user);

            mockMvc.perform(get("/user/listLogin/{name}/{password}", name, password)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").value(user));
        }
    }
}

