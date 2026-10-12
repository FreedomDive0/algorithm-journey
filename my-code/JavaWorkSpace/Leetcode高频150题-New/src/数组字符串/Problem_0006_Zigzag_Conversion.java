package 数组字符串;

import java.util.ArrayList;
import java.util.List;

public class Problem_0006_Zigzag_Conversion {
	public String convert(String s, int numRows) {
		if (numRows < 2) {// numRows<2时不需要变换
			return s;
		}
		List<StringBuilder> rows = new ArrayList<StringBuilder>();
		for (int i = 0; i < numRows; i++) {
			rows.add(new StringBuilder());
		}
		int i = 0, flag = -1;
		for (char c : s.toCharArray()) {
			if (i == 0 || i == numRows - 1) {
				flag = -flag;
			}
			rows.get(i).append(c);
			i += flag;
		}
		for (int j = 1; j < numRows; j++) {
			rows.get(0).append(rows.get(j));
		}
		return rows.get(0).toString();
	}
}
