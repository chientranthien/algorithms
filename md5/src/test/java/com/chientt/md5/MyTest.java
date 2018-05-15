package com.chientt.md5;

import org.springframework.util.DigestUtils;

/**
 * @author chientt
 */
public class MyTest {

    public static void main(String[] args) {

        String lala = DigestUtils.md5DigestAsHex("a".getBytes());
        System.out.println("lala: " + lala);
        Hash h = new MD5Encoder();
        h.hash("a");
    }
}
