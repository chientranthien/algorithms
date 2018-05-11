package com.chientt.md5;

/**
 *
 * @author chientt
 */
public class MD5Encoder implements Hash {

    public static final int BIT_PER_BYTE = 8;

    public static final int[] ALL_ROUND = new int[]{
        7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22,
        5, 9, 14, 20, 5, 9, 14, 20, 5, 9, 14, 20, 5, 9, 14, 20,
        4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23,
        6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21};

    public static final int[] K = new int[64];

    static {
        for (int i = 0; i < 64; i++) {
            K[i] = (int) Math.floor(
                    Math.pow(2, 32)
                    * Math.abs(Math.sin(i + 1)));
        }
    }
    public static byte ZERO = 0x00;
    public static byte FIRT_BIT_1 = (byte) 0x80;

    @Override
    public String hash(String input) {
        int a0 = 0x67452301; //A
        int b0 = 0xefcdab89; //B
        int c0 = 0x98badcfe; //C
        int d0 = 0x10325476; //D

        byte[] bytes = input.getBytes();
        int bitCount = bytes.length * BIT_PER_BYTE;
        int a = 512 - bitCount % 512;
        int paddingLen;
        if (a >= 72) {
            paddingLen = a;
        } else {
            paddingLen = a + 512;
        }
        byte bit1 = (byte) 0x80;

        byte[] padding = new byte[paddingLen / 8];
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

        }

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}
