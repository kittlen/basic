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
}
