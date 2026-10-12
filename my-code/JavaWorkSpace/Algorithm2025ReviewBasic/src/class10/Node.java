package class10;

import java.util.ArrayList;
import java.util.List;

public class Node {
	public int Value;// 点的名字
	public int in;// 入度
	public int out;// 出度
	public List<Node> nexts;// 相连的点
	public List<Edge> edges;

	public Node(int value) {
		Value = value;
		in = 0;
		out = 0;
		nexts = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
	}

}
