package ioUtil;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 编码读取文件 {

	public static void main(String[] args) {
		
		try {
//			printByFileInputStream("F:\\MyEclipse WorksPace\\java功能技巧方法\\数据库真分页.txt");
			printByFileInputStream("C:\\Users\\Administrator\\Desktop\\技巧\\带搜索的输入框.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void printByFileReader(String filePath) throws IOException
	{
	    BufferedReader reader=new BufferedReader(
	            new FileReader(filePath)
	            );
	    if(!reader.ready())
	    {
	        System.out.println("文件流暂时无法读取");
	    }
	    int result=0;
	    while((result=reader.read())!=-1)
	    {
	        //因为读取到的是int类型的，所以要强制类型转换
	        System.out.print((char)result);
	    }
	    reader.close();
	}
	
	/**
	 * 带编码
	 * @param filePath
	 * @throws IOException
	 */
	public static void printByFileInputStream(String filePath) throws IOException{
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(filePath);
//			String fileEncode=codeString(new File(filePath)); //判断文件编码格式
			String fileEncode=charset(filePath); //判断文件编码格式
            InputStreamReader reader = new InputStreamReader(fis,fileEncode);
            BufferedReader br = new BufferedReader(reader);
            String line;
            String ruturnLine = "";
            while ((line = br.readLine()) != null) {
//                System.out.println(line);
            	ruturnLine+=line;
            }
            br.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	
	/**
	 * 判断文件的编码格式(简单)
	 * @param fileName :file
	 * @return 文件编码格式
	 * @throws Exception
	 */
	public static String codeString(File fileName) throws Exception{
		BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName));
		int p = (bin.read() << 8) + bin.read();
		String code = null;
		
		switch (p) {
			case 0xefbb:
				code = "UTF-8";
				break;
			case 0xfffe:
				code = "Unicode";
				break;
			case 0xfeff:
				code = "UTF-16BE";
				break;
			default:
				code = "GBK";
		}
		bin.close();
		return code;
	}
	
	/**
     * 判断文本文件的字符集，文件开头三个字节表明编码格式。 (复杂)
     * <a href="http://blog.163.com/wf_shunqiziran/blog/static/176307209201258102217810/">参考的博客地址</a>
     * 
     * @param path
     * @return
     * @throws Exception
     * @throws Exception
     */
    public static String charset(String path) {
        String charset = "GBK";
        byte[] first3Bytes = new byte[3];
        try {
            boolean checked = false;
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
            bis.mark(0); // 读者注： bis.mark(0);修改为 bis.mark(100);我用过这段代码，需要修改上面标出的地方。 
                        // Wagsn注：不过暂时使用正常，遂不改之
            int read = bis.read(first3Bytes, 0, 3);
            //通过前三个字符判断文件编码
            if (read == -1) {
                bis.close();
                return charset; // 文件编码为 ANSI
            } else if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
                charset = "UTF-16LE"; // 文件编码为 Unicode
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xFE && first3Bytes[1] == (byte) 0xFF) {
                charset = "UTF-16BE"; // 文件编码为 Unicode big endian
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xEF && first3Bytes[1] == (byte) 0xBB
                    && first3Bytes[2] == (byte) 0xBF) {
                charset = "UTF-8"; // 文件编码为 UTF-8
                checked = true;
            }
            bis.reset();
            //逐个字符进行判断文件编码
            if (!checked) {
                while ((read = bis.read()) != -1) {
                    if (read >= 0xF0)
                        break;
                    if (0x80 <= read && read <= 0xBF) // 单独出现BF以下的，也算是GBK
                        break;
                    if (0xC0 <= read && read <= 0xDF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) // 双字节 (0xC0 - 0xDF)
                            // (0x80 - 0xBF),也可能在GB编码内
                            continue;
                        else
                            break;
                    } else if (0xE0 <= read && read <= 0xEF) { // 也有可能出错，但是几率较小
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) {
                            read = bis.read();
                            if (0x80 <= read && read <= 0xBF) {
                                charset = "UTF-8";
                                break;
                            } else
                                break;
                        } else
                            break;
                    }
                }
            }
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("--文件-> [" + path + "] 采用的字符集为: [" + charset + "]");
        return charset;
    }

}
