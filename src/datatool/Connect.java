package datatool;

import java.sql.*;

import Log.Log;

public class Connect {
	private boolean isConnect = false;
	private String name;
    private Connection conDB = null;
	public Connect (){}
	public boolean isConnect(){
		return isConnect;
	}
	public Connection getConnect(){
		return conDB;
	}
	public void close(){
		try{
			if(conDB!=null){conDB.close();isConnect=false;
			Log.show("Mysql Connect Close.");}
		}catch(Exception e){
		}
	}
	public void Connect (String Database){
		Connectport(Database);
	}
	private void Connectport(String Database){
		switch(Database.toUpperCase()){
			case "MYSQL":
				ConnectMysql();
				Log.show("Mysql Connect Open");
				break;
			default:
				System.out.println("Can't Find Database : "+Database);
				break;
		}
	}
	private boolean ConnectMysql(){
		 try{
		     Class.forName("com.mysql.jdbc.Driver");
		     //conDB = DriverManager.getConnection("jdbc:mysql://localhost:8888/mydataofcar","root","");
		     conDB = DriverManager.getConnection("jdbc:mysql://localhost:8888/mydataofcar","useryu","math1s@201");
		     isConnect = true ;
		     return true ;
		 }
		 catch(Exception e){
		     System.out.println("Connect Mysql Exception"+e);
		 }
		 return false ;
	}
}
