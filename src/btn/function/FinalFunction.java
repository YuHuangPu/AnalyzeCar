package btn.function;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

import btn.Tool.PhotoSizeSet;

import java.awt.BorderLayout;

import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Log.loginsert;
import PIC.getImage;
import Rcaller.RConnect;
import Tool.Slide;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FinalFunction extends JPanel {

	/**
	 * Create the panel.
	 */
	public static boolean finaltag = false;
	public FinalFunction() {
		setBackground(new Color(0, 0, 0));
		Function ();
	}
	private HashMap<String, Object> bag;
	public FinalFunction(HashMap<String, Object> bag) {
		this.bag = bag ;
		replace ();
	}
	private RConnect R;
	private void R(String name , Double [] grade){
		R.setGrade(name, grade,false);
	}
	private void replace (){
		R = new RConnect();
		Slide Slide;
		Slide = new Slide((JPanel) bag.get("ALL"),"FINAL");
		Slide.start();
		
		((JPanel)bag.get("ALL")).removeAll();
		((JPanel)bag.get("ALL")).repaint();
		//this.setOpaque(false);
		Function ();

		((JPanel)bag.get("ALL")).add(this);
		
		//((JPanel)bag.get("ALL")).setBackground(new Color(0,0,0,200));
		((JPanel)bag.get("ALL")).repaint();
		
	}
	TitledBorder TA []  = new TitledBorder [3];
	private Double carvalue(String Name, Double [] grade ,int i,Color c){
		R(Name,grade);
		Double gradeall = 1000*(grade[0]*0.178+grade[1]*0.224+grade[2]*0.205+grade[3]*0.204+grade[4]*0.19);
		JPanel panel_1 = new JPanel();
		panel_1.setName(String.valueOf(gradeall));
		TitledBorder T = new TitledBorder(null, Name, TitledBorder.CENTER, TitledBorder.TOP, null, c);
		T.setBorder(new LineBorder(c, 3));
		
		T.setTitleFont(new Font("地眃次次砰W5", Name.length()>35?Font.PLAIN:Font.BOLD, Name.length()>35?15:18));
		TA[i]=T;
		panel_1.setBorder(T);
		panel_1.setBounds(490, 30 + i*160, 430, 150);
		add(panel_1);
		panel_1.setOpaque(false);
		panel_1.setLayout(null);
		String str_grade = String.valueOf(gradeall).substring(0, 4);
		
		JLabel lblNewLabel_1 = new JLabel(
				new PhotoSizeSet().getphoto(((HashMap<String,byte[]>)bag.get("likephoto")).get(Name), 211, 120));
		lblNewLabel_1.setBounds(10, 20, 211, 120);
		
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setOpaque(false);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(231, 65, 189, 75);
		panel_1.add(panel);
		panel.setLayout(null);
		
		
		for(int x []: new int [][]{{0,0},{51,1},{129,2}}){
			JLabel label = new JLabel(String.valueOf(str_grade.charAt(x[1])));
			label.setForeground(c);
			label.setBounds(x[0], 0, 60, 75);
			panel.add(label);
			label.setHorizontalAlignment(SwingConstants.LEFT);
			label.setFont(new Font("地眃次次砰W5(P)", Font.PLAIN, 99));	
		}
		JLabel label_1 = new JLabel(".");
		label_1.setForeground(c);
		label_1.setBounds(103, 0, 29, 75);
		panel.add(label_1);
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("地眃次次砰W5(P)", Font.PLAIN, 99));
		return gradeall;
			
	}
	private Double findtheKing(Double [] find){
		Double G [] = new Double [3];
		G[0] = find [0];
		G[1] = find [1];
		G[2] = find [2];
		for(int i =0 ; i < G.length-1 ;i++){
			for(int j =i+1 ; j<G.length;j++){
				if(G[i]<G[j]){
					Double a =G [j];
					G[j] = G[i];
					G[i] = a;
				}
			}
		}
		return G[0];
	}
	private void Function (){
		this.setBounds(0, 0, 930, 528);
		this.setBackground(new Color(0,0,0,150));
		setLayout(null);
		

		JLabel lblNewLabel1 = new JLabel(new ImageIcon(new getImage("crown.png").getImageF()));
		//lblNewLabel1.setBounds(465, 10 +160, 60, 60);
		add(lblNewLabel1);
		
		String Names = 
				((HashMap<String,Double[]>)bag.get("chkLike")).keySet().toString().substring(1,((HashMap<String,Double[]>)bag.get("chkLike")).keySet().toString().length()-1)
				;
		String plotname = "";
		new loginsert(((JTextField) bag.get("TextID")).getText(),Names,"FINALGO").start();
		//((255,0,0),(0,206,209),(255,255,0));
		Color c [] = new Color[3] ;
		c[0] = new Color (255,51,51);
		c[1] = new Color (0,206,209);
		c[2] = new Color (255,255,0);
		Double gradefind [] = new Double [3];
		for(int i = 0 ;i<3;i++){
			plotname = plotname + Names.split(", ")[i] + ", ";
			gradefind[i] = 
			carvalue(Names.split(", ")[i],((HashMap<String,Double[]>)bag.get("chkLike")).get(Names.split(", ")[i]),i,c[i]);
		}
		Double king = findtheKing(gradefind);
		for(int i =0 ; i < 3 ;i ++){
			if(king == gradefind[i]){
				lblNewLabel1.setBounds(470, 10 +i*160, 60, 60);
				TA[i].setTitle(TA[i].getTitle());
				break;
			}
		}
		R.start();
		try {
			R.join();
			finaltag = true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JButton button = new JButton("\uF085\uF085"){
			protected void paintComponent(Graphics g){
				  if(getModel().isArmed()){
					  ((JPanel)bag.get("plAll")).repaint();
				  }
				  super.paintComponent(g);
			}
		};
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((JPanel)bag.get("ALL")).removeAll();
				((JPanel)bag.get("ALL")).setOpaque(false);
				((JPanel)bag.get("ALL")).add(((JPanel)bag.get("plAll")));
				((JPanel)bag.get("ALL")).repaint();
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Wingdings 3", Font.PLAIN, 20));
		button.setBorderPainted(false);
		button.setBackground(Color.BLACK);
		button.setBounds(10, 495, 70, 23);
		add(button);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon("bag\\["+plotname.substring(0, plotname.length()-2)+"].png"));
		lblNewLabel.setForeground(new Color(0, 128, 0));
		lblNewLabel.setBounds(8, 30, 470, 470);
		add(lblNewLabel);
		
		
	}
}
