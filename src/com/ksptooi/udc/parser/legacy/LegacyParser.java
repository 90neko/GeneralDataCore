package com.ksptooi.udc.parser.legacy;


/**
 * ���ݾɰ����ݸ�ʽ�Ľ�����
 */
public class LegacyParser {

	/**
	 * ![Legacy]
	 * ���ַ����н�����key
	 * @param str Ҫ�������ַ���
	 * @return key
	 */
	public static String toKey(String str) {
		
		int valueMarkIndex = str.indexOf("=");
		
		return str.substring(0, valueMarkIndex);
		
	}
	
	/**
	 * ![Legacy]
	 * ���ַ����н�����value
	 * @param str Ҫ�������ַ���
	 * @return value
	 */
	public static String toValue(String str) {
		
		int valueMarkIndex = str.indexOf("=");
		
		return str.substring(valueMarkIndex+1);
		
	}
	
	
}
