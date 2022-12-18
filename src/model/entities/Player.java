package model.entities;

import java.util.ArrayList;
import java.util.List;

import model.exceptions.GeneralException;

public class Player {

	private List<Hand> hands;
	private double money;
	private int splittedHands;

	public Player() {

		this.hands = new ArrayList<>();
		this.money = 1000.00;
		this.splittedHands = 0;

	}

	public double getMoney() {

		return this.money;

	}

	public Hand getHand() {

		return this.hands.get(0);

	}

	public Hand getHand(int handIndex) {

		return this.hands.get(handIndex - 1);

	}

	public List<Hand> getHands() {

		return this.hands;

	}

	public void bet(double amount) throws GeneralException {

		if (this.money < amount) {

			throw new GeneralException("you don't have enough money");

		} else {

			this.money -= amount;
			this.hands.get(0).setBet(amount);

		}
	}

	public void hint(Card card) {

		this.hands.get(0).addCard(card);

	}

	public void hint(Card card, int handIndex) {

		this.hands.get(handIndex - 1).addCard(card);

	}

	public void doubleDown(Card card, int handIndex) {

		try {

			if (this.money < this.hands.get(handIndex - 1).getBetAmount()) {

				throw new GeneralException("you don't have enough money for a split");

			} else {

				this.money -= this.hands.get(handIndex - 1).getBetAmount();
				this.hands.get(handIndex - 1).doubleDown();

			}

		} catch (GeneralException e) {

			System.out.println("Hey, bro, " + e.getMessage());

		}

	}

	public void splitHand(int handIndex) {

		try {

			if (this.hands.get(handIndex - 1).get(-1).compareTo(this.hands.get(handIndex - 1).get(-2))) {

				throw new GeneralException("you can't split because you don't have two equal cards");

			} else if (this.money < this.hands.get(handIndex - 1).getBetAmount()) {

				throw new GeneralException("you don't have enough money for a split");

			} else if (this.splittedHands >= 4) {

				throw new GeneralException("you can't split because you have the maximum of splitted hands");

			} else if (handIndex > 4) {

				throw new GeneralException("enter a number < 4");
			}

			else {

				this.hands.add(new Hand());
				this.hands.get(handIndex).addCard(this.hands.get(0).removeCard(-1));
				this.splittedHands += 1;
				this.hands.get(handIndex).setBet(this.hands.get(handIndex - 1).getBetAmount());

			}

		} catch (GeneralException e) {

			System.out.println("Hey, bro, " + e.getMessage());

		}
	}

}
