package com.ksptooi.generaldatacore.test.dataconnect;

import com.ksptooi.generaldatacore.dataInteface.DataConnection;
import com.ksptooi.generaldatacore.dataInteface.FileDataConnection;
import com.ksptooi.generaldatacore.entity.data.DataSet;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DataConnect_Collection {


    DataConnection connection = null;


    @Before
    public void Before() throws IOException {

        //���������ļ�
        File file = Paths.get("C://127.txt").toFile();

        if(!file.exists()){
            file.createNewFile();
        }

        //�����ݽӿ�
        connection = new FileDataConnection(file.toPath());
    }



    /**
     * �ļ����ݽӿڲ���(����)
     */
    @Test
    public void FileDataConnectionUnit() throws IOException {


        ArrayList<String> stringList = new ArrayList<String>();
        stringList.add("����1");
        stringList.add("����2");
        stringList.add("����3");
        stringList.add("����4");


        DataSet data = connection.getDataMap().setAutomatic(true);

        data.val("key8",stringList);

        System.out.println(data.val("key8"));


    }



}
