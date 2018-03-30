package btn.listener.Action;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;

import Tool.btnOpaqueClose;
import btn.function.ShowPhotoFunction;
import btn.listener.btnAction;

public class ShowPhotoAction extends Thread{
	private HashMap<String, Object> bag ;
	public ShowPhotoAction(HashMap<String, Object> bag) {
		this.bag = bag;
	}
	
	public void run (){Action();}
	private void Action(){
		
		((HashMap<String, JPanel>)bag.get("PageStatus")).put("PageAll",(JPanel) bag.get("plAll"));
		int [] plAll = {((JPanel) bag.get("plAll")).getX(),((JPanel) bag.get("plAll")).getY(),((JPanel) bag.get("plAll")).getWidth(),((JPanel) bag.get("plAll")).getHeight()};
		while(((JPanel) bag.get("plAll")).getX()!=930){
			((JPanel) bag.get("plAll")).setBounds(((JPanel) bag.get("plAll")).getX()+1, plAll[1], plAll[2], plAll[3]);
			((JPanel) bag.get("ALL")).repaint();
			try {
				sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		((JPanel) bag.get("ALL")).removeAll();
		((JPanel) bag.get("ALL")).add(((JPanel) bag.get("plPhotoBtns")));
		((JPanel) bag.get("plPhotoBtns")).getComponents()[0].setBounds(0, 0, 70, 480);
		((JPanel) bag.get("plPhotoBtns")).setBounds(884, -515, 70, 480);
		
		final JButton btnBack = new JButton("\uF085\uF085");
		final JButton btnUp = new JButton("\uF082");
		btnUp.addMouseListener(new MouseListener(){

			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(!btnUp.getText().equals("ƒñ"))
					btnUp.doClick();
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}});
		btnUp.setFont(new Font("Wingdings 3", Font.PLAIN, 20));
		btnUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ShowPhotoFunction(bag,btnUp,btnBack,"UpDown");
			}});
		btnUp.setForeground(new Color(0,0,0,100));
		btnUp.setBorderPainted(false);
		new btnOpaqueClose(btnUp,((JPanel) bag.get("ALL")));
		btnUp.setBackground(Color.BLACK);
		btnUp.setBounds(884, 0, 70, 23);
		((JPanel) bag.get("ALL")).add(btnUp);
		bag.put("btnUpDown", btnUp);
		
		
		btnBack.setFont(new Font("Wingdings 3", Font.PLAIN, 20));
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ShowPhotoFunction(bag,btnUp,btnBack,"Back");
			}});
		btnBack.setForeground(Color.WHITE);
		btnBack.setBorderPainted(false);
		btnBack.setBackground(Color.BLACK);
		btnBack.setBounds(884, -23, 70, 23);
		((JPanel) bag.get("ALL")).add(btnBack);
		
		((JPanel) bag.get("ALL")).repaint();
		btnUp.doClick();
	}
}
