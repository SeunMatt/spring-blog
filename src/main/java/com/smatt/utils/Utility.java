package com.smatt.utils;

import org.jsoup.Jsoup;

/**
 * Created by smatt on 05/08/2017.
 */
public class Utility {

    public static String makePreviewText(String htmlContent) {
        String text = Jsoup.parse(htmlContent).getElementsByTag("p").first().html();
       if(text.length() > 150) {
           return text.substring(0, 150);
       }
       return text;
    }
}
