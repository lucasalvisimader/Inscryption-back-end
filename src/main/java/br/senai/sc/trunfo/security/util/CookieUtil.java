package br.senai.sc.trunfo.security.util;

import br.senai.sc.trunfo.model.entity.User;
import br.senai.sc.trunfo.security.exception.CookieNaoEncontradoException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.util.WebUtils;

public class CookieUtil {
    public static Cookie gerarCookie(User user) {
        String token = JWTUtil.gerarToken(user);
        Cookie cookie = new Cookie("JWT", token);
        cookie.setPath("/");
        cookie.setMaxAge(1800);
        return cookie;
    }

    public static String getToken(HttpServletRequest request) throws CookieNaoEncontradoException {
        Cookie cookie = WebUtils.getCookie(request, "JWT");
        if (cookie != null) {
            return cookie.getValue();
        }
        throw new CookieNaoEncontradoException("JWT");
    }
}
