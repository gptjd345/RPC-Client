//package com.example.toyProject;
//
//import static org.junit.Assert.fail;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//import org.junit.Test;
//
//
//
//public class JDBCText {
//	static {
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testConnection() {
//		
//		try(Connection con = 
//				DriverManager.getConnection(
//                // Oracle19 버전인 경우 => "jdbc:oracle:thin:@localhost:1521:orcl"
//                // Oracle11 버전인 경우 => "jdbc:oracle:thin:@localhost:1521:XE"
//						"jdbc:oracle:thin:@192.168.0.142:1521/orclpdb",
//						"testuser3",
//						"testuser3")){
//			System.out.println(con);
//		} catch (Exception e) {
//			fail(e.getMessage());
//		}
//		
//	}
//
//}
