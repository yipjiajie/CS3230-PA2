/*	MD ASHMAWI
 *	A0110818M
 *	CS3230 PA2 PART B
 */

import java.util.*;

public class PolyTimeReduction {
	
	static TreeSet<String> vertices;
	static ArrayList<String> edgeList;
	
	public static void main(String[] args) {
		
		int T; // Number of test case
		int N; // Number of vertices
		int M; // Number of directed edges
		
		int Nv; // Number of vertices of transformed graph
		int Me; // Number of edges of transformed graph
		
		Scanner sc = new Scanner(System.in);
		
		// Reads number of test cases
		T = sc.nextInt();
		
		for (int i = 1; i <= T; ++i) {
			vertices = new TreeSet<String>();
			edgeList = new ArrayList<String>();
			
			N = sc.nextInt(); 
			Nv = 3 * N;
			
			M = sc.nextInt();
			initializeGraph(M, sc);
			
			Me = edgeList.size();
			
			System.out.println(Nv +" "+ Me);
			
			outputResults();
		}
	}

	private static void initializeGraph(int M, Scanner sc) {
		for(int j = 0; j < M; j++) {
		
			String u = sc.next().substring(1);
			String v = sc.next().substring(1);
			
			setVertices(u,v);
			setEdges(u,v);
		}
	}
	
	private static void setVertices(String u, String v) {
		if(!vertices.contains(u)) {
			vertices.add(u);
			edgeList.add("H"+u+" "+"V"+u);
			edgeList.add("T"+u+" "+"V"+u);
		}
		
		if(!vertices.contains(v)) {
			vertices.add(v);
			edgeList.add("H"+v+" "+"V"+v);
			edgeList.add("T"+v+" "+"V"+v);
		}
	}
	
	private static void setEdges(String u, String v) {
		String edge = "H"+u+" "+"T"+v;
		edgeList.add(edge);
	}
	
	private static void outputResults() {
		Collections.sort(edgeList);
		for(int i=0; i<edgeList.size(); i++) {
			System.out.println(edgeList.get(i));	
		}
	}
}
