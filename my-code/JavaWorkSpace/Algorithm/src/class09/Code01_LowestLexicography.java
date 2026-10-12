package class09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;

public class Code01_LowestLexicography {
	public static String lowestString1(String[] strs) {
		if(strs==null||strs.length==0) {
			return "";
		}
		HashSet<Integer> use = new HashSet<Integer>();//存储strs使用过的下标
		ArrayList<String> all = new ArrayList<String>();//存储所有的字符串组合
		String path = "";
		String res = null;
		process(all, strs, path, use);
		res = all.get(0);
		for (String str : all) {
			if(str.compareTo(res)<0) {
				res = str;
			}
		}
		return res;
	}
	public static void process(ArrayList<String> all,String[] strs,String path,HashSet<Integer> use) {
		if(use.size()==strs.length) {//所有字符串都是用过了
			all.add(path);
		}else {
			for(int i=0;i<strs.length;i++) {
				if(!use.contains(i)) {
					use.add(i);
					process(all, strs, path+strs[i], use);
					use.remove(i);
				}
			}
		}
	}
	
	public static String lowestString2(String[] strs) {
		if(strs==null||strs.length==0) {
			return "";
		}
		String res = "";
		Arrays.sort(strs, new MyCompareTo());
		for(int i=0;i<strs.length;i++) {
			res += strs[i];
		}
		return res;
	}
	public static class MyCompareTo implements Comparator<String> {
		@Override
		public int compare(String s1, String s2) {
			return (s1+s2).compareTo(s2+s1);
		}
	}
	
	
	// for test
		public static String generateRandomString(int strLen) {
			char[] ans = new char[(int) (Math.random() * strLen) + 1];
			for (int i = 0; i < ans.length; i++) {
				int value = (int) (Math.random() * 5);
				ans[i] = (char) (97 + value);
			}
			return String.valueOf(ans);
		}

		// for test
		public static String[] generateRandomStringArray(int arrLen, int strLen) {
			String[] ans = new String[(int) (Math.random() * arrLen) + 1];
			for (int i = 0; i < ans.length; i++) {
				ans[i] = generateRandomString(strLen);
			}
			return ans;
		}

		// for test
		public static String[] copyStringArray(String[] arr) {
			String[] ans = new String[arr.length];
			for (int i = 0; i < ans.length; i++) {
				ans[i] = String.valueOf(arr[i]);
			}
			return ans;
		}

		public static void main(String[] args) {
			int arrLen = 6;
			int strLen = 5;
			int testTimes = 100000;
			for (int i = 0; i < testTimes; i++) {
				String[] arr1 = generateRandomStringArray(arrLen, strLen);
				String[] arr2 = copyStringArray(arr1);
				if (!lowestString1(arr1).equals(lowestString2(arr2))) {
					System.out.println("Oops!");
				}
			}
			System.out.println("finish!");
		}

}
