package btn.listener.Action;

import java.awt.Color;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GUI.Main;
import PIC.getImage;
import Tool.Slide;

public class CleanAction extends Thread{
	private HashMap<String, Object> bag ;
	public CleanAction(HashMap<String, Object> bag) {
		this.bag = bag;
	}
	public void run(){
		Main.star_ = false;
		if(((JButton)bag.get("btnOpencar")).getName().equals("CLOSE"))
		new FinalAction(bag,"CLOSE").start();
		
		Action();
		Main.star_ = true;}
	private Slide Slide;
	private void TEXTREPLACE (){
		((JTextField)bag.get("EASY")).setEnabled(true);
		((JTextField)bag.get("SAFETY")).setEnabled(true);
		((JTextField)bag.get("COZY")).setEnabled(true);
		((JTextField)bag.get("CONTROLLED")).setEnabled(true);
		((JTextField)bag.get("MONEY")).setEnabled(true);
		((JTextField)bag.get("TextID")).setEnabled(true);
		((JTextField) bag.get("MONEYMAX")).setEnabled(true);
		((JTextField) bag.get("MONEYMIN")).setEnabled(true);
		((JTextField) bag.get("CCMAX")).setEnabled(true);
		((JTextField) bag.get("CCMIN")).setEnabled(true);
		
		
		((JTextField)bag.get("EASY")).setText("0");
		((JTextField)bag.get("SAFETY")).setText("0");
		((JTextField)bag.get("COZY")).setText("0");
		((JTextField)bag.get("CONTROLLED")).setText("0");
		((JTextField)bag.get("MONEY")).setText("0");
		((JTextField)bag.get("TextID")).setText("Thanks");
		((JTextField)bag.get("MONEYMAX")).setText("9999");
		((JTextField)bag.get("MONEYMIN")).setText("0");
		((JTextField)bag.get("CCMAX")).setText("9999");
		((JTextField)bag.get("CCMIN")).setText("0");
		
	}
	private void LBLREPLACE(){
		((JLabel)bag.get("lblCount_")).setText("");
		((JLabel)bag.get("lblCount")).setForeground(Color.pink);
		((JLabel)bag.get("lblCount")).setText("       謝   謝   指   教");
		((JLabel)bag.get("lblBackground")).setIcon(new ImageIcon(new getImage("QAQ2.jpg").getImageF()));
		((JLabel)bag.get("lblCarName")).setText("");
		((JLabel)bag.get("lblHeart2")).setIcon(new ImageIcon());
		((JLabel)bag.get("lblW")).setForeground(Color.red);
	}
	private void BUTTONREPLACE(){
		((JButton)bag.get("btnSend")).setEnabled(false);
		((JButton)bag.get("btnOpencar")).setEnabled(false);
	}
	private void PAGEREPLACE(){
		if(((HashMap<String,JPanel>)bag.get("PageStatus")).remove("PAGE1")==null){
			((JLabel)bag.get("lblCount")).setText("       歡   迎   指   教");
		}
		((HashMap<String,JPanel>)bag.get("PageStatus")).remove("PAGE1");
		((HashMap<String,JPanel>)bag.get("PageStatus")).remove("PAGE2");
		((HashMap<String,JPanel>)bag.get("PageStatus")).remove("PageAll");
	}
	private void PANELREPLACE(){
		((JPanel)bag.get("plMeun")).setOpaque(false);
		((JPanel)bag.get("plCarName")).setOpaque(false);
		((JPanel)bag.get("plMeun")).setOpaque(false);
		((JPanel)bag.get("plUser")).setOpaque(false);
		((JPanel)bag.get("plMakeList")).removeAll();
		((JPanel)bag.get("plMakeList")).add(((JComboBox<String>)bag.get("cBoxMake")));
		((JPanel)bag.get("plChose")).removeAll();
		((JPanel)bag.get("plChose")).add((JPanel) bag.get("plSupChoseA"));
		((JPanel) bag.get("plSupChoseB")).removeAll();
		for(int i =0 ;i<4;i++){
			((JPanel) bag.get("plSupChoseB")).add(((JComboBox) bag.get("cbcarlist"+i)));
			((JComboBox) bag.get("cbcarlist"+i)).setSelectedIndex(0);
		}
		((JPanel) bag.get("plSupChoseB")).add(((JPanel) bag.get("plCC")));
	}
	private void Action (){
		Slide = new Slide(((JPanel)bag.get("plAll")));
		Slide.setXY(((JPanel)bag.get("plSubMain")), 0, ((JPanel)bag.get("plSubMain")).getY(), -710, ((JPanel)bag.get("plSubMain")).getY(), 1);
		Slide.start();
		try {
			Slide.join();
			PANELREPLACE();
			TEXTREPLACE ();
			LBLREPLACE();
			BUTTONREPLACE();
			PAGEREPLACE();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((JComboBox<String>)bag.get("cBoxMake")).setSelectedIndex(0);
		((JPanel)bag.get("plSubMain")).removeAll();
		((JPanel)bag.get("plSubMain")).add(((HashMap<String,JPanel>)bag.get("PageStatus")).get("PAGE0"));
		System.gc();
		((JPanel)bag.get("ALL")).repaint();
		
	}
}
