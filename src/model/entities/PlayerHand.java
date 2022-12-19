package model.entities;

public class PlayerHand extends Hand {

	private double bet;
	private boolean busted;

	public PlayerHand() {

		super();
		this.bet = 0;
		this.busted = false;

	}

	public double getBetAmount() {

		return this.bet;

	}

	public boolean hasBusted() {

		return this.busted;

	}

	public void bust() {

		this.busted = true;

	}

	public void setBet(double amount) {

		this.bet += amount;

	}

	public void doubleDown() {

		this.bet = this.bet * 2;

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

		sb.append("]" + String.format(" (%d)", this.totalValue()) + String.format(" ($%.2f)", this.bet));

		return sb.toString();

	}

}
