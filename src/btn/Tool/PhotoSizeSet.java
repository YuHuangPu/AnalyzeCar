package btn.Tool;

import java.awt.Image;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.ImageIcon;

import Log.Log;
import datatool.Connect;

public class PhotoSizeSet extends Thread{
	private String name ;
	//private ImageIcon II[] = new ImageIcon[10];
	private HashMap<Integer,byte[]> byMap = new HashMap<Integer,byte[]>();
	public PhotoSizeSet(String name){this.name = name ;}
	public PhotoSizeSet(){}
	public void run (){BtnUse ();}
	private void BtnUse (){
		//ImageIcon II = new ImageIcon("D:\\yu.huang\\Desktop\\JJ\\PIC\\2016 Audi A3 Sedan 30 TFSI\\1.jpg") ;
		Connect C = new Connect();
		C.Connect("mysql");
		try {
			PreparedStatement Pstmt = C.getConnect().prepareStatement("SELECT * FROM carfile where name =?");
	        Pstmt.setString(1, name);
	        Log.show("SELECT * FROM carfile where name = '".toUpperCase()+name+"'");
			ResultSet result = Pstmt.executeQuery();
	        result.next();
	        for(int i =0;i<10;i++){
	        	Blob blob = result.getBlob(i+2);
	        	//II[i] = new ImageIcon(blob.getBytes(1, (int)blob.length()));
	        	byMap.put(i, blob.getBytes(1, (int)blob.length()));
	        	Log.show("Car Photo get("+(i+1)+")");
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			C.close();
			C=null;
			System.gc();
		}
	}
	public ImageIcon getphoto(int i ,int w,int h){
		ImageIcon I = new ImageIcon(byMap.get(i));
		I.setImage(I.getImage().getScaledInstance(w,h,Image.SCALE_DEFAULT));
		return I;
	}
	public ImageIcon getphoto(byte [] photobyte ,int w,int h){
		ImageIcon I = new ImageIcon(photobyte);
		I.setImage(I.getImage().getScaledInstance(w,h,Image.SCALE_DEFAULT));
		return I;
	}
	public ImageIcon getphoto(byte [] photobyte){
		ImageIcon I = new ImageIcon(photobyte);
		return I;
	}
	public ImageIcon getphoto(int i ){
		//I.setImage(I.getImage().getScaledInstance(w,h,Image.SCALE_DEFAULT));
		return new ImageIcon(byMap.get(i));
	}
}
