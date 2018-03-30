package btn.listener.Action;

import java.awt.Font;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import btn.Tool.PhotoSizeSet;
import btn.function.HeartFunction;
import GUI.Main;
import Log.loginsert;
import PIC.getImage;

public class HeartAction extends Thread{
	private HashMap<String, Object> bag ;
	public HeartAction(HashMap<String, Object> bag) {
		this.bag = bag ;
		
	}
	public void run (){
		Action();
		
	}
	private void Action(){
		if(((JLabel)bag.get("lblHeart2")).getName().equals("full")){//if value = like then value = not like
			Main.bag_ -- ;
			new loginsert(((JTextField) bag.get("TextID")).getText(),((String) bag.get("Name")),"not like").start();
			new HeartFunction(bag,"RM").start();
			((JLabel)bag.get("lblHeart2")).setIcon(new ImageIcon(new getImage("love.png").getImageF()));
			((JLabel)bag.get("lblHeart2")).setName("love");
		}else if(((JLabel)bag.get("lblHeart2")).getName().equals("love")){
			new loginsert(((JTextField) bag.get("TextID")).getText(),((String) bag.get("Name")),"like").start();
			/*((HashMap<String,JButton>)bag.get("car3")).get(String.valueOf(Main.bag_%3)).setIcon(
					new PhotoSizeSet().getphoto(((HashMap<String, byte[]>)bag.get("carphoto")).get(((JLabel)bag.get("lblCarName")).getText())
							, 70, 39)
					);
			((String[])bag.get("bagIII"))[Main.bag_%3] = ((JLabel)bag.get("lblCarName")).getText();
			Main.bag_ ++;*/
			Main.bag_ ++ ;
			((JLabel)bag.get("lblHeart2")).setIcon(new ImageIcon(new getImage("full love.png").getImageF()));
			new HeartFunction(bag,"ADD").start();
			((JLabel)bag.get("lblHeart2")).setName("full");
		}
		if(Main.bag_==0){
			((JButton)bag.get("btnOpencar")).setIcon(new ImageIcon(new getImage("gift.png").getImageF()));
			((JButton)bag.get("btnOpencar")).setName("CLOSE");
			new FinalAction(bag,"COLSE").start();
			}
		if(Main.bag_==1){
			((JButton)bag.get("btnOpencar")).setIcon(new ImageIcon(new getImage("gift2.png").getImageF()));
		}
		
		((JPanel)bag.get("plAll")).repaint();
	}
}
