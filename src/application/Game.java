package application;

import java.util.Scanner;

import model.entities.Card;
import model.entities.Deck;
import model.entities.Hand;
import model.enums.Rank;
import model.enums.Rating;

public class Game {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Deck deck = new Deck();
		Hand hand = new Hand();

		hand.addCard(new Card(Rank.Spades, Rating.Ace));
		hand.addCard(new Card(Rank.Spades, Rating.Two));
		hand.addCard(new Card(Rank.Spades, Rating.Three));
		
		System.out.println(hand);

		while (true) {

			System.out.println("***************************");
			System.out.print("\nHit or stand (h/s)\n->: ");

			char ask = sc.next().charAt(0);

			if (ask == 'h') {

				hand.addCard(deck.deal());
				System.out.println("\n" + hand);
				continue;

			} else {

				System.out.println("\n" + hand);
				System.out.println("\nYou standed!! Game over");
				break;
			}

		}

		sc.close();
//		try {
//
//			Card card = new Card(Rank.Spades, Rating.Ace);
//			System.out.println(card);
//			card.changeValue();
//			System.out.println(card);
//
//		} catch (ChangeAceValueException e) {
//
//			System.out.println(e.getMessage());
//		}


	}

}
