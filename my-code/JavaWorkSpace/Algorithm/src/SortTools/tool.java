package SortTools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class tool {
	static int num = 0;

	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		String s = sc.nextLine();
//		s = s.replace('.', ' ');
//		System.out.println(s);

		// 处理文本文件
//		List<String> nameList = new ArrayList<>();
//		nameList = reader("");
//
//		FileOutputStream output = null;
//		try {
//			output = new FileOutputStream("");
//			System.out.println(nameList.size());
//			for (String str : nameList) {
//				output.write(str.getBytes());
//				output.write("\n".getBytes());
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				if (output != null)
//					output.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		System.out.println(num + " " + num);
		String targetcatalog = new String();
//		// 获取目录下全部文件
		File directory = new File("");
		File destinationFile = new File("");
		List<File> files = listAllFiles(directory);
		for (int i = 0; i < files.size(); i++) {
			File tmp = new File(destinationFile + "\\" + files.get(i).getName());
			files.get(i).renameTo(tmp);
//			System.out.println(destinationFile + "\\" + files.get(i).getName());
		}
	}

	// 获取文本内容
	static List<String> reader(String path) {
		List<String> nameList = new ArrayList<>();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));
			String line = reader.readLine();
			while (line != null) {
				if (line.contains("magnet")) {
//					nameList.add(line);// :不变
					System.out.println(line);
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nameList;
	}

	// 获取目录下全部文件
	public static List<File> listAllFiles(File directory) {
		List<File> fileList = new ArrayList<>();
		listFilesRecursive(directory, fileList);
		return fileList;
	}

	private static void listFilesRecursive(File dir, List<File> fileList) {
		File[] files = dir.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isFile()) {
					fileList.add(file);
				} else if (file.isDirectory()) {
					listFilesRecursive(file, fileList);
				}
			}
		}
	}
}
