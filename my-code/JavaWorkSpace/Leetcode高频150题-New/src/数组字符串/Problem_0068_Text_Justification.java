package 数组字符串;

import java.util.ArrayList;
import java.util.List;

public class Problem_0068_Text_Justification {

//	public List<String> fullJustify(String[] words, int maxWidth) {
//		TreeMap<Integer, List<Integer>> map = new TreeMap<>();
//		for (int i = 0; i < words.length; i++) {
//			int size = words[i].length();
//			if (!map.containsKey(size)) {
//				ArrayList<Integer> list = new ArrayList<Integer>();
//				list.add(i);
//				map.put(size, list);
//			} else {
//				map.get(size).add(i);
//			}
//		}
//		List<String> res = new ArrayList<String>();
//		while(!map.isEmpty()) {
//			
//		}
//	}
	public List<String> FullJustify(String[] words, int maxWidth) {
		List<String> ans = new ArrayList();
		int e = 0;
		int n = words.length;
		while (true) {
			int s = e;
			int sumLen = 0;
			while (e < n && sumLen + words[e].length() + e - s <= maxWidth)// e-s表示最少空格数妙手
			{
				sumLen += words[e++].length();
			}
			// 特殊情况:最后一行
			if (e == n) {
				StringBuilder tmp = Join(words, s, e, " ");
				tmp.append(Blank(maxWidth - tmp.length()));
				ans.add(tmp.toString());
				return ans;
			}
			int wordsNum = e - s;
			int spaceNum = maxWidth - sumLen;// sumLen只表示单词的长度妙手
			// 特殊情况:只有一个单词
			if (wordsNum == 1) {
				StringBuilder tmp = new StringBuilder();
				tmp.append(words[s]);
				tmp.append(Blank(spaceNum));
				ans.add(tmp.toString());
				continue;
			}
			int anySpace = spaceNum / (wordsNum - 1);// 每个单词间最少的空格数
			int extraSpace = spaceNum % (wordsNum - 1);//
			StringBuilder sb = new StringBuilder();
			sb.append(Join(words, s, s + extraSpace + 1, Blank(anySpace + 1)));
			sb.append(Blank(anySpace));
			sb.append(Join(words, s + extraSpace + 1, e, Blank(anySpace)));
			ans.add(sb.toString());
		}
	}

	// 将字符串数组从l到r用sep连接
	public StringBuilder Join(String[] words, int l, int r, String sep) {
		StringBuilder sb = new StringBuilder(words[l]);
		for (int i = l + 1; i < r; i++) {
			sb.append(sep);
			sb.append(words[i]);
		}
		return sb;
	}

	// 返回n个空格
	public String Blank(int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(" ");
		}
		return sb.toString();
	}

}
