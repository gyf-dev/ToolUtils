package com.gyf.cipher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 加密工具类
 * Created by gyf on 2016/11/14.
 */
public class Md5 {
    private Md5(){}
    /**
     * Md 5 string.
     * md5加密通过String
     *
     * @param str the str
     * @return the string
     */
    public static String MD5(String str) {
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("Md5");
            // 使用指定的字节更新摘要
            mdInst.update(str.getBytes());
            // 获得密文
            byte[] md = mdInst.digest();
            // 字节数组转换为 十六进制 数并返回
            return StringUtil.byte2Hex(md);
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    /**
     * Md 5 string.
     * md5加密通过字节数组
     *
     * @param bytes the bytes
     * @return the string
     */
    public static String MD5(byte[] bytes) {
        try {
            MessageDigest mdInst = MessageDigest.getInstance("Md5");
            mdInst.update(bytes);
            byte[] md = mdInst.digest();
            return StringUtil.byte2Hex(md);
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    /**
     * Md 5 string.
     * md5加密通过输入流
     *
     * @param is the is
     * @return the string
     */
    public static String MD5(InputStream is) {
        byte[] dataBytes = new byte[1024];
        int read;
        try {
            MessageDigest mdInst = MessageDigest.getInstance("Md5");
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
     * Md 5 string.
     * md5加密通过文件
     *
     * @param file the file
     * @return the string
     */
    public static String MD5(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            return MD5(fis);
        } catch (FileNotFoundException e) {
            return "";
        }

    }

    /**
     * Md 5 by path string.
     * md5加密通过文件路径
     *
     * @param path the path
     * @return the string
     */
    public static String MD5ByPath(String path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            return MD5(fis);
        } catch (FileNotFoundException e) {
            return "";
        }
    }


    /**
     * Md 5 check boolean.
     * md5检验通过字符串
     *
     * @param str          the str
     * @param toBeCheckSum the to be check sum
     * @return the boolean
     */
    public static boolean MD5Check(String str, String toBeCheckSum) {
        return MD5(str).equals(toBeCheckSum);
    }

    /**
     * Md 5 check boolean.
     * md5检验通过字节数组
     *
     * @param bytes        the bytes
     * @param toBeCheckSum the to be check sum
     * @return the boolean
     */
    public static boolean MD5Check(byte[] bytes, String toBeCheckSum) {
        return MD5(bytes).equals(toBeCheckSum);
    }

    /**
     * Md 5 check boolean.
     * md5检验通过输入流
     *
     * @param is           the is
     * @param toBeCheckSum the to be check sum
     * @return the boolean
     */
    public static boolean MD5Check(InputStream is, String toBeCheckSum) {
        return MD5(is).equals(toBeCheckSum);
    }

    /**
     * Md 5 check boolean.
     * md5检验通过文件
     *
     * @param file         the file
     * @param toBeCheckSum the to be check sum
     * @return the boolean
     */
    public static boolean MD5Check(File file, String toBeCheckSum) {
        return MD5(file).equals(toBeCheckSum);
    }

    /**
     * Md 5 check boolean.
     * md5检验通过文件路径
     *
     * @param path         the path
     * @param toBeCheckSum the to be check sum
     * @return the boolean
     */
    public static boolean MD5CheckByPath(String path, String toBeCheckSum) {
        return MD5ByPath(path).equals(toBeCheckSum);
    }

}
