package Tool;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class btnOpaqueClose {
	private final JButton btn;
	private final JPanel pl;
	public btnOpaqueClose (JButton btn,JPanel pl){this.btn = btn;this.pl=pl;Close ();}
	private void Close (){
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pl.repaint();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pl.repaint();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pl.repaint();
			}
		});
		btn.setOpaque(false);
	}
}
