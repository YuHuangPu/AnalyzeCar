package Log;

import datatool.Connect;
import datatool.Sql;

public class loginsert extends Thread{
	private String ID ,  Values,  Action ;
	public loginsert (String ID , String Values, String Action){
		this.ID = ID;
		this.Values = Values;
		this.Action = Action;
	}
	public void run (){
		insert ();
	}
	private void insert (){
		Connect C = new Connect();
		C.Connect("MYSQL");
		Sql CMD = new Sql(C.getConnect());
		
		try {
			CMD.setSQL("insert into log(id, value, action) values ("
					+ "'"+ID+"',"
					+ "'"+Values+"',"
					+ "'"+Action+"'"
					+ ")");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			CMD.sqlClose();
			C.close();
			CMD = null;
			C = null;
		}
		
		
		
	}
}
