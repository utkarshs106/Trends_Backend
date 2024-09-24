package org.example.trends_backend.UserService.Repository;

import org.example.trends_backend.UserService.Model.Token;
import org.example.trends_backend.UserService.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
    Token findByuser(User user);
    Token save(Token token);

    @Override
    void deleteAll();

}

