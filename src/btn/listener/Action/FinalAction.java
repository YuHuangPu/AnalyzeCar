package btn.listener.Action;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import GUI.Main;
import Log.Log;
import Log.loginsert;
import PIC.getImage;
import Tool.SetCarName;
import Tool.Slide;
import Tool.btnOpaqueClose;
import btn.Tool.PhotoSizeSet;
import btn.function.FinalFunction;
import datatool.Connect;

public class FinalAction extends Thread{
	private HashMap<String, Object> bag;
	private int count = 0;
	private String pass;
	public FinalAction(HashMap<String, Object> bag,String pass) {
		 this.bag = bag;
		 this.pass = pass;
	}
	public void run (){
		Action();
		if(C!=null)
		C.close();
		try {
			if(Pstmt!=null)
				Pstmt.close();
			if(result!=null)
				result.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private Connect C = null;
	private PreparedStatement Pstmt =null;
	private	ResultSet result = null;
	private void Connect(){
		//select l.id, l.car, f.pic0 from carlike l, carfile f where f.name = l.car
		C = new Connect();
		C.Connect("MYSQL");
		String sql = "select l.id,g.*, f.pic0 from carlike l, carfile f,cargrade g where "
				+ "f.name = l.car and f.name = g.name "
				+ "and l.id ='"
				+ ((JTextField) bag.get("TextID")).getText()
				+ "'"
				+ "order by l.up desc limit 10";
		try {
			Pstmt = C.getConnect().prepareStatement(sql);
			result = Pstmt.executeQuery();
			HashMap<String,byte[]> bsphoto = new HashMap<String,byte[]>();
	        while(result.next()){
	        	byte [] bs= result.getBlob(8).getBytes(1, (int)result.getBlob(8).length());
	        	//II[i] = new ImageIcon(blob.getBytes(1, (int)blob.length()));
	        	
	        	paset(result.getString(2), bs, new Double [] {
	        			result.getDouble(3+0),
	        			result.getDouble(3+1),
	        			result.getDouble(3+2),
	        			result.getDouble(3+3),
	        			result.getDouble(3+4)
	        	});
	        	bsphoto.put(result.getString(2), bs);
	        	Log.show("Like Car photo get("+(++count)+")");
	        	}
	        bag.put("likephoto", bsphoto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			Log.show(sql);
			
		}
		///////////
	}
	private JPanel pl;
	private void scroll(){
		
		((JPanel)bag.get("plMeunValues")).addMouseWheelListener(new MouseWheelListener(){

			@Override
			public void mouseWheelMoved(MouseWheelEvent arg0) {
				int y = pl.getY() + arg0.getWheelRotation()*-10;
				if(y>=0){
					if(y!=0)
						((JPanel)bag.get("plAll")).repaint();
					pl.setLocation(0, 0);}
				if (y<0&& y> 327 - count*85){
				pl.setLocation(0, y);
				}
				((JPanel)bag.get("plAll")).repaint();
			}});
		pl.setOpaque(false);
		pl.setBounds(0, 0, 148, count*85);
		((JPanel)bag.get("plMeunValues")).add(pl);
		pl.setLayout(null);
		bag.put("plPhotoCheck", pl);
	}
	protected void Action(){
		if("CLEAN".equals(pass)){
			C = new Connect();
			C.Connect("MYSQL");
			String sql = "delete from carlike where id ='"
					+ ((JTextField) bag.get("TextID")).getText()
					+ "'";
			try {
				Pstmt = C.getConnect().prepareStatement(sql);
				Pstmt.executeUpdate();
			}catch(Exception E){
				System.out.println(E);
			}
			((HashMap<String,Double[]>)bag.get("chkLike")).clear();
			Main.bag_ =0;
			((JLabel)bag.get("lblHeart2")).setIcon(new ImageIcon(new getImage("love.png").getImageF()));
			((JLabel)bag.get("lblHeart2")).setName("love");
			((JButton)bag.get("btnOpencar")).setIcon(new ImageIcon(new getImage("gift.png").getImageF()));
			Closeview();
		}else if ("START".equals(pass)){
			new FinalFunction(bag);
			
		}
		else if(((JButton)bag.get("btnOpencar")).getName().equals("OPEN")||"OPEN".equals(pass)){
			if(Main.bag_!=0){
				
				((JPanel)bag.get("plMeunValues")).removeAll();
				((JPanel)bag.get("plAll")).repaint();
				((JButton)bag.get("btnOpencar")).setName("CLOSE");
				((JPanel)bag.get("plSubMeun")).setBounds(10, 10, 168, 380);
				((JButton)bag.get("btnSend")).setBounds(89, 347, 69, 23);
				((JButton)bag.get("btnClean")).setBounds(10, 347, 69, 23);
				((JPanel)bag.get("plMeunValues")).setBounds(10, 10, 148, 327);
				
				pl = new JPanel();
				Connect();
				scroll();
			}else{
				Slide S = new Slide(((JPanel)bag.get("plAll")),"Disappear");
				S.setDis(((JLabel)bag.get("lblWaring")));
				S.start();
			}
		}else if("CLOSE".equals(pass)||((JButton)bag.get("btnOpencar")).getName().equals("CLOSE")){
			Closeview();
		}
		((JPanel)bag.get("plAll")).repaint();
	}
	private void Closeview(){
		Integer [] plMeunLocal = ((Integer [])bag.get("plMeunLocal"));
		Integer [] plMeunValuesLocal = ((Integer [])bag.get("plMeunValuesLocal"));
		Integer [] btn2Local = ((Integer [])bag.get("btn2Local"));
		((JButton)bag.get("btnOpencar")).setName("OPEN");
		((JPanel)bag.get("plMeunValues")).removeAll();
		((JPanel)bag.get("plAll")).repaint();
		((JPanel)bag.get("plSubMeun")).setBounds(plMeunLocal[0], plMeunLocal[1], plMeunLocal[2], plMeunLocal[3]);
		((JButton)bag.get("btnSend")).setBounds(btn2Local[0], btn2Local[1], btn2Local[2], btn2Local[3]);
		((JButton)bag.get("btnClean")).setBounds(btn2Local[0]-79, btn2Local[1], btn2Local[2], btn2Local[3]);
		((JPanel)bag.get("plMeunValues")).add(((JPanel)bag.get("plValueGrade")));
		((JPanel)bag.get("plMeunValues")).setBounds(plMeunValuesLocal[0], plMeunValuesLocal[1], plMeunValuesLocal[2], plMeunValuesLocal[3]);
	}
	private void paset(final String name , final byte[] bs,final Double [] grade){
		
		final JCheckBox chkcarlike = new JCheckBox(){
			protected void paintComponent(Graphics g){
				  if(getModel().isArmed()){
					  ((JPanel)bag.get("plAll")).repaint();
				  }
				  super.paintComponent(g);
			}
		};;
		
		chkcarlike.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				((JPanel)bag.get("plAll")).repaint();
			}
		});
		chkcarlike.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(((HashMap<String,Double[]>)bag.get("chkLike")).size()<3){
					if(chkcarlike.isSelected()){
						((HashMap<String,Double[]>)bag.get("chkLike")).put(name, grade);
					}
					else
						((HashMap<String,Double[]>)bag.get("chkLike")).remove(name);
				}
				else{
					((HashMap<String,Double[]>)bag.get("chkLike")).remove(name);
					chkcarlike.setSelected(false);
				}
				((JButton)bag.get("btnSend")).setEnabled(
						((HashMap<String,Double[]>)bag.get("chkLike")).size()==3?true:false);
			}});
		Boolean isLike = ((HashMap<String,Double[]>)bag.get("chkLike")).get(name) == null? false 
			:true;
		chkcarlike.setSelected(isLike);
		chkcarlike.setOpaque(false);
		chkcarlike.setLayout(null);
		chkcarlike.setBounds(10, count*85, 148, 70);
		pl.add(chkcarlike);
		
		JButton btnNewButton = new JButton(new PhotoSizeSet().getphoto(bs,120,68)){
			protected void paintComponent(Graphics g){
				  if(getModel().isArmed()){
					  ((JPanel)bag.get("plAll")).repaint();
				  }
				  super.paintComponent(g);
			}
		};
		btnNewButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(Main.star_){
				new StarAction(bag,name,grade).start();
				((JLabel)bag.get("lblBackground")).setIcon(new PhotoSizeSet().getphoto(bs));
				((JLabel)bag.get("lblHeart2")).setIcon(new ImageIcon(new getImage("full love.png").getImageF()));
				((JLabel)bag.get("lblHeart2")).setName("full");
				new SetCarName(bag,name).run();
				String carname_new [] = ((HashMap<String, String []>)bag.get("CarName_New")).get(name);
				String html = "<table border = 0><tr>"
						+ "<td align=center><font color='#FFCC22'>"+carname_new[0]+"</font></td>"
						+ "<td width='800px',  rowspan = 2 ><font size=6 ,color='#EEE8AA'>"+carname_new[2]+"</font></td></tr><tr>"
						+ "<td align=center><font color='#FFCC22'>"+carname_new[1]+"</font></td></tr>"
						+ "</table>";
				((JLabel)bag.get("lblCarName")).setText("<html>"+html+"</html>");
				}
			}});
		//new StarAction(bag,name,grade).start();
		btnNewButton.setOpaque(false);
		new btnOpaqueClose(btnNewButton,((JPanel)bag.get("plAll")));
		btnNewButton.setBounds(18, 0, 120, 70);
		chkcarlike.add(btnNewButton);
	}
}	
/*if(arg0.getActionCommand().equals("OPENlikecar")){
					btnOpencar.setActionCommand("CLOSElikecar");
					((JPanel)bag.get("plSubMeun")).setBounds(10, 10, 168, 380);
					((JButton)bag.get("btnSend")).setBounds(89, 347, 69, 23);
					((JButton)bag.get("btnClean")).setBounds(10, 347, 69, 23);
					((JPanel)bag.get("plMeunValues")).removeAll();
					((JPanel)bag.get("plMeunValues")).setBounds(10, 50, 148, 287);
					final int count = 0 ;
					final JPanel pl = new JPanel();
					((JPanel)bag.get("plMeunValues")).addMouseWheelListener(new MouseWheelListener(){
	
						@Override
						public void mouseWheelMoved(MouseWheelEvent arg0) {
							int y = pl.getY() + arg0.getWheelRotation()*10;
							if (y<0&& y> 287 - count*70){
							pl.setLocation(0, pl.getY()+arg0.getWheelRotation()*10);
							((JPanel)bag.get("plAll")).repaint();
							}
							
						}});
					pl.setOpaque(false);
					pl.setBounds(0, 0, 148, count*70);
					((JPanel)bag.get("plMeunValues")).add(pl);
					pl.setLayout(null);
					bag.put("plPhotoCheck", pl);
					for(int i =0 ; i<count ;i++){
						JCheckBox chckbxNewCheckBox = new JCheckBox("");
						chckbxNewCheckBox.addChangeListener(new ChangeListener() {
							public void stateChanged(ChangeEvent arg0) {
								((JPanel)bag.get("plAll")).repaint();
							}
						});
						chckbxNewCheckBox.setOpaque(false);
						chckbxNewCheckBox.setLayout(null);
						chckbxNewCheckBox.setBounds(10, i*70, 148, 68);
						pl.add(chckbxNewCheckBox);
						
						JButton btnNewButton = new JButton("New button");
						new btnOpaqueClose(btnNewButton,((JPanel)bag.get("plAll")));
						btnNewButton.setBounds(28, 0, 120, 68);
						chckbxNewCheckBox.add(btnNewButton);
					}
				}else if(arg0.getActionCommand().equals("CLOSElikecar")){
					btnOpencar.setActionCommand("OPENlikecar");
					((JPanel)bag.get("plMeunValues")).removeAll();
					((JPanel)bag.get("plSubMeun")).setBounds(10, 42, 168, 317);
					((JButton)bag.get("btnSend")).setBounds(89, 284, 69, 23);
					((JButton)bag.get("btnClean")).setBounds(10, 284, 69, 23);
					((JPanel)bag.get("plMeunValues")).add(((JPanel)bag.get("plValueGrade")));
					((JPanel)bag.get("plMeunValues")).setBounds(10, 50, 148, 219);
				}*/