package btn.function;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import Log.Log;
import btn.listener.Action.FinalAction;
import datatool.Connect;
import datatool.Sql;

public class HeartFunction extends Thread {
	private HashMap<String, Object> bag;
	private String action;

	public HeartFunction(HashMap<String, Object> bag, String action) {
		this.bag = bag;
		this.action = action;
	}

	public void run() {
		Function();
		if(!((JButton)bag.get("btnOpencar")).getName().equals("OPEN"))
			new FinalAction(bag,"OPEN").start();
	}

	private void Function() {
		Connect c = new Connect();
		c.Connect("Mysql");
		Sql CMD = new Sql(c.getConnect());
		try {
			if (action.equals("ADD")) {
				String sql = "INSERT into carlike(id,car) values ('" + ((JTextField) bag.get("TextID")).getText()
						+ "', '" + ((String) bag.get("Name")) + "')";
				Log.show(sql);
				CMD.setSQL(sql);

			} else if (action.equals("RM"))
				CMD.setSQL("delete from carlike where id = '" + ((JTextField) bag.get("TextID")).getText()
						+ "' and car = '" + ((String) bag.get("Name")) + "'");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			c.close();
			CMD.sqlClose();
			c = null;
			CMD = null;
		}
		
	}
}
