package com.smatt.dao;

import com.smatt.models.Section;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by smatt on 22/04/2017.
 */
public interface SectionRepository extends MongoRepository<Section, String> {
    public Section findBySection(String section);
}
