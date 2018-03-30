package btn.function;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Tool.Slide;
import Tool.btnOpaqueClose;
import btn.Tool.PhotoSizeSet;
import btn.listener.btnAction;
import btn.listener.Action.ForeAction;
import javax.swing.SwingConstants;

import GUI.Main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StarFunction extends JPanel {
	private HashMap <String,Object> bag ;
	private boolean DEMOphoto = true;
	private PhotoSizeSet Photo;

	/**
	 * Create the panel.
	 */
	public StarFunction(HashMap <String,Object> bag ,String Name,Double[]Grade,PhotoSizeSet Photo) {
		this.Photo = Photo;
		this.bag = bag;
		PhotoPage(Name,Grade);
		((HashMap<String,JPanel>)bag.get("PageStatus")).put("PAGE2", this);
	}
	private void PhotoPage(String Name,Double[]Grade){
		setLayout(null);
		setOpaque(false);
		setBounds(0, 0, 710, 400);
		JLabel lblShowPhoto = new JLabel("\uF064\uF063\uF064\uF063\uF064\uF063\uF064\uF063\uF064\uF063\uF064\uF063\uF064\uF063\uF064\uF063\uF064\uF063\uF064\uF063\uF064\uF063\uF064\uF063\uF064\uF063");
		lblShowPhoto.addMouseListener(new btnAction(bag,null,null,"ShowPhoto"));
		lblShowPhoto.setForeground(Color.WHITE);
		lblShowPhoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowPhoto.setFont(new Font("Wingdings 2", Font.PLAIN, 20));
		lblShowPhoto.setBounds(90, 370, 530, 23);
		bag.put("lblShowPhoto", lblShowPhoto);
		add(lblShowPhoto);
		//if(DEMOphoto)
			//Photo = new PhotoSizeSet(Name);
		bag.put("Grade", Grade);
		bag.put("Name", Name);
		JPanel plMainPhoto = new JPanel();
		plMainPhoto.setBackground(Color.WHITE);
		plMainPhoto.setBounds(10, 10, 610, 350);
		this.add(plMainPhoto);
		plMainPhoto.setLayout(null);
		
		final JLabel lblMainPhoto = new JLabel();
		if(DEMOphoto){
			//((JLabel)bag.get("lblBackground")).setIcon(Photo.getphoto(0));
			lblMainPhoto.setIcon(Photo.getphoto(0, 600, 337));
		}
		else
			lblMainPhoto.setText("DEMO CLOSE");
		lblMainPhoto.setBounds(5, 7, 600, 337);
		plMainPhoto.add(lblMainPhoto);
		
		JPanel plPhotoBtns = new JPanel();
		plPhotoBtns.setOpaque(false);
		plPhotoBtns.setBounds(630, 43, 70, 284);
		this.add(plPhotoBtns);
		plPhotoBtns.setLayout(null);
		
		final JPanel plSubPhotoBtns = new JPanel();
		plSubPhotoBtns.setOpaque(false);
		plSubPhotoBtns.setBackground(new Color(0,0,0,0));
		plSubPhotoBtns.setBounds(0, 0, 70, 480);
		plPhotoBtns.add(plSubPhotoBtns);
		plSubPhotoBtns.setLayout(null);
		bag.put("plPhotoBtns", plPhotoBtns);
		
		for(int i = 0,j=0 ; i<10 ; i++,j=j+49){
			final JButton btnPhoto = new JButton();
			if(DEMOphoto)
				btnPhoto.setIcon(Photo.getphoto(i, 70, 39));
			else
				btnPhoto.setText(String.valueOf(i));
			btnPhoto.setActionCommand(String.valueOf(i));
			btnPhoto.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(bag.get("btnUpDown")!=null){
						((JButton)bag.get("btnUpDown")).doClick();
					}
					if(DEMOphoto){ 
						((JLabel)bag.get("lblBackground")).setIcon(Photo.getphoto(Integer.valueOf(btnPhoto.getActionCommand())));
						lblMainPhoto.setIcon(Photo.getphoto(Integer.valueOf(btnPhoto.getActionCommand()), 600, 337));
					}else lblMainPhoto.setText(btnPhoto.getActionCommand());
				}});
			btnPhoto.setBounds(0, j, 70, 39);
			plSubPhotoBtns.add(btnPhoto);
		}
		
		JButton btnup = new JButton("ƒñ");
		btnup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(plSubPhotoBtns.getY()<0)
					plSubPhotoBtns.setBounds(0, plSubPhotoBtns.getY()+49, 70, 480);
				((JPanel)bag.get("plAll")).repaint();
			}
		});
		btnup.setBorderPainted(false);
		btnup.setForeground(Color.WHITE);
		btnup.setBackground(Color.BLACK);
		btnup.setFont(new Font("Wingdings 3", Font.PLAIN, 20));
		btnup.setBounds(630, 10, 70, 23);
		this.add(btnup);
		
		JButton btndown = new JButton("\uF082");
		btndown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(plSubPhotoBtns.getY()>-49*4)
					plSubPhotoBtns.setBounds(0, plSubPhotoBtns.getY()-49, 70, 480);
				((JPanel)bag.get("plAll")).repaint();
			}
		});
		btndown.setBorderPainted(false);
		btndown.setBackground(Color.BLACK);
		btndown.setForeground(Color.WHITE);
		btndown.setFont(new Font("Wingdings 3", Font.PLAIN, 20));
		btndown.setBounds(630, 337, 70, 23);
		this.add(btndown);
		Slide 
		Slide = new Slide(((JPanel)bag.get("plAll")),"re");
		Slide.start();
		
		
		
		JButton btnNext = new JButton("\uF086\uF086");
		btnNext.setActionCommand("btnTo3");
		btnNext.addActionListener(new btnAction(bag));
		btnNext.setForeground(Color.WHITE);
		btnNext.setFont(new Font("Wingdings 3", Font.PLAIN, 20));
		btnNext.setBorderPainted(false);
		btnNext.setBackground(Color.BLACK);
		btnNext.setBounds(630, 370, 70, 23);
		add(btnNext);

		
		((JPanel)bag.get("plSubMain")).add(this);
		
		JButton btnFore = new JButton("\uF085\uF085");
		btnFore.addActionListener(new btnAction(bag,"1"));
		btnFore.setActionCommand("btnFore");
		btnFore.setForeground(Color.WHITE);
		btnFore.setFont(new Font("Wingdings 3", Font.PLAIN, 20));
		btnFore.setBorderPainted(false);
		btnFore.setBackground(Color.BLACK);
		btnFore.setBounds(10, 370, 70, 23);
		add(btnFore);
		((JPanel)bag.get("plMain")).repaint();
	}
}
