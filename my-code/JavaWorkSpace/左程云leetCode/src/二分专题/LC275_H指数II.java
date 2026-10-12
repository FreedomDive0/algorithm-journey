package 二分专题;

public class LC275_H指数II {
	public static void main(String[] args) {
		System.out.println(hIndex(new int[] { 0, 0 }));
	}

	public static int hIndex(int[] citations) {
		int l = 0;
		int r = citations.length - 1;
		int ans = 0;
		int n = citations.length;
		while (l <= r) {
			int mid = l + (r - l) / 2;
			if (citations[mid] < n - mid) {
				l = mid + 1;
			} else {
				ans = citations.length - mid;
				r = mid - 1;
			}
		}
		return ans;
	}
}
