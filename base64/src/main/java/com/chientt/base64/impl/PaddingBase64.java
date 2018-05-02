package com.chientt.base64.impl;

import com.chientt.base64.Encoder;

/**
 *
 * @author chientt
 */
public class PaddingBase64 implements Encoder {

    static private final boolean DEBUG = true;
    static private final byte ZERO_BYTE = 0;
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
        for (int i = 0, j = 0; i < len - mod; i += 3, k += 4) {
            byte b1 = strAsBytes[j++];
            byte b2 = strAsBytes[j++];
            byte b3 = strAsBytes[j++];
            encodeTriple(b1, b2, b3, encodedBytes, k);
        }
        if (mod == 1) {
            byte b1 = strAsBytes[len - 1];
            encodedBytes[k++] = get1stEncodedByte(b1);
            encodedBytes[k++] = get2ndEncodedByte2(b1, ZERO_BYTE);
            encodedBytes[k++] = PADDING_INDEX;
            encodedBytes[k] = PADDING_INDEX;
        } else if (mod == 2) {
            byte b1 = strAsBytes[len - 2];
            byte b2 = strAsBytes[len - 1];
            encodedBytes[k++] = get1stEncodedByte(b1);
            encodedBytes[k++] = get2ndEncodedByte2(b1, b2);
            encodedBytes[k++] = get3rdEncodedByte(b2, ZERO_BYTE);
            encodedBytes[k] = PADDING_INDEX;
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

    private byte get1stEncodedByte(byte b1) {
        return (byte) (b1 >> 2);
    }

    private byte get2ndEncodedByte2(byte b1, byte b2) {
        byte residual1 = (byte) (b1 & 0x03);
        return (byte) ((b2 >> 4) | (residual1 << 4));
    }

    private byte get3rdEncodedByte(byte b2, byte b3) {
        byte residual2 = (byte) ((b2 & 0x0f) << 2);
        return (byte) (residual2 | (b3 >> 6));
    }

    private byte get4thEncodedByte(byte b3) {
        return (byte) ((b3 & 0x3f));
    }

    private void encodeTriple(byte b1, byte b2, byte b3, byte[] encodedBytes, int fromIndex) {

        encodedBytes[fromIndex++] = get1stEncodedByte(b1);
        encodedBytes[fromIndex++] = get2ndEncodedByte2(b1, b2);
        encodedBytes[fromIndex++] = get3rdEncodedByte(b2, b3);
        encodedBytes[fromIndex] = get4thEncodedByte(b3);
    }

}
