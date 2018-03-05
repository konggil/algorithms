/*
 * {2,3,4,5,6,0,1}
 */

public class FindMinimum {
	public static void main(String[] args) {
		int[] arr = { 2, 3, 1 };
		System.out.println(arr[findStart(0, arr.length - 1, arr)]);
	}

	static int findStart(int low, int high, int[] arr) {
		if (low == high) {
			return low;
		}
		int mid = (low + high) / 2;
		if (mid == low) {
			if (arr[low] < arr[high]) {
				return low;
			} else {
				return high;
			}
		}
		if (arr[mid] > arr[low] && arr[mid] < arr[high]) {
			return low;
		}
		if (arr[mid] < arr[low]) {
			high = mid;
		} else {
			low = mid + 1;
		}
		return findStart(low, high, arr);
	}
}