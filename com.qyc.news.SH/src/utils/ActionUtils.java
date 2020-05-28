package utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;

public class ActionUtils {
	public static void actionPrintWrite(String content) throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = response.getWriter();
		pw.append(content);
		pw.flush();
		pw.close();
	}

	/**
	 * MultipartForm响应
	 * 
	 * @param          factory.setRepository(new
	 *                 File(System.getProperty("java.io.tmpdir")));
	 *                 web程序：如果你不用tomcat，直接一个java类main方法，然后直接输出System.getProperty("java.io.tmpdir")那么结果是你当前系统下的临时文件目录如
	 *                 win7：C:\Users\用户名\AppData\Local\Temp。但如果你把web程序放入tomcat下然后再输出System.getProperty("java.io.tmpdir")，那么这是结果是：D:\apache-tomcat-7.0.47\temp。
	 * @param savePath 下载目录相对路径
	 * @return 提交表单Map
	 * @throws Exception
	 */
	public static Map<String, String> MultipartFormResponse(String savePath) throws Exception {
		Map<String, String> map = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		// 上传文件名
		String fileName = null;
		String path = ServletActionContext.getServletContext().getRealPath(savePath);
		File uploadPath = new File(path);
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		// 检测是不是存在上传文件
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (isMultipart) {
			map = new HashMap<String, String>();

			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 指定在内存中缓存数据大小,单位为byte,这里设为1Mb
			factory.setSizeThreshold(1024 * 1024);
			// 设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			// factory.setRepository(new File(discCachePath));//自定义缓存路径
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 指定单个上传文件的最大尺寸,单位:字节，这里设为50Mb
			upload.setFileSizeMax(50 * 1024 * 1024);
			// 指定一次上传多个文件的总尺寸,单位:字节，这里设为50Mb
			upload.setSizeMax(50 * 1024 * 1024);
			upload.setHeaderEncoding("UTF-8");

			List<FileItem> items = null;
			// 解析request请求
			items = upload.parseRequest(request);
			if (items != null) {
				// 迭代解析表单项目
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = iter.next();
					// 如果是普通表单属性
					if (item.isFormField()) {
						// 相当于input的name属性 <input type="text" name="content">
						String name = item.getFieldName();
						// input的value属性
						String value = item.getString();
						map.put(name, value);
					}
					// 如果是上传文件
					else {
						// 属性名
						// String fieldName = item.getFieldName();
						// 路径
						fileName = item.getName();
						// 文件名
						fileName = fileName.substring(fileName.lastIndexOf("/") + 1);// 获得上传文件的文件名
						// 写入文件
						item.write(new File(path, fileName));
						map.put("fileName", fileName);
					}
				}
			}
		}
		return map;
	}

	/**
	 * MultipartForm响应
	 * 
	 * @param discCachePath 磁盘缓存路径 //factory.setRepository(new
	 *                      File(System.getProperty("java.io.tmpdir")));
	 * @param savePath      下载目录相对路径
	 * @return 提交表单Map
	 * @throws Exception
	 */
	public static Map<String, String> MultipartFormResponse(String discCachePath, String savePath) throws Exception {
		Map<String, String> map = null;

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		// 上传文件名
		String fileName = null;
		String path = ServletActionContext.getServletContext().getRealPath(savePath);
		File uploadPath = new File(path);
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		// 检测是不是存在上传文件
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (isMultipart) {
			map = new HashMap<String, String>();

			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 指定在内存中缓存数据大小,单位为byte,这里设为1Mb
			factory.setSizeThreshold(1024 * 1024);
			// 设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
			factory.setRepository(new File(discCachePath));
			// factory.setRepository(new File(discCachePath));//自定义缓存路径
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 指定单个上传文件的最大尺寸,单位:字节，这里设为50Mb
			upload.setFileSizeMax(50 * 1024 * 1024);
			// 指定一次上传多个文件的总尺寸,单位:字节，这里设为50Mb
			upload.setSizeMax(50 * 1024 * 1024);
			upload.setHeaderEncoding("UTF-8");

			List<FileItem> items = null;
			// 解析request请求
			items = upload.parseRequest(request);
			if (items != null) {
				// 迭代解析表单项目
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = iter.next();
					// 如果是普通表单属性
					if (item.isFormField()) {
						// 相当于input的name属性 <input type="text" name="content">
						String name = item.getFieldName();
						// input的value属性
						String value = item.getString();
						map.put(name, value);
					}
					// 如果是上传文件
					else {
						// 属性名
						// String fieldName = item.getFieldName();
						// 路径
						fileName = item.getName();
						// 文件名
						fileName = fileName.substring(fileName.lastIndexOf("/") + 1);// 获得上传文件的文件名
						// 写入文件
						item.write(new File(path, fileName));
						map.put("fileName", fileName);
					}
				}
			}
		}
		return map;
	}

	/**
	 * MultipartForm响应  路径名应该在传过来时添加一个文本key为"dic" 值为"文件夹名" 用于创建文件夹
	 * 
	 * @param          factory.setRepository(new
	 *                 File(System.getProperty("java.io.tmpdir")));
	 *                 web程序：如果你不用tomcat，直接一个java类main方法，然后直接输出System.getProperty("java.io.tmpdir")那么结果是你当前系统下的临时文件目录如
	 *                 win7：C:\Users\用户名\AppData\Local\Temp。但如果你把web程序放入tomcat下然后再输出System.getProperty("java.io.tmpdir")，那么这是结果是：D:\apache-tomcat-7.0.47\temp。
	 * @return 提交表单Map
	 * @throws Exception
	 */
	public static Map<String, String> MultipartFormResponse() throws Exception {
		Map<String, String> map = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		//保存的路径名
		String path = "defaultDic";

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		// 检测是不是存在上传文件
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (isMultipart) {
			map = new HashMap<String, String>();

			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 指定在内存中缓存数据大小,单位为byte,这里设为1Mb
			factory.setSizeThreshold(1024 * 1024);
			// 设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			// factory.setRepository(new File(discCachePath));//自定义缓存路径
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 指定单个上传文件的最大尺寸,单位:字节，这里设为50Mb
			upload.setFileSizeMax(50 * 1024 * 1024);
			// 指定一次上传多个文件的总尺寸,单位:字节，这里设为50Mb
			upload.setSizeMax(50 * 1024 * 1024);
			upload.setHeaderEncoding("UTF-8");

			List<FileItem> items = null;
			// 解析request请求
			items = upload.parseRequest(request);
			if (items != null) {
				// 迭代解析表单项目
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = iter.next();
					// 如果是普通表单属性
					if (item.isFormField()) {
						// 相当于input的name属性 <input type="text" name="content">
						String name = item.getFieldName();
						// input的value属性
						String value = item.getString();
						map.put(name, value);
						if (name.equals("dic")) {
							path = ServletActionContext.getServletContext().getRealPath(value);
							File uploadPath = new File(path);
							if (!uploadPath.exists()) {
								uploadPath.mkdirs();
							}
						}
					}
					// 如果是上传文件
					else {
						// 属性名
						String fileName = item.getFieldName();
						// 路径
						/*fileName = item.getName();
						// 文件名
						fileName = fileName.substring(fileName.lastIndexOf("/") + 1);// 获得上传文件的文件名
*/						// 写入文件
						item.write(new File(path, fileName));
						map.put("fileName", fileName);
					}
				}
			}
		}
		return map;
	}
}
