package org.dnyanyog.repo;

import java.util.Optional;
import org.dnyanyog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> { 

    Optional<User> findByUserEmail(String userEmail);

    Optional<User> findByUserIdAndUserNameIgnoreCase(Integer userId, String userName);
    
    
    

}
