package com.zhiyi.falcon;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("http_client_ip");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}

		// 如果是多级代理，那么取第一个ip为客户ip
		if (ip != null && ip.indexOf(",") != -1) {
			ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
		}

		return ip;
	}

	public static boolean checkNullStr(String str) {
		if (null == str || "undefined".equalsIgnoreCase(str)|| "null".equalsIgnoreCase(str) || "".equals(str.trim())) {
			return true;
		}
		return false;
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static long computeTime(String time1, String time2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long diff = 0;
		try {
			Date d1 = df.parse(time1);
			Date d2 = df.parse(time2);
			diff = d1.getTime() - d2.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return diff;
	}

	public static String dateToString(Date date, String formatString) {
		if (checkNullStr(formatString)) {
			formatString = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat dd = new SimpleDateFormat(formatString);
		String str = "";
		if (null != date) {
			str = dd.format(date);
		}
		return str;
	}

	public static Date stringToDate(String date, String format) {
		if (Utils.checkNullStr(date)) {
			return null;
		}
		if (Utils.checkNullStr(format)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date getCurDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date today = calendar.getTime();
		return today;
	}

	public static Date getYesterDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date yesterday = calendar.getTime();
		return yesterday;
	}

	public static final SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	public static final SimpleDateFormat sd2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZ");
	public static final SimpleDateFormat sd3 = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat sd4 = new SimpleDateFormat("yyyy-MM-ddZZ");
	public static final SimpleDateFormat sd5 = new SimpleDateFormat("'T'HH:mm:ss");
	public static final SimpleDateFormat sd6 = new SimpleDateFormat("'T'HH:mm:ssZZ");
	public static final SimpleDateFormat sd7 = new SimpleDateFormat("HH:mm:ss");
	public static final SimpleDateFormat sd8 = new SimpleDateFormat("HH:mm:ssZZ");
	public static final SimpleDateFormat sd9 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US);

	public static long getTime(String s) throws Exception {
		try {
			return sd1.parse(s).getTime();
		} catch (Exception e) {
		}
		try {
			return sd1.parse(s).getTime();
		} catch (Exception e) {
		}
		try {
			return sd2.parse(s).getTime();
		} catch (Exception e) {
		}
		try {
			return sd3.parse(s).getTime();
		} catch (Exception e) {
		}
		try {
			return sd4.parse(s).getTime();
		} catch (Exception e) {
		}
		try {
			return sd5.parse(s).getTime();
		} catch (Exception e) {
		}
		try {
			return sd6.parse(s).getTime();
		} catch (Exception e) {
		}
		try {
			return sd7.parse(s).getTime();
		} catch (Exception e) {
		}
		try {
			return sd8.parse(s).getTime();
		} catch (Exception e) {
		}
		try {
			return sd9.parse(s).getTime();
		} catch (Exception e) {
		}
		return 10000l;
	}

	/**
	 * 判断手机号格式是否正确
	 * 
	 * @author 杨东
	 * @since v1.0
	 * @date 2013年9月28日 上午11:07:02
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^([\\d]{1,4}-?[\\d]{1,10})$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * 判断座机号是否正确
	 * 
	 * @author huxin
	 * @since v1.0
	 * @date 2013年10月24日 下午14:31
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean isPhoneNO(String mobiles) {
		Pattern p = Pattern.compile("^((123)|(147)|(13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * 判断邮箱格式是否正确
	 * 
	 * @author huxin
	 * @since v1.0
	 * @date 2013年10月28日 下午12:46
	 * 
	 * @param email
	 * @return boolean
	 */
	public static boolean isEmail(String email) {
		Pattern p = Pattern.compile("^([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+.[a-zA-Z]{2,4}$");
		Matcher m = p.matcher(email);
		return m.matches();
	}

	public static String formatCode(long code) {
		String codeString;
		if (code < 10) {
			codeString = "000000" + code;
		} else if (code < 100) {
			codeString = "00000" + code;
		} else if (code < 1000) {
			codeString = "0000" + code;
		} else if (code < 10000) {
			codeString = "000" + code;
		} else if (code < 100000) {
			codeString = "00" + code;
		} else if (code < 1000000) {
			codeString = "0" + code;
		} else {
			codeString = String.valueOf(code);
		}

		return codeString;
	}

	public static String formatSectionCode(long code) {
		String codeString;
		if (code < 10) {
			codeString = "00000" + code;
		} else if (code < 100) {
			codeString = "0000" + code;
		} else if (code < 1000) {
			codeString = "000" + code;
		} else if (code < 10000) {
			codeString = "00" + code;
		} else if (code < 100000) {
			codeString = "0" + code;
		} else {
			codeString = String.valueOf(code);
		}

		return codeString;
	}

	public static boolean isNumberStr(String numberStr) {
		try {
			Long.parseLong(numberStr);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static String unicodeDecode(String strText) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		char c;
		while (i < strText.length()) {
			c = strText.charAt(i);
			if (c == '\\' && (i + 1) != strText.length() && strText.charAt(i + 1) == 'u') {
				sb.append((char) Integer.parseInt(strText.substring(i + 2, i + 6), 16));
				i += 6;
			} else {
				sb.append(c);
				i++;
			}
		}

		return sb.toString();
	}

	public static String getPercent(Double d) {
		NumberFormat num = NumberFormat.getPercentInstance();
		num.setMaximumIntegerDigits(3);
		num.setMaximumFractionDigits(2);
		return num.format(d);
	}

	public static String getCommunityCode() {
		UUID uuid = UUID.randomUUID();
		String code = uuid.toString();
		code.replaceAll("-", "");
		code = Utils.encodeByMD5(code);
		code = code.substring(8, 24);
		return code;
	}

	// 计算两点距离
	private final static double EARTH_RADIUS = 6378137.0;

	public static double gps2m(double lng_a, double lat_a, double lng_b, double lat_b) {
		double radLat1 = (lat_a * Math.PI / 180.0);
		double radLat2 = (lat_b * Math.PI / 180.0);
		double a = radLat1 - radLat2;
		double b = (lng_a - lng_b) * Math.PI / 180.0;
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}

	public static boolean isDate(String date) {
		Pattern p = Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{2}$");
		Matcher m = p.matcher(date);
		return m.matches();
	}

	/**
	 * 字符串的格式的日期如何比较大小
	 * 
	 */
	public static boolean compareStringDate(String date, String startDate, String endDate) {
		if (startDate.compareTo(date) <= 0 && endDate.compareTo(date) >= 0) {
			return true;
		} else {
			return false;
		}
	}

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

	private static String toHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			sb.append(HEX_DIGITS[(bytes[i] & 0xf0) >>> 4]);
			sb.append(HEX_DIGITS[bytes[i] & 0x0f]);
		}
		return sb.toString();
	}

	private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static String doGet(String requestUrl, Map<String, String> params) throws IOException {
		if (params != null && params.size() > 0) {
			requestUrl += "?";
			for (String key : params.keySet()) {
				String value = params.get(key);
				// requestUrl += (key + "=" + new String(value.getBytes(),
				// "ISO8859-1") + "&");
				requestUrl += (key + "=" + URLEncoder.encode(value, "UTT-8") + "&");
			}
			requestUrl = requestUrl.substring(0, requestUrl.length() - 1);
		}

		URL url = new URL(requestUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("Content-Type", "text/html; charset=UTF-8");
		connection.connect();
		int responsecode = connection.getResponseCode();

		if (responsecode == HttpURLConnection.HTTP_OK) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			String result = "";
			while ((line = reader.readLine()) != null) {
				result += "\n" + line;
			}
			reader.close();

			return result;
		}
		connection.disconnect();

		return null;
	}

	public static String doPost(String requestUrl, Map<String, String> params) throws IOException {
		String BOUNDARY = UUID.randomUUID().toString();
		String PREFIX = "--", LINEND = "\r\n";
		String MULTIPART_FROM_DATA = "multipart/form-data";
		String CHARSET = "UTF-8";

		URL uri = new URL(requestUrl);
		HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
		conn.setReadTimeout(30 * 1000);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("connection", "keep-alive");
		conn.setRequestProperty("Charsert", "utf-8");
		conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);

		// 组拼文本类型的参数
		StringBuilder builder = new StringBuilder();
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				builder.append(PREFIX);
				builder.append(BOUNDARY);
				builder.append(LINEND);
				builder.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + LINEND);
				builder.append("Content-Type: text/plain; charset=" + CHARSET + LINEND);
				builder.append("Content-Transfer-Encoding: 8bit" + LINEND);
				builder.append(LINEND);
				builder.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
				builder.append(LINEND);
			}
		}

		DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
		outStream.write(builder.toString().getBytes());

		byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
		outStream.write(end_data);
		outStream.flush();

		int responsecode = conn.getResponseCode();
		if (responsecode == HttpURLConnection.HTTP_OK) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			String result = "";
			while ((line = reader.readLine()) != null) {
				result += (line + System.getProperty("line.separator"));
			}
			reader.close();

			return result;
		}
		conn.disconnect();

		return null;
	}

	public static String add40Minutes(String s, int n) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Calendar cd = Calendar.getInstance();
			cd.setTime(sdf.parse(s));
			cd.add(Calendar.MINUTE, n);// 分钟数
			return sdf.format(cd.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String add40Minutes2(String s, int n) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			Calendar cd = Calendar.getInstance();
			cd.setTime(sdf.parse(s));
			cd.add(Calendar.MINUTE, n);// 分钟数
			return sdf.format(cd.getTime());
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean isEmpty(List<?> data) {
		if (null == data || data.size() <= 0) {
			return true;
		}
		return false;
	}

	public static String dateToStringWithNoLine(Date date) {
		SimpleDateFormat dd = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = "";
		if (null != date) {
			str = dd.format(date);
		}
		return str;
	}

	public static boolean startWithIgnoreCase(String content, String prefix) {
		if (content == null || prefix == null) {
			return false;
		}
		String temp = content.substring(0, prefix.length());
		if (prefix.equalsIgnoreCase(temp)) {
			return true;
		}
		return false;
	}

	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除指定文件夹下所有文件
	// param path 文件夹完整绝对路径
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}
	public static final String getNullStr(String str){
		if(null == str || "null".equalsIgnoreCase(str) || str.length()<=0){
			return "";
		}
		return str;
	}
	
	public static float getFloatFormatStr(float number) {
		String str;
		try {
			String parten = "#.##";
			DecimalFormat decimal = new DecimalFormat(parten);
			str = decimal.format(number);
			return Float.parseFloat(str);
		} catch (Exception e) {
		}
		return 0;
	}
	public static double getDoubleFormatStr(double number) {
		String str;
		try {
			String parten = "#.##";
			DecimalFormat decimal = new DecimalFormat(parten);
			str = decimal.format(number);
			return Double.parseDouble(str);
		} catch (Exception e) {
		}
		return 0;
	}
	public static final boolean hasCurAuthoriy(List<String> authNameList,String authorityName){
		boolean hasAuthority = false;
		for (String authorityStr : authNameList) {
			if (authorityStr.equals(authorityName)) {
				hasAuthority = true;
			}
		}
		return hasAuthority;
	}
}
