package blackjack;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.entities.Card;
import model.entities.Dealer;
import model.entities.Player;
import model.entities.PlayerHand;
import model.enums.Rank;
import model.enums.Rating;
import model.exceptions.GeneralException;

public class BlackJackGame {

	private final double minimalBet = 200;

	private Player player;
	private Dealer dealer;
	private Scanner sc = new Scanner(System.in);

	private boolean gameOver;

	public BlackJackGame() {

		player = new Player();
		dealer = new Dealer();
		this.gameOver = false;

	}

	public void welcomeMessage() {

		System.out.println("********************* Welcome to the Monkeys Cassino *********************");
		System.out.println("\nMinimal bet: $" + this.minimalBet);

	}

	public void betPlacing() {

		System.out.println("\n*************************************************");
		System.out.println("\nYour money: $" + this.player.getMoney());

		while (true) {

			try {

				System.out.print("\nEnter your bet: ");
				double roundBet = sc.nextDouble();

				if (roundBet < this.minimalBet) {

					throw new GeneralException(String
							.format("your bet is lesser than the minimal ($%.2f). Enter a new bet!", this.minimalBet));

				} else {

					player.bet(roundBet);
					System.out.println("\nYour current money: " + String.format("$%.2f", this.player.getMoney()));
					break;

				}

			} catch (GeneralException e) {

				System.out.println("\nHey, bro, " + e.getMessage());
				continue;

			}
		}

	}

	public void dealingPlayerCards() {

		try {

			System.out.println("\n*************************************************");
			System.out.println("\nThe dealer is dealing your cards...");
			Thread.sleep(1000);
//			player.getHand().addCard(dealer.deal());
//			player.getHand().addCard(dealer.deal());
			player.getHand().addCard(new Card(Rank.Spades, Rating.Ten));
			player.getHand().addCard(new Card(Rank.Hearts, Rating.Ten));
			System.out.println("\nYour cards: " + player.getHand());
			Thread.sleep(2000);

		} catch (InterruptedException e) {

			System.out.println("Error: " + e.getMessage());
		}

	}

	public void dealingDealerCards() {

		if (!this.gameOver == true) {

			try {

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

	}

	public void checkBlackJack() {

		if (player.getHand().totalValue() == 21) {

			System.out.println("\n>>>>>>>>>>>>>>>>>>>> BLACKJACK <<<<<<<<<<<<<<<<<<<");
			player.payment(player.getHand().getBetAmount() * 3);
			System.out.println("\nYour current money: " + String.format("$%.2f", player.getMoney()));
			this.gameOver = true;

		}

	}

	public void playerMove() {

		if (!this.gameOver == true) {

			int currentHand = 1;

			while (true) {

				try {

					System.out.println("\n*************************************************");
					Thread.sleep(1000);
					System.out.println("\nMoves:\n\n1 - Hit\n2 - Stand\n3 - Double-down\n4 - Split");

					System.out.printf("\n\nYour move for hand #%d: ", currentHand);
					int move = sc.nextInt();

					if (move < 1 || move > 4) {

						throw new InputMismatchException("enter a valid answer!!");

					}

					if (move == 1) {

						player.getHand(currentHand).addCard(dealer.deal());
						System.out.println("\n" + player);

						if (player.getHand(currentHand).totalValue() > 21) {

							System.out.printf("\nHAND #%d BUSTED\n", currentHand);
							player.getHand(currentHand).bust();

							if (player.hasSplitted() && currentHand + 1 <= player.getHands().size()) {

								currentHand++;
								continue;

							} else {

								break;

							}

						} else {

							continue;
						}

					} else if (move == 2) {

						System.out.println("\nYou standed!");

						if (player.hasSplitted() && currentHand + 1 <= player.getHands().size()) {

							System.out.println("\n" + player);
							currentHand++;
							continue;

						} else {

							break;

						}

					} else if (move == 3) {

						player.doubleDown(currentHand, dealer.deal());
						System.out.println(player);

						if (player.getHand(currentHand).totalValue() > 21) {

							System.out.printf("\nHAND #%d BUSTED\n", currentHand);
							player.getHand(currentHand).bust();

							if (player.hasSplitted() && currentHand + 1 <= player.getHands().size()) {

								currentHand++;
								continue;

							} else {

								break;

							}

						} else {

							continue;
						}

					} else if (move == 4) {

						player.splitHand(currentHand);
						player.getHand(currentHand).addCard(dealer.deal());
						System.out.println("\n" + player);

					}

				} catch (InputMismatchException e) {

					System.out.println("\n>>>>>>>>>>>>> Hey, bro, " + e.getMessage());

				} catch (InterruptedException e) {

					System.out.println("\n>>>>>>>>>>>>> Error: " + e.getMessage());

				} catch (GeneralException e) {

					System.out.println("\n>>>>>>>>>>>>> Hey, bro, " + e.getMessage());
				}

			}
		}

	}

	public void revealDealerCard() {

		if (!this.gameOver == true) {

			System.out.println("\n*************************************************");
			System.out.println("\nThe dealer is revealing his card...");
			dealer.revealCard();

			if (dealer.getHand().totalValue() == 21) {

				this.gameOver = true;

				int handCounter = 1;

				for (PlayerHand h : player.getHands()) {

					if (h.hasBusted() == false) {

						System.out.printf("Hand #%d payback: $%.2f\n", handCounter,
								player.getHand(handCounter).getBetAmount() * 2);

					} else if (h.hasBusted() == true) {

						System.out.printf("Hand #%d BUSTED -> You lost your bet ($%.2f)", handCounter,
								player.getHand(handCounter).getBetAmount());

					}

					handCounter++;

				}
			}

		}
	}

	public void result() {

		if (!this.gameOver == true) {

			int handCounter = 1;

			for (PlayerHand h : player.getHands()) {

				if (h.totalValue() > dealer.getHand().totalValue() && h.hasBusted() == false) {

					System.out.printf("Hand #%d WIN -> Payback: $%.2f\n", handCounter,
							player.getHand(handCounter).getBetAmount() * 2);
					player.payment(player.getHand(handCounter).getBetAmount() * 2);

				} else if (h.totalValue() == dealer.getHand().totalValue() && h.hasBusted() == false) {

					System.out.printf("Hand #%d PUSH -> Payback: $%.2f\n", handCounter,
							player.getHand(handCounter).getBetAmount());
					player.payment(player.getHand(handCounter).getBetAmount());

				} else if (h.totalValue() < dealer.getHand().totalValue() && h.hasBusted() == false) {

					System.out.printf("Hand #%d LOST -> You lost your bet ($%.2f)", handCounter,
							player.getHand(handCounter).getBetAmount());

				} else if (h.hasBusted() == true) {

					System.out.printf("Hand #%d BUSTED -> You lost your bet ($%.2f)", handCounter,
							player.getHand(handCounter).getBetAmount());

				}

				handCounter++;

			}

			System.out.printf("\n\nYour current money: $%.2f", player.getMoney());

		}

	}

	public boolean playAgain() {

		while (true) {

			try {

				System.out.print("\nPlay again (y/n)?\n->: ");

				char ask = sc.next().toLowerCase().charAt(0);

				if (ask != 'y' && ask != 'n') {

					throw new InputMismatchException("enter a valid answer!!");

				} else if (ask == 'y') {

					dealer.reset();
					player.reset();
					this.gameOver = false;
					return true;

				} else {

					return false;
				}

			} catch (InputMismatchException e) {

				System.out.println("\nHey, bro, " + e.getMessage());
				continue;
			}
		}

	}

}
