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
	 * �ҵ���һ���ڵ��
	 * @return ����һ�������ڵ��λ����Ϣ������
	 */
	public static int[] findNextNode(String str) {
		
		
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
							
				nodeContentStart = str.indexOf("{",nodeNameStart);
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
						nodeList.add(NodeParser.toNode(str.substring(nodeNameStart,nodeEnd+1)));
						break;
					}
					
				}
				
				break;
				
			}
			
		}
		
		int[] out = {nodeNameStart,nodeEnd};
		
		return out;
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
	 * ���˰����ڵ���ı�
	 */
	public static ArrayList<String> clearNodeData(ArrayList<String> input) {
		
		boolean hasNode = false;
		
		for(int i=0;i<input.size();i++) {
			
			if(input.get(i).contains("--")) {
				hasNode = true;
			}
			
			if(hasNode) {
				input.remove(i);
				i--;
			}
			
		}
		
		
		return input;
	}
	
	/**
	 * ���˰����ڵ����ı�[�����ɽڵ�λ�ñ�ʶ��]
	 */
	public static ArrayList<String> clearNodeBlock(String str) {
		

		String nodeBlock = null;
		
		int[] nodeLocation = null;
		
		while(true) {
			
			nodeLocation = NodeParser.findNextNode(str);
			
			//�ж����޽ڵ��Start��ʶ��
			if(nodeLocation[0] != -1) {
				
				//��ȡ�ڵ��ı�
				nodeBlock = str.substring(nodeLocation[0],nodeLocation[1]+1);
				
				//�滻�м��ı�
				str = str.replace(nodeBlock, "{{"+toNode(nodeBlock).getName()+"}}");
				
				continue;
			}
			
			break;
			
		}
		
		//����Ԫ����=>���ϴ��
		String[] split = str.split(",");
		
		ArrayList<String> op = new ArrayList<String>();
		
		for(String s:split) {
			op.add(s);
		}
		
		return op;
	}
	
	
	
	
	public static void main(String[] args) {
		
		
		String str = "\r\n" + 
				"key1=5\r\n" + 
				"key2=10\r\n" + 
				"\r\n" + 
				"--node1=>{\r\n" + 
				"\r\n" + 
				"	\"n1k1\"=25,\r\n" + 
				"	\"n1k2\"=35,\r\n" + 
				"	\r\n" + 
				"	--node1_1=>{\r\n" + 
				"		\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"--node2=>{\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"key3=15";
		
		
		ArrayList<String> clearNodeBlock = clearNodeBlock(str);
		
		for(String s:clearNodeBlock) {
			System.out.println(s);
		}
		
		
	}
	
}
