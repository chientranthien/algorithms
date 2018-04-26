package com.chientt.base64.impl;

import com.chientt.base64.Encoder;

/**
 *
 * @author chientt
 */
public class PaddingBase64 implements Encoder {

    static private final int SIGN = -128;

    private static final char[] BASE_64_TABLE = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
    };

    @Override
    public String encode(String str) {
        byte[] strAsBytes = str.getBytes();
        int len = strAsBytes.length;
        byte[] encodedBytes = new byte[len * 4 / 3];

        for (int i = 0, j = 0, m = 0; i < len; i += 3) {
            byte b1 = strAsBytes[j];
            byte b2 = strAsBytes[++j];
            byte b3 = strAsBytes[++j];

            byte c1 = (byte) (b1 >> 2);
            byte k = (byte) (b1 & 0x03);
            byte c2 = (byte) ((b2 >> 4) | (k << 4));
            byte l = (byte) (b3 & 0xc0);
            byte l2 = (byte) ((b2 & 0x0f) << 2);
            byte c3 = (byte) (l2 | (l >> 6));
            byte c4 = (byte) ((b3 & 0x3f));
            System.out.println("b1: " + b1 + " b2: " + b2 + " b3: " + b3);
            System.out.println("c1: " + c1 + " c2: " + c2 + " c3: " + c3 + " c4: " + c4);
            encodedBytes[m] = c1;
            encodedBytes[++m] = c2;
            encodedBytes[++m] = c3;
            encodedBytes[++m] = c4;
        }

        char[] encodedChar = new char[encodedBytes.length];
        for (int i = 0; i < encodedChar.length; i++) {
            encodedChar[i] = BASE_64_TABLE[encodedBytes[i]];
        }
        return new String(encodedChar);
    }

    @Override
    public String decode(String str) {
        return null;

    }

}
