package com.smatt.utils;

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
        if(request.getServerName().contains("localhost")) {
            return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        }
        return request.getScheme() + "://" + request.getServerName();
    }



}
