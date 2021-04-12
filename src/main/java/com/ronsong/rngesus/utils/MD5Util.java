package com.ronsong.rngesus.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class MD5Util {
    public static String md5Hex(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            byte[] digest = messageDigest.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            log.warn(e.getMessage());
        }
        return password;
    }

   public static void main(String[] args) {
        String pwd = "123456";
        String encode = MD5Util.md5Hex(pwd);
        log.info("pwd: [{}] -> encode: [{}]", pwd, encode);
   }
}