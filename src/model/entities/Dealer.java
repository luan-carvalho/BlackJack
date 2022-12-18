package model.entities;

public class Dealer {

	private Deck deck;
	private Hand hand;

	public Dealer() {

		this.deck = new Deck();
		this.hand = new DealerHand();
	}

	public Hand getHand() {

		return this.hand;
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

	public void dealDealersCards() {

		this.hand.addCard(deal());
		this.hand.addCard(deal());
		this.hand.get(1).setFaceDown();

	}

	public void revealCard() {

	}

}
