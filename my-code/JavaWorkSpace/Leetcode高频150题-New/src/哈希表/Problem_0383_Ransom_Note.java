package 哈希表;

import java.util.HashMap;

public class Problem_0383_Ransom_Note {
	// 1.哈希表
	public boolean canConstruct(String ransomNote, String magazine) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < magazine.length(); i++) {
			if (!map.containsKey(magazine.charAt(i))) {
				map.put(magazine.charAt(i), 1);
			} else {
				map.put(magazine.charAt(i), map.get(magazine.charAt(i)) + 1);
			}
		}
		for (int i = 0; i < ransomNote.length(); i++) {
			if (!map.containsKey(ransomNote.charAt(i))) {
				return false;
			} else {
				int num = map.get(ransomNote.charAt(i));
				if (num - 1 == 0) {
					map.remove(ransomNote.charAt(i));
				} else {
					map.put(ransomNote.charAt(i), num - 1);
				}
			}
		}
		return true;
	}

	// 2.只有小写字母 用数组替代哈希表
	public boolean canConstruct1(String ransomNote, String magazine) {
		int[] map = new int[26];
		for (int i = 0; i < magazine.length(); i++) {
			map[magazine.charAt(i) - 'a']++;
		}
		for (int i = 0; i < ransomNote.length(); i++) {
			if (map[ransomNote.charAt(i) - 'a'] == 0) {
				return false;
			}
			map[ransomNote.charAt(i) - 'a']--;
		}
		return true;
	}
}
