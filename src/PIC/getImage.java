package PIC;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class getImage {
	private String IconName;
	public getImage(String IconName){
		this.IconName = IconName;
	}
	public Image getImageF(){
		
		Image I ;
		
		try {
			I = (Image)ImageIO.read(getClass().getResourceAsStream(IconName));
		} catch (IOException e) {
			I = null;
		}
		
		return I;
	}
}
