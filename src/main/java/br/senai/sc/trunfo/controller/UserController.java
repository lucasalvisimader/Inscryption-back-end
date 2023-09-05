package br.senai.sc.trunfo.controller;

import br.senai.sc.trunfo.model.dto.UserDTO;
import br.senai.sc.trunfo.model.dto.UserRankingUpdateDTO;
import br.senai.sc.trunfo.model.dto.UserUpdateDTO;
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
import org.springframework.security.access.prepost.PreAuthorize;
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

    public void saveAdmin(@RequestBody @Valid UserDTO objectDTO) {
        ResponseEntity.ok(userService.save(objectDTO));
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/save")
    public ResponseEntity<User> save(@RequestBody @Valid UserDTO objectDTO) {
        return ResponseEntity.ok(userService.saveUser(objectDTO));
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/getUser")
    public ResponseEntity<User> listLogin(@NotNull HttpServletRequest request) {
        return ResponseEntity.ok(JWTUtil.getUser(request));
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/update/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody @Valid UserUpdateDTO objectDTO) {
        return ResponseEntity.ok(userService.update(id, objectDTO));
    }

    @PreAuthorize("hasAuthority('PLAYER')")
    @PutMapping("/userRankingUpdate/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody @Valid UserRankingUpdateDTO objectDTO) {
        return ResponseEntity.ok(userService.update(id, objectDTO));
    }

    @PreAuthorize("hasAuthority('PLAYER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

}
