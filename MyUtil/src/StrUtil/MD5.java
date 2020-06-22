package StrUtil;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	/**
	 * 32位MD5加密
	 * 
	 * @param str
	 *            待加密字符串
	 * @return 32位MD5加密字符串
	 * @throws NoSuchAlgorithmException
	 */
	public static String UseMD5(String str) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(str.getBytes());
		BigInteger bigInteger = new BigInteger(1, md.digest());
		return bigInteger.toString(16);
	}
	
	 //加密字符串
    public String getMD5Code(String info){
        try {
            MessageDigest md5=MessageDigest.getInstance("MD5");
            md5.update(info.getBytes("utf-8"));
            byte[]encryption=md5.digest();
            StringBuffer stringBuffer=new StringBuffer();
            for(int i=0;i<encryption.length;i++){
                if(Integer.toHexString(0xff &encryption[i]).length()==1){
                    stringBuffer.append("0").append(Integer.toHexString(0xff&encryption[i]));
                }else {
                    stringBuffer.append(Integer.toHexString(0xff&encryption[i]));
                }
            }
            return stringBuffer.toString();
        } catch (Exception e) {
//            e.printStackTrace();
            return "";
        }
    }
   public static void main(String[] args) throws NoSuchAlgorithmException {
	   MD5 md5=new MD5();
	System.out.println(md5.UseMD5("asdwnansdgkalwkalsndwasdg4a21c3zx45s7a89s4651ca23s1d3"));
	System.out.println(md5.getMD5Code("asdwnansdgkalwkalsndwasdg4a21c3zx45s7a89s4651ca23s1d3"));
}
}
