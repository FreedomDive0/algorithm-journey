package SortTools;

public class test {
	public static void main(String[] args) {
		double wight = 93;
		double low = 0.015;
		double high = 0.025;
		int weeks = 0;
		while (wight > 70) {
			wight -= wight * high;
			weeks += 2;
			System.out.println("第" + weeks + "周：体重" + wight);
		}
		System.out.println("===========================================");
		wight = 93;
		for (int i = 0; i < 6; i++) {
			wight -= wight * high;
			weeks += 2;
			System.out.println("第" + weeks + "周：体重" + wight);
		}
	}
}
