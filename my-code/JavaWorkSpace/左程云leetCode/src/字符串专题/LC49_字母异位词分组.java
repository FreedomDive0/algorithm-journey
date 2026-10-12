package 字符串专题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LC49_字母异位词分组 {
	public List<List<String>> groupAnagrams(String[] strs) {
		return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(str -> {
			char[] arr = str.toCharArray();
			Arrays.sort(arr);
			return new String(arr);
		})).values());
	}

	public List<List<String>> groupAnagrams1(String[] strs) {
		return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(str -> {
			// 返回 str 排序后的结果。
			// 按排序后的结果来grouping by，算子类似于 sql 里的 group by。
			char[] array = str.toCharArray();
			Arrays.sort(array);
			return new String(array);
		})).values());

	}
}
