/*  HOMEWORK 2: PACKAGES, INHERITANCE, AND POLYMORPHISM
 *  Matthew Herpich
 *  ID: mherpich
 *  
 * Creature class is abstract and houses the methods which are inherited by the Insect, Bird, and Cat subclasses.
 * Creature class uses getter/setter methods for all private variables and passes protected variables to subclasses
 * for pre-defined attributes (e.g., ID displayed in Grid).  Move, eat, and breed methods are abstract and defined
 * in subclasses.
 */

package predatorPrey;

public abstract class Creature {
	private int xcoord;
	private int ycoord;
	private int breedage = 0;
	
	//protected attributes will be set by subclasses
	protected int starvethresh;
	protected int breedthresh;
	protected int starveage;
	protected char creatureID;
	protected char preyID;
	
	//abstract methods will be defined in subclasses
	public abstract void moveCreature(int worlddim, char[][] gridobj);
	public abstract int[] eatCreature(int worlddim, char[][] gridobj);
	public abstract int[] breedCreature(int worlddim, char[][] gridobj);
	
	//getters and setters
	public char returncreatureID(){
		return creatureID;
	}
	public char returnpreyID(){
		return preyID;
	}
	public void setxCoord(int xcinput){
		xcoord = xcinput;
	}
	public int getxCoord(){
		return xcoord;
	}
	public void setyCoord(int ycinput){
		ycoord = ycinput;
	}
	public int getyCoord(){
		return ycoord;
	}
	public void setbreedage(int bage){
		breedage = bage;
	}
	public int getbreedage(){
		return breedage;
	}
	public void setstarvethresh(int sthresh){
		starvethresh = sthresh;
	}
	public int getstarvethresh(){
		return starvethresh;
	}
	public void setstarveage(int sage){
		starveage = sage;
	}
	public int getstarveage(){
		return starveage;
	}
	public int getbreedthresh(){
		return breedthresh;
	}
}
