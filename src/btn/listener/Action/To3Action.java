package btn.listener.Action;

import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JTextField;

import GUI.Main;
import Log.loginsert;
import Rcaller.RConnect;
import Tool.Slide;
import btn.function.StarFunction;
import btn.function.To3Function;

public class To3Action extends Thread{
	private HashMap<String, Object> bag;
	public To3Action(HashMap<String, Object> bag) {
		this.bag = bag;
	}
	public void run(){Action();}
	private Slide Slide;
	private void Action(){
		Main.star_ = false;
		Double [] grade = (Double [])bag.get("Grade");
		String name = (String)bag.get("Name");
		RConnect R = new RConnect();
		R.Connect();
		R.setGrade(name, grade);
		R.start();
		
		Slide = new Slide(((JPanel)bag.get("plAll")));
		Slide.setXY(((JPanel)bag.get("plSubMain")), 0, ((JPanel)bag.get("plSubMain")).getY(), -710, ((JPanel)bag.get("plSubMain")).getY(), 1);
		Slide.start();
		try {
			R.join();
			Slide.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((JPanel)bag.get("plSubMain")).removeAll();
		new To3Function(bag);
		new loginsert(((JTextField) bag.get("TextID")).getText(),name,"TO3").start();
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
		/*try {
			Slide.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
