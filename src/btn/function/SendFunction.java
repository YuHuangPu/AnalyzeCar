package btn.function;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import GUI.Main;
import btn.listener.btnAction;
import btn.listener.Action.StarAction;
import datatool.Connect;
import datatool.Sql;
import Log.Log;
import PIC.getImage;
import Tool.SetCarName;
import Tool.searchLimit;

public class SendFunction extends Thread {
	private HashMap<String, Object> bag ;
	private	Double grade [], tag [] ,tagI [];
	private String name;
	private HashMap<String,Double []> G;
	private JPanel JP ;
	public SendFunction (HashMap<String, Object> bag){
		this.bag = bag;
		connect ();
		((JPanel)bag.get("plSubMain")).add(JP);
		((HashMap<String,JPanel>)bag.get("PageStatus")).put("PAGE1", JP);
	}
	private void setGrade(){
		this.grade = new Double[]{Double.valueOf(((JTextField)bag.get("EASY")).getText())
				,Double.valueOf(((JTextField)bag.get("SAFETY")).getText())
				,Double.valueOf(((JTextField)bag.get("COZY")).getText())
				,Double.valueOf(((JTextField)bag.get("CONTROLLED")).getText())
				,Double.valueOf(((JTextField)bag.get("MONEY")).getText())};
		tag = new Double[]{1.0,0.0};
		tagI = new Double[]{1.0,0.0};
		name = "";
		JP = new JPanel();
		bag.put("carphoto", new HashMap<String, byte[]>());
	}
	public void run(){
		addStar();
	}
	private void connect (){
		c = new Connect ();
		c.Connect("MYSQL");
		CMD = new Sql(c.getConnect());
		try{
			searchID();
			point ();
		}catch(Exception e){
			Log.show(e.toString());
		}finally{
			c.close();
			CMD.sqlClose();
			c = null;
			CMD = null;
		}
	}
	private void searchID() throws Exception{
		String ID =null;
		switch (((JTextField)bag.get("TextID")).getText().toUpperCase()){
			case "MAN":
				((JTextField)bag.get("TextID")).setText("GUEST.MAN");
				ID = "MAN";
			break;
			case "WOMAN":
				((JTextField)bag.get("TextID")).setText("GUEST.WOMAN");
				ID = "WOMAN";
			break;
			case "WELCOME" :
				((JTextField)bag.get("TextID")).setText("GUEST");
				ID = "GUEST";
			break;
			case "THANKS" :
				((JTextField)bag.get("TextID")).setText("GUEST");
				ID = "GUEST";
			break;
			default :
				ID = ((JTextField)bag.get("TextID")).getText().toUpperCase();
				((JTextField)bag.get("TextID")).setText(ID);
		}
		CMD.setSQL("select count(*) from carlike where id = '"+ID+"'");
		CMD.getResultSet().next();
		if((Main.bag_ = CMD.getResultSet().getInt(1))>0){
			((JButton)bag.get("btnOpencar")).setIcon(new ImageIcon(new getImage("gift2.png").getImageF()));
		}else{
			((JButton)bag.get("btnOpencar")).setIcon(new ImageIcon(new getImage("gift.png").getImageF()));
		}
	}
	private Connect c ;
	private Sql CMD ;
	private void point () throws Exception{
		setGrade();
		JP.setLayout(null);
		JP.setOpaque(false);
		JP.setBounds(0, 0, 710, 400);
		JP.removeAll();
		((JPanel)bag.get("plAll")).repaint();
		G =new  HashMap<String,Double []>();
		
		String sql = searchLimit.getSQL("s");
		
			CMD.setSQL(sql);
			while(CMD.getResultSet().next()){
				G.put(CMD.getResultSet().getString(1), new Double []{
						CMD.getResultSet().getDouble(2),
						CMD.getResultSet().getDouble(3),
						CMD.getResultSet().getDouble(4),
						CMD.getResultSet().getDouble(5),
						CMD.getResultSet().getDouble(6)});
				name = name + CMD.getResultSet().getString(1) + "\n" ;
				double x =(CMD.getResultSet().getDouble(3)+
						//CMD.getResultSet().getDouble(3)+
						CMD.getResultSet().getDouble(4)+
						//CMD.getResultSet().getDouble(5)+
						CMD.getResultSet().getDouble(5))
						/3;
				double y =(//CMD.getResultSet().getDouble(2)*0.178+
						CMD.getResultSet().getDouble(2)+
						//CMD.getResultSet().getDouble(4)*0.205+
						CMD.getResultSet().getDouble(6)
						//+CMD.getResultSet().getDouble(6)*0.190
						)/2;
				tag[1] = Math.max(x, tag[1]);
				tag[0] = Math.min(x, tag[0]);
				tagI[1] = Math.max(y, tagI[1]);
				tagI[0] = Math.min(y, tagI[0]);
			}
		hh = this.name.split("\n").length<=30 ? 20:18 ;
		start();
	}
	private int hh ;
	
	private void addStar(){
		SetCarName SetCarName = new SetCarName(bag,name);
		SetCarName.start();
		int H = (400-hh), W = (710-hh) ;
		int i = 0;
		String starphoto  = (new String[]{"„½","„É"})[(int)(Math.random()*2)];
		try {
			SetCarName.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(final String name : this.name.split("\n")){
			final Double grade [] = G.get(name);
			double x = (grade[1]/*+grade[1]*/+grade[2]/*+grade[3]*/+grade[3])/3;
			double y = (grade[0]+grade[4])/2;
			
			int w = (int)(((W)*(x-tag[0]))/(tag[1]-tag[0])),
					h = (int)(((H)*(y-tagI[0]))/(tagI[1]-tagI[0]));
			final int [] set = {
					w, 
					-1*(h-380)};
			
			Log.show("Query("+(++i)+")"+name+"("+String.valueOf(x*100).substring(0, 4)+", "+String.valueOf(y*100).substring(0, 4)+")"+"--->("+w+", "+h+")");
			//System.out.println(name +" : "+ grade[0] + "\t" +grade[1]+ "\t"+grade[2]+ "\t"+grade[3]+ "\t"+grade[4]);
			JLabel Star = new JLabel(starphoto);
			Star.addMouseListener(new btnAction(bag,name,grade,"Star"));
			Star.setFont(new Font("Wingdings 2", Font.PLAIN, this.name.split("\n").length<=30 ? 20:18));
			Star.setForeground(new Color(245,245,245));
			Star.setBounds(set[0],set[1],hh, hh);
			JP.add(Star);
			((JPanel)bag.get("plAll")).repaint();
			//start();
			try {
				sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		Main.star_ = true;
		((JButton)bag.get("btnSend")).setEnabled(false);
	}
	
}
