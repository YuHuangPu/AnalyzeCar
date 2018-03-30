package Rcaller;

import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Log.Log;
import rcaller.RCaller;
import rcaller.RCode;


public class RConnect extends Thread{
	public RConnect (){Connectdo();}
	public void Connect(){
		
	}
	private RCaller caller;
	private String name = "";
	private JLabel Radar;
	private RCode code;
	private String title [] = {"獽┦","┦","滴続┦","巨北┦","竒蕾┦"};
	private Double Grade[] = {5.0,5.0,5.0,5.0,5.0};
	private void Connectdo (){
            caller = new RCaller();
            try{
            caller.setRscriptExecutable("C:\\Program Files\\R\\R-3.1.1\\bin\\x64\\Rscript");
            }catch(Exception e){
            	try{
            		caller.setRscriptExecutable("D:\\R2.4\\bin\\x64\\Rscript.exe");
                }catch(Exception e1){
                	try{
                		caller.setRscriptExecutable("E:\\R-3.2.3\\bin\\x64\\Rscript.exe");
                    }catch(Exception e2){
                        	
                    }
                }
            }
            caller.cleanRCode();
            code = new RCode();
           code.clear();
	}
	public void setGrade(String name,Double [] Grade,JLabel Radar){
		this.Radar = Radar;
		this.name = name;
		this.Grade = Grade;
		start();
	}
	public void setGrade(String name,Double [] Grade){
		this.name = name;
		this.Grade = Grade;
	}
	public void setGrade(String name, Double [] Grade,boolean tag){
		//allG = Grade;
		for(int i =0 ; i<5;i++){
			allG[i] = allG[i] + String.valueOf(Grade[i]) + ", ";
		}
		this.name = this.name + name +", ";
		this.tag = tag;
	}
	private String [] allG = {"","","","",""};
	private boolean tag = true;
	
	public void run (){Rdo();}
	private void Rdo(){
 		code.addRCode("library(fmsb);"
 				+ "maxmin <- data.frame("+title[0]+"=c(1,0),"+title[1]+"=c(1,0),"+title[2]+"=c(1,0),"+title[3]+"=c(1,0),"+title[4]+"=c(1,0));"
 	      		+ (tag? 
 	      		 "dat <- data.frame("+title[0]+"=c("+Grade[0]+"),"+title[1]+"=c("+Grade[1]+"),"+title[2]+"=c("+Grade[2]+"),"+title[3]+"=c("+Grade[3]+"),"+title[4]+"=c("+Grade[4]+"));"
 	      		 : "dat <- data.frame("+title[0]+"=c("+allG[0].substring(0, allG[0].length()-2)+"),"+title[1]+"=c("+allG[1].substring(0, allG[1].length()-2)+"),"+title[2]+"=c("+allG[2].substring(0, allG[2].length()-2)+"),"+title[3]+"=c("+allG[3].substring(0, allG[3].length()-2)+"),"+title[4]+"=c("+allG[4].substring(0, allG[4].length()-2)+"));"
 	      		 )
 	      		+ "dat <- rbind(maxmin,dat);"
 	      		+ "setwd('bag/');"
 	      		+ (tag ? "png(file='"+(tag ?name :"["+name.substring(0,name.length()-2)+"]")+".png',width = 420, height = 420,bg = 'transparent');"
 	      		 :"png(file='"+(tag ?name :"["+name.substring(0,name.length()-2)+"]")+".png',width = 470, height = 470,bg = 'transparent');"
 	      		)
 	      		+ "par(mar=c(0,0,0,0),bg = 'transparent',fg='#FFFFFF',family='地眃次次砰W5');"
 	      		
 	      		+ (tag? 
 	      				"radarchart(dat,pfcol='transparent',calcex=1,vlcex=1.2,axistype=0,axislabcol='#FFFFFF',cglcol='#DDDDDD',cglwd=3,cglty=1,plwd=5,pcol='#FF0000',pty=16,seg=8);"
 	      				:"radarchart(dat,plty=c(1,1,1),calcex=2,vlcex=1.5,axistype=0,axislabcol='#FFFFFF',cglcol='#DDDDDD',cglwd=2,cglty=1,pfcol='transparent',plwd=3,pcol=c('#FF0000','#00CED1','#FFFF00'),pty=16,seg=8);"
 	   	      		 )
 	      		//+ "par(xx);"
 	      		+ "dev.off();");
         caller.setRCode(code);
         caller.runOnly();
         for(String log : caller.getRCode().toString().split("\n")){
        	 Log.show(log);
         }
         //Radar.setIcon(new ImageIcon("bag/"+name+".png"));
         
	}
}
