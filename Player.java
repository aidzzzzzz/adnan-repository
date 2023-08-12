public class Player {
	private String player;
	private Player teammate;

	public Player(String n) {
		player = n;
	}

	public String getName() {
		return player;
	}

	public void setName(String name) {
		this.player = name;
	}

	public Player getTeammate() {
		return teammate;
	}

	public void setTeammate(Player teammate) {
		this.teammate = teammate;
	}

	public boolean equals(Player player) {
		String playerr = this.player;
		if (playerr == player.getName()) {
			return true;
		} else {
			return false;
		}

	}

	public String toString() {
		String toReturn = "Name: " + player;
		return toReturn;
	}

}
