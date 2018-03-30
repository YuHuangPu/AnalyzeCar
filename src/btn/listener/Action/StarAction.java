package btn.listener.Action;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GUI.Main;
import Log.loginsert;
import Tool.Slide;
import btn.Tool.PhotoSizeSet;
import btn.function.StarFunction;

public class StarAction extends Thread{
	private HashMap <String,Object> bag ;
	private boolean DEMO = true;
	private String name ;
	private Double [] grade ;
	private Slide Slide;
	public StarAction (HashMap <String,Object> bag,String name ,Double [] grade){
		this.bag = bag;
		this.name = name;
		this.grade = grade;
	}
	public void  run() {staraction();}
	private void staraction() {
		Main.star_ = false;
		Slide = new Slide(((JPanel)bag.get("plAll")));
		Slide.setXY(((JPanel)bag.get("plSubMain")), 0, ((JPanel)bag.get("plSubMain")).getY(), -710, ((JPanel)bag.get("plSubMain")).getY(), 1);
		Slide.start();
		PhotoSizeSet Photo =null;
		if(DEMO){
		Photo = new PhotoSizeSet(name);
		Photo.start();}
		try {
			Slide.join();
			if(DEMO)
			Photo.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new loginsert(((JTextField) bag.get("TextID")).getText(),name,"STAR").start();
		((JPanel)bag.get("plSubMain")).removeAll();
		new StarFunction(bag,name,grade,Photo);
		Slide= null;
		Slide = new Slide(((JPanel)bag.get("plAll")));
		Slide.setXY(((JPanel)bag.get("plSubMain")), -710, ((JPanel)bag.get("plSubMain")).getY(), 0, ((JPanel)bag.get("plSubMain")).getY(), 1);
		Slide.start();
		try {
			Slide.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Main.star_ = true;
	}
	
}
