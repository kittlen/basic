package util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;

public class util {

	/**
	 * 生成32位UUID
	 * @return
	 */
	public static String getId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}

	/**
	 * 32位MD5加密
	 * 
	 * @param str 待加密字符串
	 * @return 32位MD5加密字符串
	 * @throws NoSuchAlgorithmException
	 */
	public static String UseMD5(String str) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(str.getBytes());
		BigInteger bigInteger = new BigInteger(1, md.digest());
		return bigInteger.toString(16);
	}

	/**
	 * 获取系统当前日期时间 年月日
	 * 
	 * @return 系统当前日期时间
	 */
	public static String getNowTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(System.currentTimeMillis());
	}

	/**
	 * 获取当前时间的SQL时间类型
	 * 
	 * @return SQL时间
	 */
	public static java.sql.Date getSQDate() {
		Date date = new Date();
		// 将当前时间转化为sql时间
		java.sql.Date transDate = new java.sql.Date(date.getTime());
		return transDate;
	}

	/**
	 * 获取系统当前日期时间 年月日时分秒
	 * 
	 * @return 系统当前日期时间
	 */
	public static String getNowDetailedTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(System.currentTimeMillis());
	}

	/**
	 * 获取当前日期所对应的学期
	 * 
	 * @return 当前学期
	 */
	public static String getNewSemester() {
		String date = getNowTime();
		int y = Integer.parseInt(date.split("-")[0]);
		int M = Integer.parseInt(date.split("-")[1]);
		if (M > 8) {
			return y + "-" + (y + 1) + "学年上学期";
		} else {
			return (y - 1) + "-" + (y) + "学年下学期";
		}

	}

	/**
	 * 获取对应日期所对应的学期 0当前日期 1-n前n日期对应的学期(1上一个学期 2上两个学期)
	 * 
	 * @param index
	 * @return
	 */
	public static String getSemester(int index) {
		String date = getNowTime();
		int y = Integer.parseInt(date.split("-")[0]);
		int M = Integer.parseInt(date.split("-")[1]);
		if (index % 2 == 0) {
			index /= 2;
			if (M > 8) {
				return (y - index) + "-" + (y + 1 - index) + "学年上学期";
			} else {
				return (y - 1 - index) + "-" + (y - index) + "学年下学期";
			}
		} else {
			index /= 2;
			if (M > 8) {
				return (y - 1 - index) + "-" + (y - 1 - index) + "学年下学期";
			} else {
				return (y - 1 - index) + "-" + (y - index) + "学年上学期";
			}

		}

	}

	/**
	 * 用于将异常对象e中的错误信息构建为一个String字 符串，该字符串 将用于输出到日志文件当中去
	 * 
	 * @param e 异常对象
	 * @return 异常对象中的异常信息字符串
	 */
	public static String getExceptionMsg(Throwable e) {
		// 将异常对象中的异常记录转换为记录数组
		StackTraceElement[] s = e.getStackTrace();
		StringBuffer sb = new StringBuffer();// 从数组中取出异常信息，构建为一个字符串
		for (int i = 0; i < s.length; i++) {
			sb.append("\r\n");
			sb.append(s[i]);
		}
		return sb.toString();
	}

	/**
	 * 用于登录提示 如果第一次调用网页 则显示为空(不显示) 否则正常显示
	 * 
	 * @param str 登录提示
	 * @return 空或传入的登录提示
	 */
	public static String sendJspNull(String str) {
		if (str.equals("null")) {
			return "";
		}
		return str;
	}

	/**
	 * list转json死循环解锁
	 * 
	 * @param parmas [] 需要过滤的属性名
	 * @return jsonCongig list转json过滤条件
	 */
	public static JsonConfig jsonConfig(final String[] parmas) {
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object arg0, String arg1, Object arg2) {
				// TODO Auto-generated method stub
				// 这个college 是个外键，把他过滤就行了 对应model的对应属性 (首单词小写)
				for (int i = 0; i < parmas.length; i++)
					if (arg1.equals(parmas[i])) {
						return true;
					}
				return false;
			}
		});
		return config;
	}

	/**
	 * ajax数据回传
	 * 
	 * @param json list转换过后的jsonArray数据
	 * @throws IOException
	 */
	public static void writ(JSONArray json) throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(json.toString());
		pw.close();
		pw.flush();
	}

	/**
	 * 实现从网页代码从获取图片的路径
	 * 
	 * @param news_content html代码
	 * @return 图片路径list集
	 */
	public static List<String> getImg(String news_content) {
		String regex = "src=\"(.*?)\"";
		List<String> list = new ArrayList<String>();
		Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
		Matcher ma = pa.matcher(news_content);
		while (ma.find()) {
			list.add(ma.group());
		}
		return list;
	}

	/**
	 * 文件上传工具
	 * 
	 * @param servlet  Servlet对象
	 * @param request  request对象
	 * @param dirname  上传目标文件夹
	 * @param fileName 上传后文件名
	 * @throws Exception 异常
	 * @return 文件名
	 */
	public static String fileupload(HttpServlet servlet, HttpServletRequest request, String dirname, String fileName)
			throws Exception {
		// 配置上传参数
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
		factory.setSizeThreshold(1024 * 1024 * 3);
		// 设置临时存储目录
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置最大文件上传值
		upload.setFileSizeMax(1024 * 1024 * 40);
		// 设置最大请求值 (包含文件和表单数据)
		upload.setSizeMax(1024 * 1024 * 50);
		// 中文处理
		upload.setHeaderEncoding("UTF-8");

		// 构造临时路径来存储上传的文件
		// 这个路径相对当前应用的目录
		String uploadPath = servlet.getServletContext().getRealPath("/") + dirname;

		// 如果目录不存在则创建
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		// 解析请求的内容提取文件数据
		List<FileItem> formItems = upload.parseRequest(request);

		if (formItems != null && formItems.size() > 0) {
			// 迭代表单数据
			for (FileItem item : formItems) {
				// 处理不在表单中的字段
				if (!item.isFormField()) {

					String fName = new File(item.getName()).getName();
					String type = fName.substring(fName.lastIndexOf("."));

					if (fileName.equals("")) {
						fileName = new File(item.getName()).getName();
					} else {
						fileName = fileName + type;
					}
					String filePath = uploadPath + File.separator + fileName;
					File storeFile = new File(filePath);
					// 在控制台输出文件的上传路径
					System.out.println(filePath);
					// 如果文件存在,则删除原文件
					if (storeFile.exists()) {
						storeFile.delete();
					}
					// 保存文件到硬盘
					item.write(storeFile);
				}
			}
		}
		return fileName;

	}

	/**
	 * 删除文件，可以是文件或文件夹
	 *
	 * @param fileName 要删除的文件名
	 * @return 删除成功返回true，否则返回false
	 */
	public static boolean delete(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println("删除文件失败:" + fileName + "不存在！");
			return false;
		} else {
			if (file.isFile())
				return deleteFile(fileName);
			else
				return deleteDirectory(fileName);
		}
	}

	/**
	 * 删除单个文件
	 *
	 * @param fileName 要删除的文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				System.out.println("删除单个文件" + fileName + "成功！");
				return true;
			} else {
				System.out.println("删除单个文件" + fileName + "失败！");
				return false;
			}
		} else {
			System.out.println("删除单个文件失败：" + fileName + "不存在！");
			return false;
		}
	}

	/**
	 * 删除目录及目录下的文件
	 *
	 * @param dir 要删除的目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String dir) {
		// 如果dir不以文件分隔符结尾，自动添加文件分隔符
		if (!dir.endsWith(File.separator))
			dir = dir + File.separator;
		File dirFile = new File(dir);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
			System.out.println("删除目录失败：" + dir + "不存在！");
			return false;
		}
		boolean flag = true;
		// 删除文件夹中的所有文件包括子目录
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
			// 删除子目录
			else if (files[i].isDirectory()) {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag) {
			System.out.println("删除目录失败！");
			return false;
		}
		// 删除当前目录
		if (dirFile.delete()) {
			System.out.println("删除目录" + dir + "成功！");
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 冒泡排序
	 */
	public static void mpp(int a[]) {

		int x = 0;
		for (int i = a.length; i >= 0; i--) {
			for (int t = 0; t < i - 1; t++) {
				if (a[t] < a[t + 1]) {
					x = a[t + 1];
					a[t + 1] = a[t];
					a[t] = x;
				}
			}
		}
	}

	/**
	 * 快速排序
	 */
	public static void ksp(int a[], int x, int y) {

		if (x > y) {
			return;
		} // 当分割的数组仅剩下1位时退出递归
		int start = x;
		int end = y;
		int key = a[x];// 选一个数充当key值
		while (start < end) {
			while (start < end && a[end] <= key) {// 从数组后面开始,当后面的数比key值小时
				end--;
			}
			while (start < end && a[start] >= key) {// 从数组前面开始.当前面的数比key值大时
				start++;
			}
			// 把数组前面比key小的数与数组后面比key大的数交换
			int z = a[end];
			a[end] = a[start];
			a[start] = z;
		}

		// 当前面与后面的位置相同时,交换key值与前面位置所在的数组值
		if (start >= end) {
			int z = a[start];
			a[start] = key;
			a[x] = z;
		}

		// 将数组分为两部分分别进行递归,直到将数组分割仅剩1位.
		ksp(a, 0, start - 1);
		ksp(a, start + 1, y);

	}

	/**
	 * 堆排序(完全二叉树)
	 */
	public static void dp(int a[], int x, int y) {

		if (y < x) {
			return;
		} // 当二叉树只有一个根节点时退出递归
		int end = y;// 节点数
		while (end > x) {// 比较子节点与父节点位置的数字大小,大的数字放在父节点上;
			int w = (end - 1) / 2;// 父节点位置;
			if (a[end] > a[w]) {
				int z = a[end];
				a[end] = a[w];
				a[w] = z;
			}
			end--;
		}

		// 将最后一个节点与根节点交换,将最大的数放在数组最后
		int z = a[y];
		a[y] = a[x];
		a[x] = z;
		y--;

		// 递归比较剩下的子父节点大小
		dp(a, x, y);

	}

}
