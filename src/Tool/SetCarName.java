package Tool;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;

import datatool.Connect;
import datatool.Sql;

public class SetCarName extends Thread{
	private HashMap<String, Object> bag;
	private String Name ;
	public SetCarName(HashMap<String, Object> bag, String name) {
		this.bag = bag;
		this.Name = name;
	}
	public void run(){
		Connect C = new Connect();
		C.Connect("mysql");
		Sql s = new Sql(C.getConnect());
		String sql = "";
		String NAMES = "";
		for(String name : Name.split("\n")){
			NAMES = NAMES + "'"+name+"', ";
		}
		sql = "select concat(年分,' ' , 品牌,' ' , 款式),年分, 品牌, 款式 from allcar where concat(年分,' ' , 品牌,' ' , 款式) "
				+ "in ("+NAMES.substring(0,NAMES.length()-2)+")";
		HashMap <String , String [] > CarName_New = (HashMap <String , String [] >)bag.get("CarName_New");
				
		
		try {
			s.setSQL(sql);
			while(s.getResultSet().next()){
				CarName_New.put(s.getResultSet().getString(1), new String [] {
						s.getResultSet().getString(2),
						s.getResultSet().getString(3),
						s.getResultSet().getString(4)});
			}
			bag.put("CarName_New",CarName_New);
			getphoto(sql,NAMES,s );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			C.close();
			s.sqlClose();
			C = null;
			s = null;
		}
	}
	private void getphoto(String sql,String NAMES,Sql s) throws Exception{
		sql = "select name,pic0 from carfile where name "
				+ "in ("+NAMES.substring(0,NAMES.length()-2)+")";
		
		s.setSQL(sql);
		while(s.getResultSet().next()){
			Blob blob = s.getResultSet().getBlob(2);
			((HashMap<String, byte[]>)bag.get("carphoto"))
			.put(s.getResultSet().getString(1), (blob.getBytes(1, (int)blob.length())));
		}
	}
}
