import java.util.*;

public class KMP {

	public static void main(String[] args) {
		System.out.println((new KMP()).strStr(args[0], args[1]));
	}

	public int strStr(String haystack, String needle) {
		int[] nextVal = new int[haystack.length()];
		getNextVal(needle, nextVal);
		int i = 0, j = 0;
		while (i < haystack.length() && j < needle.length()) {
			if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
				++i;
				++j;
			} else {
				j = nextVal[j];
			}
		}
		if (j >= needle.length()) {
			return i - needle.length();
		} else {
			return 0;
		}
	}

	void getNextVal(String haystack, int[] nextVal) {
		int i = 0, j = -1;
		nextVal[i] = j;
		while (i < haystack.length() - 1) {
			if (j == -1 || haystack.charAt(i) == haystack.charAt(j)) {
				++i;
				++j;
				if (haystack.charAt(i) != haystack.charAt(j)) {
					nextVal[i] = j;
				} else {
					nextVal[i] = nextVal[j];
				}
			} else {
				j = nextVal[j];
			}
		}
	}

}
