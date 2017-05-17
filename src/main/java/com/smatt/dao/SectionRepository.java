package com.smatt.dao;

import com.smatt.models.Section;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by smatt on 22/04/2017.
 */
public interface SectionRepository extends CrudRepository<Section, String> {
    public Section findBySection(String section);
}
