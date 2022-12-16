package model.entities;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private List<Hand> hands;
	private double money;

	public Player() {

		this.hands = new ArrayList<>();
		this.money = 1000.00;

	}

	public double getMoney() {

		return this.money;

	}
}
