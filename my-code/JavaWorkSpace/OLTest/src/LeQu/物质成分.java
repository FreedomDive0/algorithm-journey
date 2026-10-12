package LeQu;

import java.util.HashMap;

public class 物质成分 {
	public static void main(String[] args) {
		String s = "983046877";
		System.out.println(s.substring(1));
	}

	public HashMap<String, Integer> ContainElement(String material) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		if (material == null || material.length() < 1) {
			return map;
		}
		int len = material.length();
		int index = 0;// 字符串下标
		StringBuilder sb = new StringBuilder();// 存储临时元素
		while (index < len) {
			if ((material.charAt(index) >= 'A' && material.charAt(index) <= 'Z')
					|| material.charAt(index) >= 'a' && material.charAt(index) <= 'z') {
				// 处理字符
				sb.append(material.charAt(index));
				index++;
				continue;
			} else if (material.charAt(index) >= '0' && material.charAt(index) <= '9') {
				// 处理数字与括号
				String element = sb.toString();
				sb.delete(0, sb.length());
				int[] num1 = getNum(material, index);
				index = num1[1];
				int[] num2 = new int[2];
				if (material.charAt(index) == ')') {
					num2 = getNum(material, index);
					index = num2[1];
					map.put(element, map.getOrDefault(element, 0) + num1[0] * num2[0]);
				} else {
					map.put(element, map.getOrDefault(element, 0) + num1[0]);
				}
			} else {
				index++;
			}
		}
		return map;
	}

	public int[] getNum(String str, int index) {// 获取数字 返回正确下标
		StringBuilder sb = new StringBuilder();
		while (index < str.length() && str.charAt(index) >= '0' && str.charAt(index) <= '9') {
			sb.append(str.charAt(index));
			index++;
		}
		return new int[] { Integer.valueOf(sb.toString()), index };
	}
}
