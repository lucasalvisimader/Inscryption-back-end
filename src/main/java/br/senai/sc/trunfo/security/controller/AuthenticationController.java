package br.senai.sc.trunfo.security.controller;

import br.senai.sc.trunfo.model.entity.User;
import br.senai.sc.trunfo.security.enums.Profile;
import br.senai.sc.trunfo.security.model.Login;
import br.senai.sc.trunfo.security.util.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping("/user")
public class AuthenticationController {
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());

        Authentication authentication = authenticationManager.authenticate(token);
        if (authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            if (!user.getAuthorities().contains(Profile.ADMIN)) {
                Cookie cookie = CookieUtil.gerarCookie(user);
                response.addCookie(cookie);
                return ResponseEntity.ok(authentication.getPrincipal());
            }
        }
        return ResponseEntity.status(403).build();
    }
}
