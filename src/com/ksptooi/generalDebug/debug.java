package com.ksptooi.generalDebug;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ishiyamasayuri.Entity.GDCEntity;
import com.ksptooi.gdc.FileDAL.GeneralFileIO;
import com.ksptooi.gdc.MysqlAPI.MysqlController;

public class debug {

	public static void main(String[] args){
		
		
//		FileDAL_Input FDI=new FileDAL_Input();
//		FileDAL_OutPut FDO=new FileDAL_OutPut();
//		FileControllerBLL FCB=new FileControllerBLL();
//		
//		IOController_V5 V5=new IOController_V5();
//		File F=new File("F:/123.gd");
//		
//		V5.createNewGdcFile(F);
//		
//		V5.setTarget(F);
//		
//		V5.addLine("key1=5");
//		
//		System.out.println(V5.getKeyValue("@LineType"));
		
		
		MysqlController MC=new MysqlController();
		
		File file = new File("C:/123.gd");
		
		GeneralFileIO GF = new GeneralFileIO();
		
		GDCEntity GE = GF.getGDCEntity(file);
		
		while(GE.next()){
			
			System.out.println(GE.get());
			
		}
		
		
		GE.addLast("������");
		
		GF.writeFile(file, GE);
		
		
//		System.out.println(MC.tableIsExists("playertable"));
//		
//		ResultSet rs=MC.query("select * from playertable");
//		
//		
//		try {
//			
//			
//			while(rs.next()){
//				System.out.println(rs.getString("playername"));
//				System.out.println("��Ϊ��");
//				return;
//			}
//			
//			System.out.println("Ϊ��");
//			System.out.println("123");
//			
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		
		
	}

}