package com.chientt.base64.impl;

import com.chientt.base64.Encoder;

/**
 *
 * @author chientt
 */
public class PaddingBase64 implements Encoder {

    static private final boolean DEBUG = true;
    static private final byte PADDING_INDEX = 64;

    private static final char[] BASE_64_TABLE = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/', '='
    };

    @Override
    public String encode(String str) {
        if (DEBUG) {
            System.out.println("str: " + str);
        }
        byte[] strAsBytes = str.getBytes();
        int len = strAsBytes.length;
        int mod = len % 3;
        int resultLen = mod == 0 ? len * 4 / 3 : (len / 3 * 4) + 4;
        byte[] encodedBytes = new byte[resultLen];
        int k = 0;
        for (int i = 0, j = 0; i < len - mod; i += 3) {
            byte b1 = strAsBytes[j++];
            byte b2 = strAsBytes[j++];
            byte b3 = strAsBytes[j++];

            byte encoded1 = (byte) (b1 >> 2);
            byte residual1 = (byte) (b1 & 0x03);
            byte encoded2 = (byte) ((b2 >> 4) | (residual1 << 4));
            byte residual2 = (byte) ((b2 & 0x0f) << 2);
            byte encoded3 = (byte) (residual2 | (b3 >> 6));
            byte encoded4 = (byte) ((b3 & 0x3f));

            if (DEBUG) {
                System.out.println("b1: " + b1 + " b2: " + b2 + " b3: " + b3);
                System.out.println("c1: " + encoded1 + " c2: " + encoded2
                        + " c3: " + encoded3 + " c4: " + encoded4);
            }

            encodedBytes[k++] = encoded1;
            encodedBytes[k++] = encoded2;
            encodedBytes[k++] = encoded3;
            encodedBytes[k++] = encoded4;
        }
        if (mod == 1) {
            byte b1 = strAsBytes[len - 2];
            byte residual = (byte) (b1 & 0x03);

            encodedBytes[k++] = (byte) (b1 >> 2);
            encodedBytes[k++] = (byte) (residual << 4);
            encodedBytes[k++] = PADDING_INDEX;
            encodedBytes[k++] = PADDING_INDEX;
        } else if (mod == 2) {
            byte b1 = strAsBytes[len - 2];
            byte residual = (byte) (b1 & 0x03);
            byte b2 = strAsBytes[len - 1];
            encodedBytes[k++] = (byte) (b1 >> 2);
            encodedBytes[k++] = (byte) (residual << 4 | b2 >> 4);
            encodedBytes[k++] = (byte) ((b2 & 0x0f) << 2);
            encodedBytes[k++] = PADDING_INDEX;
        }
        char[] encodedChar = new char[encodedBytes.length];
        for (int i = 0; i < encodedChar.length; i++) {
            if (encodedBytes[i] == -32) {
                System.out.println("encodedBytes[i]=-32 i:" + i);
            }
            encodedChar[i] = BASE_64_TABLE[encodedBytes[i]];
        }
        return new String(encodedChar);
    }

    @Override
    public String decode(String str) {
        return null;

    }

}
