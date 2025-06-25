package org.dnyanyog.repo;

import org.dnyanyog.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LoginRepo extends JpaRepository<Login, Long> {
    Optional<Login> findByLoginName(String loginName);
    
    Optional<Login> findByLoginNameAndPassword(String loginName, String password);
}
