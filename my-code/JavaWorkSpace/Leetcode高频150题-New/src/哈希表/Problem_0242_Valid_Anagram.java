package 哈希表;

public class Problem_0242_Valid_Anagram {
	public boolean isAnagram(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		int[] map = new int[128];
		int all = s.length();
		for (int i = 0; i < s.length(); i++) {
			map[s.charAt(i)]++;
		}
		for (int i = 0; i < t.length(); i++) {
			map[t.charAt(i)]--;
			if (map[t.charAt(i)] >= 0) {
				all--;
			}
		}
		return all == 0;
	}
}
