package com.smatt.dao;

import com.smatt.models.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by smatt on 22/04/2017.
 */
public interface CategoryRepository extends CrudRepository<Category, String> {
    public Category findByCategory(String category);
}
