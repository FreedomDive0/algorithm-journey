package class12;

public class Code01_RobotWalk {
	public static int ways1(int N,int M,int K,int P) {
		if(N<2 || P>N || P<1 || M>N ||M<1 || K<0) {
			return 0;
		}
		return process1(N, M, K, P);
	}
	public static int process1(int N,int cur,int rest,int P) {
		if(rest==0) {
			return cur == P ? 1:0;
		}
		if(cur==1) {
			return process1(N, 2, rest-1, P);
		}
		if(cur==N) {
			return process1(N, N-1, rest-1, P);
		}
		return process1(N, cur-1, rest-1, P) + process1(N, cur+1, rest-1, P);
	}
	
	
	public static int ways2(int N,int M,int K,int P) {
		if(N<2 || P>N || P<1 || M>N ||M<1 || K<0) {
			return 0;
		}
		int[][] dp = new int[N+1][K+1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= K; j++) {
				dp[i][j] = -1;
			}
		}
		return process2(N, M, K, P,dp);
	}
	public static int process2(int N,int cur,int rest,int P,int[][] dp) {
		if(dp[cur][rest]!=-1) {
			return dp[cur][rest];
		}
		if(rest==0) {
			dp[cur][rest] = cur == P ? 1:0;
			return dp[cur][rest];
		}
		if(cur==1) {
			dp[cur][rest] = process2(N, 2, rest-1, P, dp);
			return dp[cur][rest];
		}
		if(cur==N) {
			dp[cur][rest] = process2(N, N-1, rest-1, P, dp);
			return dp[cur][rest];
		}
		dp[cur][rest] =  process2(N, cur-1, rest-1, P,dp) + process2(N, cur+1, rest-1, P,dp);
		return dp[cur][rest];
	}
	
//	动态规划
	public static int dpWay(int N,int M,int K,int P) {
		if(N<2 || P>N || P<1 || M>N ||M<1 || K<0) {
			return 0;
		}
		int[][] dp = new int[N+1][K+1];
		dp[P][0] = 1;
		for(int rest = 1; rest <= K;rest++) {
			for(int cur = 1; cur<=N ; cur++) {
				if(cur==1) {
					dp[cur][rest] = dp[cur+1][rest-1];
				}else if(cur==N) {
					dp[cur][rest] = dp[cur-1][rest-1];
				}else {
					dp[cur][rest] = dp[cur-1][rest-1] + dp[cur+1][rest-1];
				}
			}
		}
		return dp[M][K];
	}
	
	public static void main(String[] args) {
		System.out.println(ways1(7, 4, 9, 5));
		System.out.println(ways2(7, 4, 9, 5));
		System.out.println(dpWay(7, 4, 9, 5));
//		System.out.println(ways4(7, 4, 9, 5));
//		System.out.println(ways5(7, 4, 9, 5));
	}
}
