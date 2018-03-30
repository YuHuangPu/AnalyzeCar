package Tool;

import java.awt.Color;

import javax.swing.*;

public class Slide extends Thread{
	private int []XY ;
	private Object comp;
	private JPanel Main;
	private String name = "" ;
	public Slide(JPanel Main) {
		this.Main = Main ;
	}

	public Slide(JPanel Main, String name) {
		this.Main = Main ;
		this.name = name;
	}
	
	public void run (){
		if(name.equals(""))
			Animation();
		else if (name.equals("Disappear")){
			Disappear();
		}else if (name.equals("FINAL")){
			Final();
		}
	}
	private void Disappear(){
		((JLabel)comp).setForeground(new Color(255,0,0));
		int i = 255;
		while(i!=0){
			((JLabel)comp).setForeground(new Color(255,0,0,i--));
			Main.repaint();
			try {
				sleep(i/10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	private void Final(){
		Main.setBounds(36, 396, 954, 538);
		
		while(Main.getY()!=10){
			Main.setBounds(36
					, Main.getY()<=10?10:Main.getY()-2
					, 954
					, 538);
			Main.repaint();
			try {
				sleep(7);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void setDis(Object comp){
		this.comp = comp;
		
	}
	private void Animation(){
		int xmove =XY[2]-XY[0]==0 ?0 : (XY[2]-XY[0] >0 ?1:-1);
		int ymove =XY[3]-XY[1]==0 ?0 : (XY[3]-XY[1] >0 ?1:-1);
		int X = XY[0],Y = XY[1];
		JPanel JP = ((JPanel)Main);
		while(X!=XY[2] || Y !=XY[3]){
			X = X!=XY[2]? (X + xmove):X ;
			Y = Y!=XY[3]? (Y + ymove):Y ;
			switch(comp.getClass().getSimpleName()){
				case "JPanel":
					((JPanel)comp).setBounds(X, Y, ((JPanel)comp).getWidth(), ((JPanel)comp).getHeight());
					break;
				case "JLabel" :
					((JLabel)comp).setBounds(X, Y, ((JLabel)comp).getWidth(), ((JLabel)comp).getHeight());
					break;
			}
			JP.repaint();
			try {
				sleep(XY[4]);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setXY(Object comp,int earlyX, int earlyY, int finalX, int finalY,int time) {
		this.comp = comp;
		XY = new int []{earlyX,earlyY,finalX,finalY,time};
	}

}
