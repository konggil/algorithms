package cc;

public class ArrayCombination {
	public static void main(String[] args) {
		String[] array = { "a", "b", "c" };
		subset(array);
		System.out.println("==========================");
		subset1(array);
	}

	public static void subset(String[] array) {
		boolean[] subsetFlag = new boolean[array.length];
		subset(subsetFlag, 0, array);
	}

	private static void subset(boolean[] subsetFlag, int index, String[] array) {
		if (index >= subsetFlag.length) {
			for (int i = 0; i < subsetFlag.length; i++) {
				if (subsetFlag[i] == true) {
					System.out.print(array[i] + " ");
				}
			}
			System.out.println();
		} else {
			subsetFlag[index] = false;
			subset(subsetFlag, index + 1, array);
			subsetFlag[index] = true;
			subset(subsetFlag, index + 1, array);
		}
	}

	public static void subset1(String[] array) {
		String[] subset = new String[array.length];
		subset1(subset, 0, array, 0);
	}

	private static void subset1(String[] subset, int indexSubset, String[] array, int indexArray) {
		if (indexArray >= array.length) {
			for (int i = 0; i < indexSubset; i++) {
				System.out.print(subset[i] + " ");
			}
			System.out.println();
		} else {
			subset1(subset, indexSubset, array, indexArray + 1);
			for (int i = indexArray; i < array.length; i++) {
				subset[indexSubset] = array[i];
				subset1(subset, indexSubset + 1, array, i + 1);
			}
		}
	}
}