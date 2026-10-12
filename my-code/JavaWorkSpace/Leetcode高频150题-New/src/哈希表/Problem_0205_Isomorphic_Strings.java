package 哈希表;

public class Problem_0205_Isomorphic_Strings {
//	public boolean isIsomorphic(String s, String t) {
//		HashMap<Character, Character> map = new HashMap<Character, Character>();
//		HashSet<Character> set = new HashSet<Character>();
//		char[] s1 = s.toCharArray();
//		char[] s2 = t.toCharArray();
//		for (int i = 0; i < s1.length; i++) {
//			if (!map.containsKey(s1[i])) {
//				if (!set.contains(s2[i])) {
//					map.put(s1[i], s2[i]);
//					set.add(s2[i]);
//				} else {
//					return false;
//				}
//			}
//		}
//		for (int i = 0; i < s2.length; i++) {
//			if (s2[i] != map.get(s1[i])) {
//				return false;
//			}
//		}
//		return true;
//	}
	// 2.数组替换
	public static boolean isIsomorphic(String s, String t) {
		int[] s2t = new int[129];// 将0空出为标志位
		int[] t2s = new int[129];
		for (int i = 0; i < s.length(); i++) {
			int x1 = s.charAt(i) + 1;
			int x2 = t.charAt(i) + 1;
			if (s2t[x1] == 0) {// 未放东西
				if (t2s[x2] != 0) {
					return false;
				}
				s2t[x1] = x2;
				t2s[x2] = x1;
			} else {// 放了东西
				if (s2t[x1] != x2) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		String s = "badc";
		String t = "baba";
		isIsomorphic(s, t);
	}
}
