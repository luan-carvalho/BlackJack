package application;

import blackjack.BlackJackGame;

public class Game {

	public static void main(String[] args) {

		BlackJackGame game = new BlackJackGame();

		game.welcomeMessage();

		game.betPlacing();
		game.dealingCards();
		game.checkBlackJack();
		game.playerMove();

	}

}
