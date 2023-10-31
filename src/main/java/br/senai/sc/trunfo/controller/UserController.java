package br.senai.sc.trunfo.controller;

import br.senai.sc.trunfo.model.dto.UserDTO;
import br.senai.sc.trunfo.model.entity.User;
import br.senai.sc.trunfo.security.util.JWTUtil;
import br.senai.sc.trunfo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(
        origins = "http://localhost:3000",
        allowCredentials = "true"
)
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<User> save(@RequestBody @Valid UserDTO objectDTO) {
        return ResponseEntity.ok(userService.save(objectDTO));
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> listLogin(@NotNull HttpServletRequest request) {
        return ResponseEntity.ok(JWTUtil.getUser(request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
