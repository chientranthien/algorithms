package com.chientt.md5;

/**
 * @author chientt
 */
public class MD5Encoder implements Hash {

    public static final int BIT_PER_BYTE = 8;

    public static final int[] s = new int[]{
        7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22,
        5, 9, 14, 20, 5, 9, 14, 20, 5, 9, 14, 20, 5, 9, 14, 20,
        4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23,
        6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21};

    public static final int[] K = new int[]{
        0xd76aa478, 0xe8c7b756, 0x242070db, 0xc1bdceee,
        0xf57c0faf, 0x4787c62a, 0xa8304613, 0xfd469501,
        0x698098d8, 0x8b44f7af, 0xffff5bb1, 0x895cd7be,
        0x6b901122, 0xfd987193, 0xa679438e, 0x49b40821,
        0xf61e2562, 0xc040b340, 0x265e5a51, 0xe9b6c7aa,
        0xd62f105d, 0x02441453, 0xd8a1e681, 0xe7d3fbc8,
        0x21e1cde6, 0xc33707d6, 0xf4d50d87, 0x455a14ed,
        0xa9e3e905, 0xfcefa3f8, 0x676f02d9, 0x8d2a4c8a,
        0xfffa3942, 0x8771f681, 0x6d9d6122, 0xfde5380c,
        0xa4beea44, 0x4bdecfa9, 0xf6bb4b60, 0xbebfbc70,
        0x289b7ec6, 0xeaa127fa, 0xd4ef3085, 0x04881d05,
        0xd9d4d039, 0xe6db99e5, 0x1fa27cf8, 0xc4ac5665,
        0xf4292244, 0x432aff97, 0xab9423a7, 0xfc93a039,
        0x655b59c3, 0x8f0ccc92, 0xffeff47d, 0x85845dd1,
        0x6fa87e4f, 0xfe2ce6e0, 0xa3014314, 0x4e0811a1,
        0xf7537e82, 0xbd3af235, 0x2ad7d2bb, 0xeb86d391};

    public static byte ZERO = 0x00;
    public static byte FIRT_BIT_1 = (byte) 0x80;

    @Override
    public String hash(String input) {
        int a0 = 0x67452301; //A
        int b0 = 0xefcdab89; //B
        int c0 = 0x98badcfe; //C
        int d0 = 0x10325476; //D

        byte[] bytes = input.getBytes();
        int len = bytes.length;
        int bitCount = bytes.length * BIT_PER_BYTE;
        int paddingLen = 64 - len % 64;
        if (paddingLen <= 8) {
            paddingLen += 64;
        }

        byte[] padding = new byte[paddingLen];
        padding[0] = (byte) 0x80;
        for (int i = 1; i < paddingLen - 8; i++) {
            padding[i] = 0;
        }
        byte[] bitCountAsBytes = convertLongToBytes(bitCount);
        System.arraycopy(bitCountAsBytes, 0, padding, paddingLen - 8, 8);
        byte[] newArr = new byte[padding.length + bytes.length];

        System.arraycopy(bytes, 0, newArr, 0, bytes.length);
        System.arraycopy(padding, 0, newArr, bytes.length, padding.length);

        for (int i = 0; i < newArr.length; i += 64) {
            int A = a0;
            int B = b0;
            int C = c0;
            int D = d0;
            int[] M = convertBytesToInts(newArr, i, 64);
            int[] M2 = decode(newArr, 64,i );
            for (int j = 0; j < 64; j++) {
                int F = 0, g = 0;
                if (j < 16) {
                    F = F(B, C, D);
                    g = i;
                } else if (j < 32) {
                    F = G(B, C, D);
                    g = (5 * i + 1) % 16;
                } else if (j < 48) {
                    F = H(B, C, D);
                    g = (3 * i + 5) % 16;

                } else if (j < 64) {
                    F = I(B, C, D);
                    g = (7 * i) % 16;
                }
                F += A + K[i] + M[g];
                A = D;
                D = C;
                C = B;
                B += leftrotate(F, s[i]);

            }
            a0 += a0 + A;
            b0 += b0 + B;
            c0 += c0 + C;
            d0 += d0 + D;
        }

        System.out.println("a0 " + Integer.toHexString(a0));
        System.out.println("b0 " + Integer.toHexString(b0));
        System.out.println("c0 " + Integer.toHexString(c0));
        System.out.println("d0 " + Integer.toHexString(d0));
        return null;
    }

    private int leftrotate(int x, int c) {
        return (x << c) | (x >> (32 - c));
    }

    @Override
    public String dehash(String input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private byte[] convertLongToBytes(long num) {
        byte[] result = new byte[8];
        result[0] = (byte) (num & 0xff);
        result[1] = (byte) ((num >> 8) & 0xff);
        result[2] = (byte) ((num >> 16) & 0xff);
        result[3] = (byte) ((num >> 24) & 0xff);
        result[4] = (byte) ((num >> 32) & 0xff);
        result[5] = (byte) ((num >> 40) & 0xff);
        result[6] = (byte) ((num >> 48) & 0xff);
        result[7] = (byte) ((num >> 56) & 0xff);
        return result;
    }

    public int[] convertBytesToInts(byte[] input, int offset, int size) {
        if (size % 4 != 0) {
            return null;
        }
        int toIndex = offset + size;
        int[] result = new int[size / 4];
        for (int i = offset, j = 0; i < toIndex; j++) {
            int num = input[i++]& 0xff;
            num |= (input[i++]& 0xff )<< 8;
            num |= (input[i++]& 0xff  )<< 16;
            num |= (input[i++] & 0xff )<< 24;
            result[j] = num;
        }
        return result;
    }

    public int[] decode(byte buffer[], int len, int offset) {
        int[] decodeBuffer = new int[16];
        int i, j;
        for (i = j = 0; j < len; i++, j += 4) {
            decodeBuffer[i] = ((buffer[j + offset] & 0xff)) | (((buffer[j + 1 + offset] & 0xff)) << 8) | (((buffer[j + 2 + offset] & 0xff)) << 16) | (((buffer[j + 3 + offset] & 0xff)) << 24);
        }
        return decodeBuffer;
    }

    private int F(int B, int C, int D) {
        return (B & C) | (~B & D);
    }

    private int G(int B, int C, int D) {
        return (B & D) | (B & ~D);
    }

    private int H(int B, int C, int D) {
        return B ^ C ^ D;
    }

    private int I(int B, int C, int D) {
        return C ^ (B | ~D);
    }
}
