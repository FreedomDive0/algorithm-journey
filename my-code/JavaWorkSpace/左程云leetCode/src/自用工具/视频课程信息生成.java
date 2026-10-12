package 自用工具;

import java.io.File;
import java.util.Scanner;

public class 视频课程信息生成 {
	public static void main(String[] args) {
		System.out.println("请输入视频文件目录");
		String sourceCatalog = new String();
		Scanner sc = new Scanner(System.in);
		sourceCatalog = sc.nextLine();
		int num = 0;// 视频文件数
		float sumTime = 0;// 总时长
		File file = new File(sourceCatalog);
		File[] files = file.listFiles();
		for (File f : files) {
			String name = f.getName();
			name = name.substring(0, name.length() - 26);
			System.out.println(name);
		}
	}
}
