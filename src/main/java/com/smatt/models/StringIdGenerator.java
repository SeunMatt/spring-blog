package com.smatt.models;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Session;
import org.hibernate.tuple.ValueGenerator;

/**
 * Created by smatt on 16/05/2017.
 */
public class StringIdGenerator implements ValueGenerator<String> {

    @Override
    public String generateValue(Session session, Object o) {
        return RandomStringUtils.randomAlphanumeric(10);
    }

}
