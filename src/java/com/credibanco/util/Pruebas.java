package com.credibanco.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pruebas {
    
    private static final String PATTERN_PASSWORD = "11111111";
    
    public static void main(String args[]) {
        try {
        String cadenaRecibida = "0123456789";
        
        
        System.out.println(cadenaRecibida.substring(0,4));
        System.out.println(cadenaRecibida.substring(4,8));
        System.out.println(cadenaRecibida.substring(8,10));
	System.out.println(cadenaRecibida.length());
       
        
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
