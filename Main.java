import java.util.*;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws CardNotAvailableException, CardNotValidException, TeammateNotAvailableException {

		System.out.println(
				"HELLO WORLD\t\t\tWelcome TO 400!!!!\t\t\tHELLO WORLD\n\t\t\t\n\t\tRULES:  - Every player must make a bid; no player "
						+ "may pass!\n\t\t\t- No suit is named in the bid!\n\t\t\t- The minimum bid for each player is two, regardless "
						+ "if the player can or cannot take two tricks.\n\t\t\t- When a player's point total is above 30, "
						+ "his minimum bid becomes three.\n\t\t\t- Once one of you reaches a score of 41, you and "
						+ "your teammate WIN! And the game is OVER!\n\t\t\t- If one of you displays a card not in hand, the game will"
						+ " CRASH!\n\t\t\t- If one of you displays an invalid card, the game will CRASH!\n\t\t\t-"
						+ " Cards: 52.\n\t\t\t- Play: ClockWise.\n\nENJOY WORLD\t\t\tWELCOME TO 400!!!!\t\t\tENJOY WORLD");
		System.out.println();
		Scanner scan = new Scanner(System.in);
		System.out.print("Player 1 please enter your name: ");
		String name1 = scan.nextLine();
		Player player1 = new Player(name1);
		System.out.print("Player 2 please enter your name: ");
		String name2 = scan.nextLine();
		Player player2 = new Player(name2);
		System.out.print("Player 3 please enter your name: ");
		String name3 = scan.nextLine();
		Player player3 = new Player(name3);
		System.out.print("Player 4 please enter your name: ");
		String name4 = scan.nextLine();
		Player player4 = new Player(name4);

		System.out.println();

		boolean cond = true;

		System.out.print(player1.getName() + " please choose your teammate: ");
		String teammate1 = scan.nextLine();
		while(teammate1.equalsIgnoreCase(player2.getName())) {
			System.out.print("Cannot choose " + player2.getName() + " as a teammate, choose another teammate: ");
			teammate1 = scan.nextLine();
		}
		if(!( teammate1.equalsIgnoreCase(player2.getName()) || teammate1.equals(player3.getName()) || teammate1.equalsIgnoreCase(player4.getName()))){
			throw new TeammateNotAvailableException("No such player exists! Game CRASHED!");
		}
		if (teammate1.equalsIgnoreCase(player3.getName())) {
			player1.setTeammate(player3);
			player3.setTeammate(player1);
		} else {
			player1.setTeammate(player4);
			player4.setTeammate(player1);
			cond = false;
		}

		System.out.println();

		System.out.println(player1.getName() + " and " + teammate1 + " are teammates!");

		System.out.println();

		if (cond) {
			player2.setTeammate(player4);
			player4.setTeammate(player2);
			System.out.println(player2.getName() + " and " + player4.getName() + " are teammates!\n");
		} else {
			player2.setTeammate(player3);
			player3.setTeammate(player2);
			System.out.println(player2.getName() + " and " + player3.getName() + " are teammates!");
		}

		System.out.println();

		int score1 = 0, finalScore1 = 0;
		int score2 = 0, finalScore2 = 0;
		int score3 = 0, finalScore3 = 0;
		int score4 = 0, finalScore4 = 0;
		int bid1, bid2, bid3, bid4;
		int counter = 1;
		while (finalScore1 < 41 || finalScore2 < 41 || finalScore3 < 41 || finalScore4 < 41) {
			Game game = new Game();

			ArrayList<String> player1Deck = Game.orderDeck(player1);
			System.out.println();
			ArrayList<String> player2Deck = Game.orderDeck(player2);
			System.out.println();
			ArrayList<String> player3Deck = Game.orderDeck(player3);
			System.out.println();
			ArrayList<String> player4Deck = Game.orderDeck(player4);
			
			System.out.println();

			while (true) {
				System.out.print(player1.getName() + " please enter your bid for this round: ");
				bid1 = scan.nextInt();
				while (!Game.checkBid(bid1, finalScore1)) {
					System.out.print(player1.getName() + " you can't bid less than 2, enter another score: ");
					bid1 = scan.nextInt();
				}

				System.out.print(player2.getName() + " please enter your bid for this round: ");
				bid2 = scan.nextInt();
				while (!Game.checkBid(bid2, finalScore2)) {
					System.out.print(player2.getName() + " you can't bid than 2, enter another score: ");
					bid2 = scan.nextInt();
				}

				System.out.print(player3.getName() + " please enter your bid for this round: ");
				bid3 = scan.nextInt();
				while (!Game.checkBid(bid3, finalScore3)) {
					System.out.print(player3.getName() + " you can't bid less than 2, enter another score: ");
					bid3 = scan.nextInt();
				}

				System.out.print(player4.getName() + " please enter your bid for this round: ");
				bid4 = scan.nextInt();
				while (!Game.checkBid(bid4, finalScore4)) {
					System.out.print(player4.getName() + "you can't bid less than 2, enter another score: ");
					bid4 = scan.nextInt();
				}

				if (Game.checkSumOfBids(bid1, bid2, bid3, bid4)) {
					break;
				} else {
					System.out.println("\nSum of bids did not exceed 10! Please enter different bids: \n");
				}
				
			
			}

			System.out.println();

			Player winner = Game.winnerWinnerChickenDinner(player1, player1Deck, player2, player2Deck, player3,
					player3Deck, player4, player4Deck);

			if (winner.equals(player1)) {
				score1++;
			} else if (winner.equals(player2)) {
				score2++;
			} else if (winner.equals(player3)) {
				score3++;
			} else {
				score4++;
			}

			System.out.println();

			System.out.println("Winner of this round: " + winner.getName());

			System.out.println();

			Game.printAllDecks(player1, player1Deck, player2, player2Deck, player3, player3Deck, player4, player4Deck);

			System.out.println();

			while (counter != 13) {

				if (winner.equals(player1)) {
					winner = Game.winnerWinnerChickenDinner(player1, player1Deck, player2, player2Deck, player3,
							player3Deck, player4, player4Deck);
				} else if (winner.equals(player2)) {
					winner = Game.winnerWinnerChickenDinner(player2, player2Deck, player3, player3Deck, player4,
							player4Deck, player1, player1Deck);
				} else if (winner.equals(player3)) {
					winner = Game.winnerWinnerChickenDinner(player3, player3Deck, player4, player4Deck, player1,
							player1Deck, player2, player2Deck);
				} else {
					winner = Game.winnerWinnerChickenDinner(player4, player4Deck, player1, player1Deck, player2,
							player2Deck, player3, player3Deck);
				}

				System.out.println();

				System.out.println("Winner of this round: " + winner.getName());

				System.out.println();

				counter++;
				if (winner.equals(player1)) {
					score1++;
				} else if (winner.equals(player2)) {
					score2++;
				} else if (winner.equals(player3)) {
					score3++;
				} else {
					score4++;
				}

				Game.printAllDecks(player1, player1Deck, player2, player2Deck, player3, player3Deck, player4,
						player4Deck);

				System.out.println();
			}
			score1 = Game.compareScores(score1, bid1);
			score2 = Game.compareScores(score2, bid2);
			score3 = Game.compareScores(score3, bid3);
			score4 = Game.compareScores(score4, bid4);

			finalScore1 += score1;
			score1 = 0;
			finalScore2 += score2;
			score2 = 0;
			finalScore3 += score3;
			score3 = 0;
			finalScore4 += score4;
			score4 = 0;

			System.out.println(player1.getName() + "'s updated score: " + finalScore1);
			System.out.println(player2.getName() + "'s updated score: " + finalScore2);
			System.out.println(player3.getName() + "'s updated score: " + finalScore3);
			System.out.println(player4.getName() + "'s updated score: " + finalScore4);

		}

		if (finalScore1 >= 41) {
			System.out.println("CONGRATULATIONS!!!!! " + player1.getName().toUpperCase() + " AND "
					+ player1.getTeammate().getName().toUpperCase() + " WON THE GAME!!!");
		} else if (finalScore2 >= 41) {
			System.out.println("CONGRATULATIONS!!!!! " + player2.getName().toUpperCase() + " AND "
					+ player2.getTeammate().getName().toUpperCase() + " WON THE GAME!!!");
		} else if (finalScore3 >= 41) {
			System.out.println("CONGRATULATIONS!!!!! " + player3.getName().toUpperCase() + " AND "
					+ player3.getTeammate().getName().toUpperCase() + " WON THE GAME!!!");
		} else if (finalScore3 >= 41) {
			System.out.println("CONGRATULATIONS!!!!! " + player4.getName().toUpperCase() + " AND "
					+ player4.getTeammate().getName().toUpperCase() + " WON THE GAME!!!");
		}

	}

}
