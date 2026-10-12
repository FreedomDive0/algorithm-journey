package 字符串专题;

import java.util.HashSet;

public class LC929_独特的电子邮件地址 {
	public static void main(String[] args) {
		numUniqueEmails(new String[] { "test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com",
				"testemail+david@lee.tcode.com" });
	}

//	public static int numUniqueEmails(String[] emails) {
//		HashSet<String> set = new HashSet<String>();
//		for (int i = 0; i < emails.length; i++) {
//			String[] strs = emails[i].split("@");
//			int plusIndex = strs[0].indexOf('+');
//			if (plusIndex != -1) {// 去除加号
//				strs[0] = strs[0].substring(0,plusIndex);
//			}
//			ArrayList<Character> chs = new ArrayList<Character>();
//			for (char c : strs[i].toCharArray()) {
//				if (c != '.') {
//					chs.add(c);
//				}
//			}
//			strs[0] = chs.toString();
//			set.add((strs[0] + strs[1]).toString());
//		}
//		return set.size();
//	}
	public static int numUniqueEmails(String[] emails) {
		HashSet<String> set = new HashSet<String>();
		for (String str : emails) {
			StringBuilder sb = new StringBuilder();
			int i = 0;
			boolean flag = true;
			while (i < str.length()) {
				char c = str.charAt(i);
				if (c == '@')
					break;
				if (c == '.' && ++i > 0)// 注意continue i不变死循环
					continue;
				if (c == '+')
					flag = false;
				if (flag)
					sb.append(c);
				i++;
			}
			set.add(sb.append(str.substring(i)).toString());
		}
		return set.size();
	}
}
