package com.ksptooi.udc.parser;


/**
 * ���ݾɰ����ݸ�ʽ�Ľ�����
 */
public class Parser {
	
	
	/**
	 * ���ַ����н�����key
	 * @param str Ҫ�������ַ���
	 * @return key
	 */
	public static String toKey(String str) {
		
		
		int keyStart = 0;
		int keyEnd = 0;
		int markCount = 0;
		
		//����key��ʶ��λ��
		for(int i=0;i<str.length();i++) {
			
			
			if(str.charAt(i) == '"'){
				
				if(markCount<1) {
//					System.out.println("��ʼ:"+i);
					keyStart = i;
					markCount ++;
					continue;
				}
				
				if(markCount==1) {
//					System.out.println("����:"+i);
					keyEnd = i;
					markCount ++;
				}	
			}
				
		}
		
		return str.substring(keyStart+1, keyEnd);
		
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
