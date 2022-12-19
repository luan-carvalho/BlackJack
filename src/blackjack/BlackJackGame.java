package blackjack;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.entities.Dealer;
import model.entities.Player;
import model.exceptions.GeneralException;

public class BlackJackGame {

	private final double minimalBet = 200;

	private Player player;
	private Dealer dealer;
	private Scanner sc = new Scanner(System.in);

	private boolean blackJack;

	public BlackJackGame() {

		player = new Player();
		dealer = new Dealer();
		this.blackJack = false;

	}

	public void welcomeMessage() {

		System.out.println("********************* Welcome to the Monkeys Cassino *********************");
		System.out.println("\nMinimal bet: $" + this.minimalBet);
		System.out.println("Your start money: $" + this.player.getMoney());

	}

	public void betPlacing() {

		System.out.println("\n*************************************************");

		while (true) {

			try {

				System.out.print("\nEnter your bet: ");
				double roundBet = sc.nextDouble();

				if (roundBet < this.minimalBet) {

					throw new GeneralException("your bet is lesser than the minimal. Enter a new bet");

				} else {

					player.bet(roundBet);
					player.printMoney();
					break;

				}

			} catch (GeneralException e) {

				System.out.println("\nHey, bro, " + e.getMessage());
				continue;

			}
		}

	}

	public void dealingCards() {

		try {

			System.out.println("\n*************************************************");
			System.out.println("\nThe dealer is dealing your cards...");
			Thread.sleep(1000);
			player.getHand().addCard(dealer.deal());
			player.getHand().addCard(dealer.deal());
			System.out.println("\nYour cards: " + player.getHand());
			
			Thread.sleep(2000);

			System.out.println("\n*************************************************");
			System.out.println("\nThe dealer is dealing his cards...");
			Thread.sleep(1000);
			dealer.dealDealersCards();
			System.out.println("\nDealer's cards: " + dealer.getHand());
			Thread.sleep(2000);

		} catch (InterruptedException e) {

			System.out.println("Error: " + e.getMessage());
		}

	}

	public void checkBlackJack() {

		if (player.getHand().totalValue() > 21) {

			System.out.println("\n>>>>>>>>>>>>>>>>>>>> BLACKJACK <<<<<<<<<<<<<<<<<<<");
			player.payment(player.getHand().getBetAmount() * 3);
			System.out.println("\nYour current money: " + String.format("$%f", player.getMoney()));
			this.blackJack = true;

		}

	}

	public void playerMove() {

		if (!this.blackJack == true) {

			while (true) {

				try {

					int currentHand = 1;

					System.out.println("\n*************************************************");
					System.out.println("\nMoves:\n\n1 - Hit\n2 - Stand\n3 - Double-down\n4 - Split");
					System.out.printf("\n\nYour move for hand #%d: ", currentHand);
					int move = sc.nextInt();

					if (move < 1 || move > 4) {

						throw new InputMismatchException("Enter a valid answer!!");

					}
					if (move == 1) {

//						player.getHand(currentHand).addCard(dealer.deal());
//						System.out.println(player.getHand());
//
//						if (player.getHand().totalValue() > 21) {
//
//							System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>> YOU BUSTED <<<<<<<<<<<<<<<<<<<<<");
//							System.out.println();
//						}

						break;

					} else if (move == 2) {

						System.out.println("\nYou standed!");

					} else if (move == 3) {

						break;

					} else if (move == 4) {

						break;

					}

				} catch (InputMismatchException e) {

					System.out.println("\nError: " + e.getMessage());
				}

			}
		}

	}

}
