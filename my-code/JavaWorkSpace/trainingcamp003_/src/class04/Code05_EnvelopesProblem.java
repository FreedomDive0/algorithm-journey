package class04;

import java.util.Arrays;
import java.util.Comparator;

public class Code05_EnvelopesProblem {
	public static class Envelope {
		int length;
		int width;

		public Envelope(int length, int width) {
			this.length = length;
			this.width = width;
		}

	}

	public static class EnvelopeComparator implements Comparator<Envelope> {

		@Override
		public int compare(Envelope o1, Envelope o2) {// 信封按长递增 宽递减的顺序排序
			return o1.length != o2.length ? o1.length - o2.length : o2.width - o1.width;
		}

	}

	public static int max(int[][] martix) {
		if (martix == null || martix.length < 1 || martix[0].length < 1) {
			return 0;
		}
		Envelope[] envelopes = new Envelope[martix.length];
		for (int i = 0; i < martix.length; i++) {// 构建信封数组用于排序
			envelopes[i] = new Envelope(martix[i][0], martix[i][1]);
		}
		Arrays.sort(envelopes, new EnvelopeComparator());// 信封排序
// 使用长递增宽递减的数组 长递增宽递减 求宽组成的最长递增子序列就为答案 ∵长已经递增 又∵宽递减 长度长但宽小为干扰项 但是顺序为宽递减  ∴最长递增子序列为答案										
		int tail = 0;
		int[] ends = new int[martix.length];
		ends[0] = envelopes[0].width;
		for (int i = 1; i < envelopes.length; i++) {
			if (envelopes[i].width > ends[tail]) {// 没有floor
				ends[++tail] = envelopes[i].width;
			} else {// 二分查找 floor
				int left = 0;
				int right = tail;
				while (left < right) {
					int mid = left + ((right - left) >> 1);
					if (ends[mid] < envelopes[i].width) {
						left = mid + 1;
					} else {
						right = mid;
					}
				}
				ends[left] = envelopes[i].width;
			}
		}
		return tail + 1;
	}

	public static void main(String[] args) {
		int[][] test = { { 3, 4 }, { 2, 3 }, { 4, 5 }, { 1, 3 }, { 2, 2 }, { 3, 6 }, { 1, 2 }, { 3, 2 }, { 2, 4 } };
		System.out.println(max(test));
	}
}
