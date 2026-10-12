package 自用工具;

import java.io.File;

public class 视频课程计时器 {
	public static void main(String[] args) {
		// 指定文件夹路径
		File folder = new File("D:\\CourseFile\\算法与数据结构全阶班【完结】【瑞客论坛 www.ruike1.com】\\04-大厂算法和数据结构刷题【完结】");

		// 获取该目录下的所有文件
		File[] files = folder.listFiles();

		if (files != null) {
			for (File file : files) {
				// 判断是否为文件，且后缀名为视频文件
				if (file.isFile() && isVideoFile(file)) {
					String name = file.getName();
					if (name.contains("【"))
						name = name.substring(0, name.indexOf('【'));
					name = name.substring(name.indexOf('节') + 2);
					System.out.println(name);
				}
			}
		} else {
			System.out.println("目录不存在或无法读取。");
		}
	}

	// 判断是否为视频文件
	private static boolean isVideoFile(File file) {
		String fileName = file.getName().toLowerCase();
		return fileName.endsWith(".mp4");
	}
}
