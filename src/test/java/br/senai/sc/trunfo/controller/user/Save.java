package br.senai.sc.trunfo.controller.user;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import br.senai.sc.trunfo.controller.UserController;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.senai.sc.trunfo.service.UserService;
import br.senai.sc.trunfo.model.dto.UserDTO;
import br.senai.sc.trunfo.model.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;

public class Save {
    @WebMvcTest(UserController.class)
    public static class SaveControllerTest {
        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private UserService userService;

        @Test
        public void saveControllerTest() throws Exception {
            UserDTO userDTO = new UserDTO("Agourinho", "123", null);
            User user = new User();
            BeanUtils.copyProperties(userDTO, user);

            when(userService.save(userDTO)).thenReturn(user);
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(user);

            mockMvc.perform(post("/user/save")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").value(user));
        }
    }
}
