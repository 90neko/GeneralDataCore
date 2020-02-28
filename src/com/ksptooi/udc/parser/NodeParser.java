package com.ksptooi.udc.parser;

import java.util.ArrayList;

import com.ksptooi.udc.entity.node.Node;

public class NodeParser {

	/**
	 * ���ַ�������ΪNode
	 * @param str �ַ���
	 * @return node
	 */
	public static Node toNode(String str) {
		
		int start = str.indexOf("=>{");	
		int end = str.lastIndexOf("}");
		
		String nodeContent = str.substring(start+3, end);
		String nodeName = str.substring(2,start);	
		
		Node node=new Node(nodeName, parseNodeContent(nodeContent));
		return node;
	}
	
	/**
	 * ���ַ�������ΪNodeContent
	 * @return ����NodeContent��List
	 */
	public static ArrayList<String> parseNodeContent(String str){
		
		String[] split = str.split(",");
		
		ArrayList<String> ret = new ArrayList<String>();
		
		for(String s:split) {
			ret.add(s);
		}
		
		return ret;
	}
	
	
	
	/**
	 * ���ַ����н�����NodeList
	 * @return ����Node�ļ���
	 */
	public static ArrayList<Node> parserNodeList(String str) {
		
//		System.out.println("�ܳ�:"+str.length());
		
		int nodeNameStart = 0;
		int nodeContentStart = 0;
		int nodeEnd = 0;
		int openedMark = 0;
		
		ArrayList<Node> nodeList = new ArrayList<Node>();
		
		
		//�ҽڵ����Ʊ�ʶ��
		for(int i=0;i<str.length();i++) {
			
			nodeNameStart=str.indexOf("--",i);
			
			
			//�ڵ����Ʊ�ʶ��
			if(nodeNameStart!=-1) {
				
//				System.out.println("�ڵ�Name��ʶ��:"+nodeNameStart);
					
				nodeContentStart = str.indexOf("{",nodeNameStart);
//				System.out.println("�ڵ�Content��ʶ��:"+nodeContentStart);
				openedMark++;
				
				//�ҽڵ������
				for(int u=nodeContentStart+1;u<=str.length();u++) {
					
					if(str.charAt(u)=='{') {
						openedMark++;
					}
					
					if(str.charAt(u)=='}') {
						openedMark--;
					}
					
					if(openedMark<1){
//						
						i=u;
						nodeEnd=u;
//						System.out.println(str.substring(nodeNameStart,nodeEnd+1));
//						System.out.println("������:"+u);
						nodeList.add(NodeParser.toNode(str.substring(nodeNameStart,nodeEnd+1)));
						break;
					}
					
				}
				
				
			}
			
			
			
		}
		
		
		
		return nodeList;
	}
	
	
	
	/**
	 * ���ַ����н�����NodeList
	 * @return ����Node�ļ���
	 */
//	public static ArrayList<Node> parserNodeList(String str) {
//		
//		System.out.println("�ܳ�:"+str.length());
//		
//		int nodeStart = 0;
//		int nodeEnd = 0;
//		
//		int subNodeStart = 0;
//		
//		int subNodeFlagCount = 0;
//		
//		//�ҽڵ����Ʊ�ʶ��
//		for(int i=0;i<str.length();i++) {
//			
//			nodeStart=str.indexOf("--",i);
//			
//			
//			//�ڵ����Ʊ�ʶ��
//			if(nodeStart!=-1) {
//				
//				System.out.println("�ڵ�����:"+nodeStart);
//				
//				i = nodeStart;
//				
//				//�ҽڵ������
//				for(int u=nodeStart;u<=str.length();) {
//					
//					subNodeStart = str.indexOf("--",u+2);
//						
//					//�жϼ�¼�ӽڵ�
//					if(subNodeStart!=-1) {
//						
//						System.out.println("�ӽڵ�λ��:"+subNodeStart);
//						u = subNodeStart+2;
//						subNodeFlagCount++;
//						
//					}
//					
//					nodeEnd = str.indexOf("}",u);
//					
//					//�жϼ�¼������
//					if(nodeEnd!=-1) {
//						
//						if(subNodeFlagCount>0) {
//							u=nodeEnd+1;
//							subNodeFlagCount--;
//							continue;
//						}
//						i =nodeEnd+1;
//						System.out.println("�ڵ������:"+nodeEnd);
//						break;
//					}
//					
//				}
//				
//				
//			}
//			
//			
//			
//		}
//		
//		
//		
//		
//		return null;
//	}
	
	
	public static void main(String[] args) {
		
//		Node node = toNode("GFDGFG");
//		
//		System.out.println(node.getName());
//		
//		for(String s:node.getContent()) {
//			System.out.println(s);
//		}
		
		ArrayList<Node> parserNodeList = parserNodeList("key1=5\r\n" + 
				"key2=10\r\n" + 
				"\r\n" + 
				"--node=>{\r\n" + 
				"\r\n" + 
				"--node1_1=>{--node1_1=>{}},\r\n" + 
				"--node1_2=>{},\r\n" + 
				"\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"--node2=>{\r\n" + 
				"\r\n" + 
				"--node2_1=>{},\r\n" + 
				"--node2_2=>{},\r\n" + 
				"\r\n" + 
				"}");
		
		
		
		System.out.println(parserNodeList.get(0).getName());
		System.out.println(parserNodeList.get(0).getContent().get(2));
		
	}
	
}
