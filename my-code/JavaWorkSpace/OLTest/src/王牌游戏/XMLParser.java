package 王牌游戏;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLParser {
	static HashMap<String, Label> map = new HashMap<String, Label>();

	public static void main(String[] args) {
		String s = "<test>\r\n" + "\"main\"\r\n" + "<a1 key1=\"hoolai1\" key2=\"company1\"/>\r\n"
				+ "<name1 key1=\"super1\">\"cctv1\"</name1>\r\n" + "<int1>666 </int1>\r\n" + "<second>\r\n"
				+ "<a2 key1=\"hoolai2\" key2=\"company2\"/>\r\n" + "<name2 key1=\"super2\">\"cctv2\"</name2>\r\n"
				+ "<int2>777 </int2>\r\n" + "</second>\r\n" + "<char1> \"c\" </char1>\r\n" + "</test>";
		s = replaceBlank(s);
		Label label = analysisXML(s, new Label(), 0, s.length(), new Stack<String>());
		print(label, 1);
	}

	public static class Label {// 标签
		public String name;
		public ArrayList<Attribute> attributes;
		public String contains;
		public boolean single;
		public ArrayList<Label> child;
		public Label brother;

		public Label() {
			name = null;
			attributes = new ArrayList<Attribute>();
			single = false;
			child = new ArrayList<Label>();
			brother = null;
		}

		public Label(String name) {
			this.name = name;
			attributes = new ArrayList<Attribute>();
			child = new ArrayList<Label>();
			brother = null;
			single = false;
		}

		public void setAttributes(Attribute attribute) {
			attributes.add(attribute);
		}

		public void setContains(String contains) {
			this.contains = contains;
		}

		public void setChild(Label child) {
			this.child.add(child);
		}

		@Override
		public String toString() {
			return "Label {name=" + name + ", attributes:" + printAttributes() + ", contains=" + contains + ", single="
					+ single + " childSize:" + child.size() + "}";
		}

		public String printAttributes() {
			String res = "";
			for (int i = 0; i < attributes.size(); i++) {
				res += attributes.get(i).toString();
			}
			return res;
		}
	}

	public static class Attribute {// 属性
		private String name;
		private String value;

		public Attribute() {
			name = null;
			value = null;
		}

		public Attribute(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return name + " = " + value + " ";
		}

	}

	public static Label analysisXML(String xml, Label root, int start, int end, Stack<String> stack) {
		if (xml == null) {
			return null;
		}
		int indexS = start;
		int indexE = start;
		String res = null;
		while (indexE < end) {
			if (root.name == null) {// 第一次进入
				indexE = xml.indexOf('>', indexS);
				res = getLabel(xml, root, indexS, indexE);
				indexS = ++indexE;
				if (root.single) {// 单标签不入栈
					root.brother = analysisXML(xml, new Label(), indexS, end, stack);
				} else {
					stack.add(res);
				}
			}
			while (!stack.isEmpty()) {
				if (xml.charAt(indexS) == '<') {
					Label label = new Label();
					indexE = xml.indexOf('>', indexS);
					res = getLabel(xml, label, indexS, indexE);
					String name = res;
					if (xml.charAt(indexS + 1) != '/') {// 开始标签
						if (label.single) {
							root.setChild(label);
							indexS = ++indexE;
						} else {
							stack.add(name);
							indexS = ++indexE;
							root.setChild(analysisXML(xml, label, indexS, end, stack));
							indexS = xml.indexOf("</" + name + ">", indexS) + ("</" + name + ">").length();
						}
					} else {// 结束标签
						indexS = ++indexE;
						if (stack.peek().equals(name)) {// 标签结束
							stack.pop();
							return root;
						}
					}
				} else if (xml.charAt(indexS) == '"') {// 处理文本
					indexE = xml.indexOf('"', indexS + 1);
					res = getContains(xml, indexS, indexE);
					root.contains = res;
					indexS = ++indexE;
				} else {
					indexE = xml.indexOf('<', indexS);
					res = getNums(xml, indexS, indexE);
					root.contains = res;
					indexS = indexE;
				}
			}
		}
		return root;
	}

	public static String getLabel(String xml, Label label, int start, int end) {// 处理标签并返回标签名称
		if (xml == null || start == end) {
			return null;
		}
		int indexS = start;
		int indexE = start;
		String res = null;
		while (indexE < end) {
			if (xml.charAt(indexE) == '<') {
				boolean endLabel = false;
				if (xml.charAt(++indexE) == '/') {// 处理结束标签
					endLabel = true;
					indexS++;
					indexE = xml.indexOf('>', indexS);
				} else {// 处理开始标签
					indexE = xml.indexOf(' ', indexS);
					indexE = indexE == -1 || indexE > end ? xml.indexOf('>', indexS) : indexE;// 判断有无属性
				}
				label.name = xml.substring(indexS + 1, indexE).trim();// 去掉两边空格
				indexS = ++indexE;// 更新下标
				if (endLabel) {
					break;
				}
			} else if (xml.charAt(indexE) == '=') {// 处理属性
				String name = xml.substring(indexS, indexE).trim();
				indexS = xml.indexOf('"', indexS) + 1;
				indexE = xml.indexOf('"', indexS);
				String value = xml.substring(indexS, indexE);
				label.setAttributes(new Attribute(name, value));
				indexS = ++indexE;
			} else if (xml.charAt(indexE) == '/') {// 单标签
				label.single = true;
				break;
			} else {
				indexE++;
			}
		}
		res = label.name;
		map.put(res, label);
		return res;
	}

	public static String replaceBlank(String str) {// 去除字符串中的回车、换行符、制表符
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	public static String getContains(String xml, int start, int end) {// 获得内容
		if (xml == null) {
			return null;
		}
		String res = xml.substring(start, end + 1).trim();
		return res;
	}

	public static String getNums(String xml, int start, int end) {// 获得内容
		if (xml == null) {
			return null;
		}
		String res = xml.substring(start, end).trim();
		return res;
	}

	public static void print(Label label, int level) {
		if (label == null) {
			return;
		}
		int curLevel = 1;
		LinkedList<Label> list = new LinkedList<>();
		HashMap<Label, Integer> map = new HashMap<>();
		list.add(label);
		map.put(label, level);
		while (!list.isEmpty()) {
			Label tmp = list.poll();
			int cur = map.get(tmp);
			System.out.print("第" + cur + "层:" + tmp + " ");
			if (curLevel != cur) {
				curLevel = cur;
				System.out.println();
			}
			if (tmp.child.size() != 0) {
				for (Label child : tmp.child) {
					list.add(child);
					map.put(child, cur + 1);
				}
			}
		}
	}
}
