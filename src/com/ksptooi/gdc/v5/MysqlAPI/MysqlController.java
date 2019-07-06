package com.ksptooi.gdc.v5.MysqlAPI;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;

import com.ksptooi.gdc.v5.MysqlBLL.MysqlControllerBLL;

/**
 * @author KspTooi
 * @deprecated
 */
public class MysqlController {

	
	private String mysql_URL=null;
	
	private String mysql_User=null;
	
	private String mysql_Pwd=null;
	
	private MysqlControllerBLL MCB=null;
	
	public MysqlController(){
		
		mysql_URL=null;
		mysql_User=null;
		mysql_Pwd=null;
		MCB=new MysqlControllerBLL();
	}
	
	@Deprecated
	public String getMysql_URL() {
		return mysql_URL;
	}
	@Deprecated
	public void setMysql_URL(String mysql_URL) {
		this.mysql_URL = mysql_URL;
	}
	@Deprecated
	public String getMysql_User() {
		return mysql_User;
	}
	@Deprecated
	public void setMysql_User(String mysql_User) {
		this.mysql_User = mysql_User;
	}
	@Deprecated
	public String getMysql_Pwd() {
		return mysql_Pwd;
	}
	@Deprecated
	public void setMysql_Pwd(String mysql_Pwd) {
		this.mysql_Pwd = mysql_Pwd;
	}
	
	
	@Deprecated
	//���ļ��м���Mysql������Ϣ
	public void loadConfigFromgdFile(File File){
		MCB.loadConfigFromgdFile_MysqlBLL(File);
	}
	
	@Deprecated
	//ִ�в�ѯ���
	public ResultSet query(String sql_Query){
		return MCB.query_MysqlBLL(sql_Query);
	}
	@Deprecated
	//ִ�зǲ�ѯ���
	public void noQuery(String sql_Update){
		MCB.noQuery_MysqlBLL(sql_Update);
	}
	@Deprecated
	//���ĳ�ű��Ƿ����
	public boolean tableIsExists(String TableName){
		return MCB.tableIsExists_MysqlBLL(TableName);
	}
	@Deprecated
	//��ȡ���ݿ�����
	public Connection getMysqlConnect(){
		return MCB.getMysqlConnect_BLL();
	}
	
	
}
