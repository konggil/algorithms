/*
 * https://www.geeksforgeeks.org/aho-corasick-algorithm-pattern-searching/
 */


import java.util.*;

public class AC {
	public static void main(String[] args) {
		String text = "ahishers";
		String[] patterns = { "he", "she", "hers", "his" };
		ac(text, patterns);
	}

	public static void ac(String text, String[] patterns) {
		int[][] trie = new int[50][26];
		int[] fail = new int[50];
		int[] out = new int[50];
		for (int i = 0; i < 50; i++) {
			fail[i] = -1;
			out[i] = 0;
			for (int j = 0; j < 26; j++) {
				trie[i][j] = -1;
			}
		}
		int state = 1;
		for (int i = 0; i < patterns.length; i++) {
			int currentState = 0;
			for (int j = 0; j < patterns[i].length(); j++) {
				int ch = patterns[i].charAt(j) - 'a';
				if (trie[currentState][ch] == -1) {
					trie[currentState][ch] = state++;
				}
				currentState = trie[currentState][ch];
			}
			out[currentState] |= (1 << i);
		}
		/////
		for (int j = 0; j < 26; j++) {
			if (trie[0][j] == -1) {
				trie[0][j] = 0;
			}
		}
		/////
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int j = 0; j < 26; j++) {
			// if (trie[0][j] != -1) {
			if (trie[0][j] != 0) {
				fail[trie[0][j]] = 0;
				queue.offer(trie[0][j]);
			}
		}
		while (!queue.isEmpty()) {
			state = queue.poll();
			for (int j = 0; j < 26; j++) {
				if (trie[state][j] != -1) {
					int failure = fail[state];
					// while (failure != 0 && trie[failure][j] == -1) {
					while (trie[failure][j] == -1) {
						failure = fail[failure];
					}
					/*
					 * if (failure == 0) { fail[trie[state][j]] = 0; } else { fail[trie[state][j]] =
					 * trie[failure][j]; }
					 */
					fail[trie[state][j]] = trie[failure][j];
					out[trie[state][j]] |= out[failure];
					queue.offer(trie[state][j]);
				}
			}
		}
		////////////////////////////////////////////////////////
		state = 0;
		for (int i = 0; i < text.length(); i++) {
			int ch = text.charAt(i) - 'a';
			// while (state != 0 && trie[state][ch] == -1) {
			while (trie[state][ch] == -1) {
				state = fail[state];
			}
			// if (state != 0 || state == 0 && trie[state][ch] != -1) {
			state = trie[state][ch];
			if (out[state] != 0) {
				for (int j = 0; j < patterns.length; j++) {
					if ((out[state] & (1 << j)) != 0) {
						System.out
								.println("hit pattern " + patterns[j] + " at index " + (i - patterns[j].length() + 1));
					}
				}
			}
			// }
		}
	}
}
