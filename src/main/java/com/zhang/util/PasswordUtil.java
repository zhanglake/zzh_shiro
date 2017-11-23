package com.zhang.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Created by zhenghua.zhang on 2017/11/17.
 */
public class PasswordUtil {

    public static String createSalt() {
        char[] codes = {
                '0','1','2','3','4','5','6','7','8','9',
                'a','b','c','d','e','f','g','h','i','j',
                'k','l','m','n','o','p','q','r','s','t',
                'u','v','w','x','y','z'
        };
        StringBuilder salt = new StringBuilder();
        for (int i = 0; i < 32; i ++) {
            salt.append(new Random().nextInt(31));
        }
        return salt.toString();
    }

    public static String createPwd(String pwd, String salt) {
        StringBuilder contact = new StringBuilder(pwd).append(salt);
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return "check jdk";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = contact.toString().toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static String createSaltByShiro() {
        return new SecureRandomNumberGenerator().nextBytes().toHex();
    }

    public static String createPwdByShiro(String password, String salt, String userName) {
        String algorithmName = "md5";
        int hashIterations = 2;
        SimpleHash hash = new SimpleHash(algorithmName, password, userName + salt, hashIterations);
        String encodedPassword = hash.toHex();
        return encodedPassword;
    }

}
