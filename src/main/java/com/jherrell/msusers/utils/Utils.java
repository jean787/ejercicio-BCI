package com.jherrell.msusers.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Utils {

    public static String generateDateWithFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(new Date());
    }

    public static String generateToken(){
        return UUID.randomUUID().toString();
    }

    public static boolean matchesRegex(String value, String regex) {
        return value.matches(regex);
    }
}
