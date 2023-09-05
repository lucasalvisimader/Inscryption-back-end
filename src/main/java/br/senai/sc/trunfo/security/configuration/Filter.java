package br.senai.sc.trunfo.security.configuration;

import br.senai.sc.trunfo.model.entity.User;
import br.senai.sc.trunfo.security.exception.CookieNaoEncontradoException;
import br.senai.sc.trunfo.security.util.CookieUtil;
import br.senai.sc.trunfo.security.util.JWTUtil;
import com.auth0.jwt.exceptions.JWTDecodeException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

public class Filter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        if (rotaPrivada(request.getRequestURI())) {
            try {
                String token = CookieUtil.getToken(request);
                System.out.println(token);
                User user = JWTUtil.getUser(token);
                response.addCookie(CookieUtil.gerarCookie(user));
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        user.getUsername(), null, user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (JWTDecodeException e) {
                System.out.println("Token inválido!");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            } catch (CookieNaoEncontradoException e) {
                System.out.println(e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean rotaPrivada(String url) {
        Set<String> rotasPrivadas = Set.of(
                "/user/update/**",
                "/user/userRankingUpdate/**",
                "/user/delete/**",
                "/card/**"
        );

        return rotasPrivadas.contains(url);
    }
}
