/*  HOMEWORK 2: PACKAGES, INHERITANCE, AND POLYMORPHISM
 *  Matthew Herpich
 *  ID: mherpich
 *  
 * Cat class which extends Creature class and overwrites the eat, move, and breed methods according to proper behavior.
 */

package predatorPrey;

public class Cat extends Creature{
	//constructor
	public Cat(){
		super();
		this.creatureID = 'C';
		this.preyID = 'B';
		this.starvethresh = 12;
		this.breedthresh = 10;
		this.starveage = 0;
	}
	
	//eat method for cats
	public int[] eatCreature(int worlddim, char[][] gridobj){
		//check for boundary conditions
		boolean isup = false;
		boolean isdown = false;
		boolean isright = false;
		boolean isleft = false;
		char charup = Character.MIN_VALUE;
		char chardown = Character.MIN_VALUE;
		char charright = Character.MIN_VALUE;
		char charleft = Character.MIN_VALUE;
			
		if(this.getyCoord() < (worlddim - 1)){
			isright = true;
			charright = gridobj[this.getxCoord()][this.getyCoord()+1];}
		else{
			isright = false;
			charright = Character.MIN_VALUE;}
		
		if(this.getyCoord() > 0){
			isleft = true;
			charleft = gridobj[this.getxCoord()][this.getyCoord()-1];}
		else{
			isleft = false;
			charleft = Character.MIN_VALUE;}
		
		if(this.getxCoord() < (worlddim - 1)){
			isdown = true;
			chardown = gridobj[this.getxCoord()+1][this.getyCoord()];}
		else{
			isdown = false;
			chardown = Character.MIN_VALUE;}
		
		if(this.getxCoord() > 0){
			isup = true;
			charup = gridobj[this.getxCoord()-1][this.getyCoord()];}
		else{
			isup = false;
			charup = Character.MIN_VALUE;}
		
		int[] prey = new int[2];
		prey[0] = -9999;
		prey[1] = -9999;
		
		//check for and eat prey
		if((isup == true) && (charup == this.returnpreyID())){
			int oldloc = this.getxCoord();
			this.setxCoord(oldloc - 1);
			prey[0] = this.getxCoord();
			prey[1] = this.getyCoord();
			this.starveage = 0;
		} else if((isdown == true) && (chardown == this.returnpreyID())){
			int oldloc = this.getxCoord();
			this.setxCoord(oldloc + 1);
			prey[0] = this.getxCoord();
			prey[1] = this.getyCoord();
			this.starveage = 0;
		} else if((isleft == true) && (charleft == this.returnpreyID())){
			int oldloc = this.getyCoord();
			this.setyCoord(oldloc - 1);
			prey[0] = this.getxCoord();
			prey[1] = this.getyCoord();
			this.starveage = 0;
		} else if((isright == true) && (charright == this.returnpreyID())){
			int oldloc = this.getyCoord();
			this.setyCoord(oldloc + 1);
			prey[0] = this.getxCoord();
			prey[1] = this.getyCoord();
			this.starveage = 0;
		} else{
			this.starveage += 1;
		}
		return prey;
	}		
	
	//move method for cats
	public void moveCreature(int worlddim, char[][] gridobj){
		//check for boundary conditions
		boolean isup = false;
		boolean isdown = false;
		boolean isright = false;
		boolean isleft = false;
		boolean haseaten = false;
		char charup = Character.MIN_VALUE;
		char chardown = Character.MIN_VALUE;
		char charright = Character.MIN_VALUE;
		char charleft = Character.MIN_VALUE;
			
		if(this.getyCoord() < (worlddim - 1)){
			isright = true;
			charright = gridobj[this.getxCoord()][this.getyCoord()+1];}
		else{
			isright = false;
			charright = Character.MIN_VALUE;}
		
		if(this.getyCoord() > 0){
			isleft = true;
			charleft = gridobj[this.getxCoord()][this.getyCoord()-1];}
		else{
			isleft = false;
			charleft = Character.MIN_VALUE;}
		
		if(this.getxCoord() < (worlddim - 1)){
			isdown = true;
			chardown = gridobj[this.getxCoord()+1][this.getyCoord()];}
		else{
			isdown = false;
			chardown = Character.MIN_VALUE;}
		
		if(this.getxCoord() > 0){
			isup = true;
			charup = gridobj[this.getxCoord()-1][this.getyCoord()];}
		else{
			isup = false;
			charup = Character.MIN_VALUE;}		
		
		//move to open space if has not eaten
		if((haseaten == false) && (isup == true) && (charup == Character.MIN_VALUE)){
			int oldloc = this.getxCoord();
			this.setxCoord(oldloc - 1);
		} else if((haseaten == false) && (isdown == true) && (chardown == Character.MIN_VALUE)){
			int oldloc = this.getxCoord();
			this.setxCoord(oldloc + 1);
		} else if((haseaten == false) && (isleft == true) && (charleft == Character.MIN_VALUE)){
			int oldloc = this.getyCoord();
			this.setyCoord(oldloc - 1);
		} else if((haseaten == false) && (isright == true) && (charright == Character.MIN_VALUE)){
			int oldloc = this.getyCoord();
			this.setyCoord(oldloc + 1);
		}
	}

	//Cats breed method
	public int[] breedCreature(int worlddim, char[][] gridobj){
		//check for boundary conditions
		boolean isup = false;
		boolean isdown = false;
		boolean isright = false;
		boolean isleft = false;
		char charup = Character.MIN_VALUE;
		char chardown = Character.MIN_VALUE;
		char charright = Character.MIN_VALUE;
		char charleft = Character.MIN_VALUE;
			
		if(this.getyCoord() < (worlddim - 1)){
			isright = true;
			charright = gridobj[this.getxCoord()][this.getyCoord()+1];}
		else{
			isright = false;
			charright = Character.MIN_VALUE;}
		
		if(this.getyCoord() > 0){
			isleft = true;
			charleft = gridobj[this.getxCoord()][this.getyCoord()-1];}
		else{
			isleft = false;
			charleft = Character.MIN_VALUE;}
		
		if(this.getxCoord() < (worlddim - 1)){
			isdown = true;
			chardown = gridobj[this.getxCoord()+1][this.getyCoord()];}
		else{
			isdown = false;
			chardown = Character.MIN_VALUE;}
		
		if(this.getxCoord() > 0){
			isup = true;
			charup = gridobj[this.getxCoord()-1][this.getyCoord()];}
		else{
			isup = false;
			charup = Character.MIN_VALUE;}
		
		int[] breed = new int[2];
		breed[0] = -9999;
		breed[1] = -9999;
		
		//check for open spot to breed
		if((isup == true) && (charup == Character.MIN_VALUE)){
			breed[0] = this.getxCoord() - 1;
			breed[1] = this.getyCoord();
		} else if((isdown == true) && (chardown == Character.MIN_VALUE)){
			breed[0] = this.getxCoord() + 1;
			breed[1] = this.getyCoord();
		} else if((isleft == true) && (charleft == Character.MIN_VALUE)){
			breed[0] = this.getxCoord();
			breed[1] = this.getyCoord() - 1;
		} else if((isright == true) && (charright == Character.MIN_VALUE)){
			breed[0] = this.getxCoord();
			breed[1] = this.getyCoord() + 1;
		}
		return breed;
	}			
}

