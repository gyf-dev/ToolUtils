package com.gyf.toolutils4a.cipher.base64;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Base64 加密解密工具类
 * Created by gyf on 2016/11/8.
 */
public class Base64 {

    /**
     * Instantiates a new Base 64.
     */
    private Base64() {
    }

    static {
        encoder = new Base64Encoder();
        decoder = new Base64Decoder();
    }

    /**
     * The constant encoder.
     */
    private static Base64Encoder encoder;
    /**
     * The constant decoder.
     */
    private static Base64Decoder decoder;

    /**
     * base64加密
     *
     * @param str the str
     * @return the string
     */
    public static String encode(String str) {
        return encoder.encode(str.getBytes());
    }

    /**
     * base64加密
     *
     * @param bytes the bytes
     * @return the string
     */
    public static String encode(byte[] bytes) {
        return encoder.encode(bytes);
    }

    /**
     * base64加密.
     *
     * @param bytes the bytes
     * @param out   the out
     */
    public static void encode(byte[] bytes, OutputStream out) {
        try {
            encoder.encode(bytes, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * base64加密.
     *
     * @param str the str
     * @param out the out
     */
    public static void encode(String str, OutputStream out) {
        try {
            encoder.encode(str.getBytes(), out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对输入流的加密
     *
     * @param in  the in
     * @param out the out
     * @throws IOException the io exception
     */
    public static void encode(InputStream in, OutputStream out) {
        try {
            encoder.encode(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * base64解密
     *
     * @param str the str
     * @return the byte [ ]
     */
    public static byte[] decode(String str) {
        try {
            return decoder.decodeBuffer(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * base64对输入流解密.
     *
     * @param in  the in
     * @param out the out
     */
    public static void decode(InputStream in, OutputStream out) {
        try {
            decoder.decodeBuffer(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * base64对输入流解密.
     *
     * @param in the in
     * @return the byte [ ]
     */
    public static byte[] decode(InputStream in) {
        try {
            return decoder.decodeBuffer(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
