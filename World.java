/*  HOMEWORK 2: PACKAGES, INHERITANCE, AND POLYMORPHISM
 *  Matthew Herpich
 *  ID: mherpich
 *  
 * Created a World class which initializes a Creature array of Birds, Cats, and Insects based on
 * user input to command line.  Creatures move around a Grid (matrix) which has dimensions initialized by user according
 * to pre-determined behaviors.  Grid is displayed after each turn as a JFrame (may be minimized) with a 1.5sec sleep.
 * Loop is run until World achieves steady state solution, at which time user is prompted for another run at command line.
 * 
 * World class has methods which call upon eat/starve, move, and breed Creature methods to simulate movement.
 * World class also houses main program.
 */

package predatorPrey;
import java.util.Random;
import javax.swing.JFrame;
import java.util.Scanner;


public class World {
	//initialize private variables
	private int worlddim;
	private Creature[] creaturearray;
	private char[][] initialgrid;
	private char[][] updatedgrid;
	
	//constructor
	public World(Creature[] creatures, int dimensions){
		creaturearray = creatures;
		worlddim = dimensions;
		initialgrid = new char[worlddim][worlddim];
		updatedgrid = new char[worlddim][worlddim];
	}
	
	//getters and setters
	public void setCreature(Creature[] newcreature){
		creaturearray = newcreature;
	}
	public Creature[] returnCreature(){
		return creaturearray;
	}

	//develop character matrix representing positions of Creature objects
	public char[][] initializeWorld(){
		for(int x = 0; x < worlddim; x++){
			for(int y = 0; y < worlddim; y++){
				initialgrid[x][y] = Character.MIN_VALUE;
			}
		}
		for(int a = 0; a < creaturearray.length; a++){
			initialgrid[creaturearray[a].getxCoord()][creaturearray[a].getyCoord()] = creaturearray[a].returncreatureID();
		}
		return initialgrid;
	}
	
	//World move method
	public char[][] moveWorld(){
		updatedgrid = this.initializeWorld();
		int[] preycoord = new int[2];
		for(int b = 0; b < creaturearray.length; b++){
			preycoord = creaturearray[b].eatCreature(this.worlddim, updatedgrid);
			if((preycoord[0] != -9999) && (preycoord[1] != -9999)){
				for(int c = 0; c < creaturearray.length; c++){
					if((creaturearray[c].returncreatureID() == creaturearray[b].returnpreyID()) && (creaturearray[c].getxCoord() == preycoord[0]) && (creaturearray[c].getyCoord() == preycoord[1])){
						setCreature(removeCreature(creaturearray, c));
						if(c < b){
							b -= 1;
						}
						break;}
				}
				updatedgrid = this.initializeWorld();
			} else {
				creaturearray[b].moveCreature(this.worlddim, updatedgrid);
				updatedgrid = this.initializeWorld();
			}
		}
		return updatedgrid;
	}
	
	//Starve World method
	public char[][] starveWorld(){
		for(int d = 0; d < creaturearray.length; d++){
			if((creaturearray[d].getstarveage() >= creaturearray[d].getstarvethresh()) && (creaturearray[d].getstarvethresh() != -9999))
				setCreature(removeCreature(creaturearray, d));
		}
		updatedgrid = this.initializeWorld();
		return updatedgrid;
	}
	
	//Breed World method
	public char[][] breedWorld(){
		int[] breedcoord = new int[2];
		int origlength = creaturearray.length;
		for(int e = 0; e < origlength; e++){
			creaturearray[e].setbreedage(creaturearray[e].getbreedage()+1);
			if(creaturearray[e].getbreedage() % creaturearray[e].getbreedthresh() == 0){
				breedcoord = creaturearray[e].breedCreature(this.worlddim, updatedgrid);
				if((breedcoord[0] != -9999) && (breedcoord[1] != -9999)){
					setCreature(insertCreature(creaturearray));
					if(creaturearray[e].returncreatureID() == 'o'){
						creaturearray[creaturearray.length-1] = new Insect();
					} else if(creaturearray[e].returncreatureID() == 'B'){
						creaturearray[creaturearray.length-1] = new Bird();
					} else {
						creaturearray[creaturearray.length-1] = new Cat();
					}
					creaturearray[creaturearray.length-1].setxCoord(breedcoord[0]);
					creaturearray[creaturearray.length-1].setyCoord(breedcoord[1]);
					updatedgrid = this.initializeWorld();}
			}
		}
		updatedgrid = this.initializeWorld();
		return updatedgrid;
	}
	
	//world display method
	public void displayWorld(char[][] worldgrid, int dispose){
		new MyGridLayout(worlddim, worldgrid, dispose);
	}
	
	//remove Creature method
	public Creature[] removeCreature(Creature[] original, int element){
	    Creature[] n = new Creature[original.length - 1];
	    System.arraycopy(original, 0, n, 0, element );
	    System.arraycopy(original, element+1, n, element, original.length - element-1);
	    return n;
	}
	
	//insert Creature method
	public Creature[] insertCreature(Creature[] original){
	    Creature[] n = new Creature[original.length + 1];
	    for(int r = 0; r < original.length; r++){
	    	n[r] = original[r];
	    }
	    return n;
	}
	

	//main program
	public static void main(String[] args){
		//initialize world variables
		int numbirds;
		int numinsects;
		int numcats;
		int dimensions;
		int countsim = 0;
		int humresp = 1;
		do{
		Scanner reader = new Scanner(System.in);
		
		System.out.println("*****************************************");
		System.out.println("*  WELCOME TO PREDATOR-PREY SIMULATION  *");
		System.out.println("*****************************************");
		System.out.println();
		
		System.out.println("Enter number of cats (C) for simulation: ");
		numcats = reader.nextInt();
		
		//check for invalid cats input
		while(numcats < 0){
			System.out.println("Invalid input.  Please enter an integer > 1.");
			System.out.println("Enter number of cats (C) for simulation: ");
			numcats = reader.nextInt();
		}
		
		System.out.println("Enter number of birds (B) for simulation: ");
		numbirds = reader.nextInt();
		
		//check for invalid birds input
		while(numbirds < 0){
			System.out.println("Invalid input.  Please enter an integer > 1.");
			System.out.println("Enter number of birds (B) for simulation: ");
			numbirds = reader.nextInt();
		}
		
		System.out.println("Enter number of insects (o) for simulation: ");
		numinsects = reader.nextInt();
		
		//check for invalid insects input
		while(numinsects < 0){
			System.out.println("Invalid input.  Please enter an integer > 1.");
			System.out.println("Enter number of insects (o) for simulation: ");
			numinsects = reader.nextInt();
		}
		
		System.out.println("Enter dimension of predator-prey world: ");
		dimensions = reader.nextInt();

		//check for invalid dimensions input
		while(((dimensions * dimensions) < (numbirds + numinsects + numcats))|| (dimensions < 1)){
			System.out.println("Invalid input.  Please make dimensions^2 larger than sum of number of creatures.");
			System.out.println("Enter dimension of predator-prey world: ");
			dimensions = reader.nextInt();
		}
			
		
		char[][] gridobj = new char[dimensions][dimensions];
		char[][] gridobj2 = new char[dimensions][dimensions];
		char[][] gridobj3 = new char[dimensions][dimensions];
		Random rnd = new Random();
				
		//generate list of possible positions
		int sampleset[] = new int[dimensions * dimensions];
		for(int j=1; j <= (dimensions * dimensions); j++){
			sampleset[j-1] = j;
		}
		//shuffle array for randomized grid
		int selectint = 0;
		int tmp = 0;
		int rownum = 0;
		int colnum = 0;
		for(int a = 0; a < sampleset.length; a++){
			selectint = rnd.nextInt(sampleset.length);
			tmp = sampleset[selectint];
			sampleset[selectint] = sampleset[a];
			sampleset[a] = tmp;
		}
			
		//initialize creature object array
		Creature[] creatureobjarr = new Creature[numbirds + numcats + numinsects];
		int creatureset[] = new int[numbirds + numcats + numinsects];
		for(int k = 0; k < creatureset.length; k++){
			creatureset[k] = sampleset[k];
		}
		
		//initial ordering will prioritize predator movement over prey
		//initialize Cats
		for(int l = 0; l < numcats; l++){
			rownum = (creatureset[l]-1)/dimensions;
			colnum = (creatureset[l] - (rownum * dimensions)-1);
			creatureobjarr[l] = new Cat();
			creatureobjarr[l].setxCoord(rownum);
			creatureobjarr[l].setyCoord(colnum);
		}
		
		//initialize Birds
		for(int m = numcats; m < (numcats + numbirds); m++){
			rownum = (creatureset[m]-1)/dimensions;
			colnum = (creatureset[m] - (rownum * dimensions)-1);
			creatureobjarr[m] = new Bird();
			creatureobjarr[m].setxCoord(rownum);
			creatureobjarr[m].setyCoord(colnum);
		}
		
		//initialize Insects
		for(int n = (numcats + numbirds); n < (numcats + numbirds + numinsects); n++){
			rownum = (creatureset[n]-1)/dimensions;
			colnum = (creatureset[n] - (rownum * dimensions)-1);
			creatureobjarr[n] = new Insect();
			creatureobjarr[n].setxCoord(rownum);
			creatureobjarr[n].setyCoord(colnum);
		}
		
		//create World object and initialization grid
		World sampleworld = new World(creatureobjarr,dimensions);
		gridobj = sampleworld.initializeWorld();
		sampleworld.displayWorld(gridobj,1);
		for (int l=0; l<dimensions; l++){
			for (int m=0; m<dimensions; m++){
				gridobj2[l][m] = gridobj[l][m];
			}
		}		
		
		//run World simulation until graphs are same
		do{
			countsim = 0;
			for (int p=0; p<dimensions; p++){
				for (int q=0; q<dimensions; q++){
					gridobj3[p][q] = gridobj2[p][q];
				}
			}
			gridobj2 = sampleworld.moveWorld();
			gridobj2 = sampleworld.starveWorld();
			gridobj2 = sampleworld.breedWorld();
			sampleworld.displayWorld(gridobj2,1);
			for (int r=0; r<dimensions; r++){
				for (int s=0; s<dimensions; s++){
					if(gridobj3[r][s] != gridobj2[r][s])
						countsim += 1;
				}
			}
		} while(countsim > 0);
		sampleworld.displayWorld(gridobj2,1);
		System.out.println("*****************************************");
		System.out.println("*             END SIMULATION            *");
		System.out.println("*****************************************");
		System.out.println();
		
		System.out.println("Would you like to try again (Yes = 1, No = 0)? ");
		humresp = reader.nextInt();
		
	} while(humresp == 1);
	System.out.println("Good bye.");
	}
}
