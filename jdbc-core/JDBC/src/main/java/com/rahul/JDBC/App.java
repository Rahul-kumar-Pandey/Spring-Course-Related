package com.rahul.JDBC;
import java.sql.*;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException
    {
    	String url="jdbc:postgresql://localhost:5432/MyDB1";
    	String uname="postgres";
    	String pass="0000";
    	String sql="select sname from student where sid=1";
    	String name="";
    			
    	Connection conn=DriverManager.getConnection(url,uname,pass);
    	Statement st=conn.createStatement();
    	ResultSet rs=st.executeQuery(sql);
    	
    	//rs.next();
    	if(rs.next()) {
    		name=rs.getString(1);
    	}
    	conn.close();
    	System.out.println("name is:"+name);
    }
}
