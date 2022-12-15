package model.entities;

import model.enums.Rank;
import model.enums.Rating;
import model.exceptions.ChangeAceValueException;

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

	public void changeValue() throws ChangeAceValueException {

		if (this.rating != Rating.Ace) {

			throw new ChangeAceValueException("this is not an Ace!");

		} else if (this.rating == Rating.Ace && this.value == 11) {

			throw new ChangeAceValueException("this ace has already been changed!");

		} else {

			this.value = 11;
		}
	}

	@Override
	public String toString() {

		if (this.faceUp == true) {

			return this.rating + " of " + this.rank;

		} else {

			return "This card is face-down";
		}

	}
}
