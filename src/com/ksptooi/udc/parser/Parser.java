package com.ksptooi.udc.parser;

public class Parser {

	/**
	 * ���ַ����н�����key
	 * @param str Ҫ�������ַ���
	 * @return key
	 */
	public static String toKey(String str) {
		
		int valueMarkIndex = str.indexOf("=");
		
		return str.substring(0, valueMarkIndex);
		
	}
	
	/**
	 * ���ַ����н�����value
	 * @param str Ҫ�������ַ���
	 * @return value
	 */
	public static String toValue(String str) {
		
		int valueMarkIndex = str.indexOf("=");
		
		return str.substring(valueMarkIndex+1);
		
	}
	
}
