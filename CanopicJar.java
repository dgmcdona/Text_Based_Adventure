
public class CanopicJar extends Item{
	public CanopicJar(int itemStrength){
		super(itemStrength);
	}

	public void useItem(Player player){
		player.setHealth(player.getHealth() + this.getItemStrength());
	}

	public String toString(){
		return String.format("[Canopic Jar] %s", super.toString());
	}
}