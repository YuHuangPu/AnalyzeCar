package GUI.repaint;

import javax.swing.JPanel;

public class repaint extends Thread{
	private final JPanel all;
	public repaint (JPanel all){this.all = all;start();}
	public void run(){
		while(true){
		all.repaint();
		System.out.println("aa");
		}
	}
	
}
