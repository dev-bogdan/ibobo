
public class weapon {
	String weaponInHand;
	int cost;
	
	public weapon(String wpInHand, int cost) {
		this.weaponInHand = wpInHand;
		this.cost = cost;
	}

	public String getWeaponInHand() {
		return weaponInHand;
	}

	public void setWeaponInHand(String weaponInHand) {
		this.weaponInHand = weaponInHand;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
}
