package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	
	protected static Connection connetion = null;
	
	public static void getConnection()
	{
		try 
		{
            System.out.println("> Start Program ...");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("> Driver Loaded successfully.");
            connetion = DriverManager.getConnection("jdbc:oracle:thin:@ localhost:1521:xe"," COMP214-1", "*******");
            System.out.println("Database connected successfully.");
		}                                  
		catch(Exception connect)
		{
			connect.printStackTrace();
		}
	}
	
	public static void disConnect()
	{
		try
		{
			connetion.close();
			System.out.println("Database disconnected successfully.");
		}
		catch(Exception disconnect)
		{
			disconnect.printStackTrace();
		}
	}
}