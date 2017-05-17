package com.smatt.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

/**
 * Created by smatt on 29/04/2017.
 */
@Service
public class TokenGenerator {

    private SecureRandom secureRandom;
    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    Logger logger = Logger.getLogger(TokenGenerator.class);

    public TokenGenerator() {
        secureRandom = new SecureRandom();
        logger.info("generate token instantiated");
    }

    public String getToken(int lenght) {
        StringBuilder sb = new StringBuilder(lenght);
        for( int i = 0; i < lenght; i++ )
            sb.append( AB.charAt( secureRandom.nextInt(AB.length()) ) );

        return sb.toString();
    }


}
