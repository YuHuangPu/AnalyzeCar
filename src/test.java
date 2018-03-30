import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;

import datatool.Connect;  
                                                                                 
public class test {  
    public static void main(String[] args) {  
    	Connect C = new Connect();
		C.Connect("mysql");
		PreparedStatement Pstmt;
		try {
			Pstmt = C.getConnect().prepareStatement("SELECT distinct «~µP FROM allcar");
			ResultSet result = Pstmt.executeQuery();
			//http://c.8891.com.tw/assets/img/carInfo/brandLogo/AstonMartin.png
			String Type = "";
			while(result.next()){
				System.out.println(result.getString(1));
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		C.close();}  
    }  
}  
