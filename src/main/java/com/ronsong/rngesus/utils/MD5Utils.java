package com.ronsong.rngesus.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    public static String getMD5(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            byte[] digest = messageDigest.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02X", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return password;
    }

   public static void main(String[] args) {
       String pwd = MD5Utils.getMD5("234");
       System.out.println(pwd);
   }
}
