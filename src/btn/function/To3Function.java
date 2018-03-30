package btn.function;

import javax.swing.JPanel;

import Rcaller.RConnect;
import Tool.btnOpaqueClose;
import btn.listener.btnAction;
import datatool.Connect;
import datatool.Sql;

import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;

import PIC.getImage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class To3Function extends JPanel {
	private HashMap<String, Object> bag;
	/**
	 * Create the panel.
	 */
	public To3Function() {
		setBackground(new Color(0, 0, 0));
		page3();
	}
	public To3Function(HashMap<String, Object> bag) {
		this.bag = bag;page3();
		this.setOpaque(false);
	}
	private void page3(){
		setBounds(0, 0, 710, 400);
		setLayout(null);
		Color titlecolor = new Color(245,245,245);
		Double [] grade = (Double [])bag.get("Grade");
		String name = (String)bag.get("Name");
		Double money = 0.0;
		Connect c = new Connect();
		c.Connect("mysql");
		Sql CMD = new Sql(c.getConnect());
		try {
			CMD.setSQL("select 價錢 from allcar where concat(年分,' ',品牌,' ',款式) = '"+name+"'");
			CMD.getResultSet().next();
			money = CMD.getResultSet().getDouble(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			c.close();
			CMD.sqlClose();
			c = null;
			CMD = null;
		}
		
		JButton btnFore = new JButton("\uF085\uF085");
		btnFore.addActionListener(new btnAction(bag,"1"));
		new btnOpaqueClose(btnFore,((JPanel)bag.get("plAll")));
		btnFore.setActionCommand("btnFore");
		btnFore.setForeground(Color.WHITE);
		btnFore.setFont(new Font("Wingdings 3", Font.PLAIN, 20));
		btnFore.setBorderPainted(false);
		btnFore.setBackground(Color.BLACK);
		btnFore.setBounds(10, 367, 70, 23);
		add(btnFore);
		
		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setBounds(419, 10, 120, 75);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label_2 = new JLabel("\u2764");
		label_2.setBounds(0, 0, 120, 75);
		panel_2.add(label_2);
		label_2.setForeground(new Color(255, 0, 0));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("MS Gothic", Font.PLAIN, 55));
		
		JLabel label_1 = new JLabel("\u2764");
		label_1.setBounds(0, 0, 120, 75);
		panel_2.add(label_1);
		label_1.setForeground(new Color(255, 255, 0));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("MS Gothic", Font.PLAIN, 75));
		
		JLabel lblTitleII = new JLabel("\u2764");
		lblTitleII.setBounds(0, 0, 120, 75);
		panel_2.add(lblTitleII);
		lblTitleII.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleII.setFont(new Font("MS Gothic", Font.PLAIN, 99));
		lblTitleII.setForeground(new Color(255,0,0));
		Double heartgrade = 100*(grade[0]*0.178+grade[1]*0.224+grade[2]*0.205+grade[3]*0.204+grade[4]*0.19);
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setForeground(new Color(0, 255, 255));
		//String.valueOf(100*(grade[0]*0.178+grade[1]*0.224+grade[2]*0.205+grade[3]*0.204+grade[4]*0.19)).substring(0, 4)
		//
		lblNewLabel_1.setText(String.valueOf(heartgrade).substring(0, 4));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblNewLabel_1.setFont(new Font("Brush Script MT", Font.PLAIN, 80));
		lblNewLabel_1.setBounds(502, 10, 198, 80);
		add(lblNewLabel_1);
		String [] title5name = {"\u4FBF \u5229 ", "\u5B89 \u5168 ", "\u8212 \u9069 ","\u64CD \u4F5C ","\u7D93 \u6FDF "};
		for(int I = 0 ; I<5 ;I++){
			JPanel panel_1 = new JPanel();
			panel_1.setOpaque(false);
			panel_1.setBounds(442, 165+(I*51), 258, 23);
			add(panel_1);
			panel_1.setLayout(null);
			panel_1.setOpaque(false);
			
			JLabel lblEasy = new JLabel(title5name[I]+"\u6027 :");
			lblEasy.setBounds(0, 0, 80, 21);
			panel_1.add(lblEasy);
			lblEasy.setHorizontalAlignment(SwingConstants.CENTER);
			lblEasy.setForeground(titlecolor);
			lblEasy.setFont(new Font("微軟正黑體", Font.BOLD, 15));
			Color ishigt = (grade)[I]*100 >=90 ? Color.red : Color.white;
			if((grade)[I]*100<=40)ishigt = new Color(255,255,255,100); 
			JLabel label_15 = new JLabel("\u25A0");
			label_15.setForeground(ishigt);
			label_15.setFont(new Font("MS Gothic", Font.PLAIN, 12));
			label_15.setBounds(195, 2, 12, 21);
			panel_1.add(label_15);
			
			JPanel panel = new JPanel();
			panel.setBounds(82, 2, 118, 21);
			panel_1.add(panel);
			panel.setBorder(new LineBorder(ishigt == Color.red? Color.red:Color.white , 2, true));
			panel.setOpaque(false);
			panel.setBackground(Color.BLUE);
			panel.setLayout(null);
			
				for(int i =0 ; i<(int)((grade)[I]*10);i++){
					JLabel lblNewLabel_3 = new JLabel("\u2588");
					lblNewLabel_3.setForeground(ishigt);
					lblNewLabel_3.setBounds((i+1)*10, 0, 6, 21);
					panel.add(lblNewLabel_3);
					lblNewLabel_3.setFont(new Font("MS Gothic", Font.PLAIN, 12));
					if(i==(int)((grade)[I]*10)-1&&(Double.valueOf(String.valueOf(grade[I]*100).substring(1,2)))>=5){
						JLabel lblNewLabel = new JLabel("\u258B");
						lblNewLabel.setForeground(ishigt);
						lblNewLabel.setBounds((i+2)*10, 0, 6, 21);
						panel.add(lblNewLabel);
						lblNewLabel.setFont(new Font("MS Gothic", Font.PLAIN, 12));
					}
				}
			
			JLabel lblNewLabel_2 = new JLabel(String.valueOf((grade)[I]*100.0).substring(0, 4));
			lblNewLabel_2.setBounds(203, 4, 53, 21);
			panel_1.add(lblNewLabel_2);
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 20));
			lblNewLabel_2.setForeground((ishigt == Color.red ?new Color(255,0, 0):Color.yellow));
		}
		((JPanel)bag.get("plSubMain")).add(this);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(new getImage("piggy-bank.png").getImageF()));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.RED);
		label.setFont(new Font("MS Gothic", Font.PLAIN, 70));
		label.setBounds(447, 90, 65, 65);
		add(label);
		
		JLabel lblw = new JLabel();
		String M = String.valueOf(money == 0.0 ? 1234.56 :money);
		// money==Math.round(money)? Math.round(money) : money
		if(Math.round(Double.valueOf(M)) == money)
			lblw.setText(Math.round(Double.valueOf(M)) + " w");
		else
			lblw.setText(M + " w");
		lblw.setHorizontalAlignment(SwingConstants.CENTER);
		lblw.setForeground(new Color(221,170,0));
		lblw.setFont(new Font("Brush Script MT", Font.BOLD, 45));
		lblw.setBounds(502, 110, 198, 40);
		add(lblw);
		
		JLabel lblPhoto = new JLabel(new ImageIcon("bag/"+name+".png"));
		lblPhoto.setBounds(10, 10, 422, 418);
		add(lblPhoto);
	}
	
}
