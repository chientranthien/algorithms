package com.chientt.md5;

import org.springframework.util.DigestUtils;

/**
 * @author chientt
 */
public class MyTest {

    public static void main(String[] args) {

        String lala = DigestUtils.md5DigestAsHex("The quick brown fox jumps over the lazy dog".getBytes());
        System.out.println("lala: " + lala);
        Hash h = new MD5Hasher();
        System.out.println( h.hash("The quick brown fox jumps over the lazy dog"));
        byte a = 1;
        byte b = 1;
        byte c = 100;
        byte d = 101;
    }

    private static void haha(byte a, byte b, byte c, byte d) {
        int a1 = a;
        int a2 = a & 0xff;
        System.out.print(a);
        System.out.println(a2);

        int b1 = b << 8;
        int b2 = ((b & 0xff) << 8);
        System.out.print(b1);
        System.out.println(" " + b2);

        int c1 = c << 16;
        int c2 = ((c & 0xff) << 16);
        System.out.print(c1);
        System.out.println(" " + c2);

        int d1 = d << 24;
        int d2 = ((d & 0xff) << 24);
        System.out.print(d2);
        System.out.println(" " + d2);

        System.out.print(a1 | b1 | c1 | d1);
        System.out.println(" " + (a2 | b2 | c2 | d2));

    }
}
