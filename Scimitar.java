
public class Scimitar extends Item{
	public Scimitar(int itemStrength){
		super(itemStrength);
	}

	public void useItem(Player player){
		player.setAttackPower(player.getAttackPower() + this.getItemStrength());
	}

	public String toString(){
		return String.format("[Scimitar] %s", super.toString());
	}
}