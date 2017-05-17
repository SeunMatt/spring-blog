package com.smatt.dao;

import com.smatt.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by smatt on 22/03/2017.
 */

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
public interface UserRepository extends CrudRepository<User, String> {
    public User findByUsername(String username);
    public User findByEmail(String email);
    public User findByToken(String token);
}
