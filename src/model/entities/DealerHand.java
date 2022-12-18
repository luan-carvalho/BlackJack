package model.entities;

public class DealerHand extends Hand {

	public DealerHand() {

		super();

	}

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

		if (this.cards.stream().filter(c -> c.isFaceUp() == false).toList().size() > 0) {

			sb.append("]");

		} else {

			sb.append("]" + String.format(" (%d)", totalValue()));

		}

		return sb.toString();

	}

}
