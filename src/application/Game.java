package application;

import blackjack.BlackJackGame;

public class Game {

	public static void main(String[] args) {

		BlackJackGame game = new BlackJackGame();

		game.welcomeMessage();

		do {

			game.betPlacing();
			game.dealingPlayerCards();
			game.checkBlackJack();
			game.dealingDealerCards();
			game.playerMove();
			game.revealDealerCard();
			game.result();

		} while (game.playAgain());

	}

}
