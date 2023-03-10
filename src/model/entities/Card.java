package model.entities;

import model.enums.Rank;
import model.enums.Rating;
import model.exceptions.GeneralException;

public class Card {

	private Rank rank;
	private Rating rating;
	private int value;
	private boolean faceUp;

	public Card(Rank rank, Rating rating) {

		this.rank = rank;
		this.rating = rating;
		this.value = rating.getValue();
		this.faceUp = true;

	}

	public Rank getRank() {
		return rank;
	}

	public Rating getRating() {
		return rating;
	}

	public int getValue() {
		return value;
	}

	public boolean isFaceUp() {
		return faceUp;
	}

	public void setFaceUp() {

		this.faceUp = true;

	}

	public void setFaceDown() {

		this.faceUp = false;

	}

	// method to change the ace value
	// it throws an exception if the card is not an ace or if its an ace that has
	// already changed

	public void changeValue() throws GeneralException {

		if (this.rating != Rating.Ace) {

			throw new GeneralException("this is not an Ace!");

		} else if (this.rating == Rating.Ace && this.value == 1) {

			throw new GeneralException("this ace has already changed its value!");

		} else {

			this.value = 1;
		}
	}

	@Override
	public String toString() {

		if (this.faceUp == true) {

			return this.rating + " of " + this.rank + String.format(" (%d)", this.value);

		} else {

			return "This card is face-down";
		}

	}

	public boolean compareTo(Card other) {

		if (this.rating == other.rating) {

			return true;

		} else {

			return false;
		}

	}
}
