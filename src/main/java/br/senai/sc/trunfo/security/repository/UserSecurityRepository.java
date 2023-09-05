package br.senai.sc.trunfo.security.repository;

import br.senai.sc.trunfo.security.model.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSecurityRepository extends JpaRepository<UserSecurity, Long> {
    UserSecurity findByUsername(String username);
}
