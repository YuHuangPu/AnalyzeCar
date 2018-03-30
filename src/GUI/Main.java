package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Rcaller.RConnect;
import Tool.Slide;
import Tool.btnOpaqueClose;
import Tool.textLimit;
import btn.listener.btnAction;
import btn.listener.Action.StarAction;
import datatool.Connect;
import datatool.Sql;
import listener.StartAction;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import GUI.repaint.repaint;
import Log.Log;
import Log.loginsert;
import PIC.getImage;

import javax.swing.UIManager;
import java.awt.Window.Type;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.MaskFormatter;
import javax.swing.event.CaretEvent;
import javax.swing.border.LineBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import javax.swing.JCheckBox;
import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeEvent;
import btn.Tool.area5BT;
import java.awt.Component;
import java.awt.Toolkit;
import javax.swing.DropMode;
import javax.imageio.ImageIO;
import javax.swing.DebugGraphics;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Main {
	public static int bag_ = 0;
	public static boolean star_ = true;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		initialize();
		Log.show("Start .. ");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private HashMap<String, Object> bag;
	private btnAction btnAction;
	private Date startime;
	private Color dis = new Color(0, 0, 0, 0);
	private HashMap<String, JPanel> PageStatus;
	private JTextField textID;
	private JTextField CCMAX;
	private JTextField CCMIN;

	private void initialize() {
		PageStatus = new HashMap<String, JPanel>();

		bag = new HashMap<String, Object>();
		btnAction = new btnAction(bag);
		frame = new JFrame();
		frame.setIconImage(
				Toolkit.getDefaultToolkit().getImage("D:\\yu.huang\\Desktop\\yupro\\MyBeta160325\\15LOGO1.png"));
		frame.setName("MyShow");
		frame.setBackground(Color.WHITE);
		bag.put("FRAME", frame.getContentPane());
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				startime = (new Date());
				new File("bag").mkdirs();
				new File("bag").deleteOnExit();
				new loginsert("ROOT", "OPEN", "OPEN").start();
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				new loginsert("ROOT", "CLOSE", "CLOSE").start();
				File F = null;
				try {
					F = new File("C:\\Users\\S201\\AppData\\Local\\Temp");
					for (String name : F.list())
						if (name.toUpperCase().startsWith("RCALLER") || name.toUpperCase().startsWith("RPLOT")) {
							new File(F + "\\" + name).delete();
							Log.show("Remove(" + F + "\\" + name + ")");
						}
				} catch (Exception e) {
					System.out.println(e.toString());
				}
				try {
					F = new File("bag\\");
					for (String name : F.list()) {
						(new File(F + "\\" + name)).delete();
						Log.show("Remove(" + F + "\\" + name + ")");
					}
				} catch (Exception e) {
					System.out.println(e.toString());
				}
				Log.show("Stop .. ");
			}
		});
		frame.setBounds(0, 100, 1016, 596);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel ALL = new JPanel();
		ALL.setOpaque(false);
		ALL.setBounds(36, 10, 954, 538);
		frame.getContentPane().add(ALL);
		ALL.setLayout(null);
		bag.put("ALL", ALL);

		JPanel plAll = new JPanel();
		plAll.setOpaque(false);
		plAll.setBounds(0, 0, 930, 528);
		ALL.add(plAll);
		bag.put("plAll", plAll);
		plAll.setLayout(null);

		JPanel plMeun = new JPanel();
		plMeun.setOpaque(false);
		plMeun.setBounds(10, 71, 190, 400);
		bag.put("plMeun", plMeun);
		plAll.add(plMeun);
		plMeun.setBackground(new Color(0, 0, 0, 100));
		plMeun.setLayout(null);

		JPanel plSubMeun = new JPanel();
		plSubMeun.setBackground(Color.BLACK);
		plSubMeun.setBorder(new LineBorder(Color.WHITE, 3));
		plSubMeun.setOpaque(false);
		plSubMeun.setBounds(10, 43, 168, 347);
		plMeun.add(plSubMeun);
		plSubMeun.setLayout(null);
		bag.put("plSubMeun", plSubMeun);

		JPanel plMeunValues = new JPanel();
		plMeunValues.setOpaque(false);
		plMeunValues.setBounds(10, 10, 148, 296);
		plSubMeun.add(plMeunValues);
		plMeunValues.setLayout(null);
		bag.put("plMeunValues", plMeunValues);

		JPanel plValueGrade = new JPanel();
		plValueGrade.setOpaque(false);
		plValueGrade.setBounds(0, 0, 148, 296);
		plMeunValues.add(plValueGrade);
		plValueGrade.setLayout(null);
		bag.put("plValueGrade", plValueGrade);

		JPanel plMakeList = new JPanel();
		bag.put("plMakeList", plMakeList);
		plMakeList.setOpaque(false);
		plMakeList.setBounds(0, 0, 148, 24);
		plValueGrade.add(plMakeList);
		plMakeList.setLayout(null);

		JComboBox cBoxMake = new JComboBox();
		cBoxMake.setBounds(0, 0, 148, 24);
		plMakeList.add(cBoxMake);
		bag.put("cBoxMake", cBoxMake);
		cBoxMake.addItemListener(new textLimit(bag));
		cBoxMake.setAutoscrolls(true);
		cBoxMake.setOpaque(false);
		cBoxMake.setModel(new DefaultComboBoxModel(MakeList()));
		cBoxMake.setForeground(Color.WHITE);
		cBoxMake.setFont(new Font("微軟正黑體", Font.PLAIN, 15));
		cBoxMake.setBorder(null);
		cBoxMake.setBackground(Color.BLACK);

		JLabel lblMakeList = new JLabel();
		lblMakeList.setHorizontalAlignment(SwingConstants.CENTER);
		lblMakeList.setName((String) cBoxMake.getSelectedItem());
		lblMakeList.setForeground(new Color(255, 255, 255));
		lblMakeList.setFont(new Font("微軟正黑體", Font.PLAIN, 15));
		lblMakeList.setBounds(0, 0, 148, 24);
		bag.put("lblMakeList", lblMakeList);

		JPanel plChose = new JPanel();
		plChose.setOpaque(false);
		plChose.setBounds(0, 39, 148, 185);
		plValueGrade.add(plChose);
		plChose.setLayout(null);
		bag.put("plChose", plChose);

		JPanel plSupChoseB = new JPanel();
		plSupChoseB.setName("plSupChoseB");
		plSupChoseB.setBounds(0, 0, 148, 185);
		plSupChoseB.setOpaque(false);
		plSupChoseB.setLayout(null);
		bag.put("plSupChoseB", plSupChoseB);
		String[][] cbList = { { "cbCarSize", "車身型式" }, { "cbCarPower", "動力型式" }, { "cbCarDrive", "驅動型式" },
				{ "cbCarPeople", "座位數　" } };
		String[] carsql = {
				"select distinct substring_index(a.車身型式,',',1) from allcar a order by length( substring_index(a.車身型式,',',1))",
				"select distinct a.動力型式 from allcar a order by length(a.動力型式)",
				"select distinct a.驅動型式 from allcar a order by 1",
				"select distinct a.座位數 from allcar a ORDER BY length(a.座位數) , a.座位數" };
		for (int i = 0; i < cbList.length; i++) {
			JComboBox cbCar = new JComboBox();
			cbCar.setModel(new DefaultComboBoxModel(CarList(cbList[i][1], carsql[i])));
			cbCar.addItemListener(new textLimit(bag));
			cbCar.setOpaque(false);
			cbCar.setForeground(Color.WHITE);
			cbCar.setFont(new Font("微軟正黑體", Font.PLAIN, 15));
			cbCar.setBorder(null);
			cbCar.setBackground(Color.BLACK);
			cbCar.setAutoscrolls(true);
			cbCar.setBounds(0, 0 + i * 39, 148, 24);
			bag.put("cbcarlist" + i, cbCar);
			plSupChoseB.add(cbCar);

			JLabel lblCarList = new JLabel((String) cbCar.getSelectedItem());
			lblCarList.setName((String) cbCar.getSelectedItem());
			lblCarList.setForeground(new Color(255, 255, 255));
			lblCarList.setFont(new Font("微軟正黑體", Font.PLAIN, 15));
			lblCarList.setHorizontalAlignment(SwingConstants.LEFT);
			lblCarList.setBounds(0, 0 + i * 39, 148, 24);
			bag.put("lblcarlist" + i, lblCarList);
		}

		JPanel plCC = new JPanel();
		plCC.setLayout(null);
		plCC.setOpaque(false);
		plCC.setBounds(0, 156, 148, 26);
		plSupChoseB.add(plCC);
		bag.put("plCC", plCC);

		JLabel lblCc = new JLabel("CC");
		lblCc.setHorizontalAlignment(SwingConstants.CENTER);
		lblCc.setForeground(Color.WHITE);
		lblCc.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));
		lblCc.setBounds(115, 2, 28, 24);
		plCC.add(lblCc);

		CCMAX = new JTextField("9999");
		CCMAX.setOpaque(false);
		CCMAX.addFocusListener(new textLimit(CCMAX,bag));
		CCMAX.setName("CCMAX");
		CCMAX.setHorizontalAlignment(SwingConstants.CENTER);
		CCMAX.setForeground(Color.WHITE);
		CCMAX.setFont(new Font("Monotype Corsiva", Font.PLAIN, 15));
		CCMAX.setBorder(new LineBorder(Color.WHITE, 2));
		CCMAX.setBounds(68, 3, 45, 21);
		plCC.add(CCMAX);

		JLabel label_1 = new JLabel("~");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Consolas", Font.PLAIN, 20));
		label_1.setBounds(40, 0, 28, 24);
		plCC.add(label_1);

		CCMIN = new JTextField("0");
		CCMIN.addFocusListener(new textLimit(CCMIN,bag));
		bag.put("CCMIN", CCMIN);
		bag.put("CCMAX", CCMAX);
		CCMIN.setOpaque(false);
		CCMIN.setName("CCMIN");
		CCMIN.setHorizontalAlignment(SwingConstants.CENTER);
		CCMIN.setForeground(Color.WHITE);
		CCMIN.setFont(new Font("Monotype Corsiva", Font.PLAIN, 15));
		CCMIN.setBorder(new LineBorder(Color.WHITE, 2));
		CCMIN.setBounds(0, 3, 45, 21);
		plCC.add(CCMIN);

		CCMIN.addCaretListener(new textLimit(CCMIN, bag));
		CCMIN.addKeyListener(new textLimit(CCMIN));
		CCMIN.addMouseListener(new textLimit(CCMIN, bag));
		CCMAX.addCaretListener(new textLimit(CCMAX, bag));
		CCMAX.addKeyListener(new textLimit(CCMAX));
		CCMAX.addMouseListener(new textLimit(CCMAX, bag));

		String titlename[][] = { { "\u4FBF   \u5229   \u6027    :", "EASY" },
				{ "\u5B89   \u5168   \u6027    :", "SAFETY" }, { "\u8212   \u9069   \u6027    :", "COZY" },
				{ "\u64CD   \u4F5C   \u6027    :", "CONTROLLED" }, { "\u7D93   \u6FDF   \u6027    :", "MONEY" } };

		JPanel plSupChoseA = new JPanel();
		plSupChoseA.setName("plSupChoseA");
		plSupChoseA.setBounds(0, 0, 148, 185);
		plChose.add(plSupChoseA);
		plSupChoseA.setOpaque(false);
		plSupChoseA.setLayout(null);
		bag.put("plSupChoseA", plSupChoseA);

		for (int i = 0; i < titlename.length; i++) {
			JPanel plTitle = new JPanel();
			plTitle.setBounds(0, i * 39, 148, 24);

			plSupChoseA.add(plTitle);
			plTitle.setOpaque(false);
			plTitle.setBackground(dis);
			plTitle.setLayout(null);

			JLabel lblNewLabel_1 = new JLabel(titlename[i][0]);
			lblNewLabel_1.setForeground(Color.WHITE);
			lblNewLabel_1.setBounds(0, 0, 106, 21);
			plTitle.add(lblNewLabel_1);
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 15));

			JTextField titlevalue = new JTextField("0");
			titlevalue.addFocusListener(new textLimit(titlevalue,bag));
			titlevalue.setName("GRADE");
			titlevalue.setOpaque(false);
			titlevalue.setHorizontalAlignment(SwingConstants.CENTER);
			titlevalue.setBorder(null);
			titlevalue.addMouseListener(new textLimit(titlevalue, bag));
			titlevalue.addCaretListener(new textLimit(titlevalue, bag));
			titlevalue.addKeyListener(new textLimit(titlevalue));
			titlevalue.setSelectionColor(Color.WHITE);
			titlevalue.setSelectedTextColor(Color.BLACK);
			titlevalue.setFont(new Font("微軟正黑體", Font.PLAIN, 15));
			titlevalue.setForeground(Color.WHITE);
			titlevalue.setBackground(Color.WHITE);
			titlevalue.setBounds(106, 3, 42, 21);
			plTitle.add(titlevalue);
			bag.put(titlename[i][1], titlevalue);
		}

		JPanel plmonetset = new JPanel();
		plmonetset.setOpaque(false);
		plmonetset.setBounds(0, 236, 148, 26);
		plValueGrade.add(plmonetset);
		plmonetset.setLayout(null);

		final JLabel label_4 = new JLabel("W");
		bag.put("lblW", label_4);
		label_4.setForeground(new Color(255,0,0));
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {
					String pl = ((JPanel) bag.get("plChose")).getComponent(0).getName().equals("plSupChoseB")
							? "plSupChoseA" : "plSupChoseB";
					((JPanel) bag.get("plChose")).removeAll();
					((JPanel) bag.get("plChose")).add(((JPanel) bag.get(pl)));
					label_4.setForeground(pl.endsWith("A")?new Color(255,0,0):new Color(255,255,255));
					((JPanel) bag.get("plAll")).repaint();
				}
			}
		});
		label_4.setBounds(115, 2, 28, 24);
		plmonetset.add(label_4);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));

		JTextField MAX = new JTextField("9999");
		JTextField MIN = new JTextField("0");
		bag.put("MONEYMAX", MAX);
		bag.put("MONEYMIN", MIN);
		MIN.addFocusListener(new textLimit(MIN,bag));
		MAX.addFocusListener(new textLimit(MAX,bag));
		MIN.addCaretListener(new textLimit(MIN, bag));
		MIN.addKeyListener(new textLimit(MIN));
		MIN.addMouseListener(new textLimit(MIN, bag));
		MAX.addMouseListener(new textLimit(MAX, bag));
		MAX.addCaretListener(new textLimit(MAX, bag));
		MAX.addKeyListener(new textLimit(MAX));
		MIN.setName("MONEYMIN");
		MAX.setName("MONEYMAX");
		MAX.setBounds(68, 3, 45, 21);
		plmonetset.add(MAX);
		MAX.setOpaque(false);
		MAX.setHorizontalAlignment(SwingConstants.CENTER);
		MAX.setForeground(Color.WHITE);
		MAX.setFont(new Font("Monotype Corsiva", Font.PLAIN, 15));
		MAX.setBorder(new LineBorder(Color.WHITE, 2));

		JLabel label_5 = new JLabel("~");
		label_5.setBounds(40, 0, 28, 24);
		plmonetset.add(label_5);
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Consolas", Font.PLAIN, 20));

		MIN.setBounds(0, 3, 45, 21);
		plmonetset.add(MIN);
		MIN.setOpaque(false);
		MIN.setHorizontalAlignment(SwingConstants.CENTER);
		MIN.setForeground(Color.WHITE);
		MIN.setFont(new Font("Monotype Corsiva", Font.PLAIN, 15));
		MIN.setBorder(new LineBorder(Color.WHITE, 2));

		JPanel plCount = new JPanel();
		plCount.setBounds(0, 272, 148, 24);
		plValueGrade.add(plCount);
		plCount.setOpaque(false);
		plCount.setLayout(null);
		bag.put("plCount", plCount);

		JLabel lblCount = new JLabel("       \u6B61   \u8FCE   \u6307   \u6559");
		lblCount.setBounds(0, 0, 148, 24);
		plCount.add(lblCount);
		lblCount.setHorizontalAlignment(SwingConstants.LEFT);
		lblCount.setForeground(Color.RED);
		lblCount.setFont(new Font("微軟正黑體", Font.PLAIN, 15));
		bag.put("lblCount", lblCount);

		JLabel lblCount_ = new JLabel("");
		lblCount_.setBounds(105, 0, 43, 24);
		plCount.add(lblCount_);
		lblCount_.setHorizontalAlignment(SwingConstants.LEFT);
		lblCount_.setForeground(Color.RED);
		lblCount_.setFont(new Font("微軟正黑體", Font.PLAIN, 15));
		bag.put("lblCount_", lblCount_);

		JButton btnSend = new JButton("\u9001\u51FA") {
			protected void paintComponent(Graphics g) {
				if (getModel().isArmed()) {
					((JPanel) bag.get("plAll")).repaint();
				}
				super.paintComponent(g);
			}
		};
		btnSend.setBounds(89, 316, 69, 23);
		plSubMeun.add(btnSend);
		btnSend.setOpaque(false);
		btnSend.setBorderPainted(false);
		btnSend.setEnabled(false);
		btnSend.setForeground(Color.WHITE);
		new btnOpaqueClose(btnSend, plAll);
		btnSend.setBackground(Color.WHITE);
		btnSend.setActionCommand("btnSend");
		btnSend.addActionListener(btnAction);
		btnSend.setFont(new Font("微軟正黑體", Font.PLAIN, 15));
		bag.put("btnSend", btnSend);

		JButton btnClean = new JButton("\u6E05\u9664") {
			protected void paintComponent(Graphics g) {
				if (getModel().isArmed()) {
					((JPanel) bag.get("plAll")).repaint();
				}
				super.paintComponent(g);
			}
		};
		btnClean.setBounds(10, 316, 69, 23);
		plSubMeun.add(btnClean);
		btnClean.setOpaque(false);
		btnClean.addActionListener(new btnAction(bag));
		btnClean.setActionCommand("btnClean");
		btnClean.setBorderPainted(false);
		btnClean.setForeground(Color.WHITE);
		new btnOpaqueClose(btnClean, plAll);
		btnClean.setFont(new Font("微軟正黑體", Font.PLAIN, 15));
		btnClean.setBackground(Color.WHITE);
		bag.put("btnClean", btnClean);

		JLabel lblWaring = new JLabel("\u8ACB\u5148\u9078\u64C7\u559C\u597D\u8ECA\u8F1B");
		lblWaring.setForeground(new Color(0, 0, 0, 0));
		lblWaring.setFont(new Font("華康棒棒體W5", Font.BOLD, 20));
		lblWaring.setHorizontalAlignment(SwingConstants.CENTER);
		lblWaring.setBounds(10, 10, 170, 41);
		plMeun.add(lblWaring);
		bag.put("lblWaring", lblWaring);

		JPanel plMain = new JPanel();
		plMain.setOpaque(false);
		bag.put("plMain", plMain);
		plMain.setBounds(210, 71, 710, 400);
		plMain.setBackground(new Color(0, 0, 0, 0));
		plAll.add(plMain);
		plMain.setLayout(null);

		JPanel plSubMain = new JPanel();
		plSubMain.setOpaque(false);
		bag.put("plSubMain", plSubMain);
		plSubMain.setLayout(null);
		plSubMain.setBackground(new Color(0, 0, 0, 100));
		plSubMain.setBounds(0, 0, 710, 400);
		plMain.add(plSubMain);

		JPanel plPage0 = new JPanel();
		plPage0.setOpaque(false);
		plPage0.setBounds(0, 0, 710, 400);
		plSubMain.add(plPage0);
		plPage0.setLayout(null);

		JLabel lblBackground = new JLabel("");
		lblBackground.setOpaque(true);
		lblBackground.setBounds(0, 0, 1000, 562);
		frame.getContentPane().add(lblBackground);
		lblBackground.setBackground(new Color(105, 105, 105));
		lblBackground.setIcon(new ImageIcon(new getImage("QAQ2.jpg").getImageF()));
		bag.put("lblBackground", lblBackground);
		PageStatus.put("PAGE0", plPage0);

		JPanel plCarName = new JPanel();
		plCarName.setOpaque(false);
		plCarName.setBounds(149, 10, 771, 51);
		plAll.add(plCarName);
		plCarName.setForeground(new Color(245, 245, 245));
		plCarName.setBackground(new Color(0, 0, 0, 100));
		plCarName.setBorder(null);
		bag.put("plCarName", plCarName);
		plCarName.setLayout(null);

		JLabel lblHeart2 = new JLabel(); // \u2665\u2661
		lblHeart2.setName("love");
		// lblHeart2.setIcon(new ImageIcon("PIC\\love.png"));
		plCarName.add(lblHeart2);
		lblHeart2.setBounds(0, 0, 50, 50);
		lblHeart2.addMouseListener(new btnAction(bag, null, null, "Heart"));
		bag.put("lblHeart2", lblHeart2);

		JLabel lblCarName = new JLabel();
		lblCarName.setBounds(65, 0, 706, 51);
		plCarName.add(lblCarName);
		lblCarName.setForeground(new Color(255, 255, 0));
		lblCarName.setHorizontalAlignment(SwingConstants.LEFT);
		lblCarName.setFont(new Font("華康流風體W3", Font.BOLD, 35));
		bag.put("lblCarName", lblCarName);

		JPanel plUser = new JPanel();
		plUser.setOpaque(false);
		plUser.setBackground(new Color(0, 0, 0, 100));
		plUser.setBounds(10, 10, 129, 51);
		plAll.add(plUser);
		plUser.setLayout(null);
		bag.put("plUser", plUser);
		textID = new JTextField();
		textID.setBounds(10, 12, 62, 25);
		plUser.add(textID);
		textID.addMouseListener(new textLimit(textID, bag));
		textID.setName("ID");
		textID.setText("Welcome");
		textID.setBorder(null);
		textID.setOpaque(false);
		textID.setForeground(new Color(0, 255, 0));
		textID.setFont(new Font("華康棒棒體W5", Font.PLAIN, 15));
		textID.setHorizontalAlignment(SwingConstants.LEFT);
		textID.setColumns(10);
		bag.put("TextID", textID);

		JButton btnOpencar = new JButton() {
			protected void paintComponent(Graphics g) {
				if (getModel().isArmed()) {
					((JPanel) bag.get("plAll")).repaint();
				}
				super.paintComponent(g);
			}
		};
		btnOpencar.setBounds(84, 0, 45, 45);
		plUser.add(btnOpencar);
		bag.put("btnOpencar", btnOpencar);
		btnOpencar.setEnabled(false);
		btnOpencar.setActionCommand("likecar");
		btnOpencar.setName("OPEN");
		btnOpencar.addActionListener(btnAction);
		btnOpencar.setBorderPainted(false);
		btnOpencar.setBackground(Color.white);
		new btnOpaqueClose(btnOpencar, plAll);
		btnOpencar.setOpaque(false);
		btnOpencar.setIcon(new ImageIcon(new getImage("gift.png").getImageF()));
		bag.put("PageStatus", PageStatus);
		bag.put("plMeunLocal",
				new Integer[] { ((JPanel) bag.get("plSubMeun")).getX(), ((JPanel) bag.get("plSubMeun")).getY(),
						((JPanel) bag.get("plSubMeun")).getWidth(), ((JPanel) bag.get("plSubMeun")).getHeight() });
		bag.put("plMeunValuesLocal",
				new Integer[] { ((JPanel) bag.get("plMeunValues")).getX(), ((JPanel) bag.get("plMeunValues")).getY(),
						((JPanel) bag.get("plMeunValues")).getWidth(),
						((JPanel) bag.get("plMeunValues")).getHeight() });
		bag.put("btn2Local",
				new Integer[] { ((JButton) bag.get("btnSend")).getX(), ((JButton) bag.get("btnSend")).getY(),
						((JButton) bag.get("btnSend")).getWidth(), ((JButton) bag.get("btnSend")).getHeight() });
		bag.put("CarName_New",new HashMap <String , String [] >());
	}

	private String[] MakeList() {
		Connect C = new Connect();
		C.Connect("mysql");
		PreparedStatement Pstmt = null;
		String Type = "廠牌　　\n";
		try {
			Pstmt = C.getConnect().prepareStatement("SELECT distinct 品牌 FROM allcar");
			ResultSet result = Pstmt.executeQuery();
			while (result.next()) {
				Type = Type + (result.getString(1)) + "\n";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (Pstmt != null)
					Pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			C.close();
		}
		return Type.split("\n");
	}

	private String[] CarList(String name, String sql) {
		Connect C = new Connect();
		C.Connect("mysql");
		PreparedStatement Pstmt = null;
		String Type = name + "\n";
		try {
			Pstmt = C.getConnect().prepareStatement(sql);
			ResultSet result = Pstmt.executeQuery();
			while (result.next()) {
				Type = Type + (result.getString(1)) + "\n";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (Pstmt != null)
					Pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			C.close();
		}
		return Type.split("\n");
	}
}
