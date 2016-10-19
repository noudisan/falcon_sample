package com.zhiyi.utils.crypto;

import org.apache.log4j.Logger;

import java.security.MessageDigest;

public class Md5Utils {


    private static Logger LOG = Logger.getLogger(Md5Utils.class);

    public static String encode(String content) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(content.getBytes());
            return getEncode16(digest);
        } catch (Exception e) {
            LOG.error("",e);
        }
        return null;
    }

    public static String encode32(String content) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(content.getBytes());
            return getEncode32(digest);
        } catch (Exception e) {
            LOG.error("",e);
        }
        return null;
    }


    /**
     * 32位加密
     *
     * @param digest
     * @return
     */
    private static String getEncode32(MessageDigest digest) {
        StringBuilder builder = new StringBuilder();
        for (byte b : digest.digest()) {
            builder.append(Integer.toHexString((b >> 4) & 0xf));
            builder.append(Integer.toHexString(b & 0xf));
        }
        return builder.toString();
    }

    /**
     * 16位加密
     *
     * @param digest
     * @return
     */
    private static String getEncode16(MessageDigest digest) {
        StringBuilder builder = new StringBuilder();
        for (byte b : digest.digest()) {
            builder.append(Integer.toHexString((b >> 4) & 0xf));
            builder.append(Integer.toHexString(b & 0xf));
        }
        return builder.substring(8, 24).toString();
    }

}
