package com.huiwan.gdata.a;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlIteTest {

	public SqlIteTest() {
		// TODO Auto-generated constructor stub
	}

	
	private static final String Class_Name = "org.sqlite.JDBC";
//    private static final String DB_URL = "jdbc:sqlite:C:/Users/rui/Documents/Tencent Files/1067165280/FileRecv/sqlited/data.bytes";

    private static final String DB_URL = "jdbc:sqlite:C:/Users/rui/Documents/Tencent Files/1067165280/FileRecv/sqlited/tdb.db";

//    data source=test.db;password=test;
    public static void main(String args[]) {
        // load the sqlite-JDBC driver using the current class loader
        Connection connection = null;
        try {
            connection = createConnection();
           
            func1(connection);
            System.out.println("Success!");
        }  catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
    }
    
    // 创建Sqlite数据库连接
    public static Connection createConnection() throws SQLException, ClassNotFoundException {
        Class.forName(Class_Name);
    
        return DriverManager.getConnection(DB_URL);
        
    }

    public static void func1(Connection connection) throws SQLException {
//        Statement statement = connection.createStatement(0);
//        Statement statement1 = connection.createStatement();
        PreparedStatement p=  connection.prepareStatement("SELECT act_id,act_type FROM activity");
        
        
        
//        statement.setQueryTimeout(30); // set timeout to 30 sec.
        // 执行查询语句
        ResultSet rs = p.executeQuery();
        while (rs.next()) {
            String col1 = rs.getString("act_id");
            String col2 = rs.getString("act_type");
            System.out.println("col1 = " + col1 + "  col2 = " + col2);
            
//            System.out.println(location);
            // 执行插入语句操作
//            statement1.executeUpdate("insert into table_name2(col2) values('1')");
            // 执行更新语句
//            statement1.executeUpdate("update table_name2 set 字段名1=" +  col1 + " where 字段名2='" +  col2 + "'");
        }
        
}
    }
