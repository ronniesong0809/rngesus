package com.ronsong.rngesus.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
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
            log.warn(e.getMessage());
        }
        return password;
    }

   public static void main(String[] args) {
        String pwd = "123456";
        String encode = MD5Utils.getMD5(pwd);
        log.info("pwd: [{}] -> encode: [{}]", pwd, encode);
   }
}
