package com.smatt.dao;

import com.smatt.models.Tag;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by smatt on 26/08/2017.
 */
public interface TagRepository extends CrudRepository<Tag, String>  {
    Tag findByTag(String tag);
}
