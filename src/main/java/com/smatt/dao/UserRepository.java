package com.smatt.dao;

import com.smatt.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by smatt on 22/03/2017.
 */

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
public interface UserRepository extends MongoRepository<User, String> {
    public User findByUsername(String username);
    public User findByEmail(String email);
}
