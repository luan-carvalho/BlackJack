package blackjack;

import java.util.Scanner;

import model.entities.Dealer;
import model.entities.Player;
import model.exceptions.GeneralException;

public class BlackJackGame {

	private final double minimalBet = 1000.00;

	private Player player;
	private Dealer dealer;
	private Scanner sc = new Scanner(System.in);

	public BlackJackGame() {

		player = new Player();
		dealer = new Dealer();

	}

	public void welcomeMessage() {

		System.out.println("********************* Welcome to the Monkeys Cassino *********************");
		System.out.println("\nMinimal bet: $" + this.minimalBet);

	}

	public void betPlacing() {

		try {

			System.out.print("Enter your bet: ");
			double roundBet = sc.nextDouble();

			if (roundBet < this.minimalBet) {

				throw new GeneralException("your bet is lesser than the minimal. Enter a new bet");

			} else {

				player.bet(roundBet);

			}

		} catch (GeneralException e) {

			System.out.println("Hey, bro, " + e.getMessage());
		}

	}

}
