import java.util.Random;
import java.lang.Math;

public class Room{
	//Initialize Room variables
	private String name;
	private String description;
	private Room north;
	private Room east;
	private Room west;
	private Room south;
	private Monster monster;
	private Item item;

	//Room constructor
	public Room(String name, String description){
		this.name = name;
		this.description = description;
		//this.hasEnemy = Math.floor((int)(Math.random() * 2));
	}
	//Setter methods
	public void setNorth(Room north){this.north = north;}
	public void setEast(Room east){this.east = east;}
	public void setWest(Room west){this.west = west;}
	public void setSouth(Room south){this.south = south;}
	public void setExits(Room north, Room east, Room west, Room south){
		this.north = north;
		this.east = east;
		this.west = west;
		this.south = south;
	}
	public void setItem(Item item){
		this.item = item;
	}
	public void setMonster(Monster monster){
		this.monster = monster;
	}
	//Getter methods
	
	public Room getNorth(){return this.north;}
	public Room getEast(){return this.east;}
	public Room getWest(){return this.west;}
	public Room getSouth(){return this.south;}
	public String getName(){return this.name;}
	public String getDescription(){return this.description;}
	public Monster getMonster(){return this.monster;}
	//Creates a string of exits; if a room has an exit in that direction,
	//that room is labeled and added to the exit string with a newline.
	public String getExits(){
		String exits = "";
		if(this.getNorth() != null){
			exits += "[North] " + this.getNorth().getName() + "\n";
		}
		if(this.getEast() != null){
			exits += "[East] " + this.getEast().getName() + "\n";
		}
		if(this.getWest() != null){
			exits += "[West] " + this.getWest().getName() + "\n";
		}
		if(this.getSouth() != null){
			exits += "[South] " + this.getSouth().getName() + "\n";
		}
		exits += "\n";
		return exits;
	}
	public Item getItem(){
		return this.item;
	}

	public boolean hasMonster(){
		if(this.monster != null){
			return true;
		}
		else{
			return false;
		}
	}
	//returns String representation of the Room.
	public String toString(){
		return String.format("___________________________________________________________________%n"+
							"%s%n%s" + 
							"___________________________________________________________________%n",
							 this.getDescription(), this.getExits());
	}
}