package 栈;

import java.util.LinkedList;

public class Problem_0071_Simplify_Path {
	public String simplifyPath(String path) {
		if (path.length() < 2) {
			return "/";
		}
		String[] files = path.split("/");
		LinkedList<String> stack = new LinkedList<String>();
		for (String file : files) {
			if ("..".equals(file)) {
				if (!stack.isEmpty()) {
					stack.pollLast();
				}
			} else if (file.length() > 0 && !".".equals(file)) {
				stack.addLast(file);
			}
		}
		StringBuilder ans = new StringBuilder();
		if (stack.isEmpty()) {
			ans.append("/");
		} else {
			for (String str : stack) {
				ans.append("/");
				ans.append(str);
			}
		}
		return ans.toString();
	}
}
