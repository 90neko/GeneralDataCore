package com.ksptooi.v5.Log;



public abstract class LogManager{

	
	String Prefix="Prefix_example";

	
	//����һ����Ϣ
	public abstract void logInfo(String Message);
	
	//���;�����Ϣ
	public abstract void logWarning(String Message);
	
	//���ʹ�����Ϣ
	public abstract void logError(String Message);
	
	
	
	public String getPrefix() {
		return Prefix;
	}

	public void setPrefix(String prefix) {
		Prefix = prefix;
	}
	
	
}
