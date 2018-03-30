package btn.function;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;

import Tool.Slide;

public class ShowPhotoFunction extends Thread{
	private HashMap<String, Object> bag;
	private JButton btnBack,btnUp;
	private String Action;
	public ShowPhotoFunction(HashMap<String, Object> bag, JButton btnUp, JButton btnBack,String Action) {
		this.btnBack = btnBack;
		this.btnUp = btnUp;
		this.bag = bag;
		this.Action = Action;
		start();
	}
	public void run (){
		Function();
	}
	private void Function(){
		if(btnBack.getText().equals("\uF085\uF085")&&Action.equals("Back")){
			int [] plAll = {((JPanel) bag.get("plAll")).getX(),((JPanel) bag.get("plAll")).getY(),((JPanel) bag.get("plAll")).getWidth(),((JPanel) bag.get("plAll")).getHeight()};
			while(((JPanel) bag.get("plPhotoBtns")).getY()!=-515){
				((JPanel) bag.get("plPhotoBtns")).setBounds(884, ((JPanel) bag.get("plPhotoBtns")).getY()-1, 70, 480);
				btnBack.setBounds(884, btnBack.getY()-1, 70, 23);
				btnUp.setBounds(884, btnBack.getY()-1, 70, 23);
				((JPanel) bag.get("ALL")).repaint();
				try {
					sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			((JPanel) bag.get("ALL")).removeAll();
			bag.remove("btnUpDown");
			((JPanel) bag.get("plPhotoBtns")).setBounds(630, 43, 70, 284);
			((HashMap<String, JPanel>)bag.get("PageStatus")).get("PAGE2").add(((JPanel) bag.get("plPhotoBtns")));
			((JPanel) bag.get("ALL")).add((JPanel) bag.get("plAll"));
			while(((JPanel) bag.get("plAll")).getX()!=0){
				((JPanel) bag.get("plAll")).setBounds(((JPanel) bag.get("plAll")).getX()-1, plAll[1], plAll[2], plAll[3]);
				((JPanel) bag.get("ALL")).repaint();
				try {
					sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(Action.equals("UpDown")){
			btnUp.setEnabled(false);
			if(btnUp.getText().equals("\uF082")){
				btnUp.setText("ƒñ");
				while(((JPanel) bag.get("plPhotoBtns")).getY()!=0){
					((JPanel) bag.get("plPhotoBtns")).setBounds(884, ((JPanel) bag.get("plPhotoBtns")).getY()+1, 70, 480);
					btnBack.setBounds(884, btnBack.getY()+1, 70, 23);
					btnUp.setBounds(884, btnUp.getY()+1, 70, 23);
					((JPanel) bag.get("ALL")).repaint();
					try {
						sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else if(btnUp.getText().equals("ƒñ")){
				btnUp.setText("\uF082");
				while(((JPanel) bag.get("plPhotoBtns")).getY()!=-515){
					((JPanel) bag.get("plPhotoBtns")).setBounds(884, ((JPanel) bag.get("plPhotoBtns")).getY()-1, 70, 480);
					btnBack.setBounds(884, btnBack.getY()-1, 70, 23);
					btnUp.setBounds(884, btnUp.getY()-1, 70, 23);
					((JPanel) bag.get("ALL")).repaint();
					try {
						sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			btnUp.setEnabled(true);
		}	
	}
}
