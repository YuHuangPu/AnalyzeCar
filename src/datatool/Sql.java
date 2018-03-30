package datatool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Log.Log;

public class Sql {
	private final Connection conDB; 
	public Sql(Connection conDB){
		this.conDB=conDB;
	}
	private Statement stat = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private String SQL = null;
    public void setSQL(String SQL) throws Exception{
    	this.SQL = SQL ;
    	Log.show("CMD Open");
    	trans();
    }
    public ResultSet getResultSet(){
		return rs;
    }
    private void trans() throws Exception{
    	switch(SQL.split(" ")[0].toUpperCase()){
    		case "CREATE":
    			Update();
    			break;
    		case "SELECT":
    			Query();
    			break;
    		case "UPDATE":
    			Update();
    			break;
    		case "INSERT":
    			Update();
    		break;
    		case "DELETE":
    			Update();
    		break;
    	}
    	Log.show(SQL.toUpperCase());
    }
	private void Update() throws SQLException {
		pst = conDB.prepareStatement(SQL);
        pst.executeUpdate();
	}
	private void Query() throws SQLException {
    		stat = conDB.createStatement();
    	    rs = stat.executeQuery(SQL);
	}
	public void sqlClose(){
		SQL = null;
		try {
			if(stat != null)
				stat.close();
			if(rs != null)
				rs.close();
			if(pst != null)
				pst.close();
		} catch (SQLException e) {
			System.out.println("SQL CLOSE Exception : "+e);
		}
		Log.show("CMD Close");
	}
}
