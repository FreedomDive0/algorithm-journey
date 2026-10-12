package 二分专题;

public class LC278_第一个错误的版本 {
//	public class Solution extends VersionControl {
//		public int firstBadVersion(int n) {
//			int l = 1;
//			int r = n;
//			int index = n;
//			while (l <= r) {
//				int mid = l + (r - l) / 2;
//				if (isBadVersion(mid)) {
//					index = mid;
//					r = mid - 1;
//				} else {
//					l = mid + 1;
//				}
//
//			}
//			return index;
//		}
//	}
	public int firstBadVersion(int n) {
		int l = 0;
		int r = n;
		int index = n;// 记录结果 加快推进速度
		while (l <= r) {
			int m = l + ((r - l) >> 1);
			if (isBadVersion(m)) {
				index = m;
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		return index;
	}

	public boolean isBadVersion(int version) {
		return true;
	}
}
