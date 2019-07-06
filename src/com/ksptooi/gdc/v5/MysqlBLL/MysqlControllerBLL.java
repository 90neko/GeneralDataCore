package com.ksptooi.gdc.v5.MysqlBLL;

import java.io.File;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.ksptooi.gdc.Main.DataCore;
import com.ksptooi.gdc.v5.Manager.IOController_V5;
import com.ksptooi.gdc.v5.MysqlDAL.MysqlDAO;

/**
 * @author KspTooi
 * @deprecated
 */
public class MysqlControllerBLL {

	
	IOController_V5 V5=null;
	MysqlDAO MD=null;
	
	
	public MysqlControllerBLL(){
		V5=new IOController_V5();
		MD=new MysqlDAO();
	}
	
	
	
	//���ļ��м���Mysql������Ϣ
	public void loadConfigFromgdFile_MysqlBLL(File File){
		
		DataCore.LogManager.logInfo("�������ļ���ȡ���ݿ���Ϣ");
		
		String MysqlAddress=null;
		String DataBaseName=null;
		String MysqlUser=null;
		String MysqlPwd=null;
		String Param=null;
		
		
		V5.setTarget(File);
		
		MysqlAddress=V5.getKeyValue("MysqlAddress");
		DataBaseName=V5.getKeyValue("DataBaseName");
		MysqlUser=V5.getKeyValue("MysqlUser");
		MysqlPwd=V5.getKeyValue("MysqlPwd");
		Param=V5.getKeyValue("Param");
		
		//�������Ƿ�ɹ�
		if(MysqlAddress == null){
			DataCore.LogManager.logError("�������ļ���ȡ���ݿ���Ϣʱ����! - δ�ҵ�Key:MysqlAddress");
			System.exit(0);
		}
		
		if(DataBaseName == null){
			DataCore.LogManager.logError("�������ļ���ȡ���ݿ���Ϣʱ����! - δ�ҵ�Key:DataBaseName");
			System.exit(0);
		}
		
		if(MysqlUser == null){
			DataCore.LogManager.logError("�������ļ���ȡ���ݿ���Ϣʱ����! - δ�ҵ�Key:MysqlUser");
			System.exit(0);
		}
		
		if(MysqlPwd == null){
			DataCore.LogManager.logError("�������ļ���ȡ���ݿ���Ϣʱ����! - δ�ҵ�Key:MysqlPwd");
			System.exit(0);
		}
		
		if(Param == null){
			DataCore.LogManager.logError("�������ļ���ȡ���ݿ���Ϣʱ����! - δ�ҵ�Key:Param");
			System.exit(0);
		}
		
		
		DataCore.mysql_URL="jdbc:mysql://"+MysqlAddress+"/"+DataBaseName+Param;
		DataCore.mysql_User=MysqlUser;
		DataCore.mysql_Pwd=MysqlPwd;
		
	}
	
	//ִ�в�ѯ���
	public ResultSet query_MysqlBLL(String sql_Query){
		
		MD.getMysqlConnect();
		
		return MD.query(sql_Query);
		
	}
	
	
	//ִ�зǲ�ѯ���
	public void noQuery_MysqlBLL(String sql_Update){
		
		MD.getMysqlConnect();
		
		MD.noQuery(sql_Update);
		
	}
	
	
	//���ĳ�ű��Ƿ����
	public boolean tableIsExists_MysqlBLL(String TableName){
		
		MD.getMysqlConnect();
		
		
		try {
			
			ResultSet rs=DataCore.mysql_Conn.getMetaData().getTables(null, null, TableName, null);
			
	         if (rs.next()) {  
	               return true;  
	         }else {  
	               return false;  
	         } 
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			DataCore.LogManager.logError("���ݿ����Ӵ���");
		}
		
		
		return false;
		
	}
	
	//��ȡ���ݿ�����
	public Connection getMysqlConnect_BLL(){
		
		MD.getMysqlConnect();
		
		return DataCore.mysql_Conn;
	}
		
	
}
