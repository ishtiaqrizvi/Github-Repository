
import java.util.*;
import java.util.Random;


class Vertex
{
	public int pre;          //these are public modifiers and can be everywhere
	public int dist;
	public int status;

}

    public class DS3_DijkstraAlgorithm
    {
		
	private int l;
        private int n;		
        static int mat2[][];        //this array will store weights
        public static final int Inf = 9999;
		public static final int t = 0;
		public static final int p = 1;
		static int[] sp ;
		static int pathArray[] ;        //path between specified vertices will be stored as array in pathArray 

		public static void main(String... arg)
        {
			DS3_DijkstraAlgorithm D1 = new DS3_DijkstraAlgorithm();
            	        int n, temp;
			int[] aray;
			int a, b;
                        Scanner scan = new Scanner(System.in);		

                        System.out.println("Enter the number of Nodes in Graph"); 
                        n = scan.nextInt();	
			int l = (n*(n-1))/2;          //l stores number of elements  in lower triangle
			sp = new int[l];              
			
                        mat2 = new int[n + 1][n + 1];
			
			Random rn = new Random();           //Random() method is called from Random Class defined in java.util.Random
			
			for(int i = 0; i < n ; i++)
			{
				
				for (int j = 0; j < i; j++)
                            {	
					int answer = rn.nextInt(25) + 1;
					mat2[i][j] = mat2[j][i] = answer;
						
					
                    if (i == j) 
                    {
						// Digonal elements are set to Zero
                        mat2[i][j] = 0;

                    }

					
                }
	
			}

           System.out.println("Matrix Elements are:");
		    for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {	// Receving Input for Matrix
					
					System.out.print(mat2[i][j]+"\t");
				}
				System.out.println("");
			}

			int count = 0;
			System.out.println("");
			System.out.println("");
			System.out.println("Lower Triangle Array :");
			System.out.print("[");
				for(int i = 0; i < n; i++)
				{
					for(int j = 0; j < i; j++)
					{
						sp[count] = mat2[i][j];                  //print the lower traingle array
						System.out.print("\t"+ mat2[i][j]);
						count++;
					}
					
				}
			System.out.print("]");
			System.out.println("");
			System.out.println("Enter the source node:");
			int src = scan.nextInt();

			System.out.println("Enter the Destination node:");
			int dest = scan.nextInt();
			System.out.println("");
			System.out.println("");
			shortestPath(src, dest) ;            //shortestPath method is called to get the shortest path

			shortestPath(src, n) ;
			
			System.out.println("Distance is "+ sp[dest]) ;            
			scan.close();
				System.out.println( "Path is ") ;
				System.out.print(src + " " );
				for( int j = 0; j < n; j++ ){
					if (pathArray[ j ] != -1) {
						
						if (pathArray[ j ]  != dest )
							System.out.print(pathArray[ j ] + "  " );
					}
				}	
				System.out.print(dest + " " );
				
				
			scan.close();
        }

		//Method where Dijkstra algorithm is implemented
		static void shortestPath(int v, int n) {
		pathArray = new int[n] ;
                //********Dijkstra Algorithm logic*********
		int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = 0;
            sp[i] = mat2[v][i];
			
        }
		for (int i = 0;i < n;i++)
				pathArray[i] = -1 ;
		
        s[v] = 1;
        sp[v] = 0;
        for (int i = 1; i < n - 1; i++) {
            int u = 0, dis = 0;
            for (int j = 0; j < s.length; j++) {
                if (s[j] == 0) {
                    dis = sp[j];
                    u = j;
                    for (int k = j + 1; k < s.length; k++) {

                        if (dis > sp[k] && s[k] == 0) {
                            dis = sp[k];
                            u = k;
                        }
                    }
                    break;
                }

            }
            s[u] = 1;
            for (int j = 1; j < n; j++) {
                if (s[j] == 0) {
                    if (sp[j] > (sp[u] + mat2[u][j])) {
                        sp[j] = sp[u] + mat2[u][j];
						pathArray[ j ] = u;

                    }
					else {
						if (sp[j] < (sp[u] + mat2[u][j]))
							pathArray[ j ] = -1 ;
							
					}
                }
            }
        }

    }	
		
		
    }