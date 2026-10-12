package 字符串专题;

public class LC165_比较版本号 {
	public static void main(String[] args) {
		compareVersion("0.1", "1.1");
	}

	public static int compareVersion(String version1, String version2) {
		String[] v1 = version1.split("\\.");
		String[] v2 = version2.split("\\.");
		if (v1.length > v2.length) {
			v2 = copy(v2, v1.length);
		} else if (v1.length < v2.length) {
			v1 = copy(v1, v2.length);
		}
		for (int i = 0; i < v1.length; i++) {
			int n1 = Integer.valueOf(v1[i]);
			int n2 = Integer.valueOf(v2[i]);
			if (n1 > n2) {
				return 1;
			} else if (n1 < n2) {
				return -1;
			}
		}
		return 0;
	}

	public static String[] copy(String[] v, int length) {
		String[] res = new String[length];
		for (int i = 0; i < length; i++) {
			if (i < v.length) {
				res[i] = v[i];
			} else {
				res[i] = "0";
			}
		}
		return res;
	}

}
