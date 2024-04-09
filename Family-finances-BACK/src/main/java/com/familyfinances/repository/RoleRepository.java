package com.familyfinances.repository;
import com.familyfinances.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE r.role = ?1")
    Role findByRoleName(String role);
}