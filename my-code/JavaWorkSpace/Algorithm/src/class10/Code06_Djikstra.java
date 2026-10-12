package class10;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

public class Code06_Djikstra {
	public static HashMap<Node, Integer> djikstra1(Node from){
		HashMap<Node, Integer> nodeMap = new HashMap<Node, Integer>();
		HashSet<Node> selectedNode = new HashSet<Node>();
		nodeMap.put(from, 0);
		Node minNode = unselectedAndMin(nodeMap, selectedNode);
		while(minNode!=null) {
			for (Edge edge : minNode.edges) {
				//这个点未在nodeMap里标过值
				if(!nodeMap.containsKey(edge.to)) {
					nodeMap.put(minNode, edge.weight+nodeMap.get(minNode));
				}else{
					nodeMap.put(edge.to, Math.min(nodeMap.get(edge.to),edge.weight+nodeMap.get(minNode)));
				}
			}
			selectedNode.add(minNode);
			minNode = unselectedAndMin(nodeMap, selectedNode);
		}
		return nodeMap;
	}
	public static Node unselectedAndMin(HashMap<Node, Integer> nodeMap,HashSet<Node> selectedNode) {
		Node minNode = null;
		int distance = Integer.MAX_VALUE;
		for (Entry<Node, Integer> entry : nodeMap.entrySet()) {
			Node node = entry.getKey();
			int nodeDistance = entry.getValue();
			if(!selectedNode.contains(node)&&nodeDistance<distance) {
				minNode = node;
				distance = nodeMap.get(node);
			}
		}
		return minNode;
	}
	
	
	public static class NodeRecord{
		Node node;
		int distance;
		public NodeRecord(Node node, int distance) {
			this.node = node;
			this.distance = distance;
		}
	}
	public static class NodeHeap{
		private Node[] nodes;
		private HashMap<Node, Integer> distanceMap;
		private HashMap<Node, Integer> indexMap;
		private int size;
		
		public NodeHeap(int size) {
			nodes = new Node[size];
			distanceMap = new HashMap<Node, Integer>();
			indexMap = new HashMap<Node, Integer>();
			this.size = 0;
		}
		
		public boolean isEmpty() {
			return size==0;
		}
		
		public void heapInsert(int index) {
			while(distanceMap.get(nodes[index])<distanceMap.get(nodes[(index-1)/2])) {
				swap(index,(index-1)/2);
				index = (index-1)/2;
			}
		}
		
		public void swap(int index1,int index2) {
			Node tmp = nodes[index1];
			nodes[index1] = nodes[index2];
			nodes[index2] = tmp;
			indexMap.put(nodes[index1], index1);
			indexMap.put(nodes[index2], index2);
		}
		
		public NodeRecord pop() {
			NodeRecord nodeRecord = null;
			if(!isEmpty()) {
				nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
				swap(0,size-1);
				indexMap.put(nodes[size-1], -1);
				distanceMap.remove(nodes[size-1]);
				nodes[size-1]=null;
				heapify(0, --size);
			}
			return nodeRecord;
		}
		
		public void heapify(int index,int size) {
			int leftSon = index*2+1;
			while(leftSon < size) {
				int min = leftSon+1 < size && distanceMap.get(nodes[leftSon+1])<distanceMap.get(nodes[leftSon])?leftSon+1:leftSon;
				min = distanceMap.get(nodes[index])<distanceMap.get(nodes[min])?index:min;
				if(index==min) {
					break;
				}
				swap(index,min);
				index = min;
				leftSon = index*2+1;
			}
		}
//		从未出现过使用add
		public boolean isEnterd(Node node) {
			return distanceMap.containsKey(node);
		}
//		未被挑选过可以更新 
		public boolean inHeap(Node node) {
			return isEnterd(node)&&indexMap.get(node)!=-1;
		}
		public void addOrUpdateOrIgnore(Node node,int distance) {
//			在堆中查看是否需要更新
			if(inHeap(node)) {
				distanceMap.put(node, Math.min(distanceMap.get(node), distance));
//				查看是否更新堆结构
				heapInsert(indexMap.get(node));
			}
//			未加入过 创建新的点
			if(!isEnterd(node)) {
				nodes[size] = node;
				indexMap.put(node,size);
				distanceMap.put(node, distance);
				heapInsert(size++);
			}
		}
	}
	public static HashMap<Node, Integer> djikstra2(Node from,int size){
		NodeHeap nodeHeap = new NodeHeap(size);
		nodeHeap.addOrUpdateOrIgnore(from, 0);
		HashMap<Node, Integer> result = new HashMap<Node, Integer>();
		while(!nodeHeap.isEmpty()) {
			NodeRecord record = nodeHeap.pop();
			Node cur = record.node;
			int distance = record.distance;
			for (Edge edge : cur.edges) {
				nodeHeap.addOrUpdateOrIgnore(edge.to, distance+edge.weight);
			}
			result.put(cur, distance);
		}
		return result;
	}
}
