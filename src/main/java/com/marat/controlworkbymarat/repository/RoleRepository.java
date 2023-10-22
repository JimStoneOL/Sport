package com.marat.controlworkbymarat.repository;




import com.marat.controlworkbymarat.entity.Role;
import com.marat.controlworkbymarat.entity.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
