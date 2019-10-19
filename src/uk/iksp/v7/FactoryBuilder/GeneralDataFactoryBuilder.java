package uk.iksp.v7.FactoryBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import uk.iksp.v7.Factory.DataSessionFactory;
import uk.iksp.v7.Factory.StreamFactory;
import uk.iksp.v7.Session.DataSession;
import uk.iksp.v7.Xml.XmlUtil;
import uk.isp.v7.main.DataCore;

public class GeneralDataFactoryBuilder{

	
	
	SqlSessionFactoryBuilder ssfb=null;
	
	
	public GeneralDataFactoryBuilder(){
		ssfb = new SqlSessionFactoryBuilder();
	}
	
	
	
	//����DataSessionFactory
	public DataSessionFactory buildDataFactory(int SessionPoolSize) {
		
		return new DataSessionFactory(SessionPoolSize);	
		
	}
	
	
	//����StreamFactory
	public StreamFactory buildStreamFactory(){
		return new StreamFactory();
	}
	
	
	//����SqlSessionFactory - ���ļ�����
	public SqlSessionFactory buildSqlSessionFactory(File file){
		
		
		DataSessionFactory df = this.buildDataFactory(4);
		
		DataSession os = df.openSession(file);
		
		//��ȡ����
		String url = os.get("url");
		String userName = os.get("userName");
		String passWord = os.get("passWord");
		String poolMaximumActiveConnections = os.get("poolMaximumActiveConnections");
		
		XmlUtil.setXmlParameter(url, userName, passWord, poolMaximumActiveConnections);
		
		os.release();
		
		
		try {
			
			
			InputStreamReader isr=new InputStreamReader(new FileInputStream(new File("GeneralDataCore/GeneralDataCore-Config.xml")));
				
			SqlSessionFactory ssf = ssfb.build(isr);
			
			return ssf;
			
			
		} catch (FileNotFoundException e) {
			DataCore.LogManager.logError("����SqlSessionʱ����! - �ļ�ϵͳ����!");
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	
}
