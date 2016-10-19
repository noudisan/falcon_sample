/*
package com.zhiyi.utils;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.util.Map;

*/
/**
 * 文件生成工具类
 * <p>基于freemarker模板生成</p>
 * 
 * @author xinqigu
 *
 *//*

public class FileGernateUtil {

	protected static Logger LOG = LoggerFactory.getLogger(FileGernateUtil.class);
	
	public static final Configuration configuration = new Configuration();
	
	*/
/**
	 * 默认编码UTF-8
	 *//*

	public static final String ENCODING = "UTF-8";
	
	static {
		//StringTemplateLoader loader = new StringTemplateLoader();
		//configuration.setTemplateLoader(loader);
		//configuration.setTemplateUpdateDelay(0);0 表示每次都重新加载，否则为多少毫秒钟检测一下模版是否更改
		//configuration.setCacheStorage(new MruCacheStorage(2, 0));
		configuration.setDefaultEncoding(ENCODING);
		configuration.setBooleanFormat("true,false");
		configuration.setDateFormat("yyyy-MM-dd");
		configuration.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
		configuration.setTimeFormat("HH:mm:ss");
		configuration.setNumberFormat("0.######");
		configuration.setWhitespaceStripping(true);
		configuration.setURLEscapingCharset(ENCODING);
		configuration.setTagSyntax(0);
	}
	
	*/
/**
	 * 文件生成
	 * @param templateName 模板名称
	 * @param templateSource 模板内容
	 * @param map	替换标签的内容
	 * @param filePath	文件生成地址
	 *//*

	public static void generateFile(
			String templateName, String templateSource, Map map, String filePath) {
		
		generateFile(templateName, templateSource, map, filePath, ENCODING);
	}
	
	*/
/**
	 * 文件生成
	 * 
	 * @param templateSource 模板内容
	 * @param map	替换标签的内容
	 * @param filePath	文件生成地址
	 * @param encoding  编码
	 *//*

	public static void generateFile(String templateName, 
			String templateSource, Map map, String filePath, String encoding) {
		Template template = null;
		Writer out = null;
		byte[] bakFileBytes = null;
		File file = new File(pathFilter(filePath));
		try {
			StringTemplateLoader loader = new StringTemplateLoader();
			loader.putTemplate(templateName, templateSource);
			configuration.setTemplateLoader(loader);
			configuration.setObjectWrapper(new DefaultObjectWrapper());
			template = configuration.getTemplate(templateName);
			backFile(bakFileBytes, file);
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding));
			template.process(map, out);
			out.flush();
		} catch (Exception e) {
            LOG.error("Build Error" + templateName, e);
			restoreFile(bakFileBytes, file);
			throw new RuntimeException("Build Error" + templateName);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
                    LOG.error("",e);
				}
			}
		}
	}
	
	*/
/**
	 * 返回模板解析后的内容
	 * 
	 * @param templateName
	 * @param templateSource
	 * @param map
	 *//*

	public static String getTemplateGenerateContent(String templateName, String templateSource, Map map) {
		return getTemplateGenerateContent(templateName, templateSource, map, ENCODING);
	}
	
	*/
/**
	 * 返回模板解析后的内容
	 * 
	 * @param templateName
	 * @param templateSource
	 * @param map
	 * @param encoding
	 *//*

	public static String getTemplateGenerateContent(
			String templateName, String templateSource, Map map, String encoding) {
		Template template = null;
		String content = null;
		try {
			StringTemplateLoader loader = new StringTemplateLoader();
			loader.putTemplate(templateName, templateSource);
			configuration.setTemplateLoader(loader);
			configuration.setObjectWrapper(new DefaultObjectWrapper());
			template = configuration.getTemplate(templateName);
			
			StringWriter result = new StringWriter();
			template.process(map, result);
			content = result.toString();
			
		} catch (Exception e) {
			LOG.error("Build Error" + templateName, e);
			throw new RuntimeException("Build Error " + templateName);
		}
		return content;
	}
	
	*/
/**
	 * 文件备份
	 * @param bakFileBytes
	 * @param file
	 *//*

	private static void backFile(byte[] bakFileBytes, File file) {
		try {
			if (file.exists()) {//先备份旧页面内容
				bakFileBytes = FileCopyUtils.copyToByteArray(file);
				if (ArrayUtils.isEmpty(bakFileBytes)) {
					LOG.error(" back file is empty ");
				}
			} else {
				FileUtils.forceMkdir(file.getParentFile());
			}
		} catch (IOException e) {
			LOG.error("bak File Error:" + e);
		}
	}
	
	*/
/**
	 * 恢复文件
	 * 
	 * @param bakFileBytes
	 * @param file
	 *//*

	private static void restoreFile(byte[] bakFileBytes, File file) {
		try {
			if (!ArrayUtils.isEmpty(bakFileBytes)) {
				FileCopyUtils.copy(bakFileBytes, file);
			}
		} catch (IOException e) {
			LOG.error("restore File Error:" + e);
		}
	}

	*/
/**
	 * 
	 * @param path
	 * @return
	 *//*

	protected static String pathFilter(String path) {
		if (path != null) {
			path = path.trim();
			while (path.endsWith("/") || path.endsWith("\\")) {
				path = path.substring(0, path.length() - 1);
			}
		}
		return path;
	}
}
*/
