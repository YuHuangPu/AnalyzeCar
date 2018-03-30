package btn.listener.Action;

import java.awt.Component;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GUI.Main;
import Log.loginsert;
import Tool.Slide;

import javax.swing.JLabel;

import btn.function.SendFunction;

public class SendAction extends Thread {
	private HashMap<String, Object> bag;

	public SendAction(HashMap<String, Object> bag) {
		this.bag = bag;
	}

	public void run() {
		((HashMap<String, JPanel>) bag.get("PageStatus")).remove("PAGE1");
		((HashMap<String, JPanel>) bag.get("PageStatus")).remove("PAGE2");
		((HashMap<String, JPanel>) bag.get("PageStatus")).remove("PageAll");
		btnStart();
	}

	private Slide Slide;

	private void TEXTENABLE() {
		((JTextField) bag.get("EASY")).setEnabled(false);
		((JTextField) bag.get("SAFETY")).setEnabled(false);
		((JTextField) bag.get("COZY")).setEnabled(false);
		((JTextField) bag.get("CONTROLLED")).setEnabled(false);
		((JTextField) bag.get("MONEY")).setEnabled(false);
		((JTextField) bag.get("TextID")).setEnabled(false);
		((JTextField) bag.get("MONEYMAX")).setEnabled(false);
		((JTextField) bag.get("MONEYMIN")).setEnabled(false);
		((JTextField) bag.get("CCMAX")).setEnabled(false);
		((JTextField) bag.get("CCMIN")).setEnabled(false);
		
	}
	private void PANELENABEL(){
		((JPanel) bag.get("plSubMain")).setOpaque(true);
		((JPanel) bag.get("plMeun")).setOpaque(true);
		((JPanel) bag.get("plCarName")).setOpaque(true);
		((JPanel) bag.get("plUser")).setOpaque(true);
		((JPanel) bag.get("plMakeList")).removeAll();
		((JPanel) bag.get("plMakeList")).add(((JLabel) bag.get("lblMakeList")));
		((JPanel) bag.get("plSupChoseB")).removeAll();
		for(int i =0 ;i<4;i++){
			((JPanel) bag.get("plSupChoseB")).add(((JLabel) bag.get("lblcarlist"+i)));
			((JLabel) bag.get("lblcarlist"+i)).setText(((JLabel) bag.get("lblcarlist"+i)).getName()+" : "
					+((((JComboBox<String>)bag.get("cbcarlist"+i)).getSelectedItem().toString()).equals(((JLabel) bag.get("lblcarlist"+i)).getName())?"¤£­­"
							:(((JComboBox<String>)bag.get("cbcarlist"+i)).getSelectedItem().toString())));
		}
		((JPanel) bag.get("plSupChoseB")).add(((JPanel) bag.get("plCC")));
	}
	private void btnStart() {
		Main.star_ = false;
		TEXTENABLE();
		PANELENABEL();
		bag.put("chkLike", new HashMap<String,Double[]>());
		((JButton) bag.get("btnOpencar")).setEnabled(true);
		((JButton) bag.get("btnSend")).setEnabled(false);
		((JLabel) bag.get("lblHeart2")).setIcon(new ImageIcon());
		((JLabel) bag.get("lblCarName")).setText("");
		((JLabel) bag.get("lblMakeList")).setText((
				((((JComboBox<String>)bag.get("cBoxMake")).getSelectedItem().toString()).equals(((JLabel) bag.get("lblMakeList")).getName())?"¤£­­¼tµP"
						:(((JComboBox<String>)bag.get("cBoxMake")).getSelectedItem().toString()))));
		/*((JPanel) bag.get("plCarName")).setBounds(0, 461, ((JPanel) bag.get("plCarName")).getWidth(),
				((JPanel) bag.get("plCarName")).getHeight());*/
		Slide = new Slide((JPanel) bag.get("plAll"));
		Slide.setXY(bag.get("plSubMain"), -710, ((JPanel) bag.get("plSubMain")).getY(), 0,
				((JPanel) bag.get("plSubMain")).getY(), 1);
		Slide.start();
		try {
			Slide.join();
			((JPanel) bag.get("plSubMain")).removeAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((JPanel) bag.get("plSubMain")).setLocation(0, ((JPanel) bag.get("plSubMain")).getY());
		new SendFunction(bag);
		
		inserlog();
		
		
	}
	private void inserlog(){
		String value = 
			"Grade : (EASY : "+((JTextField) bag.get("EASY")).getText() + ", "
			+"SAFETY : "+((JTextField) bag.get("SAFETY")).getText() + ", "
			+"COZY : "+((JTextField) bag.get("COZY")).getText() + ", "
			+"CONTROLLED : "+((JTextField) bag.get("CONTROLLED")).getText() + ", "
			+"MONEY : "+((JTextField) bag.get("MONEY")).getText() + ")||"
			+"CC : " +((JTextField) bag.get("CCMIN")).getText()+"~"+((JTextField) bag.get("CCMAX")).getText()+", "
			+"MONEY : "+((JTextField) bag.get("MONEYMIN")).getText()+"~"+((JTextField) bag.get("MONEYMAX")).getText()+", "
			+((JLabel) bag.get("lblMakeList")).getName().substring(0,2) + " : "+((JLabel) bag.get("lblMakeList")).getText()+", "
			+((JLabel) bag.get("lblcarlist0")).getName() + " : "+((JLabel) bag.get("lblcarlist0")).getText()+", "
			+((JLabel) bag.get("lblcarlist1")).getName() + " : "+((JLabel) bag.get("lblcarlist1")).getText()+", "
			+((JLabel) bag.get("lblcarlist2")).getName() + " : "+((JLabel) bag.get("lblcarlist2")).getText()+", "
			+((JLabel) bag.get("lblcarlist3")).getName().substring(0,3) + " : "+((JLabel) bag.get("lblcarlist3")).getText();
		
		new loginsert(((JTextField) bag.get("TextID")).getText(),value,"SEND").start();
	}
}
