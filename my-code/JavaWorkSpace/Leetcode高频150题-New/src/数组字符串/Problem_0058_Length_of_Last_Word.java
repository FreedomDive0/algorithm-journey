package 数组字符串;

public class Problem_0058_Length_of_Last_Word {
	public static int lengthOfLastWord(String s) {
		s = s.trim();
		int start = s.lastIndexOf(' ');
		return start == -1 ? s.length() : s.length() - start - 1;
	}

	public static void main(String[] args) {
		String s = "   fly me   to   the moon  ";
		System.out.println(s.trim());
		System.out.println(lengthOfLastWord(s));
	}
}
