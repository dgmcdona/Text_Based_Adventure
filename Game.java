/*Author: David McDonald
Summary: A text based adventure game where the player moves from room to room, avoiding obstacles, 
picking up and using helpful items, fighting monsters, and unlocking doors in an effort to escape.
The game is won when the player escapes the tomb.*/


//import necessary packages
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.util.Scanner;

public class Game{
	//Declare static variables for use by the Game class' methods.
	//isAlive and wonGame are loop control variables.
	static boolean isAlive = true;
	static boolean wonGame = false;
	static Dungeon undergroundTomb = new Dungeon();//construct a new Dungeon instance.
	static Room currentRoom = undergroundTomb.getRoom0();//set starting Room.
	static Player player;//create player.

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		clearScreen();
		System.out.print("Enter your name: ");
		String name = input.nextLine();
		player = new Player(name, 100, 15, 30);

		System.out.println("You are an archaeologist in the valley of the kings. Your team has discovered what is\n" + 
						   "believed to be the tomb of Neferikare, but await clearance from the egyptian government\n" +
						   "to proceed. Driven mad by curiosity, you open the tomb in the middle of the night,\n"+
						   "and enter alone. A hidden slab of stone slides from the ceiling behind you, trapping you\n" + 
						   "inside. An inscription on the ceiling reads \'Time is not on your side, trespasser.\n" +
						   "Prove yourself worthy to Osiris to escape with your life.\n" +
						   "Sand begins to pour in from holes in the ceiling, filling the tomb. You must find a way out.");	
		startTimer();		
		while(isAlive == true && wonGame == false){
			System.out.print(currentRoom);
			move(undergroundTomb);
			/*if currentRoom.hasEnemy(){
				Monster monster = new Monster()
			}*/
		}
		System.exit(0);
	}
	//This method starts a timer that issues warnings at 30 and 60 seconds, and terminates the game at 90 seconds.
	public static void startTimer(){
		Timer clock = new Timer();
		TimerTask thirtySecs = new TimerTask(){
			public void run(){
				System.out.println("\nSand is pouring in from above. You won't have long.");
			}
		};
		TimerTask sixtySecs = new TimerTask(){
			public void run(){
				System.out.println("\nHurry! The sand is getting so deep, movement will soon become impossible.");
			}
		};
		TimerTask ninetySecs = new TimerTask(){
			public void run(){
				System.out.println("\nYou took too long; Engulfed by the sand, now you will join Osiris in the afterlife." +
								   "\nYou have paid dearly for your impatience.");
				System.exit(0);
			}
		};
		clock.schedule(thirtySecs, 1000L * 30L);
		clock.schedule(sixtySecs, 1000L * 60L);
		clock.schedule(ninetySecs, 1000L * 90L);
	}
	public static void clearScreen() {  
    	System.out.print("\033[H\033[2J");  
    	System.out.flush();  
	}

	//Method takes player input, compares it to available exits, and moves player to new room,
	//executing certain special conditions for some rooms through selection.
	public static void move(Dungeon undergroundTomb){
		Scanner input = new Scanner(System.in);
		System.out.print("Which direction do you go? [n]orth/[e]ast/[w]est/[s]outh/[q]uit: ");
		char response = input.nextLine().charAt(0);
		clearScreen();
		if((response == 'n' || response == 'N') && currentRoom.getNorth() != null){
			currentRoom = currentRoom.getNorth();
		}
		else if((response == 'e' || response == 'E') && currentRoom.getEast() != null){
			currentRoom = currentRoom.getEast();
		}
		else if((response == 'w' || response == 'W') && currentRoom.getWest() != null){
			currentRoom = currentRoom.getWest();
		}
		else if((response == 's' || response == 'S') && currentRoom.getSouth() != null){
			currentRoom = currentRoom.getSouth();
		}
		else if(response == 'q' || response == 'Q'){
			System.out.println("You lie down and die on the floor. A sorry fate...");
			isAlive = false;
		}
		else{
			System.out.println("Not a valid choice for this room, try again.");
		}

		if(currentRoom == undergroundTomb.getRoom3() || currentRoom == undergroundTomb.getRoom2()){
			System.out.print(currentRoom);
			isAlive = false;
		}
		//Sets the southern exit of the sEntranceHall room to 'outside'. The player then proceeds outside to win the game.
		if(currentRoom == undergroundTomb.getRoom9()){
			undergroundTomb.getRoom0().setSouth(undergroundTomb.getRoom10());
		}
		//if player moves outside(room 10), they win the game.
		if(currentRoom == undergroundTomb.getRoom10()){
			System.out.println("You emerge under the night sky. You have escaped with your life... This time.");
			wonGame = true;
		}
		//if the room contains an item, the player picks it up automatically.
		if(currentRoom.getItem() != null){
			System.out.println("You picked up an item!");
			player.pickUpItem(currentRoom.getItem());
			currentRoom.setItem(null);
		}
		//if the room contains a monster, combat is initiated.
		if(currentRoom.getMonster() != null){
			combat(player, currentRoom.getMonster());
		}
	}
	//the combat method initiates a loop in which player and monster deal damage/cast spells/use items until one party dies.
	public static void combat(Player player, Monster monster){
		Game.clearScreen();
		player.isFighting(true);
		System.out.printf("%s encounters a %s!! Prepare for combat! ", player.getName(), monster.getName());
		while(player.getHealth() > 0 && monster.getHealth() > 0 && player.isFighting()){
			player.takeTurn(monster);
			if(monster.getHealth() == 0){
				currentRoom.setMonster(null);
				break;
			}
			monster.takeTurn(player);
			System.out.printf("%s%s", player, monster);
		}
		if(player.getHealth() == 0){
			System.out.println("You have died.");
			isAlive = false;
		}
		else{//if the player wins, their health is boosted by the value of the monster's xp.
			System.out.printf("You killed %s. You gain %d experience.%n", monster.getName(), monster.getXP());
			player.setHealth(player.getHealth() + monster.getXP());
		}
	}
}