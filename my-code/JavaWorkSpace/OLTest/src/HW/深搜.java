package HW;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class 深搜 {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			HashSet<Integer> set = new HashSet<Integer>();
			set.add(0);
			int n = in.nextInt();
			int[] w = new int[n];
			int[] nums = new int[n];
			for (int i = 0; i < n; i++) {
				w[i] = in.nextInt();
			}
			for (int i = 0; i < n; i++) {
				nums[i] = in.nextInt();
			}
			for (int i = 0; i < n; i++) {
				ArrayList<Integer> list = new ArrayList<Integer>(set);
				for (int j = 1; j <= nums[i]; j++) {
					for (int k = 0; k < list.size(); k++) {
						set.add(list.get(k) + w[i] * j);
					}
				}
			}
			System.out.println(set.size());
		}

	}
}
