package StrUtil;

import java.util.UUID;

public class 获取主键 {
	
	/**
	 * 生成32位UUID
	 * @return
	 */
	public static String getId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}

}
