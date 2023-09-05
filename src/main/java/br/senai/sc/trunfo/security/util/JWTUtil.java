package br.senai.sc.trunfo.security.util;

import br.senai.sc.trunfo.model.entity.User;
import br.senai.sc.trunfo.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {
    private static final String SENHAFORTE = "c127a7b6adb013a5ff879ae71afa62afa4b4ceb72afaa54711dbcde67b6dc325";
    private static UserRepository userRepository;

    @Autowired
    JWTUtil(UserRepository userRepository) {
        JWTUtil.userRepository = userRepository;
    }

    public static String gerarToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(SENHAFORTE);
        return JWT.create()
                .withIssuer("WEG") //Emissor
                .withSubject(user.getUsername()) //Algum atributo de algum objeto que é seu identificador
                .withIssuedAt(new Date()) //Data da emissão
                .withExpiresAt(new Date(new Date().getTime() + 1800)) //Data da expiração
                .sign(algorithm);
    }

    public static User getUsuario(String token) {
        String username = JWT.decode(token).getSubject();
        return userRepository.findByUsername(username);
    }
}
