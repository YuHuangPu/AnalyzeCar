package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;

public class CarValue extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CarValue dialog = new CarValue();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CarValue() {
		setBounds(100, 100, 1120, 500);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 10, 510, 287);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(20, 307, 90, 50);
		getContentPane().add(btnNewButton);
		
		JButton button = new JButton("New button");
		button.setBounds(120, 307, 90, 50);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("New button");
		button_1.setBounds(320, 307, 90, 50);
		getContentPane().add(button_1);
		
		JButton button_2 = new JButton("New button");
		button_2.setBounds(220, 307, 90, 50);
		getContentPane().add(button_2);
		
		JButton button_3 = new JButton("New button");
		button_3.setBounds(420, 307, 90, 50);
		getContentPane().add(button_3);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(530, 10, 450, 450);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(10, 367, 500, 30);
		getContentPane().add(lblNewLabel_2);
	}
}
