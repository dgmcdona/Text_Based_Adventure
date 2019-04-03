public class Dungeon{
	private Room sEntranceHall;
	private Room tomb2;
	private Room sandChamber;
	private Room thePit;
	private Room nEntranceHall;
	private Room tomb1;
	private Room treasureChamber;
	private Room altarChamber;
	private Room secretPassage;
	private Room pharaohsTomb;
	private Room outside;
	private CanopicJar jar = new CanopicJar(30);
	private Scimitar scimitar = new Scimitar(10);
	private Monster mummy = new Monster("Mummy", 30, 20, 20);
	private Monster swarm = new Monster("Scarab Swarm", 100, 20, 40);

	public Dungeon(){
		this.sEntranceHall = new Room("South Entrance Hall", "You are in a hallway with walls adorned with hieroglyphs. \nA large one above the entrance reads: \'This is the tomb of Neferikare\'\n");
		this.tomb2 = new Room("Tomb 2", "You are in a room filled with small sarcophagi; \nThe heiroglyphs say that these are the children of Neferikare\n");
		this.sandChamber = new Room("Sand Chamber", "You step into a chamber filled with sand. Your feet immediately begin to sink. \nYou struggle to escape, but you only sink further.\n"+
								 "Your head sinks beneath the surface, sand fills your lungs, and you join the ranks of the dead.\n");
		this.thePit = new Room("Dark Room", "You step into the room; your right foot seeks ground but meets air, and you plummet into a pit.\nYour fall into darkness ends abruptly when your body is dashed against a pile of remains. \nYou join them in the afterlife.\n");
		this.nEntranceHall = new Room("North Entrance Hall", "You are in the north entrance hall. Entranceways surround you in every direction. \nChoose your path carefully.\n");
		this.tomb1 = new Room("Wives\' Tomb", "Three larger sarcophagi line the walls of this chamber, and the walls depict scenes of domestic life.\nThese belong to the wives of Neferikare.\n");
		this.treasureChamber = new Room("Treasure Chamber", "You are in a room filled with unimaginable treasures; Golden chariots, alabaster figurines, swords embellished with hieroglyphics.\n");
		this.altarChamber = new Room("Altar Chamber", "You find yourself in a circular chamber. In the center of the chamber is an altar to Osiris, god of the afterlife.\n");
		this.secretPassage = new Room("Secret Passage", "You are in a secret passageway. \nEvery surface is immaculate; This place has not been seen by living eyes for millenia.\n");
		this.pharaohsTomb = new Room("Pharoah\'s Tomb", "A large sarcophagus sits, unopened, squarely in the center of the room. \nYou are in the Tomb of Neferikare.\n");
		this.outside = new Room("Outside", "You emerge under the night sky. You have escaped with your life... This time.");
		
		this.sEntranceHall.setExits(nEntranceHall, tomb2, null, null);
		this.tomb2.setExits(tomb1, sandChamber, sEntranceHall, null);
		this.sandChamber.setExits(treasureChamber, null, tomb2, null);
		this.thePit.setExits(null, nEntranceHall, null, null);
		this.nEntranceHall.setExits(altarChamber, tomb1, thePit, sEntranceHall);
		this.tomb1.setExits(null, treasureChamber, nEntranceHall, tomb2);
		this.treasureChamber.setExits(secretPassage, null, tomb1, sandChamber);
		this.altarChamber.setExits(null, null, null, nEntranceHall);
		this.secretPassage.setExits(pharaohsTomb, null, null, treasureChamber);
		this.pharaohsTomb.setExits(null, null, null, secretPassage);

		this.nEntranceHall.setMonster(mummy);
		this.altarChamber.setItem(scimitar);
		this.tomb1.setItem(jar);
		this.secretPassage.setMonster(swarm);

	}

	public Room getRoom0(){return this.sEntranceHall;}
	public Room getRoom1(){return this.tomb2;}
	public Room getRoom2(){return this.sandChamber;}
	public Room getRoom3(){return this.thePit;}
	public Room getRoom4(){return this.nEntranceHall;}
	public Room getRoom5(){return this.tomb1;}
	public Room getRoom6(){return this.treasureChamber;}
	public Room getRoom7(){return this.altarChamber;}
	public Room getRoom8(){return this.secretPassage;}
	public Room getRoom9(){return this.pharaohsTomb;}
	public Room getRoom10(){return this.outside;}
}