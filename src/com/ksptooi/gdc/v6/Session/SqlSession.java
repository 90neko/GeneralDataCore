package com.ksptooi.gdc.v6.Session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import com.ksptooi.gdc.Main.DataCore;
import com.ksptooi.gdc.v6.Factory.SqlSessionFactory;

public class SqlSession {

	private SqlSessionFactory fromFactory = null;
	
	private boolean isRelease=true;

	private Connection conn=null;
	
	private Statement stm=null;
	
	private ResultSet rs=null;
	
	private PreparedStatement pStm=null;
	
	
	//SqlSessin ���췽��
	public SqlSession(SqlSessionFactory SSF,String url,String user,String pwd) {
		
		//����SSF
		this.fromFactory = SSF;
		
		
		//�ж�SSF�Ƿ�Ϊ��
		if(fromFactory == null) {
			throw new RuntimeException("SqlSessionFactory is empty");
		}
		
		//�ж�SSF�Ƿ�Ϊreally
		if(!(fromFactory instanceof SqlSessionFactory)) {
			throw new RuntimeException("SqlSessionFactory is not Really");
		}
		
		
		//�� ��������
		try {
			
			conn = DriverManager.getConnection(url, user, pwd);			
			
		} catch (SQLException e) {
			e.printStackTrace();
			DataCore.LogManager.logError("���ݿ����:����ʧ��!");
			System.exit(0);
		}
		
	}
	
	
	//���в�ѯ
	public ResultSet query(String sql) {
		
		if(isRelease) {
			throw new RuntimeException("��SqlSession�ѱ��ͷ�!");
		}
		
		try {
			
			this.stm=conn.createStatement();
			
			this.rs=stm.executeQuery(sql);
			
			return this.rs;
						
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
	
	}
	
	//���зǲ�ѯ
	public int noQuery(String sql) {
		
		if(isRelease) {
			throw new RuntimeException("��SqlSession�ѱ��ͷ�!");
		}
		
		try {
			
			this.stm=conn.createStatement();
			
			return stm.executeUpdate(sql);
			
		
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		
	}
	
	
	//�ж�ĳ�ű��Ƿ����
	public boolean isExistsTable(String tableName){
		
		if(isRelease) {
			throw new RuntimeException("��SqlSession�ѱ��ͷ�!");
		}
		
		try {
			
			ResultSet rs=conn.getMetaData().getTables(null, null, tableName, null);
			
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
	
	
	
	//�жϵ�ǰ��SqlSession�Ƿ���Ȼ��Ч
	public boolean isClosed() {
		try {
			return conn.isClosed();
		} catch (SQLException e) {
			return true;
		}
	}
	
	//����
	public synchronized void assign(SqlSessionFactory ssf){
		
		if(fromFactory != ssf) {
			throw new RuntimeException("SqlSession����ʱ���ִ���!");
		}
		
		if(!this.isRelease) {
			throw new RuntimeException("��SqlSession�ѱ�����!");
		}
		
		this.isRelease=false;
	}
	
	
	//�����ͷŵ�ǰ���ӵ����ӳ�
	public synchronized void release() {
		
		if(isRelease) {
			throw new RuntimeException("��SqlSession�ѱ��ͷ�!");
		}
		
		
		//�ر�RS
		if(rs != null) {
			
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		//�ر�STM
		if(stm != null) {
			
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		//�ر�PSTM
		if(pStm != null) {
			
			try {
				pStm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		//��SqlSessionת����Factory
		fromFactory.getListConnections().add(this);
		
//        System.out.println(this + "������listConnections���ݿ����ӳ��ˣ���");
//        System.out.println("listConnections���ݿ����ӳش�СΪ" + fromFactory.getListConnections().size());
        
        this.isRelease=true;
		
	}
	
	
	//psmt��صĲ���
	public void psmt_Create(String str) {
		
		try {
			
			this.pStm = this.conn.prepareStatement(str);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public ResultSet psmt_Query() {
		
		if(this.pStm == null) {
			throw new RuntimeException("PreparaStatementû�д���!");
		}
		
		try {
			
			return this.pStm.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void psmt_NoQuery() {
		
		if(this.pStm == null) {
			throw new RuntimeException("PreparaStatementû�д���!");
		}
		
		try {
			
			this.pStm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void psmt_setString(int i,String str2) {
		
		if(this.pStm == null) {
			throw new RuntimeException("PreparaStatementû�д���!");
		}
		
		try {
			
			this.pStm.setString(i, str2);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void psmt_setInt(int i,int i1) {
		
		if(this.pStm == null) {
			throw new RuntimeException("PreparaStatementû�д���!");
		}
		
		try {
			
			this.pStm.setInt(i, i1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void psmt_setByte(int i,Byte b1) {
		
		if(this.pStm == null) {
			throw new RuntimeException("PreparaStatementû�д���!");
		}
		
		try {
			
			this.pStm.setByte(i, b1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void psmt_setBytes(int i,byte[] b1) {
		
		if(this.pStm == null) {
			throw new RuntimeException("PreparaStatementû�д���!");
		}
		
		try {
			
			this.pStm.setBytes(i, b1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void psmt_setBoolean(int i,boolean b1) {
		
		if(this.pStm == null) {
			throw new RuntimeException("PreparaStatementû�д���!");
		}
		
		try {
			
			this.pStm.setBoolean(i, b1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void psmt_setTime(int i,Time b1) {
		
		if(this.pStm == null) {
			throw new RuntimeException("PreparaStatementû�д���!");
		}
		
		try {
			
			this.pStm.setTime(i, b1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
