package com.ksptooi.gdc.v6.Factory;

import java.util.LinkedList;
import com.ksptooi.gdc.v6.Session.SqlSession;

public class SqlSessionFactory{

	
	private String address="*";
	
	private String dbName="*";
	
	private String user="*";
	
	private String password="*";
	
	private String param="*";
	
	private int poolInitSize=16;
	
	private LinkedList<SqlSession> listConnections = new LinkedList<SqlSession>();
	
	
	//SSF���췽��
	public SqlSessionFactory(String address,String dbName,String user,String pwd,String param,int initSize) {
		
		//��ʼ������
		this.address=address;
		this.dbName=dbName;
		this.user=user;
		this.password=pwd;
		this.param=param;	
		this.poolInitSize=initSize;
		
		//����SQL����
		for(int i=0;i<poolInitSize;i++) {
			
//			System.out.println("jdbc:mysql://"+address+"/"+dbName+param);
				
			SqlSession sqlSession = new SqlSession(this,"jdbc:mysql://"+address+"/"+dbName+param,user,password);
				
//			System.out.println("��ȡ��SqlSession:"+sqlSession);
				
			listConnections.add(sqlSession);
				
		}		
		
	}
	
    public synchronized SqlSession getSqlSession(){
    	
    	
    	while(true) {
    		 		
            //������ݿ����ӳ��е����Ӷ���ĸ�������0
            if (listConnections.size() > 0) {
            	
                //�Ӽ����л�ȡһ�����ݿ�����
                SqlSession sqlSession = listConnections.removeFirst();
                
//              System.out.println("��ǰ���ݿ����ӳش�С��" + listConnections.size());
                 
                //���������Ч��
                if(sqlSession.isClosed()) {
//                	System.out.println("�������SqlSession��ʧЧ");
                	continue;
                }	
                  
                //����
                sqlSession.assign(this);
                               
                return sqlSession;
                
            }
            
            
            //������ݿ����ӳ��е����Ӷ�����ڻ�С��0
            throw new RuntimeException("��ǰ���ݿ���æ!.");		
    		
    	}
    	
        
    }

    
	
	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public int getPoolInitSize() {
		return poolInitSize;
	}

	public void setPoolInitSize(int poolInitSize) {
		this.poolInitSize = poolInitSize;
	}

	public LinkedList<SqlSession> getListConnections() {
		return listConnections;
	}

	public void setListConnections(LinkedList<SqlSession> listConnections) {
		this.listConnections = listConnections;
	}
	
	
}
