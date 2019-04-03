public class Monster extends GameCharacter{
	private int xp;
	public Monster(String name, int health, int atk, int xp){
		super(name, health, atk);
		this.xp = xp;
	}

	public void attack(Player hero){
		hero.takeDamage(super.getAttackPower());
	}

	public void takeTurn(Player hero){
		this.attack(hero);
	}

	public int getXP(){
		return this.xp;
	}

	public String toString(){
		return String.format("%s [xp] %d%n", super.toString(), this.xp);
	}
}