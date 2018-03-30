package Tool;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import datatool.Connect;
import datatool.Sql;

public class textLimit implements KeyListener, CaretListener, MouseListener, ItemListener, FocusListener {
	private JTextField Text ;
	private HashMap<String, Object> bag;
	private boolean T = false ;
	public textLimit(JTextField Text) {
		this.Text = Text;
	}
	public textLimit(JTextField Text, HashMap<String, Object> bag) {
		this.Text = Text;
		this.bag = bag;
	}
	public textLimit(HashMap<String, Object> bag) {
		this.bag = bag;
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
		if(Text.getName().equals("GRADE")){
			if(arg0.getKeyCode()==8)T = true;
			else T = false;
		}
	}
	public void caretUpdate(CaretEvent e) {
		((JPanel)(bag.get("plAll"))).repaint();
		if(Text.getName().equals("GRADE")){
			
			boolean TAG = 
					!((JTextField)bag.get("EASY")).getText().equals("")&&
					!((JTextField)bag.get("SAFETY")).getText().equals("")&&
					!((JTextField)bag.get("COZY")).getText().equals("")&&
					!((JTextField)bag.get("CONTROLLED")).getText().equals("")&&
					!((JTextField)bag.get("MONEY")).getText().equals("")
					;
			if(Text.isEnabled())
			if(TAG&&Text.getText().length()>=1){
				seartch();
			}
			else if(TAG||!Text.getText().equals("")) {
				((JLabel)bag.get("lblCount")).setText("       尚   有   空   白");
				((JLabel)bag.get("lblCount_")).setText("");
				((JButton)bag.get("btnSend")).setEnabled(false);
				((JLabel)bag.get("lblCount_")).setForeground(Color.red);
				((JLabel)bag.get("lblCount")).setForeground(Color.red);
			}
			else {
				((JLabel)bag.get("lblCount_")).setForeground(Color.red);
				((JLabel)bag.get("lblCount")).setForeground(Color.red);
				((JLabel)bag.get("lblCount")).setText("       正   在   輸   入");
				((JLabel)bag.get("lblCount_")).setText("");
				((JButton)bag.get("btnSend")).setEnabled(false);}
		}else if(Text.getName().startsWith("MONEY")){
			
			if(Text.getText().length()>1&&Text.isEnabled()){
				seartch();
			}
		}else if(Text.getName().startsWith("CC")){
			
			if(Text.getText().length()>3&&Text.isEnabled()){
				seartch();
			}
		}
    	
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		if(Text.getName().equals("GRADE")){
			int keyChar = arg0.getKeyChar();
	        if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9 && Text.getText().length()<2){  
	        	
	        }else if(Text.getText().length() == 3){
	        	keyReleased(arg0);
	        	if(T)arg0.consume();}
	        else {  
	        	
	        	arg0.consume();
	        }
		}
	}
	private void seartch(){
		Double [] grade =null;
		
		try{
		grade  = new Double[]{
				 Double.valueOf(((JTextField)bag.get("EASY")).getText().equals("")?"0":((JTextField)bag.get("EASY")).getText())
				,Double.valueOf(((JTextField)bag.get("SAFETY")).getText().equals("")?"0":((JTextField)bag.get("SAFETY")).getText())
				,Double.valueOf(((JTextField)bag.get("COZY")).getText().equals("")?"0":((JTextField)bag.get("COZY")).getText())
				,Double.valueOf(((JTextField)bag.get("CONTROLLED")).getText().equals("")?"0":((JTextField)bag.get("CONTROLLED")).getText())
				,Double.valueOf(((JTextField)bag.get("MONEY")).getText().equals("")?"0":((JTextField)bag.get("MONEY")).getText())};;
		}catch(Exception e){
			grade = null;
		}
		if(grade!=null){
			Connect c = new Connect();
			c.Connect("mysql");
			Sql CMD = new Sql(c.getConnect());
			String make = (((JComboBox<String>)bag.get("cBoxMake")).getSelectedItem().toString());
			String cbCarSize = (((JComboBox<String>)bag.get("cbcarlist"+0)).getSelectedItem().toString().trim());
			String cbCarPower = (((JComboBox<String>)bag.get("cbcarlist"+1)).getSelectedItem().toString());
			String cbCarDrive = (((JComboBox<String>)bag.get("cbcarlist"+2)).getSelectedItem().toString());
			String cbCarPeople = (((JComboBox<String>)bag.get("cbcarlist"+3)).getSelectedItem().toString());
			String max = ((JTextField)bag.get("MONEYMAX")).getText(),
					min = ((JTextField)bag.get("MONEYMIN")).getText();
			String ccmax = ((JTextField)bag.get("CCMAX")).getText(),
					ccmin = ((JTextField)bag.get("CCMIN")).getText();
			searchLimit.setLimit(grade,
					make.equals("廠牌　　")?null:("'"+make+"'")
							, max,min,
							cbCarSize.equals("車身型式")?null:(cbCarSize),
							cbCarPower.equals("動力型式")?null:(cbCarPower),
							cbCarDrive.equals( "驅動型式")?null:(cbCarDrive),
							cbCarPeople.equals("座位數　")?null:(cbCarPeople),
							ccmax,ccmin
							);
			String sql = searchLimit.getSQL("c");
			try {
				CMD.setSQL(sql);
				CMD.getResultSet().next();
				int count = CMD.getResultSet().getInt(1);
				((JLabel)bag.get("lblCount_")).setText(String.valueOf(count));
				((JLabel)bag.get("lblCount")).setText("目 前 數 量   : ");
				if(count>=3&&count<=120){
					((JButton)bag.get("btnSend")).setEnabled(true);
					((JLabel)bag.get("lblCount_")).setForeground(Color.GREEN);
					((JLabel)bag.get("lblCount")).setForeground(Color.GREEN);
				}else{
					((JButton)bag.get("btnSend")).setEnabled(false);
					((JLabel)bag.get("lblCount_")).setForeground(Color.red);
					((JLabel)bag.get("lblCount")).setForeground(Color.red);
				}
			} catch (Exception e1) {
				((JButton)bag.get("btnSend")).setEnabled(false);
			} finally{
				c.close();
				CMD.sqlClose();
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(Text.getName().equals("ID")){
			if(Text.isEnabled()){
				Text.setText("");
				((JPanel)(bag.get("plAll"))).repaint();
			}
		}else if(Text.getName().startsWith("MONEY")){
			if(Text.isEnabled()){
				Text.setText("");
				((JPanel)(bag.get("plAll"))).repaint();
			}
		}else if(Text.getName().startsWith("CC")){
			if(Text.isEnabled()){
				Text.setText("");
				((JPanel)(bag.get("plAll"))).repaint();
			}
		}else if(Text.getName().startsWith("GRADE")){
			if(Text.isEnabled()){
				Text.setText("");
				((JPanel)(bag.get("plAll"))).repaint();
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange()==1){seartch();}
	}
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void focusLost(FocusEvent arg0) {
		if(Text.getText().equals("")&&Text.getName().indexOf("MAX")==-1){
			Text.setText("0");
		}else if(Text.getText().equals("")&&Text.getName().indexOf("MAX")>=0){
			Text.setText("9999");
		}
	}

}
