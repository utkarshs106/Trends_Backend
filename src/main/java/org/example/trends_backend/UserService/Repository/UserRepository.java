package org.example.trends_backend.UserService.Repository;

import org.example.trends_backend.UserService.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);

    @Query(value = "Select id from roles where role_type = :role_type", nativeQuery = true)
    Integer findByRoleType(@Param("role_type") String role_type);

    @Override
    void deleteAll();

    User findByuserName(String username);


}
