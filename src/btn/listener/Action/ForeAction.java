package btn.listener.Action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JPanel;

import GUI.Main;
import Tool.Slide;

public class ForeAction extends Thread {
	public ForeAction(){}
	private HashMap<String, Object> bag;
	private String page;
	private Slide Slide ;
	public ForeAction(HashMap<String, Object> bag,String page) {
		this.bag = bag;
		this.page = page ;
		
	}
	public void run(){Main.star_=false;Action();}
	private void Action (){
		Slide = new Slide(((JPanel)bag.get("plAll")));
		Slide.setXY(((JPanel)bag.get("plSubMain")), 0, ((JPanel)bag.get("plSubMain")).getY(), -710, ((JPanel)bag.get("plSubMain")).getY(), 1);
		Slide.start();
		
		try {
			Slide.join();
			((JPanel)bag.get("plSubMain")).removeAll();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Slide = new Slide(((JPanel)bag.get("plAll")));
		Slide.setXY(((JPanel)bag.get("plSubMain")), -710, ((JPanel)bag.get("plSubMain")).getY(), 0, ((JPanel)bag.get("plSubMain")).getY(), 1);
		Slide.start();
		
		((JPanel)bag.get("plSubMain")).add(((HashMap<String,JPanel>)bag.get("PageStatus")).get("PAGE"+page));
		try {
			Slide.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Main.star_=true;
		//((JPanel)bag.get("plAll")).repaint();
	}
}
