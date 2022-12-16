package model.entities;

public class Dealer {

	private Deck deck;

	public Dealer() {

		this.deck = new Deck();
	}

	public void resetDeck() {

		this.deck = new Deck();
	}

	public Card deal() {

		if (deck.isEmpty()) {

			this.resetDeck();
			return deck.deal();

		} else {

			return deck.deal();

		}

	}

}
