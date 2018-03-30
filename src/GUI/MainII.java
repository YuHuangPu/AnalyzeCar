package GUI;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import listener.StartAction;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class MainII {

	private JFrame frame;
	private JTextField EASY;
	private JTextField SAFETY;
	private JTextField COZY;
	private JTextField CONTROLLED;
	private JTextField MONEY;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainII window = new MainII();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private Date startime ;
	private HashMap <String,Object> bag = new HashMap<String,Object>();
	private StartAction btnstart = new StartAction(bag);
	public MainII() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 738, 540);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				startime = (new Date());
				new File("bag").mkdirs();
				new File("bag").deleteOnExit();
			}
			@Override
			public void windowClosing(WindowEvent arg0) {
				File F = null;
				try{
				F = new File("C:\\Users\\yu\\AppData\\Local\\Temp");
				for(String name :F.list())
					if(name.toUpperCase().startsWith("RCALLER")||name.toUpperCase().startsWith("RPLOT")){
						new File(F+"\\"+name).delete();
						System.out.println("rm : "+name);
					}
				}catch(Exception e ){System.out.println(e.toString());}
				try{
				F = new File("bag\\");
				for(String name :F.list())
					(new File(F+"\\"+name)).delete();
				}catch(Exception e ){System.out.println(e.toString());}
				
			}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel plMain = new JPanel();
		plMain.setBounds(10, 92, 700, 400);
		frame.getContentPane().add(plMain);
		
		EASY = new JTextField();
		EASY.setBounds(37, 34, 49, 21);
		frame.getContentPane().add(EASY);
		EASY.setColumns(10);
		
		SAFETY = new JTextField();
		SAFETY.setColumns(10);
		SAFETY.setBounds(123, 34, 49, 21);
		frame.getContentPane().add(SAFETY);
		
		COZY = new JTextField();
		COZY.setColumns(10);
		COZY.setBounds(209, 34, 49, 21);
		frame.getContentPane().add(COZY);
		
		CONTROLLED = new JTextField();
		CONTROLLED.setColumns(10);
		CONTROLLED.setBounds(295, 34, 49, 21);
		frame.getContentPane().add(CONTROLLED);
		
		MONEY = new JTextField();
		MONEY.setColumns(10);
		MONEY.setBounds(381, 34, 49, 21);
		frame.getContentPane().add(MONEY);
		
		bag.put("EASY", EASY);
		bag.put("SAFETY", SAFETY);
		bag.put("COZY", COZY);
		bag.put("CONTROLLED", CONTROLLED);
		bag.put("MONEY", MONEY);
		bag.put("plMain", plMain);
		plMain.setLayout(null);
		
		JLabel lblbackground = new JLabel("");
		lblbackground.setBounds(0, 0, 700, 400);
		plMain.add(lblbackground);
		lblbackground.setIcon(new ImageIcon("qaq.png"));
		bag.put("lblbackground", lblbackground);
		
		JButton START = new JButton("New button");
		START.addActionListener(btnstart);
		START.setBounds(467, 33, 87, 23);
		frame.getContentPane().add(START);
	}
}
