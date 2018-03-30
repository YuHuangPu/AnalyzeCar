package btn.Tool;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Area;

import javax.swing.JButton;

public class area5BT extends JButton  {
	private Color start = new Color(240,0,0,128);
	private Color end = Color.green;
		 public area5BT(String label){
			  super(label);
			  Dimension size=getPreferredSize();
			  size.width=size.height=Math.max(size.width,size.height);
			  setPreferredSize(size);
			  setContentAreaFilled(false);
			 }
		 public void setColor (Color start,Color end){
			 this.start = start;
			 this.end = end;
		 }
		 protected void paintComponent(Graphics g){
			  if(getModel().isArmed()){
			   g.setColor(start);
			  }else{
			   g.setColor(end);
			  }
			  //g.fillOval(0,0,getSize().width-1,getSize().height-1);
			  int [] x = {0,
					  ((int)(getSize().width*0.2)),
					  ((int)(getSize().width*0.8)),
					  ((int)(getSize().width-1)),
					  ((int)(getSize().width*0.5))};
			  int [] y = {
					  ((int)(getSize().height*0.4+getSize().height*0.01)),
					  ((int)(getSize().height-1)),
					  ((int)(getSize().height-1)),
					  ((int)(getSize().height*0.4+getSize().height*0.01)),
					  ((int)(getSize().height*0.05))};
			  g.fillPolygon(x,y,5);
			  super.paintComponents(g);
			 }
		 protected void paintBorder(Graphics g){
			  g.setColor(getForeground());
			  //g.drawOval(0,0,getSize().width-1,getSize().height-1);
			  int [] x = {0,
					  ((int)(getSize().width*0.2)),
					  ((int)(getSize().width*0.8)),
					  ((int)(getSize().width-1)),
					  ((int)(getSize().width*0.5))};
			  int [] y = {
					  ((int)(getSize().height*0.4+getSize().height*0.01)),
					  ((int)(getSize().height-1)),
					  ((int)(getSize().height-1)),
					  ((int)(getSize().height*0.4+getSize().height*0.01)),
					  ((int)(getSize().height*0.05))};
			  g.drawPolygon(x,y,5);
			 }
		 Shape shape;
		 public boolean contains(int X,int Y){
		  if((shape==null)||(!shape.getBounds().equals(getBounds()))){
			  int [] x = {0,
					  ((int)(getSize().width*0.2)),
					  ((int)(getSize().width*0.8)),
					  ((int)(getSize().width-1)),
					  ((int)(getSize().width*0.5))};
			  int [] y = {
					  ((int)(getSize().height*0.4+getSize().height*0.01)),
					  ((int)(getSize().height-1)),
					  ((int)(getSize().height-1)),
					  ((int)(getSize().height*0.4+getSize().height*0.01)),
					  ((int)(getSize().height*0.05))};
			  shape=new Area(new Polygon(x, y, 5));
		  }
		  return shape.contains(X,Y);
		 }
}