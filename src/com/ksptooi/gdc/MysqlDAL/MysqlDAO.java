package com.ksptooi.gdc.MysqlDAL;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ksptooi.gdc.Util.Var;

public class MysqlDAO {


	public void getMysqlConnect(){
		
		if(Var.mysql_Conn != null){
			return ;
		}
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			Var.LogManager.writeLogOfError("δ���ҵ�Mysql����");
		}
		
		try {
			
			Var.LogManager.writeLogOfINFO("���ӵ����ݿ�");
			Var.LogManager.writeLogOfINFO("URL:"+Var.mysql_URL);
			
			Var.mysql_Conn=DriverManager.getConnection(Var.mysql_URL,Var.mysql_User,Var.mysql_Pwd);
			
			Var.LogManager.writeLogOfINFO("���ݿ����ӳɹ�");
			
		} catch (SQLException e) {
			e.printStackTrace();
			Var.LogManager.writeLogOfError("����Mysql���ݿ�����ʱ���ִ���!");
		}
		
	}
	
	
	
	public ResultSet query(String sql_Query){
		
		
		Statement stm=null;	
		ResultSet rs=null;
		
		try {
			
			
			stm=Var.mysql_Conn.createStatement();
			rs=stm.executeQuery(sql_Query);
			
			return rs;
			
		} catch (SQLException e) {
			e.printStackTrace();
			Var.LogManager.writeLogOfError("Mysql���ݿ����! - createStatement - query");
		}
		
		return null;
		
	}
	
	
	public void noQuery(String sql_Update){
		
		
		Statement stm=null;	
		
		try {
			
			
			stm=Var.mysql_Conn.createStatement();
			stm.executeUpdate(sql_Update);
			
			stm.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			Var.LogManager.writeLogOfError("Mysql���ݿ����! - createStatement - query");
		}
		
		
	}
	
	
}
