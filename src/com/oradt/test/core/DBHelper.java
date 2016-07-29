package com.oradt.test.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHelper {
    public static final String url = "jdbc:mysql://" + GlobalSettings.DBAddr + "/" + GlobalSettings.DBTable;  
    public static final String name = GlobalSettings.DBDriver;  
    public static final String user = GlobalSettings.DBuser;  
    public static final String password = GlobalSettings.DBpassword;  
  
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
  
    public void test(){
        String sql = null;  
        DBHelper db1 = null;  
        ResultSet ret = null;  
        sql = "SELECT * FROM oradt_cloud_test2.sms_message where mobile='8612011111111' order by created_time desc limit 1";//SQL语句  
        db1 = new DBHelper(sql);//创建DBHelper对象  
  
        try {  
            ret = db1.pst.executeQuery();//执行语句，得到结果集  
            while (ret.next()) {  
                String uid = ret.getString(1);   
                System.out.println(uid + "\t");  
            }//显示数据  
            ret.close();  
            db1.close();//关闭连接  
        } catch (SQLException e) {  
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
