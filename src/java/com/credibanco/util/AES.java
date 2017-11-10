package com.credibanco.util;

import java.io.*;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

 
public class AES {

    public static void main(String[] args) 
    {
        String ivKey = "a/Fo7dfcmgAtFJbF4Dsv3s==";
        String saltKey = "8G4e3DBdRFGfsn48/dES1e==";
         
        String originalString = "howtodoinjava.co";
        String encryptedString = AES.encrypt(originalString, ivKey,saltKey) ;
        String decryptedString = AES.decrypt(encryptedString, ivKey,saltKey) ;

        System.out.println(AES.decrypt("57dd4bc3b52f40489ad00381a559b7ebab0513755c244fa8b0bc0446119b6c16", ivKey,saltKey));
    }

    public static String encrypt(String plainText, String ivKey, String saltKey) 
    {
        try {
            
            SecretKey key = new SecretKeySpec(Base64.getDecoder().decode(ivKey), "AES");
            AlgorithmParameterSpec iv = new IvParameterSpec(Base64.getDecoder().decode(saltKey));
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            return Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes("UTF-8")));
            //return Base64.getEncoder().encode(cipher.doFinal(plainText.getBytes("UTF-8")));
            
        } catch (Exception e) {
            throw new RuntimeException("This should not happen in production.", e);
        }

    }
 
    public static String decrypt(String encrypted, String ivKey, String saltKey) 
    {
        try {
            SecretKey key = new SecretKeySpec(Base64.getDecoder().decode(ivKey), "AES");
            AlgorithmParameterSpec iv = new IvParameterSpec(Base64.getDecoder().decode(saltKey));
            byte[] decodeBase64 = Base64.getDecoder().decode( encrypted.replaceAll(" ", "+") );

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, iv);

            return new String(cipher.doFinal(decodeBase64), "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("This should not happen in production.", e);
        }
    }
}