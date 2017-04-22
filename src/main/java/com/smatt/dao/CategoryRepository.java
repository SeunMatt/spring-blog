package com.smatt.dao;

import com.smatt.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by smatt on 22/04/2017.
 */
public interface CategoryRepository extends MongoRepository<Category, String> {
    public Category findByCategory(String category);
}
