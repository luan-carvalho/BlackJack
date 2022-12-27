package model.entities;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DealerHand extends Hand {

	public DealerHand() {

		super();

	}

	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append(Arrays.toString(this.cards.toArray()));

		if (!(this.cards.stream().filter(c -> c.isFaceUp() == false).collect(Collectors.toList()).size() > 0)) {

			sb.append("]" + String.format(" (%d)", totalValue()));

		}

		return sb.toString();

	}

}
