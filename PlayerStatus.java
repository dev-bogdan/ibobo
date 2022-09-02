

public class PlayerStatus {

	final String nickname;
	int score;
	int lives;
	int health;
	String weaponInHand;
	double posX;
	double poxY;
	private static final String gameName = "Paladins";

	// constructor
	public PlayerStatus(String nickName, double posX, double posY) {
		this.nickname = nickName;
		this.score = 1000;
		this.lives = 1;
		this.health = 100;
		this.weaponInHand = "default knife";
		this.posX = posX;
		this.poxY = posY;
	}

	public static String getGameName() {
		return gameName;
	}

	public String afisare() {
		return "The name of the player is: " + this.nickname + " \n" + "The score he achieved: " + this.score + "\n"
				+ "Number of the lives he have: " + this.lives + "\nHealth: " + this.health + "\nHe has in hand: "
				+ this.weaponInHand + "\nAnd the position in the game is: " + this.posX + " : " + this.poxY
				+ "\n===============================\n";
	}

	//// Getters and Setters
	public String getNickname() {
		return nickname;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPoxY() {
		return poxY;
	}

	public void setPoxY(double poxY) {
		this.poxY = poxY;
	}

	////

	private boolean isPerfect(int n) {
		int sum = 0;
		for (int i = 1; i < n; i++) {
			if (n % i == 0) {
				sum += i;
			}
		}
		if (sum == n) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isPrime(int n) {
		int fl = 0;
		if (n == 0 || n == 1) {
			return false;
		}
		for (int i = 2; i < n / 2; i++) {
			if (n % i == 0) {
				fl = 1;

			}
		}
		if (fl == 0) {
			return true;
		} else {
			return false;
		}

	}

	private boolean isEvenAndSumOfDigitsIsDivisibleWith3(int n) {
		int flag = 0;
		int copyN = n;
		int sum = 0;

		while (copyN != 0) {
			int lastD = copyN % 10;
			sum += lastD;
			copyN /= 10;
		}
		if (n % 2 == 0 && sum % 3 == 0) {
			flag = 1;
		}

		if (flag == 0) {
			return false;
		} else {
			return true;
		}
	}

	////

	public void findArtifact(int artifactCode) {
		if (isPerfect(artifactCode)) {
			this.score = this.score + 19000;
			this.lives = this.lives + 1;
			this.health = 100;
		} else if (isPrime(artifactCode)) {
			this.score += 9000;
			this.lives += 2;
			this.health += 25;
			if (this.health > 100) {
				this.health = 100;
			}
		} else if (isEvenAndSumOfDigitsIsDivisibleWith3(artifactCode)) {
			this.score -= 3000;
			if (this.score < 0) {
				this.score = 0;
			}
			this.health -= 25;
			if (this.health <= 0) {
				this.lives -= 1;
				if(this.lives == 0) {
					System.out.println("Game over!");
				}
				this.health = 100;
			}
		} else {
			this.score += artifactCode;
		}

	}

	public void setWeaponInHand(String weaponInHand) {
		weapon knife = new weapon("knife", 1000);
		weapon sniper = new weapon("sniper", 10000);
		weapon ak47 = new weapon("ak47", 20000);

		if (weaponInHand.equalsIgnoreCase(sniper.weaponInHand)) {
			if (sniper.cost <= this.score) {
				this.weaponInHand = weaponInHand;
				this.score = this.score - sniper.cost;
			}
		} else if(weaponInHand.equalsIgnoreCase(knife.weaponInHand)) {
			if(knife.cost <= this.score) {
				this.weaponInHand = weaponInHand;
				this.score = this.score - knife.cost;
			}
		} else if(weaponInHand.equalsIgnoreCase(ak47.weaponInHand)) {
			if(ak47.cost <= this.score) {
				this.weaponInHand = weaponInHand;
				this.score = this.score - ak47.cost;
			}
		}
	}

	public String getWeaponInHand() {
		return weaponInHand;
	}

	public void movePlayerTo(double positionX, double positionY) {
		this.posX = positionX;
		this.poxY = positionY;
	}
	
	private  double distanceBetweenTwoPlayers(PlayerStatus PlayerA, PlayerStatus PlayerB) {

		return Math.sqrt(Math.pow((PlayerA.posX - PlayerB.posX), 2)
				+ Math.pow((PlayerA.poxY - PlayerB.poxY), 2));
	}
	
	private double winProbSameWp(PlayerStatus Player) {
		double p = (3 * Player.getHealth() + Player.getScore() / 1000) / 4;
		return p;
	}

	

	public void shouldAttackOpponent(PlayerStatus actual,PlayerStatus opponent) {
		if(actual.weaponInHand.equalsIgnoreCase(opponent.weaponInHand)) {
			if(winProbSameWp(actual) > winProbSameWp(opponent)) {
				System.out.println(actual.nickname + " won!");
			} else {
				System.out.println(opponent.nickname + " won!");
			}
		} else {
			if(distanceBetweenTwoPlayers(actual, opponent) > 1000) {
				if(actual.weaponInHand.equalsIgnoreCase("sniper")) {
					System.out.println(actual.nickname + " won!");
				} else if(opponent.weaponInHand.equalsIgnoreCase("sniper")) {
					System.out.println(opponent.nickname + " won!");
				} else if(actual.weaponInHand.equalsIgnoreCase("ak47") && opponent.weaponInHand.equalsIgnoreCase("sniper")) {
					System.out.println(opponent.nickname + " won!");
				} else if(opponent.weaponInHand.equalsIgnoreCase("ak47") && actual.weaponInHand.equalsIgnoreCase("sniper")) {
					System.out.println(actual.nickname + " won!");
				} else if(actual.weaponInHand.equalsIgnoreCase("ak47") && opponent.weaponInHand.equalsIgnoreCase("knife")) {
					System.out.println(actual.nickname + " won!");
				} else if(opponent.weaponInHand.equalsIgnoreCase("ak47") && actual.weaponInHand.equalsIgnoreCase("knife")) {
					System.out.println(opponent.nickname + " won!");
				}
			} else {
				if(actual.weaponInHand.equalsIgnoreCase("ak47")) {
					System.out.println(actual.nickname + " won!");
				} else if(opponent.weaponInHand.equalsIgnoreCase("ak47")) {
					System.out.println(opponent.nickname + " won!");
				} else if(actual.weaponInHand.equalsIgnoreCase("ak47") && opponent.weaponInHand.equalsIgnoreCase("sniper")) {
					System.out.println(actual.nickname + " won!");
				} else if(opponent.weaponInHand.equalsIgnoreCase("ak47") && actual.weaponInHand.equalsIgnoreCase("sniper")) {
					System.out.println(opponent.nickname + " won!");
				} else if(actual.weaponInHand.equalsIgnoreCase("sniper") && opponent.weaponInHand.equalsIgnoreCase("knife")) {
					System.out.println(actual.nickname + " won!");
				} else if(opponent.weaponInHand.equalsIgnoreCase("sniper") && actual.weaponInHand.equalsIgnoreCase("knife")) {
					System.out.println(opponent.nickname + " won!");
				}
			}
		}
	}
}
