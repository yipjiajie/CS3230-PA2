/*
 * Name: Yip Jiajie
 * Matriculation no.: A0101924R
 * PA2A
 * 
 */

import java.util.*;

public class HamiltonCycleVerifier {
	
	static TreeMap<String, Integer> vertices;
	static int[] visited; 
	static int[][] AdjMatrix;
	static boolean isHamiltonCycle;
	
	public static void main(String[] args) {
	
		String path; // Order of the vertices in path
		String[] arrayVertices; // Order of the vertices in path
		String[] arrayEdges; //string array of edges 
		
		int count; // Vertex index
		int T; // Number of test case
		int P; // Number of vertices in path
		int M; // Number of edges
		int N; // Number of vertices
		
		Scanner sc = new Scanner(System.in);
		
		// Reads number of test cases
		T = sc.nextInt();

		for(int i=1; i<=T; ++i){
			
			vertices = new TreeMap<String, Integer>();
			
			// Reads number of vertices
			N = sc.nextInt(); 
			initializeMatrix(N);
			
			// Reads number of edges
			M = sc.nextInt();
			arrayEdges = initializeEdges(M, sc);
			
			initializeAdjMatrix(arrayEdges);
			
			// Reads number of vertices in path
			P = sc.nextInt();
			arrayVertices = initializePath(P, sc);
			
			verifier(N, P, arrayVertices);	
			
		}
	}

	private static void initializeAdjMatrix(String[] arrayEdges) {
		for(int j=0; j<arrayEdges.length-1; j+=2) {
			int index1 = vertices.get(arrayEdges[j]);
			int index2 = vertices.get(arrayEdges[j+1]);
			
			AdjMatrix[index1][index2] = 1;
			AdjMatrix[index2][index1] = 1;
		}
	}

	private static String[] initializePath(int P, Scanner sc) {
		String[] arrayVertices;
		sc.nextLine();
		arrayVertices = new String[P];
		for(int p=0; p<P; p++) {
			arrayVertices[p] = sc.next();
		}
		return arrayVertices;
	}

	private static String[] initializeEdges(int M, Scanner sc) {
		String[] arrayEdges;
		int count;
		arrayEdges = new String[M*2];
		count = 0;
		for(int j=0; j<M*2; j++){
			String vertex = sc.next();
			arrayEdges[j] = vertex;
			if(!vertices.containsKey(vertex)) {
				vertices.put(vertex, count);
				count++;
			}
		}
		return arrayEdges;
	}

	private static void initializeMatrix(int N) {
		visited = new int[N];
		AdjMatrix = new int[N][N];
		for(int a=0; a<N; a++) {
			visited[a] = 0;
			for(int b=0; b<N; b++) {
				AdjMatrix[a][b] = 0;
			}
		}
	}
	
	private static void verifier(int N, int P, String[] arrayVertices) {
		isHamiltonCycle = true;
		
		if(P != N+1) {
			isHamiltonCycle = false;
		} else if(!arrayVertices[0].equals(arrayVertices[P-1])) {
			isHamiltonCycle = false;
		} else {
			String v0 = arrayVertices[0];
			String v1 = arrayVertices[1];
			
			int i0 = vertices.get(v0);
			int i1 = vertices.get(v1);
			
			if(AdjMatrix[i0][i1] == 1 || AdjMatrix[i1][i0] == 1) {
				for(int k=1; k < P-1; k++) {
					String vertex1 = arrayVertices[k];
					String vertex2 = arrayVertices[k+1];
					
					int index1 = vertices.get(vertex1);
					int index2 = vertices.get(vertex2);
					
					if(AdjMatrix[index1][index2] == 1 || AdjMatrix[index2][index1] == 1) {
						visited[index1] += 1;
						
						if((k==P-2 && AdjMatrix[index1][index2] == 1 ) 
								|| (k==P-2 && AdjMatrix[index2][index1] == 1)) {
							visited[index2] += 1;
						}
					} else {
						isHamiltonCycle = false;
					}
				}
			} else {
				isHamiltonCycle = false;
			}
			
			isHamiltonCycleBasedOnVisited(N);
			
		}
		isHamiltonCycleOutput(isHamiltonCycle);
	}

	private static void isHamiltonCycleBasedOnVisited(int N) {
		for(int m=0; m < N; m++) {
			if(visited[m] == 0 || visited[m] > 1) {
				isHamiltonCycle = false;
			}
		}
	}
	
	private static void isHamiltonCycleOutput(boolean isHamilton) {
		if(isHamilton == false) {
			System.out.println("NO");
		} else {
			System.out.println("YES");
		}
	}
}