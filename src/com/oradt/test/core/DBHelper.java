package com.oradt.test.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHelper {
//    public static final String url = "jdbc:mysql://" + GlobalSettings.DBAddr + "/" + GlobalSettings.DBTable;  
//    public static final String name = GlobalSettings.DBDriver;  
//    public static final String user = GlobalSettings.DBuser;  
//    public static final String password = GlobalSettings.DBpassword;  
 
    public static final String url = "jdbc:mysql://192.168.30.145/oradt_cloud_test2";  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "oradt_test2";  
    public static final String password = "123456";  
    
    public Connection conn = null;  
    public PreparedStatement pst = null;  
  
    public DBHelper(String sql) {  
        try {  
            Class.forName(name);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接  
            pst = conn.prepareStatement(sql);//准备执行语句  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public void close() {  
        try { 
            this.conn.close();  
            this.pst.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
}
