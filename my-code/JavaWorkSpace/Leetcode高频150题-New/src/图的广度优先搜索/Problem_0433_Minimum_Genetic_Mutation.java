package 图的广度优先搜索;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;

public class Problem_0433_Minimum_Genetic_Mutation {
	public int minMutation(String startGene, String endGene, String[] bank) {
		if (startGene.equals(endGene)) {
			return 0;
		}
		HashSet<String> set = new HashSet<String>();
		HashSet<String> vis = new HashSet<String>();
		for (String gene : bank) {
			set.add(gene);
		}
		if (!set.contains(endGene)) {
			return -1;
		}
		Queue<String> queue = new ArrayDeque<String>();
		int step = 1;
		queue.add(startGene);
		vis.add(startGene);
		char[] keys = new char[] { 'A', 'T', 'C', 'G' };
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				String gene = queue.poll();
				for (int j = 0; j < 8; j++) {
					for (int k = 0; k < keys.length; k++) {
						if (gene.charAt(j) != keys[k]) {
							StringBuffer sb = new StringBuffer(gene);
							sb.setCharAt(j, keys[k]);
							String next = sb.toString();
							if (!vis.contains(next) && set.contains(next)) {
								if (next.equals(endGene)) {
									return step;
								}
								queue.add(next);
								vis.add(next);
							}
						}
					}
				}
			}
			step++;
		}
		return -1;
	}
}
