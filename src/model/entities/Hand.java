package model.entities;

import java.util.ArrayList;
import java.util.List;

import model.enums.Rating;
import model.exceptions.ChangeAceValueException;

public class Hand {

	private List<Card> cards;
	private double betAmount;

	public Hand() {

		this.cards = new ArrayList<>();
		this.betAmount = 0;
	}

	public List<Card> getCards() {

		return this.cards;
	}

	public double getBetAmount() {

		return this.betAmount;

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

			} catch (ChangeAceValueException e) {

				System.out.println("Hey, bro, " + e.getMessage());
			}

		}

		this.cards.add(card);
	}

	public int totalValue() {

		int total = 0;

		for (Card card : cards) {

			total += card.getValue();
		}

		return total;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append("[");

		for (int i = 0; i < cards.size(); i++) {

			if (i == 0) {

				sb.append(cards.get(i));

			} else {

				sb.append(", " + cards.get(i));
			}

		}

		sb.append("]" + String.format(" (%d)", this.totalValue()));

		return sb.toString();
	}

}
