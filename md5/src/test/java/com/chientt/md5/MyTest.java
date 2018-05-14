package com.chientt.md5;

import java.util.Base64;

/**
 * @author chientt
 */
public class MyTest {

    public static void main(String[] args) {
        Hash h = new MD5Encoder();
        h.hash("a");
    }
}
