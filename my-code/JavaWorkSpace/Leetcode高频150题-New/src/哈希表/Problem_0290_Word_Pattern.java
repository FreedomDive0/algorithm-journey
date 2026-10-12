package 哈希表;

import java.util.HashMap;

public class Problem_0290_Word_Pattern {
	public boolean wordPattern(String pattern, String s) {
		HashMap<String, Character> s2p = new HashMap<String, Character>();
		HashMap<Character, String> p2s = new HashMap<Character, String>();
		String[] words = s.split(" ");
		char[] p = pattern.toCharArray();
		if (p.length != words.length) {
			return false;
		}
		for (int i = 0; i < words.length; i++) {
			if (s2p.containsKey(words[i])) {
				if (!p2s.containsKey(p[i]) || !words[i].equals(p2s.get(p[i]))) {
					return false;
				}
			}
			if (p2s.containsKey(p[i])) {
				if (!s2p.containsKey(words[i]) || p[i] != s2p.get(words[i])) {
					return false;
				}
			}
			s2p.put(words[i], p[i]);
			p2s.put(p[i], words[i]);
		}
		return true;
	}
}
