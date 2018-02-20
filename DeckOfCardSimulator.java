import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class DeckOfCardSimulator {

	public static void main(String[] args) {
		Deck deck = new Deck();
		deck.print(deck.cards);
		System.out.println(deck.sum(deck.cards));
		deck.shuffle();
		deck.print(deck.cards);
		System.out.println(deck.sum(deck.cards));
	}
}

class Deck {
	enum Face {
		A(1), J(10), Q(10), K(10), N(0);
		private int value;

		private Face(int value) {
			this.value = value;
		}

		public int value() {
			return value;
		}
	}

	enum Suit {
		Diamond, Heart, Club, Spade
	}

	class Card {
		Face face;
		Suit suit;
		int value;

		public Card(Suit suit, Face face, int value) {
			this.suit = suit;
			this.face = face;
			this.value = value;
		}

		public int value() {
			return value + face.value();
		}
	}

	List<Card> cards;
	final int cardNum = 52;

	public Deck() {
		cards = new LinkedList<Card>();
		for (Suit suit : Suit.values()) {
			for (int i = 2; i <= 10; i++) {
				cards.add(new Card(suit, Face.N, i));
			}
			for (Face face : Face.values()) {
				cards.add(new Card(suit, face, 0));
			}
		}
	}

	public void shuffle() {
		Random random = new Random();
		for (int i = 0; i < cardNum - 1; i++) {
			int rand = random.nextInt(cardNum - i) + i;
			if (rand != i) {
				Card temp1 = cards.get(rand);
				Card temp2 = cards.get(i);
				cards.set(i, temp1);
				cards.set(rand, temp2);
			}
		}
	}

	public int sum(List<Card> targetCards) {
		int value = 0;
		for (Card card : targetCards) {
			value += card.value();
		}
		return value;
	}

	public void print(List<Card> targetCards) {
		for (Card card : targetCards) {
			System.out.print("[" + card.suit + ":" + card.face + ":" + card.value() + "]");
		}
		System.out.println();
	}
}