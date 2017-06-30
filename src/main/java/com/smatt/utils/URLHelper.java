package com.smatt.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by smatt on 08/05/2017.
 */
public class URLHelper {

    /*
    * @param HttpServletRequest Object
     * @returns a string representation of the full base url and port
      * e.g. http://localhost:9000
    * */
    public static String getBaseUrl(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName(); //+ ":" + request.getServerPort();
    }



}
