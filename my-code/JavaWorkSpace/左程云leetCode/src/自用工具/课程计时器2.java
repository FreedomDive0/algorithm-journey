package 自用工具;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class 课程计时器2 {
	public static void main(String[] args) {
		// 确认目录
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("请输入目标文件名");
			String targetFile = new String();
			targetFile = sc.nextLine();
			System.out.println("请输入课程时长");
			int timeSize = sc.nextInt();
			System.out.println("请输入课程偏移量");
			int offset = sc.nextInt();
			sc.nextLine();// 消耗nextInt的换行符
			List<String> timeList = new ArrayList<>();
			String catalogue = "";
			String name = "LearnTime";
			// 读取本地数据
			String path = "";
//			path = "D:\\File\\Study\\Study Project\\Compulsory Subject\\日常计划\\" + catalogue + name + ".txt";
//			path = "E:\\File\\Study\\Study Project\\Compulsory Subject\\日常计划\\" + catalogue + name + ".txt";
			path = "F:\\File\\Study\\Study Project\\Compulsory Subject\\日常计划\\" + catalogue + name + ".txt";

			timeList = reader(path);
			FileOutputStream output = null;
			try {
				// 输出文件位置
				output = new FileOutputStream(
						"F:\\File\\Study\\Study Project\\Compulsory Subject\\日常计划\\" + targetFile + ".txt", true);
				System.out.println(timeList.size());
				List<Integer[]> res = process(timeList, offset, timeSize);// 几节无关课程
				Iterator<Integer[]> it = res.iterator();
				while (it.hasNext()) {
					Integer[] tmp = it.next();
					String str = "课时:" + tmp[1] + "\t\t\t" + "时长:" + tmp[0];
					System.out.println("--------------------------------");
					System.out.print(str);
					output.write(str.getBytes());
					System.out.println();
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
	}

	// F:\File\Study\Study Project\Compulsory Subject\日常计划
	// 获取课程时间表
	static List<String> reader(String path) {
		List<String> timeList = new ArrayList<>();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));
			String line = reader.readLine();
			while (line != null) {
				if (line.contains(":") && !line.contains("课时") && !line.contains("章") && !line.contains("节")
						&& !line.contains("任务") && !line.contains("专题")) {
					timeList.add(line);
//					System.out.println(line);
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timeList;
	}

	// 处理时间表
	static List<Integer[]> process(List<String> timeList, int offset, int timeSize) {
		List<Integer[]> res = new ArrayList<Integer[]>();
		int sumMin = 0;
		int sumSec = 0;
		int time = 0;
		for (int i = 0; i < timeList.size(); i++) {
			String line = timeList.get(i);
			int min = Integer.parseInt(line.substring(0, 2));
			int sec = Integer.parseInt(line.substring(3, 5));
			int tmp = min * 60 + sec;// 用秒表示
			sumMin += min;// 总计时
			sumSec += sec;
			time += tmp;
			if (time >= timeSize * 3600) {// >60min收集课程
				Integer[] arr = new Integer[2];
				arr[0] = (int) Math.floor(time / 60.0);
				arr[1] = i + 1 + offset;
				res.add(arr);
				time = 0;
			}
			if (i + 1 == timeList.size() && time != 0) {// 收尾
				Integer[] arr = new Integer[2];
				arr[0] = (int) Math.floor(time / 60.0);
				arr[1] = i + 1 + offset;
				res.add(arr);
			}
		}
		Integer[] r = new Integer[2];
		r[0] = (int) Math.floor(sumMin + sumSec / 60.0);
		r[1] = timeList.size() + offset;
		res.add(r);
		return res;
	}
}
