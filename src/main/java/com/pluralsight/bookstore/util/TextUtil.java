package com.pluralsight.bookstore.util;

public class TextUtil {

    public String sanitaze(String textSanitaze){
        return textSanitaze.replaceAll("\\s+","");
    }

}
