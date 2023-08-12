import java.util.*;

public class Game {
	static Scanner scan = new Scanner(System.in);
	static Shuffling shuffle = new Shuffling();

	public Game() {
		shuffle.fillCards();
	}

	public static void removeCard(String str, ArrayList<String> playerDeck) throws CardNotAvailableException {
		if (playerDeck.contains(str)) {
			playerDeck.remove(str);
		} else {
			throw new CardNotAvailableException("You don't have such a card! Game CRASHED!");
		}
	}

	public static Player winnerWinnerChickenDinner(Player player1, ArrayList<String> player1Deck, Player player2,
			ArrayList<String> player2Deck, Player player3, ArrayList<String> player3Deck, Player player4,
			ArrayList<String> player4Deck) throws CardNotAvailableException, CardNotValidException {
		Player winner = new Player("");

		String p1Card = enterCard(player1, player1Deck);
		String p2Card = enterCard(player2, player2Deck);
		String p3Card = enterCard(player3, player3Deck);
		String p4Card = enterCard(player4, player4Deck);
		checkIfSomeoneDidNotPlayTheSameKind(player1, p1Card, player1Deck, player2, p2Card, player2Deck, player3, p3Card,
				player3Deck, player4, p4Card, player4Deck, getKind(p1Card));

		switch (getKind(p1Card)) {
		case "Hearts":
			if (getKind(p2Card).equals("Hearts") && getKind(p3Card).equals("Hearts")
					&& getKind(p4Card).equals("Hearts")) {
				winner = possibleWinnerOutOf4(player1, p1Card, player2, p2Card, player3, p3Card, player4, p4Card);
			} else if (getKind(p2Card).equals("Hearts") && getKind(p3Card).equals("Hearts")
					&& !getKind(p4Card).equals("Hearts")) {
				winner = possibleWinnerOutOf3(player1, p1Card, player2, p2Card, player3, p3Card);
			} else if (getKind(p2Card).equals("Hearts") && !getKind(p3Card).equals("Hearts")
					&& getKind(p4Card).equals("Hearts")) {
				winner = possibleWinnerOutOf3(player1, p1Card, player2, p2Card, player4, p4Card);
			} else if (!getKind(p2Card).equals("Hearts") && getKind(p3Card).equals("Hearts")
					&& getKind(p4Card).equals("Hearts")) {
				winner = possibleWinnerOutOf3(player1, p1Card, player3, p3Card, player4, p4Card);
			} else if (!getKind(p2Card).equals("Hearts") && !getKind(p3Card).equals("Hearts")
					&& getKind(p4Card).equals("Hearts")) {
				winner = possibleWinnerOutOf2(player1, p1Card, player4, p4Card);
			} else if (getKind(p2Card).equals("Hearts") && !getKind(p3Card).equals("Hearts")
					&& !getKind(p4Card).equals("Hearts")) {
				winner = possibleWinnerOutOf2(player1, p1Card, player2, p2Card);
			} else if (!getKind(p2Card).equals("Hearts") && getKind(p3Card).equals("Hearts")
					&& !getKind(p4Card).equals("Hearts")) {
				winner = possibleWinnerOutOf2(player1, p1Card, player3, p3Card);
			} else {
				return player1;
			}
			break;

		case "Diamonds":
			if (getKind(p2Card).equals("Diamonds") && getKind(p3Card).equals("Diamonds")
					&& getKind(p4Card).equals("Diamonds")) {
				winner = possibleWinnerOutOf4(player1, p1Card, player2, p2Card, player3, p3Card, player4, p4Card);
			} else if (getKind(p2Card).equals("Diamonds") && getKind(p3Card).equals("Diamonds")
					&& !getKind(p4Card).equals("Diamonds")) {
				if (atLeastAHeart(getKind(p2Card), getKind(p3Card), getKind(p4Card))) {
					winner = player4;
				} else {
					winner = possibleWinnerOutOf3(player1, p1Card, player2, p2Card, player3, p3Card);
				}
			} else if (getKind(p2Card).equals("Diamonds") && !getKind(p3Card).equals("Diamonds")
					&& getKind(p4Card).equals("Diamonds")) {
				if (atLeastAHeart(getKind(p2Card), getKind(p3Card), getKind(p4Card))) {
					winner = player3;
				} else {
					winner = possibleWinnerOutOf3(player1, p1Card, player2, p2Card, player4, p4Card);
				}
			} else if (!getKind(p2Card).equals("Diamonds") && getKind(p3Card).equals("Diamonds")
					&& getKind(p4Card).equals("Diamonds")) {
				if (atLeastAHeart(getKind(p2Card), getKind(p3Card), getKind(p4Card))) {
					winner = player2;
				} else {
					winner = possibleWinnerOutOf3(player1, p1Card, player3, p3Card, player4, p4Card);
				}
			} else if (!getKind(p2Card).equals("Diamonds") && !getKind(p3Card).equals("Diamonds")
					&& getKind(p4Card).equals("Diamonds")) {
				if (atLeastAHeart(getKind(p2Card), getKind(p3Card), getKind(p4Card))) {
					winner = heartsHasHighestPriority(player2, p2Card, player3, p3Card, player4, p4Card);
				} else {
					winner = possibleWinnerOutOf2(player1, p1Card, player4, p4Card);
				}
			} else if (getKind(p2Card).equals("Diamonds") && !getKind(p3Card).equals("Diamonds")
					&& !getKind(p4Card).equals("Diamonds")) {
				if (atLeastAHeart(getKind(p2Card), getKind(p3Card), getKind(p4Card))) {
					winner = heartsHasHighestPriority(player2, p2Card, player3, p3Card, player4, p4Card);
				} else {
					winner = possibleWinnerOutOf2(player1, p1Card, player2, p2Card);
				}
			} else if (!getKind(p2Card).equals("Diamonds") && getKind(p3Card).equals("Diamonds")
					&& !getKind(p4Card).equals("Diamonds")) {
				if (atLeastAHeart(getKind(p2Card), getKind(p3Card), getKind(p4Card))) {
					winner = heartsHasHighestPriority(player2, p2Card, player3, p3Card, player4, p4Card);
				} else {
					winner = possibleWinnerOutOf2(player1, p1Card, player3, p3Card);
				}
			} else {
				return player1;
			}
			break;

		case "Spades":
			if (getKind(p2Card).equals("Spades") && getKind(p3Card).equals("Spades")
					&& getKind(p4Card).equals("Spades")) {

				winner = possibleWinnerOutOf4(player1, p1Card, player2, p2Card, player3, p3Card, player4, p4Card);

			} else if (getKind(p2Card).equals("Spades") && getKind(p3Card).equals("Spades")
					&& !getKind(p4Card).equals("Spades")) {
				if (atLeastAHeart(getKind(p2Card), getKind(p3Card), getKind(p4Card))) {
					winner = player4;
				} else {
					winner = possibleWinnerOutOf3(player1, p1Card, player2, p2Card, player3, p3Card);
				}
			} else if (getKind(p2Card).equals("Spades") && !getKind(p3Card).equals("Spades")
					&& getKind(p4Card).equals("Spades")) {
				if (atLeastAHeart(getKind(p2Card), getKind(p3Card), getKind(p4Card))) {
					winner = player3;
				} else {
					winner = possibleWinnerOutOf3(player1, p1Card, player2, p2Card, player4, p4Card);
				}
			} else if (!getKind(p2Card).equals("Spades") && getKind(p3Card).equals("Spades")
					&& getKind(p4Card).equals("Spades")) {
				if (atLeastAHeart(getKind(p2Card), getKind(p3Card), getKind(p4Card))) {
					winner = player2;
				} else {
					winner = possibleWinnerOutOf3(player1, p1Card, player3, p3Card, player4, p4Card);
				}
			} else if (!getKind(p2Card).equals("Spades") && !getKind(p3Card).equals("Spades")
					&& getKind(p4Card).equals("Spades")) {
				if (atLeastAHeart(getKind(p2Card), getKind(p3Card), getKind(p4Card))) {
					winner = heartsHasHighestPriority(player2, p2Card, player3, p3Card, player4, p4Card);
				} else {
					winner = possibleWinnerOutOf2(player1, p1Card, player4, p4Card);
				}
			} else if (getKind(p2Card).equals("Spades") && !getKind(p3Card).equals("Spades")
					&& !getKind(p4Card).equals("Spades")) {
				if (atLeastAHeart(getKind(p2Card), getKind(p3Card), getKind(p4Card))) {
					winner = heartsHasHighestPriority(player2, p2Card, player3, p3Card, player4, p4Card);
				} else {
					winner = possibleWinnerOutOf2(player1, p1Card, player2, p2Card);
				}
			} else if (!getKind(p2Card).equals("Spades") && getKind(p3Card).equals("Spades")
					&& !getKind(p4Card).equals("Spades")) {
				if (atLeastAHeart(getKind(p2Card), getKind(p3Card), getKind(p4Card))) {
					winner = heartsHasHighestPriority(player2, p2Card, player3, p3Card, player4, p4Card);
				} else {
					winner = possibleWinnerOutOf2(player1, p1Card, player3, p3Card);
				}
			} else {
				return player1;
			}
			break;

		case "Clubs":
			if (getKind(p2Card).equals("Clubs") && getKind(p3Card).equals("Clubs") && getKind(p4Card).equals("Clubs")) {

				winner = possibleWinnerOutOf4(player1, p1Card, player2, p2Card, player3, p3Card, player4, p4Card);

			} else if (getKind(p2Card).equals("Clubs") && getKind(p3Card).equals("Clubs")
					&& !getKind(p4Card).equals("Clubs")) {
				if (atLeastAHeart(getKind(p2Card), getKind(p3Card), getKind(p4Card))) {
					winner = player4;
				} else {
					winner = possibleWinnerOutOf3(player1, p1Card, player2, p2Card, player3, p3Card);
				}
			} else if (getKind(p2Card).equals("Clubs") && !getKind(p3Card).equals("Clubs")
					&& getKind(p4Card).equals("Clubs")) {
				if (atLeastAHeart(getKind(p2Card), getKind(p3Card), getKind(p4Card))) {
					winner = player3;
				} else {
					winner = possibleWinnerOutOf3(player1, p1Card, player2, p2Card, player4, p4Card);
				}
			} else if (!getKind(p2Card).equals("Clubs") && getKind(p3Card).equals("Clubs")
					&& getKind(p4Card).equals("Clubs")) {
				if (atLeastAHeart(getKind(p2Card), getKind(p3Card), getKind(p4Card))) {
					winner = player2;
				} else {
					winner = possibleWinnerOutOf3(player1, p1Card, player3, p3Card, player4, p4Card);
				}
			} else if (!getKind(p2Card).equals("Clubs") && !getKind(p3Card).equals("Clubs")
					&& getKind(p4Card).equals("Clubs")) {
				if (atLeastAHeart(getKind(p2Card), getKind(p3Card), getKind(p4Card))) {
					winner = heartsHasHighestPriority(player2, p2Card, player3, p3Card, player4, p4Card);
				} else {
					winner = possibleWinnerOutOf2(player1, p1Card, player4, p4Card);
				}
			} else if (getKind(p2Card).equals("Clubs") && !getKind(p3Card).equals("Clubs")
					&& !getKind(p4Card).equals("Clubs")) {
				if (atLeastAHeart(getKind(p2Card), getKind(p3Card), getKind(p4Card))) {
					winner = heartsHasHighestPriority(player2, p2Card, player3, p3Card, player4, p4Card);
				} else {
					winner = possibleWinnerOutOf2(player1, p1Card, player2, p2Card);
				}
			} else if (!getKind(p2Card).equals("Clubs") && getKind(p3Card).equals("Clubs")
					&& !getKind(p4Card).equals("Clubs")) {
				if (atLeastAHeart(getKind(p2Card), getKind(p3Card), getKind(p4Card))) {
					winner = heartsHasHighestPriority(player2, p2Card, player3, p3Card, player4, p4Card);
				} else {
					winner = possibleWinnerOutOf2(player1, p1Card, player3, p3Card);
				}
			} else {
				return player1;
			}
			break;

		}
		if (winner.equals(player1)) {
			winner = new Player(player1.getName());
		} else if (winner.equals(player2)) {
			winner = new Player(player2.getName());
		} else if (winner.equals(player3)) {
			winner = new Player(player3.getName());
		} else {
			winner = new Player(player4.getName());
		}

		return winner;

	}

	public static String enterCard(Player player, ArrayList<String> playerDeck){
		System.out.print(player.getName() + " please place your card: ");
		String cardPlayer = scan.nextLine();
		return cardPlayer;
	}

	public static int getNumber(String str) {
		return Integer.parseInt(str.substring(0, str.indexOf(' ')));
	}

	public static String getKind(String str) {
		return str.substring(str.indexOf(' ') + 1);
	}

	public static boolean checkSumOfBids(int score1, int score2, int score3, int score4) {
		if (score1 + score2 + score3 + score4 < 11) {
			return false;
		}

		return true;
	}

	public static boolean checkBid(int bid, int score) {
		if (score < 30) {
			if (bid < 2) {
				return false;
			} else {
				return true;
			}
		} else {
			if (bid < 3) {
				return false;
			} else {
				return true;
			}
		}
	}

	public static int compareScores(int score, int bid) {
		if (score >= bid) {
			switch (bid) {
			case 2:
				score = 2;
				break;
			case 3:
				score = 3;
				break;
			case 4:
				score = 4;
				break;
			case 5:
				score = 10;
				break;
			case 6:
				score = 12;
				break;
			case 7:
				score = 14;
				break;
			case 8:
				score = 16;
				break;
			case 9:
				score = 27;
				break;
			}
		} else {
			score = 0;
			score -= bid;
		}

		return score;
	}

	public static Player heartsHasHighestPriority(Player player1, String card1, Player player2, String card2,
			Player player3, String card3) {
		if (card1.equals("Hearts") && card2.equals("Hearts") && card3.equals("Hearts")) {
			return possibleWinnerOutOf3(player1, card1, player2, card2, player3, card3);
		} else if (!card1.equals("Hearts") && card2.equals("Hearts") && card3.equals("Hearts")) {
			return possibleWinnerOutOf2(player2, card2, player3, card3);
		} else if (card1.equals("Hearts") && !card2.equals("Hearts") && card3.equals("Hearts")) {
			return possibleWinnerOutOf2(player1, card1, player3, card3);
		} else if (card1.equals("Hearts") && card2.equals("Hearts") && !card3.equals("Hearts")) {
			return possibleWinnerOutOf2(player1, card1, player2, card2);
		} else if (card1.equals("Hearts") && !card2.equals("Hearts") && !card3.equals("Hearts")) {
			return player1;
		} else if (!card1.equals("Hearts") && card2.equals("Hearts") && !card3.equals("Hearts")) {
			return player2;
		} else {
			return player3;
		}
	}

	public static boolean atLeastAHeart(String p1Card, String p2Card, String p3Card) {
		if (getKind(p1Card).equals("Hearts") || getKind(p2Card).equals("Hearts") || getKind(p3Card).equals("Hearts")) {
			return true;
		}

		return false;
	}

	public static void checkIfSomeoneDidNotPlayTheSameKind(Player player1, String p1Card, ArrayList<String> p1Deck,
			Player player2, String p2Card, ArrayList<String> p2Deck, Player player3, String p3Card,
			ArrayList<String> p3Deck, Player player4, String p4Card, ArrayList<String> p4Deck, String mainKind)
			throws CardNotValidException, CardNotAvailableException {

		if (!getKind(p2Card).equals(mainKind)) {
			if ((p2Deck.contains("1 " + mainKind) || p2Deck.contains("2 " + mainKind)
					|| p2Deck.contains("3 " + mainKind) || p2Deck.contains("4 " + mainKind)
					|| p2Deck.contains("5 " + mainKind) || p2Deck.contains("6 " + mainKind)
					|| p2Deck.contains("7 " + mainKind) || p2Deck.contains("8 " + mainKind)
					|| p2Deck.contains("9 " + mainKind) || p2Deck.contains("10 " + mainKind)
					|| p2Deck.contains("11 " + mainKind) || p2Deck.contains("12 " + mainKind)
					|| p2Deck.contains("13 " + mainKind))) {
				throw new CardNotValidException("One of you placed an invalid card! Game CRASHED!");
			} else {
				removeCard(p1Card, p1Deck);
				removeCard(p2Card, p2Deck);
				removeCard(p3Card, p3Deck);
				removeCard(p4Card, p4Deck);
			}
		} else if (!getKind(p3Card).equals(mainKind)) {
			if ((p3Deck.contains("1 " + mainKind) || p3Deck.contains("2 " + mainKind)
					|| p3Deck.contains("3 " + mainKind) || p3Deck.contains("4 " + mainKind)
					|| p3Deck.contains("5 " + mainKind) || p3Deck.contains("6 " + mainKind)
					|| p3Deck.contains("7 " + mainKind) || p3Deck.contains("8 " + mainKind)
					|| p3Deck.contains("9 " + mainKind) || p3Deck.contains("10 " + mainKind)
					|| p3Deck.contains("11 " + mainKind) || p3Deck.contains("12 " + mainKind)
					|| p3Deck.contains("13 " + mainKind))) {
				throw new CardNotValidException("One of you placed an invalid card! Game CRASHED!");
			} else {
				removeCard(p1Card, p1Deck);
				removeCard(p2Card, p2Deck);
				removeCard(p3Card, p3Deck);
				removeCard(p4Card, p4Deck);
			}
		} else if (!getKind(p4Card).equals(mainKind)) {
			if ((p4Deck.contains("1 " + mainKind) || p4Deck.contains("2 " + mainKind)
					|| p4Deck.contains("3 " + mainKind) || p4Deck.contains("4 " + mainKind)
					|| p4Deck.contains("5 " + mainKind) || p4Deck.contains("6 " + mainKind)
					|| p4Deck.contains("7 " + mainKind) || p4Deck.contains("8 " + mainKind)
					|| p4Deck.contains("9 " + mainKind) || p4Deck.contains("10 " + mainKind)
					|| p4Deck.contains("11 " + mainKind) || p4Deck.contains("12 " + mainKind)
					|| p4Deck.contains("13 " + mainKind))) {
				throw new CardNotValidException("One of you placed an invalid card! Game CRASHED!");
			} else {
				removeCard(p1Card, p1Deck);
				removeCard(p2Card, p2Deck);
				removeCard(p3Card, p3Deck);
				removeCard(p4Card, p4Deck);
			}
		} else {
			removeCard(p1Card, p1Deck);
			removeCard(p2Card, p2Deck);
			removeCard(p3Card, p3Deck);
			removeCard(p4Card, p4Deck);
		}

	}

	public static Player possibleWinnerOutOf4(Player player1, String p1Card, Player player2, String p2Card,
			Player player3, String p3Card, Player player4, String p4Card) {
		int max = getMaxOutOf4(getNumber(p1Card), getNumber(p2Card), getNumber(p3Card), getNumber(p4Card));
		if (max == getNumber(p1Card)) {
			return player1;
		} else if (max == getNumber(p2Card)) {
			return player2;
		} else if (max == getNumber(p3Card)) {
			return player3;
		} else
			return player4;

	}

	public static Player possibleWinnerOutOf3(Player player1, String p1Card, Player player2, String p2Card,
			Player player3, String p3Card) {
		int max = getMaxOutOf3(getNumber(p1Card), getNumber(p2Card), getNumber(p3Card));
		if (max == getNumber(p1Card)) {
			return player1;
		} else if (max == getNumber(p2Card)) {
			return player2;
		}

		return player3;

	}

	public static Player possibleWinnerOutOf2(Player player1, String p1Card, Player player2, String p2Card) {
		int max = getMaxOutOf2(getNumber(p1Card), getNumber(p2Card));
		if (max == getNumber(p1Card)) {
			return player1;
		}

		return player2;

	}

	/*
	 * The reason why 3 methods were created is because each time players deposit
	 * their cards, we want to compare the kinds then after eliminating the players
	 * that are impossible to win, we compare the cards to return the winner. 3
	 * methods were created so that we only compare players that have the same kind,
	 * and there are several cases as seen in the winnerWinnerChickenDinner method
	 */
	public static int getMaxOutOf4(int card1, int card2, int card3, int card4) {
		if (card1 == 1) {
			return card1;
		} else if (card2 == 1) {
			return card2;
		} else if (card3 == 1) {
			return card3;
		} else if (card4 == 1) {
			return card4;
		} else {
			return Math.max(Math.max(card1, card2), Math.max(card3, card4));
		}
	}

	public static int getMaxOutOf3(int card1, int card2, int card3) {
		if (card1 == 1) {
			return card1;
		} else if (card2 == 1) {
			return card2;
		} else if (card3 == 1) {
			return card3;
		} else {
			return Math.max(Math.max(card1, card2), card3);
		}
	}

	public static int getMaxOutOf2(int card1, int card2) {
		if (card1 == 1) {
			return card1;
		} else if (card2 == 1) {
			return card2;
		} else {
			return Math.max(card1, card2);
		}
	}
	/*
	 * When we compare the kinds, we now have to compare the values.
	 */

	public static void printAllDecks(Player player1, ArrayList<String> p1Deck, Player player2, ArrayList<String> p2Deck,
			Player player3, ArrayList<String> p3Deck, Player player4, ArrayList<String> p4Deck) {

		System.out.print(player1.getName() + "'s Deck: ");
		for (int i = 0; i < p1Deck.size(); i++) {
			System.out.print(p1Deck.get(i) + "  ");
		}

		System.out.println();

		System.out.print(player2.getName() + "'s Deck: ");
		for (int i = 0; i < p2Deck.size(); i++) {
			System.out.print(p2Deck.get(i) + "  ");
		}

		System.out.println();

		System.out.print(player3.getName() + "'s Deck: ");
		for (int i = 0; i < p3Deck.size(); i++) {
			System.out.print(p3Deck.get(i) + "  ");
		}

		System.out.println();

		System.out.print(player4.getName() + "'s Deck: ");
		for (int i = 0; i < p4Deck.size(); i++) {
			System.out.print(p4Deck.get(i) + " ");
		}

		System.out.println();

	}

	public static ArrayList<String> orderDeck(Player player) {
		ArrayList<String> deck = shuffle.shuffleAndDistribute(player);
		ArrayList<String> orderedDeck = new ArrayList<>();
		for (int i = 0; i < 13; i++) {
			if (getKind(deck.get(i)).equals("Hearts")) {
				orderedDeck.add(deck.get(i));
			}
		}

		for (int i = 0; i < 13; i++) {
			if (getKind(deck.get(i)).equals("Spades")) {
				orderedDeck.add(deck.get(i));
			}
		}

		for (int i = 0; i < 13; i++) {
			if (getKind(deck.get(i)).equals("Diamonds")) {
				orderedDeck.add(deck.get(i));
			}
		}

		for (int i = 0; i < 13; i++) {
			if (getKind(deck.get(i)).equals("Clubs")) {
				orderedDeck.add(deck.get(i));
			}
		}
		System.out.print(player.getName() + "'s Deck: ");
		for (int i = 0; i < orderedDeck.size(); i++) {
			System.out.print(orderedDeck.get(i) + "  ");
		}

		return orderedDeck;
	}

}
