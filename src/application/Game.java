package application;

import blackjack.BlackJackGame;
import model.entities.Dealer;
import model.entities.Player;

public class Game {

	public static void main(String[] args) {

		BlackJackGame game = new BlackJackGame(new Player(), new Dealer());
		game.start();

	}

}
