package uk.iksp.gdc.v7.Xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.ibatis.io.Resources;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XmlUtil{
	
	
	
	//����Xml�ļ�����ԴĿ¼
	private static void saveXml(Document doc){
		
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		
		try {
			
			Transformer transformer = transformerFactory.newTransformer();
			
			DOMSource domSource = new DOMSource(doc);		
			
		    transformer.setOutputProperty(javax.xml.transform.OutputKeys.DOCTYPE_PUBLIC, doc.getDoctype().getPublicId());    
		    transformer.setOutputProperty(javax.xml.transform.OutputKeys.DOCTYPE_SYSTEM, doc.getDoctype().getSystemId());  

		    File f=new File("GeneralDataCore");
		    f.mkdirs();
			
			StreamResult reStreamResult = new StreamResult("GeneralDataCore/GeneralDataCore-Config.xml");
			
			transformer.transform(domSource, reStreamResult);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	
	//�Ӱ���Դ��ȡXml
	private static Document readXmlasDocument(){
		
		try {
			
			DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			
			Document doc = newDocumentBuilder.parse(Resources.getResourceAsStream("GeneralDataCore-Config.xml"));
			
			return doc;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	//��ȡָ��Ԫ��
	private static NodeList getPropertyElement(Document doc){
		
		
		Element element = null;
		NodeList nodeList= null;
		
		//��ȡ���ڵ�
		element = doc.getDocumentElement();
		
		//��ȡ�ڵ�environments
		nodeList=element.getElementsByTagName("environments");
		element = (Element) nodeList.item(0);
		
		//��ȡ�ڵ�environment
		nodeList=element.getElementsByTagName("environment");
		element = (Element) nodeList.item(0);
		
		//��ȡ�ڵ�dataSource
		nodeList=element.getElementsByTagName("dataSource");
		element = (Element) nodeList.item(0);
		
		//��ȡ�ڵ�property
		nodeList=element.getElementsByTagName("property");
		
		return nodeList;

	}
	
	
	
	
	//����
	public static void setXmlParameter(String url,String userName,String passWord,String poolMaximumActiveConnections){
		
		Document doc = readXmlasDocument();
		
		NodeList nl = getPropertyElement(doc);
		
		nl.item(1).getAttributes().item(1).setNodeValue(url);
		
		nl.item(2).getAttributes().item(1).setNodeValue(userName);
		
		nl.item(3).getAttributes().item(1).setNodeValue(passWord);
		
		nl.item(4).getAttributes().item(1).setNodeValue(poolMaximumActiveConnections);
		
		saveXml(doc);
		
	}
	
	
	
	

}
