package com.zhang.test;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by zhenghua.zhang on 2017/8/21.
 */
public class EncodeTest {

    @Test
    public void doEncode() {
        // base64编码和解码
        String str = "hello";
        String base64Encoded = Base64.encodeToString(str.getBytes());
        System.out.println(base64Encoded);
        String str2 = Base64.decodeToString(base64Encoded);
        System.out.println(str2);

        // base16编码和解码
        String base16Encoded = Hex.encodeToString(str.getBytes());
        System.out.println(base16Encoded);
        String str3 = new String(Hex.decode(base16Encoded.getBytes()));
        System.out.println(str3);

        // md5散列算法
        String salt = "123";    // 盐
        String md5 = new Md5Hash(str, salt).toString(); //还可以转换为 toBase64()/toHex()
        System.out.println(md5);
    }
}
