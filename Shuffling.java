import java.util.ArrayList;
import java.util.Random;

public class Shuffling {
	private static int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };
	private static String[] kinds = { "Spades", "Hearts", "Diamonds", "Clubs" };
	private static ArrayList<String> cards;

	public Shuffling() {
		cards = new ArrayList<>();
	}

	// This method fills all the 52 cards in a static list
	public void fillCards() {
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < kinds.length; j++) {
				String str = nums[i] + " " + kinds[j];
				cards.add(str);
			}
		}
	}

	/*
	 * This method distribute random cards to each player and removes the same card
	 * from the list which contains all the cards so that no player has duplicate
	 * cards
	 */
	public ArrayList<String> shuffleAndDistribute(Player player) {
		Random rnd = new Random();
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < 13; i++) {
			String card = cards.get(rnd.nextInt(cards.size()));
			list.add(card);
			cards.remove(card);
		}

		return list;
	}

}
