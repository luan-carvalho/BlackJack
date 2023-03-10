package model.entities;

public class Dealer {

	private Deck deck;
	private DealerHand hand;

	public Dealer() {

		this.deck = new Deck();
		this.hand = new DealerHand();
	}

	public DealerHand getHand() {

		return this.hand;
	}

	public void reset() {

		this.deck = new Deck();
		this.hand = new DealerHand();
	}

	public Card deal() {

		if (deck.isEmpty()) {

			this.deck = new Deck();
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

		this.hand.get(1).setFaceUp();
		System.out.println("\n" + this.hand);

		while (this.hand.totalValue() < 17) {

			try {

				this.hand.addCard(deal());
				Thread.sleep(1000);
				System.out.println("\n" + this.hand);

			} catch (InterruptedException e) {

				System.out.println("\nError: " + e.getMessage());

			}
		}

	}

}
