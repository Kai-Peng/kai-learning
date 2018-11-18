package com.concurrence;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MyEnum {
    public enum MyEnumSingleton{
        connectionFactory;
        private PhaserTest connection;
        private MyEnumSingleton(){
            try{
                System.out.println("create MyEnum object");
//                String url="jdbc:sqlserver://localhost:1079;databaseName=y2";
//                String username = "sa";
//                String password = "";
//                String driveName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//                Class.forName(driveName);
//                connection = DriverManager.getConnection(url,username,password);
                connection = new PhaserTest();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        public PhaserTest getConnection(){
            return connection;
        }
    }

    public static PhaserTest getConnection(){
        return MyEnumSingleton.connectionFactory.getConnection();
    }
}
