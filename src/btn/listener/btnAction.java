package btn.listener;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.*;

import GUI.Main;
import Log.Log;
import PIC.getImage;
import Tool.Slide;
import btn.Tool.PhotoSizeSet;
import btn.listener.Action.CleanAction;
import btn.listener.Action.FinalAction;
import btn.listener.Action.ForeAction;
import btn.listener.Action.HeartAction;
import btn.listener.Action.SendAction;
import btn.listener.Action.ShowPhotoAction;
import btn.listener.Action.StarAction;
import btn.listener.Action.To3Action;
import datatool.Connect;
import datatool.Sql;

public class btnAction implements ActionListener, MouseListener {
	private HashMap<String, Object> bag;
	private HashMap<String, byte[]> photo;
	private String name;
	private Double[] grade;
	private String btnName;
	private String page;
	public btnAction(HashMap<String, Object> bag) {
		this.bag = bag;
	}
	public btnAction(HashMap<String, Object> bag, String name, Double[] grade,String btnName) {
		this.photo = (HashMap<String, byte[]>)bag.get("carphoto");
		this.bag = bag;
		this.name =name;
		this.grade = grade;
		this.btnName = btnName; 
	}
	public btnAction(HashMap<String, Object> bag, String page) {
		this.bag = bag;
		this.page = page;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(arg0.getActionCommand()){
			case "btnFore":
				new ForeAction(bag,page).start();
				break;
			case "btnSend":
				if(((JButton)bag.get("btnOpencar")).getName().equals("CLOSE")){
					new FinalAction(bag,"START").start();}
				else if(((JButton)bag.get("btnOpencar")).getName().equals("OPEN"))
					new SendAction(bag).start();
				break;
			case "btnTo3":
				new To3Action(bag).start();
				break;
			case "btnClean":
				if(Main.star_)
				if(((JButton)bag.get("btnOpencar")).getName().equals("CLOSE")){
					new FinalAction(bag,"CLEAN").start();}
				else if(((JButton)bag.get("btnOpencar")).getName().equals("OPEN"))
					new CleanAction(bag).start();
				break;
			case "likecar":
				new FinalAction(bag,null).start();
				break;
			default : Log.show(arg0.getActionCommand() +" Click ");
			break;
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		switch(btnName){
		case "Star":
			if(arg0.getClickCount()==2 && Main.star_){
				Main.star_=false;
				Log.show(btnName +" Click = 2");
				new StarAction(bag,name,grade).start();
				arg0.getComponent().setForeground(Color.yellow);
			}
			break;
		case "Heart":
			if(arg0.getClickCount()==2){
			new HeartAction(bag).start();
			
			}
			break;
		case "ShowPhoto":
			if(arg0.getClickCount()==2){
				new ShowPhotoAction(bag).start();
			}
			break;
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		switch(btnName){
		case "Star":
			if(Main.star_){
				((JLabel)bag.get("lblBackground")).setOpaque(false);
				Connect c = new Connect();
				c.Connect("Mysql");
				Sql CMD = new Sql (c.getConnect());
				try {
					CMD.setSQL("select * from carlike where id = '"
							+ ((JTextField)bag.get("TextID")).getText()
							+ "' and car = '"
							+ name
							+ "'");
					if(CMD.getResultSet().next()){
						((JLabel)bag.get("lblHeart2")).setIcon(new ImageIcon(new getImage("full love.png").getImageF()));
						((JLabel)bag.get("lblHeart2")).setName("full");
					}else{
						((JLabel)bag.get("lblHeart2")).setIcon(new ImageIcon(new getImage("love.png").getImageF()));
						((JLabel)bag.get("lblHeart2")).setName("love");
					}
				} catch (Exception E) {
					// TODO Auto-generated catch block
					E.printStackTrace();
				} finally {
					c.close();
					CMD.sqlClose();
					c=null;
					CMD = null;
				}
				bag.put("Name", name);
				String carname_new [] = ((HashMap<String, String []>)bag.get("CarName_New")).get(name);
				String html = "<table border = 0><tr>"
						+ "<td align=center><font color='#FFCC22'>"+carname_new[0]+"</font></td>"
						+ "<td rowspan = 2 ><font size=6 ,color='#EEE8AA'>"+carname_new[2]+"</font></td></tr><tr>"
						+ "<td align=center><font color='#FFCC22'>"+carname_new[1]+"</font></td></tr>"
						+ "</table>";
				((JLabel)bag.get("lblCarName")).setText("<html>"+html+"</html>");
				((JLabel)bag.get("lblCarName")).setFont(new Font("µÿ±d¥Œ¥Œ≈ÈW5", Font.BOLD, 20));
				((JLabel)bag.get("lblBackground")).setIcon(new PhotoSizeSet().getphoto(photo.get(name)));
				((JPanel)bag.get("plAll")).repaint();
			}
			break;
		case "Heart":
			//
			break;
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
