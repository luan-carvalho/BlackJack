package model.entities;

import java.util.ArrayList;
import java.util.List;

import model.enums.Rating;
import model.exceptions.GeneralException;

public abstract class Hand {

	protected List<Card> cards;

	public Hand() {

		this.cards = new ArrayList<>();
	}

	public List<Card> getCards() {

		return this.cards;
	}

	public Card get(int cardIndex) {

		if (cardIndex < 0) {

			return this.cards.get(this.cards.size() + cardIndex);

		} else {

			return this.cards.get(cardIndex);

		}
	}

	/*
	 * This method implements the ace value changing logic by iterating through the
	 * cards in a hand while the hand + new card sums up to 21 and changing all aces
	 * value to 1 if it hasn't changed yet
	 * 
	 */

	public void addCard(Card card) {

		while (this.totalValue() + card.getValue() > 21) {

			try {

				for (Card c : this.cards) {

					if (c.getRating() == Rating.Ace && c.getValue() == 11) {

						System.out.println("\n >>>>>>>>> Ace value changed <<<<<<<<<\n");
						c.changeValue();
						continue;

					}

				}

				break;

			} catch (GeneralException e) {

				System.out.println("Hey, bro, " + e.getMessage());
			}

		}

		this.cards.add(card);
	}

	public void removeCard(Card card) {

		this.cards.remove(card);
	}

	public Card removeCard(int index) {

		if (index < 0) {

			return this.cards.remove(this.cards.size() + index);

		} else {

			return this.cards.remove(index - 1);

		}
	}

	public int totalValue() {

		int total = 0;

		for (Card card : cards) {

			total += card.getValue();
		}

		return total;
	}

	@Override
	public abstract String toString();

}
