package com.smatt.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by smatt on 22/04/2017.
 */
@Document(collection = "sections")
public class Section {

    @Id
    private String Id;
    private String section;
    private int articleCount;

    public Section() {}

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }
}
