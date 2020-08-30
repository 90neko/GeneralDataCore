package com.ksptooi.minunit.parser;

import com.ksptooi.generaldatacore.parser.KVParser;
import org.junit.Test;

import java.util.ArrayList;

public class ParserTest_List {

    @Test
    public void listToString(){

        ArrayList<String> list = new ArrayList<String>();
        list.add("����1");
        list.add("����2");
        list.add("����3");
        list.add("����4");

        //listת��Ϊ�ַ���
        String convert = KVParser.listToString(list);

        System.out.println("ת������ַ���Ϊ:"+convert);

        //�ַ���תΪlist
        ArrayList<String> convert1 = KVParser.stringToList(convert);
        System.out.println("ת�����listΪ:"+convert1.toString());

    }




}
