/*
 * https://www.hiredintech.com/classrooms/algorithm-design/lesson/19/task/31
 */
import java.util.*;

class CoverBorder {
	public static void main(String[] args) {
		//[5, 10], [3, 25], [46, 99], [39, 40], [45, 50]
		List<List<Integer>> radars=new LinkedList<List<Integer>>();
		List<Integer> list1=new LinkedList<Integer>();
		list1.add(5);
		list1.add(10);
		radars.add(list1);
		List<Integer> list2=new LinkedList<Integer>();
		list2.add(3);
		list2.add(25);
		radars.add(list2);
		List<Integer> list3=new LinkedList<Integer>();
		list3.add(46);
		list3.add(99);
		radars.add(list3);
		List<Integer> list4=new LinkedList<Integer>();
		list4.add(39);
		list4.add(40);
		radars.add(list4);
		List<Integer> list5=new LinkedList<Integer>();
		list5.add(45);
		list5.add(50);
		radars.add(list5);
		System.out.println(cover_the_border(1,radars));
	}

	public static int cover_the_border(int l, List<List<Integer>> radars) {
		List<Pair> pairs = new ArrayList<Pair>();
		for (List<Integer> list : radars) {
			pairs.add(new Pair(list.get(0), 1));
			pairs.add(new Pair(list.get(1), -1));
		}
		Collections.sort(pairs);
		int lastEnd = 0;
		int cover = 0;
		int total = 0;
		for (Pair pair : pairs) {
			if (pair.edge == 1) {
				cover++;
				if (cover == 1) {
					lastEnd = pair.index;
				}
			} else {
				cover--;
				if (cover == 0) {
					total += pair.index - lastEnd;
				}
			}
		}
		return total;
	}

	static class Pair implements java.lang.Comparable<Pair> {
		int index;
		int edge;

		public Pair(int index, int edge) {
			this.index = index;
			this.edge = edge;
		}

		public int compareTo(Pair pair) {
			if (this.index < pair.index) {
				return -1;
			}
			if (this.index == pair.index) {
				return 0;
			}
			return 1;
		}
	}
}
