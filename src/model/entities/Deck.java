package model.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.enums.Rank;
import model.enums.Rating;

public class Deck {

	private List<Card> cards;

	public Deck() {

		this.cards = new ArrayList<>();

		for (Rank rank : Rank.values()) {

			for (Rating rating : Rating.values()) {

				cards.add(new Card(rank, rating));

			}
		}

		Collections.shuffle(cards);
	}

	public Card deal() {

		return cards.remove(0);

	}

	public boolean isEmpty() {

		if (this.cards.size() == 0) {

			return true;

		} else {

			return false;
		}

	}

}