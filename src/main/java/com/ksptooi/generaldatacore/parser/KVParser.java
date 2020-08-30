package com.ksptooi.generaldatacore.parser;

import java.util.ArrayList;

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
	 * �滻��ǰ��valueΪ�µ�value
	 * item: key=xxx
	 * value: Ҫ�滻��value
	 */
	public static String setValue(String item,String value) {
		
		String key = key(item);
		
		return key + "=" + value;
	}

	/**
	 * ��ʽ��key��value
	 */
	public static String format(String key,String value){
		return key+"="+value;
	}

	/**
	 * ��listת��Ϊvalue
	 */
	public static String listToString(ArrayList<String> list){

		StringBuffer sb = new StringBuffer();

		for(int i=0;i<list.size();i++){

			sb.append(list.get(i));

			if((i+1)<list.size()){
				sb.append(",");
			}

		}

		return sb.toString();
	}

	/**
	 * ��valueת��Ϊlist
	 */
	public static ArrayList<String> stringToList(String value){

		ArrayList<String> list = new ArrayList<String>();

		String[] split = value.split(",");

		//ת������ַ���Ϊ0
		if(split.length<1){
			return null;
		}

		for(String s:split){
			list.add(s);
		}

		return list;
	}

}
