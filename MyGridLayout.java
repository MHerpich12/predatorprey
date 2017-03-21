/*  HOMEWORK 2: PACKAGES, INHERITANCE, AND POLYMORPHISM
 *  Matthew Herpich
 *  ID: mherpich
 *  
 * MyGridLayout class serves to visualize the World matrix in a JFrame with creatures labeled as JButtons.
 * Rests for 1.5sec between updates.  Passes dispose input; 1 will erase JFrame.
 */

package predatorPrey;

import java.awt.*;  
import javax.swing.*;  
  
public class MyGridLayout{  
private int dimension;
private char[][] initialgrid;
private int rownum;
private int colnum;
private String creatval;

public MyGridLayout(int diminput, char[][] passgrid, int dispose){  
	dimension = diminput;
    initialgrid = passgrid;
    
    //initialize frame and set to foreground
    JFrame f = new JFrame();
    f.setVisible(true);
    f.setAlwaysOnTop(true);
    
    //create matrix of Jbuttons with display passed into method via matrix
    for(int i = 1; i < (Math.pow(dimension,2)+1); i++){
    	rownum = (i-1)/dimension;
		colnum = (i - (rownum * dimension)-1);
		creatval = "" + initialgrid[rownum][colnum];
    	JButton sample = new JButton(creatval);
    	f.add(sample);
    }
    
    f.setLayout(new GridLayout(diminput,diminput));   
  
    f.setSize(900,900);  
    if(dispose == 1){
    try {
        Thread.sleep(1500);
    } catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
    }
    f.dispose();
    }
}  
}  