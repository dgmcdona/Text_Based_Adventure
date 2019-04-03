
public class GameCharacter {
	private String name;
	private int health;
	private int attackPower;
	//private ArrayList <Item> = new ArrayList <Item>(0);

	public GameCharacter(String name, int health, int attackPower){
		this.name = name;
		this.health = health;
		this.attackPower = attackPower;
	}

	public void takeDamage(int damage){
		if(this.health > damage){
			this.health -= damage;
		}
		else{
			this.health = 0;
		}
	}
	public void setName(String name){this.name = name;}
	public void setAttackPower(int attackPower){this.attackPower = attackPower;}
	public void setHealth(int health){this.health = health;}
	public String getName(){return this.name;}
	public int getHealth(){return this.health;}
	public int getAttackPower(){return this.attackPower;}
	public String toString(){
		return String.format("[Name] %s [Health] %d [Attack Power] %d", this.name, this.health, this.attackPower);
	}
}