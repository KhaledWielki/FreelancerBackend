package com.freelancer.backend.repository;

import com.freelancer.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AdamR on 2017-10-28.
 */

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>{

    User findUserByEmail(String email);
}
