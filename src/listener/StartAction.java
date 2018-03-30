package listener;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;

import GUI.carVaule;
import Rcaller.RConnect;

import java.util.ArrayList;
import java.util.HashMap;

import datatool.Connect;
import datatool.Sql;

public class StartAction extends Thread implements ActionListener {
	private HashMap<String, Object> bag ;
	private	Double grade [] = {50.0,50.0,0.0,20.0,50.0};
	public StartAction(HashMap<String, Object> bag) {
		this.bag = bag;
	}
	private void setGrade(){
		Double grade [] = {Double.valueOf(((JTextField)bag.get("EASY")).getText())
				,Double.valueOf(((JTextField)bag.get("SAFETY")).getText())
				,Double.valueOf(((JTextField)bag.get("COZY")).getText())
				,Double.valueOf(((JTextField)bag.get("CONTROLLED")).getText())
				,Double.valueOf(((JTextField)bag.get("MONEY")).getText())};
		this.grade = grade;
		tag = new Double[]{1.0,0.0};
		tagI = new Double[]{1.0,0.0};
	}
	private HashMap<String,Double []> G;
	private String name ="" ;
	public void actionPerformed(ActionEvent arg0) {
		((JPanel)bag.get("plMain")).removeAll();
		((JPanel)bag.get("plMain")).repaint();
		setGrade();
		Connect c = new Connect ();
		c.Connect("MYSQL");
		Sql CMD = new Sql(c.getConnect());
		String sql = "select name,easy,safety,cozy,controlled,money from cargrade where "
					+"EASY >= "+(grade[0]-5)*0.01+ " and "
					+"SAFETY >= "+(grade[1]-5)*0.01+" and "
					+"COZY >= "+ (grade[2]-5)*0.01 + " and "
					+"CONTROLLED >= "+(grade[3]-5)*0.01 + " and "
					+"MONEY >= "+(grade[4]-5)*0.01;
		try {
			CMD.setSQL(sql);
			G =new  HashMap<String,Double []>();
			G.clear();
			name = "";
			int u = 0;
			//JPanel JP = ((JPanel)bag.get("plMain"));
			while(CMD.getResultSet().next()){
				//if(u==5)break;
				/*name = name + CMD.getResultSet().getString(1) + ", ";
				G.put(CMD.getResultSet().getString(1),new Double []{
							Double.valueOf(CMD.getResultSet().getString(2).substring(0,5)),
							Double.valueOf(CMD.getResultSet().getString(3).substring(0,5)),
							Double.valueOf(CMD.getResultSet().getString(4).substring(0,5)),
							Double.valueOf(CMD.getResultSet().getString(5).substring(0,5)),
							Double.valueOf(CMD.getResultSet().getString(6).substring(0,5))
						});*/
				/*addStar(JP,(int) (Math.random()*H),(int) (Math.random()*W),CMD.getResultSet().getString(1),new Double []{
						Double.valueOf(CMD.getResultSet().getString(2).substring(0,5)),
						Double.valueOf(CMD.getResultSet().getString(3).substring(0,5)),
						Double.valueOf(CMD.getResultSet().getString(4).substring(0,5)),
						Double.valueOf(CMD.getResultSet().getString(5).substring(0,5)),
						Double.valueOf(CMD.getResultSet().getString(6).substring(0,5))
					});*/
				G.put(CMD.getResultSet().getString(1), new Double []{
						Double.valueOf(CMD.getResultSet().getString(2).substring(0,5)),
						Double.valueOf(CMD.getResultSet().getString(3).substring(0,5)),
						Double.valueOf(CMD.getResultSet().getString(4).substring(0,5)),
						Double.valueOf(CMD.getResultSet().getString(5).substring(0,5)),
						Double.valueOf(CMD.getResultSet().getString(6).substring(0,5))});
				name = name + CMD.getResultSet().getString(1) + "\n" ;
				double x =(CMD.getResultSet().getDouble(2)+
						//CMD.getResultSet().getDouble(3)+
						CMD.getResultSet().getDouble(4)+
						//CMD.getResultSet().getDouble(5)+
						CMD.getResultSet().getDouble(6))
						/3;
				double y =(//CMD.getResultSet().getDouble(2)*0.178+
						CMD.getResultSet().getDouble(3)+
						//CMD.getResultSet().getDouble(4)*0.205+
						CMD.getResultSet().getDouble(5)
						//+CMD.getResultSet().getDouble(6)*0.190
						)/2;
				/*double y =(CMD.getResultSet().getDouble(2)*0.178+
						CMD.getResultSet().getDouble(3)*0.224+
						CMD.getResultSet().getDouble(4)*0.205+
						CMD.getResultSet().getDouble(5)*0.204+
						CMD.getResultSet().getDouble(6)*0.190)/5;*/
				tag[1] = Math.max(x, tag[1]);
				tag[0] = Math.min(x, tag[0]);
				tagI[1] = Math.max(y, tagI[1]);
				tagI[0] = Math.min(y, tagI[0]);
				
			}
			
			//bag.put("NAME", name.split(", "));
			//bag.put("GRADE",G);
			addStar();
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally{
			c.close();
			CMD.sqlClose();
			c = null;
			CMD = null;
		}
		//R.setGrade(new double[]{1,20,30,40,50});
		
	}
	Double [] tag ;
	Double [] tagI ;
	private void addStar(){
		JPanel JP = (JPanel) bag.get("plMain");
		for(final String name : this.name.split("\n")){
			final Double grade [] = G.get(name);
			double x = (grade[0]/*+grade[1]*/+grade[2]/*+grade[3]*/+grade[4])/3;
			double y = (grade[1]+grade[3])/2;
			//double y = (grade[0]*0.178+grade[1]*0.224+grade[2]*0.205+grade[3]*0.204+grade[4]*0.190)/5.0;
			int w = (int)(((W)*(x-tag[0]))/(tag[1]-tag[0])),
					h = (int)(((H)*(y-tagI[0]))/(tagI[1]-tagI[0]));
			//int w = (int)(Math.random()*W),h = (int)(Math.random()*H);
			
			System.out.println(name +" : "+ grade[0] + "\t" +grade[1]+ "\t"+grade[2]+ "\t"+grade[3]+ "\t"+grade[4]);
			JLabel lblNewLabel = new JLabel("\uF0EE");
			JLabel lblNewLabel2 = new JLabel(name);
			final int [] set = {
					w, 
					h};
			lblNewLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(arg0.getClickCount()==1){
						System.out.println(name+"("+(set[0]-335)+","+(185-set[1])+")" +" : "+ grade[0] + "\t" +grade[1]+ "\t"+grade[2]+ "\t"+grade[3]+ "\t"+grade[4]);
						carVaule dialog = new carVaule(name,grade);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					}
				}
			});
			lblNewLabel.setFont(new Font("Wingdings 2", Font.PLAIN, 10));
			//lblNewLabel2.setBounds(set[0],set[1]+20, 200, 30);
			lblNewLabel.setBounds(set[0],set[1],10, 10);
			JP.add(lblNewLabel2);
			JP.add(lblNewLabel);
		}
		//JP.add((JLabel) bag.get("lblbackground"));
		JP.repaint();
	}
	private final int H = (400-30) ;
	private final int W = (700-30) ;
	private void addStar(JPanel JP ,int h,int w,final String name , final Double [] grade){
		System.out.println(name +" : "+ grade[0] + "\t" +grade[1]+ "\t"+grade[2]+ "\t"+grade[3]+ "\t"+grade[4]);
		JLabel lblNewLabel = new JLabel("\uF0EE");
		JLabel lblNewLabel2 = new JLabel(name);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount()==1){
					System.out.println(name +" : "+ grade[0] + "\t" +grade[1]+ "\t"+grade[2]+ "\t"+grade[3]+ "\t"+grade[4]);
					carVaule dialog = new carVaule(name,grade);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
			}
		});
		lblNewLabel.setFont(new Font("Wingdings 2", Font.PLAIN, 35));
		lblNewLabel2.setBounds(w+31, h, 100, 31);
		lblNewLabel.setBounds(w, h, 31, 31);
		JP.add(lblNewLabel2);
		JP.add(lblNewLabel);
		JP.repaint();
		/*try {
			sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}