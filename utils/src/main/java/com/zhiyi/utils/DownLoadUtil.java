package com.zhiyi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DownLoadUtil {

    protected static Logger LOG = LoggerFactory.getLogger(DownLoadUtil.class);


    private static String toHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			sb.append(HEX_DIGITS[(bytes[i] & 0xf0) >>> 4]);
			sb.append(HEX_DIGITS[bytes[i] & 0x0f]);
		}
		return sb.toString();
	}

	private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static String encodeByMD5(String originString) {
		if (originString != null) {
			try {
				MessageDigest digest = MessageDigest.getInstance("MD5");
				digest.reset();
				digest.update(originString.getBytes());
				String resultString = toHexString(digest.digest());
				return resultString.toLowerCase();
			} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException(e);
			}
		}

		return originString;
	}

	/**
	 * 
	 * @param f
	 *            保存的文件
	 * @param imgUrl
	 *            文件地址
	 */
	public static void down(File f, String imgUrl) {
		byte[] buffer = new byte[8 * 1024];
		URL u;
		URLConnection connection = null;
		try {
			u = new URL(imgUrl);
			connection = u.openConnection();
		} catch (Exception e) {
			System.out.println("ERR:" + imgUrl);
			return;
		}
		connection.setReadTimeout(100000);
		InputStream is = null;
		FileOutputStream fos = null;
		try {
			connection.connect();
		} catch (IOException e1) {
            LOG.error("",e1);
		}
		try {
			f.createNewFile();
			is = connection.getInputStream();
			fos = new FileOutputStream(f);
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
		} catch (Exception e) {
			f.delete();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
                    LOG.error("",e);
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
                    LOG.error("",e);
				}
			}
		}
		buffer = null;
		// System.gc();
	}

	public static byte[] getBytesByURL(String imageUrl) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedInputStream bis = null;
		HttpURLConnection urlconnection = null;
		URL url = null;
		byte[] buf = new byte[1024];
		try {
			url = new URL(imageUrl);
			urlconnection = (HttpURLConnection) url.openConnection();
			urlconnection.connect();
			bis = new BufferedInputStream(urlconnection.getInputStream());
			for (int len = 0; (len = bis.read(buf)) != -1;) {
				baos.write(buf, 0, len);
			}
			return baos.toByteArray();
		} finally {
			try {
				urlconnection.disconnect();
				bis.close();
			} catch (IOException ignore) {
                LOG.error("",ignore);
			}
		}
	}
}
