
public abstract class Item {
	private int itemStrength;

	public Item(int itemStrength){
		this.itemStrength = itemStrength;
	}

	public abstract void useItem(Player player);

	public int getItemStrength(){
		return this.itemStrength;
	}

	public void setItemStrength(int strength){
		this.itemStrength = strength;
	}

	public String toString(){
		return String.format("[Strength] %d%n", this.getItemStrength());
	}

}