package br.senai.sc.trunfo.controller.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import br.senai.sc.trunfo.controller.UserController;
import br.senai.sc.trunfo.service.UserService;
import br.senai.sc.trunfo.model.dto.UserDTO;
import br.senai.sc.trunfo.model.entity.User;
import org.springframework.beans.BeanUtils;
import org.junit.jupiter.api.Test;

public class Delete {
    @WebMvcTest(UserController.class)
    public static class DeleteControllerTest {
        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private UserService userService;

        @Test
        public void deleteControllerTest() throws Exception {
            UserDTO userDTO = new UserDTO("Agourinho", "123", null);
            User user = new User();
            BeanUtils.copyProperties(userDTO, user);

            mockMvc.perform(delete("/user/delete/1"))
                    .andExpect(status().isOk());
        }
    }
}
