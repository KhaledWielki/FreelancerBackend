package com.freelancer.backend.repository;

import com.freelancer.backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AdamR on 2017-10-28.
 */

@Repository("userRepostiory")
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRole(String role);

}
