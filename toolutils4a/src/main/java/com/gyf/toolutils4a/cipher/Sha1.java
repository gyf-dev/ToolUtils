package com.gyf.toolutils4a.cipher;

import com.gyf.toolutils4a.StringUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA1 加密工具类
 * Created by gyf on 2016/11/14.
 */
public class Sha1 {

    private Sha1(){}
    /**
     * Sha 1 string.
     * sha1加密通过字符串
     *
     * @param str the str
     * @return the string
     */
    public static String SHA1(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(str.getBytes());
            byte md[] = digest.digest();
            return StringUtil.byte2Hex(md);

        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    /**
     * Sha 1 string.
     * sha1加密通过字节数组
     *
     * @param bytes the bytes
     * @return the string
     */
    public static String SHA1(byte[] bytes) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(bytes);
            byte md[] = digest.digest();
            return StringUtil.byte2Hex(md);
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    /**
     * Sha 1 string.
     * sha1加密通过输入流
     *
     * @param is the is
     * @return the string
     */
    public static String SHA1(InputStream is) {
        byte[] dataBytes = new byte[1024];
        int read;
        try {
            MessageDigest mdInst = MessageDigest.getInstance("SHA-1");
            while ((read = is.read(dataBytes)) != -1) {
                mdInst.update(dataBytes, 0, read);
            }
            byte[] md = mdInst.digest();
            return StringUtil.byte2Hex(md);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Sha 1 string.
     * sha1加密通过文件
     *
     * @param file the file
     * @return the string
     */
    public static String SHA1(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            return SHA1(fis);
        } catch (FileNotFoundException e) {
            return "";
        }

    }

    /**
     * Sha 1 by path string.
     * sha1加密通过路径
     *
     * @param path the path
     * @return the string
     */
    public static String SHA1ByPath(String path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            return SHA1(fis);
        } catch (FileNotFoundException e) {
            return "";
        }
    }

    /**
     * Sha 1 check boolean.
     * sha1检验通过字符串
     *
     * @param str          the str
     * @param toBeCheckSum the to be check sum
     * @return the boolean
     */
    public static boolean SHA1Check(String str, String toBeCheckSum) {
        return SHA1(str).equals(toBeCheckSum);
    }

    /**
     * Sha 1 check boolean.
     * sha1检验通过字节数组
     *
     * @param bytes        the bytes
     * @param toBeCheckSum the to be check sum
     * @return the boolean
     */
    public static boolean SHA1Check(byte[] bytes, String toBeCheckSum) {
        return SHA1(bytes).equals(toBeCheckSum);
    }

    /**
     * Sha 1 check boolean.
     * sha1检验通过输入流
     *
     * @param is           the is
     * @param toBeCheckSum the to be check sum
     * @return the boolean
     */
    public static boolean SHA1Check(InputStream is, String toBeCheckSum) {
        return SHA1(is).equals(toBeCheckSum);
    }

    /**
     * Sha 1 check boolean.
     * sha1检验通过文件
     *
     * @param file         the file
     * @param toBeCheckSum the to be check sum
     * @return the boolean
     */
    public static boolean SHA1Check(File file, String toBeCheckSum) {
        return SHA1(file).equals(toBeCheckSum);
    }

    /**
     * Sha 1 check boolean.
     * sha1检验通过文件路径
     *
     * @param path         the path
     * @param toBeCheckSum the to be check sum
     * @return the boolean
     */
    public static boolean SHA1CheckByPath(String path, String toBeCheckSum) {
        return SHA1ByPath(path).equals(toBeCheckSum);
    }
}
