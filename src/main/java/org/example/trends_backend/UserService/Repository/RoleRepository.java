package org.example.trends_backend.UserService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.example.trends_backend.UserService.Model.Roles;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
    Roles findByRoleType(String name);
    Roles save(Roles roles);
    Boolean existsByRoleType(String name);

    @Override
    void deleteAll();
}
