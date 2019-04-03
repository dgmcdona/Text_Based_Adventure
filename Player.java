import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.ArrayList;



public class Player extends GameCharacter{
	private boolean isFighting;
	private int mana;
	Scanner input;
	ArrayList <Item> inventory = new ArrayList <Item>(0);

	public Player(String name, int health, int attackPower, int mana){
		super(name, health, attackPower);
		input = new Scanner(System.in);
		this.mana = mana;
	}

	public void attack(Monster target){
		target.takeDamage(super.getAttackPower());
	}

	public boolean isFighting() { return this.isFighting; }
	public void isFighting(boolean isFighting) {this.isFighting = isFighting;}

	public void castSpell(Monster target){
		if(this.mana > 10){
			target.takeDamage((int)target.getHealth() / 2);
			this.mana -= 10;
		}
		else{
			System.out.println("Not enough mana.");
		}
	}

	public void takeTurn(Monster monster){
		int choice;
		boolean correct = false;
		while(!correct){
			System.out.println("What would you like to do?\n" +
								"1. Attack\n2. Cast a Spell\n3. Recharge mana\n4. Items\n5. Run!\n");
			try{
				choice = input.nextInt();
				if(choice < 6 && choice > 0){
					correct = true;
					takeTurn(choice, monster);
				}
			}
			catch(NoSuchElementException e){
				System.out.println("\nPlease enter a numberic choice from the menu.\n");
			}
		}//end loop
	}//end method

	public void useItem(){
		int choice;
		boolean correct = false;
		while(!correct){
			printItems();
			try{
				choice = input.nextInt();
				if(choice < this.inventory.size() && choice >= 0){
					correct = true;
					this.inventory.get(choice).useItem(this);
				}
			}
			catch(NoSuchElementException e){
				System.out.println("\nPlease enter a numberic choice from the menu.\n");
			}
		}//end loop
	}

	public void chargeMana(){
		this.mana += 8;
	}
	public int getMana(){
		return this.mana;
	}
	private void takeTurn(int choice, Monster monster){
		switch(choice){
			case 1: {this.attack(monster);} break;
			case 2: {this.castSpell(monster);} break;
			case 3: {this.chargeMana();} break;
			case 4: {useItem();} break;
			case 5: {this.isFighting = false;} break;
		}
	}
	private void printItems(){
		String itemString = "";
		for(int i = 0; i < this.inventory.size(); i++){
			itemString += String.format("[Enter %d]: %s%n", i, this.inventory.get(i));
		}
		System.out.println(itemString);

	}
	public void pickUpItem(Item item){
		this.inventory.add(item);
	}

	public String toString(){
		return String.format("%s [Mana] %d%n", super.toString(), this.mana);
	}
}