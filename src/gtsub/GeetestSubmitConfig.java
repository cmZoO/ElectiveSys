package gtsub;

import javax.swing.text.StyledEditorKit.BoldAction;

/**
 * GeetestWeb配置文件
 * 
 *
 */
public class GeetestSubmitConfig {

	// 填入自己的captcha_id和private_key
	private static final String geetest_id = "13ce33b8f5e2ec78d26d9722f9255277";
	private static final String geetest_key = "7d79727ed6a00212aaedfe40cafafce5";
	private static final boolean newfailback = true;

	public static final String getGeetest_id() {
		return geetest_id;
	}

	public static final String getGeetest_key() {
		return geetest_key;
	}
	
	public static final boolean isnewfailback() {
		return newfailback;
	}

}
