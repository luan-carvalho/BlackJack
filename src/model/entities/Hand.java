package model.entities;

import java.util.ArrayList;
import java.util.List;

import model.enums.Rating;
import model.exceptions.ChangeAceValueException;

public class Hand {

	private List<Card> cards;

	public Hand() {

		this.cards = new ArrayList<>();
	}

	public List<Card> getCards() {

		return this.cards;
	}

	public void addCard(Card card) {

		while (this.totalValue() + card.getValue() > 21) {

			try {

				for (Card c : this.cards) {

					if (c.getRating() == Rating.Ace && c.getValue() == 11) {

						System.out.println("\n >>>>>>>>> Ace value changed <<<<<<<<<");
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
