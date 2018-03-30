package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Rcaller.RConnect;
import btn.Tool.PhotoSizeSet;
import datatool.Connect;
import datatool.Sql;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import java.awt.event.AdjustmentListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JDesktopPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class carVaule extends JDialog {

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			carVaule dialog = new carVaule();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	private HashMap<String,JLabel> LblMap = new HashMap<String,JLabel>();
	private HashMap<String,JPanel> PlMap = new HashMap<String,JPanel>();
	private HashMap<String,JScrollBar> SbMap = new HashMap<String,JScrollBar>();
	private HashMap<String,JButton> BtnMap = new HashMap<String,JButton>();
	private PhotoSizeSet BP;
	private String name ;
	private boolean photodemo = false;
	private Double [] grade ;
	public carVaule() {
		carvaluegui();
	}
	public carVaule(String name,Double[] grade) {
		this.grade = grade ;
		this.name = name ;
		carvaluegui();
	}
	private void carvaluegui(){

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				 RConnect R = new RConnect();
				 R.Connect();
				 R.setGrade(name, grade );
			}
		});
		
		final JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.DARK_GRAY);
		desktopPane.setBounds(0, 0, 1064, 562);
		getContentPane().add(desktopPane);
		

		JPanel MainPage = new JPanel();
		MainPage.setBounds(10, 10, 584, 324);
		desktopPane.add(MainPage);
		MainPage.setBackground(Color.DARK_GRAY);
		MainPage.setLayout(null);
		
		final JButton btnNextPage = new JButton("New button");
		MainPage.add(btnNextPage);
		btnNextPage.setBounds(557, 297, 27, 27);
		/////////////////////////////////////////////////////////////
		btnNextPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBounds(100, 100, 1080, 600);
				desktopPane.setBounds(0, 0, 1064, 562);
				//LblMap.get("lblRadarPhoto").setIcon(new ImageIcon("bag/"+name+".png"));

				JLabel lblRadarPhoto = new JLabel(new ImageIcon("bag/"+name+".png"));
				lblRadarPhoto.setBounds(604, 102, 450, 450);
				desktopPane.add(lblRadarPhoto);
				LblMap.put("lblRadarPhoto", lblRadarPhoto);
				
				JLabel lblTitleI = new JLabel("\u7D9C\u5408\u6027\u80FD\u8A55\u50F9");
				lblTitleI.setHorizontalAlignment(SwingConstants.CENTER);
				lblTitleI.setForeground(Color.YELLOW);
				lblTitleI.setFont(new Font("微軟正黑體", Font.PLAIN,35));
				lblTitleI.setBounds(604, 20, 225, 60);
				desktopPane.add(lblTitleI);
				
				JLabel lblTitleII = new JLabel("\u2764");
				lblTitleII.setHorizontalAlignment(SwingConstants.CENTER);
				lblTitleII.setForeground(Color.RED);
				lblTitleII.setFont(new Font("MS Gothic", Font.PLAIN, 70));
				lblTitleII.setBounds(862, 10, 70, 72);
				desktopPane.add(lblTitleII);
				
				JLabel lblGrade = new JLabel("");
				lblGrade.setHorizontalAlignment(SwingConstants.RIGHT);
				lblGrade.setText(String.valueOf(100*(grade[0]*0.178+grade[1]*0.224+grade[2]*0.205+grade[3]*0.204+grade[4]*0.19)).substring(0, 5));
				lblGrade.setFont(new Font("Consolas", Font.PLAIN, 35));
				lblGrade.setForeground(Color.YELLOW);
				lblGrade.setBounds(907, 26, 147, 60);
				desktopPane.add(lblGrade);
				
				Connect C = new Connect();
				C.Connect("Mysql");
				Sql CMD = new Sql(C.getConnect());
				//
				String [][] column_name = new String [] [] {
					 {"年分","品牌","款式","價錢"}
					,{"動力型式","引擎型式","排氣量","最大馬力","最大扭力","馬達出力","壓縮比","系統總合輸出"}
					,{"驅動型式","變速系統"}
					,{"前輪懸吊","後輪懸吊","煞車型式","輪胎尺碼"}
					,{"車身型式","車門數","座位數","車長","車寬","車高","車重","軸距","標準行李箱容量","後座傾倒行李箱容量"}
					,{"油箱容量","市區油耗","高速油耗","平均油耗","牌照稅","燃料費"}
					,{"外觀配備","內裝配備","影音配備","便利配備","安全配備"}};
				String [][] Title = {{"基本","動力", "傳動", "底盤", "車體", "其他"}
				,{"外觀配備", "內裝配備", "影音配備", "便利配備", "安全配備"}};
				String norm[] = new String [2];
				//
				int c = 0,J=0;
				String SQL = "";
				for(String SI[] : column_name){
					for(String SII : SI){
						SQL = SQL + SII + ", " ;
					}
				}
				SQL = "select "+SQL.substring(0,SQL.length()-2)+" from allcar where concat(年分,' ',品牌,' ',款式) = '"+ name + "'";
				try {
					CMD.setSQL(SQL);
					CMD.getResultSet().next();
					for(String SI[] : column_name){if (J==6)break;
						norm[0] = (norm[0]==null?"":norm[0])+"*----" + Title[0][J] + "----*\n";J++;
						for(String SII : SI){
							norm[0] = norm[0]+SII+"\t"+ CMD.getResultSet().getString(c+1) + "\n";
							c++;
						}
					}
					for(int i=0;i<5;i++){
						norm[1] = (norm[1]==null?"":norm[1])+"*----" + Title[1][i] + "----*\n";
						for(String value : CMD.getResultSet().getString(c+1).split(", ")){
							norm[1] = norm[1] + value + "\n" ;
						}
						c++;
					}
					CMD.sqlClose();
					C.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				


				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 344, 279, 208);
				desktopPane.add(scrollPane);
				
				JTextArea textArea = new JTextArea(norm[0]);
				scrollPane.setViewportView(textArea);
				textArea.setForeground(Color.RED);
				textArea.setBackground(Color.DARK_GRAY);
				
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(315, 344, 279, 208);
				desktopPane.add(scrollPane_1);
				
				JTextArea textArea_1 = new JTextArea(norm[1]);
				scrollPane_1.setViewportView(textArea_1);
				textArea_1.setForeground(Color.RED);
				textArea_1.setBackground(Color.DARK_GRAY);
				
				
			}
		});
		
		
		
		
		/////////////////////////////////////////////////////////////////////////////
		
		if(photodemo)BP = new PhotoSizeSet(name);
		
		setBounds(100, 100, 620, 380);
		getContentPane().setLayout(null);

		
		
		JPanel plMain = new JPanel();
		plMain.setBackground(Color.DARK_GRAY);
		plMain.setBounds(0, 0, 584, 290);
		MainPage.add(plMain);
		plMain.setLayout(null);
		
		JLabel lblMainPhoto = new JLabel();
		if(photodemo)lblMainPhoto.setIcon(BP.getphoto(0,500,290));
		else lblMainPhoto.setText("DEMO CLOSE");
		lblMainPhoto.setBounds(0, 0, 500, 290);
		plMain.add(lblMainPhoto);
		LblMap.put("lblMainPhoto", lblMainPhoto);
		
		JPanel plPhotes = new JPanel();
		plPhotes.setBackground(Color.DARK_GRAY);
		plPhotes.setBounds(507, 0, 77, 290);
		plMain.add(plPhotes);
		plPhotes.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent arg0) {
				SbMap.get("sbPhotes").setValue(SbMap.get("sbPhotes").getValue()+arg0.getWheelRotation()*5);
			}
		});
		plPhotes.setLayout(null);
		
		JPanel plBtns = new JPanel();
		plBtns.setBackground(Color.DARK_GRAY);
		plBtns.setBounds(0, 0, 61, 590);
		plPhotes.add(plBtns);
		plBtns.setLayout(null);
		PlMap.put("plBtns", plBtns);
		
		JScrollBar sbPhotes = new JScrollBar();
		sbPhotes.setForeground(Color.DARK_GRAY);
		sbPhotes.setBackground(Color.DARK_GRAY);
		sbPhotes.setBounds(60, 0, 17, 290);
		sbPhotes.addAdjustmentListener(new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent arg0) {
				PlMap.get("plBtns").setBounds(0, 0-arg0.getValue(), 61, 590);
			}
		});
		plPhotes.add(sbPhotes);
		sbPhotes.setVisibleAmount(0);
		sbPhotes.setMaximum(300);
		SbMap.put("sbPhotes", sbPhotes);
		
		JPanel plCarName = new JPanel();
		plCarName.setBackground(Color.DARK_GRAY);
		plCarName.setBounds(0, 300, 542, 24);
		MainPage.add(plCarName);
		plCarName.setLayout(null);
		
		JLabel lblCarName = new JLabel(name);
		lblCarName.setForeground(Color.YELLOW);
		lblCarName.setBounds(0, 0, 542, 24);
		plCarName.add(lblCarName);
		lblCarName.setBackground(Color.DARK_GRAY);
		lblCarName.setFont(new Font("微軟正黑體", Font.BOLD | Font.ITALIC, 19));
		
		
		
		

		for(int i =0;i<10;i++){
			final JButton btnPhoto = new JButton();
			if(photodemo)btnPhoto.setIcon(BP.getphoto(i,61,50));
			else btnPhoto.setText(String.valueOf(i));
			btnPhoto.setMnemonic(i);
			btnPhoto.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(photodemo)LblMap.get("lblMainPhoto").setIcon(BP.getphoto(Integer.valueOf(btnPhoto.getMnemonic()),500,290));
					else LblMap.get("lblMainPhoto").setText("DEMO : "+String.valueOf(btnPhoto.getMnemonic()));
				}
			});
			btnPhoto.setBounds(0, 60*i, 61, 50);
			plBtns.add(btnPhoto);
			BtnMap.put("btnPhoto"+i, btnPhoto);
		}
	}
}
