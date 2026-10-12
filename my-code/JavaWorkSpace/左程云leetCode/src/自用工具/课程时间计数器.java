package 自用工具;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 课程时间计数器 {
	public static void main(String[] args) {
		// 确认目录
		System.out.println("请输入目标目录");
		String targetcatalog = new String();
		Scanner sc = new Scanner(System.in);
//		targetcatalog = sc.nextLine();//手动输入目录
		targetcatalog = "F:\\File\\Study\\Study Project\\Compulsory Subject\\日常计划";
//		while (true) {
		System.out.println("请输入目标文件名");
		String targetFile = new String();
//			targetFile = sc.nextLine();//手动输入文件名
		targetFile = "1";
		List<String> nameList = new ArrayList<>();
		String catalogue = "";
		String name = "LearnTime";
		// 获取课程时间表
		nameList = reader("F:\\File\\Study\\Study Project\\Compulsory Subject\\日常计划\\" + catalogue + name + ".txt");
		FileOutputStream output = null;
		try {
			output = new FileOutputStream(targetcatalog + "\\" + targetFile + ".txt");
			System.out.println(nameList.size());
			for (String str : nameList) {
				output.write(str.getBytes());
				output.write("\n".getBytes());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (output != null)
					output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
//	}

	// 获取课程时间表
	static List<String> reader(String path) {
		List<String> nameList = new ArrayList<>();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));
			String line = reader.readLine();
			while (line != null) {
				if (line.contains("课时") || line.contains("章")) {
//					nameList.add(line.replace(':', '-'));// : -> -
					nameList.add(line);// :不变
//					System.out.println(line);
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nameList;
	}

}
