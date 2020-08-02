package com.ksptooi.generaldatacore.parser;

public class KVParser {
	
	
	
	
	/**
	 * ���ַ����н�����key
	 * @return
	 */
	public static String key(String s) {
		
		
		int flag = s.indexOf("=", 0);
		
		
		if(flag == -1) {		
			return null;
		}
		
		return s.substring(0, flag);
	}
	
	/**
	 * ���ַ����н�����value
	 * @param s
	 * @return
	 */
	public static String value(String s) {
		
		int flag = s.indexOf("=",0);
		
		String result = null;
		
		if(flag == -1) {
			return null;
		}
		
		result = s.substring(flag+1,s.length());
		
		if(result.equalsIgnoreCase("") || result.equalsIgnoreCase(" ")) {
			return null;
		}
				
				
		return s.substring(flag+1,s.length());
	}
	
	/**
	 * ����ָ��item��valueֵ
	 */
	public static String setValue(String item,String value) {
		
		String key = key(item);
		
		return key + "=" + value;
		
	}
	
	
	
	
	
	
	
	
	
	

}
