//This program uses Dijkstra's algorithm to find shortest path.
import java.util.*;
import java.util.Random;

class Vertex
{
	public int pre;
	public int dist;
	public int status;

}

    public class DS1_Dijkstraalgorithm
    {
		
	private int l;
        private int n;	    //Num of Nodes		
        static int mat2[][];
        public static final int Inf = 9999;
		public static final int t = 0;
		public static final int p = 1;
		static int[] sp ;
		static int pathArray[] ;		


		public static void main(String... arg)
        {
			DS1_Dijkstraalgorithm D1 = new DS1_Dijkstraalgorithm();
            	
                        int n, temp;
			int[] aray;
			int a, b;
                        Scanner scan = new Scanner(System.in);		
                        System.out.println("Enter the number of Nodes in Graph"); 
                        n = scan.nextInt();	
			sp = new int[n];
			mat2 = new int[n + 1][n + 1];
			Random rn = new Random();    //Random() method is called from the Random Class to generate a matrix of random weights
			
			for(int i = 0; i < n ; i++)
			{
				
				for (int j = 0; j < i; j++)
                {	
					int answer = rn.nextInt(10) + 1;
					mat2[i][j] = mat2[j][i] = answer;
						
		    //Diagonal elements should be set to zero		
                    if (i == j) 
                    {

                        mat2[i][j] = 0;
                        
                    }

					
                }
	
			}
           //Print the matrix
           System.out.println("Matrix Elements are:");
		    for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {	
					
			System.out.print(mat2[i][j]+"\t");
				}
				System.out.println("");
			}
			
			System.out.println("Enter the source node:");
			int src = scan.nextInt();

			System.out.println("Enter the Destination node:");
			int dest = scan.nextInt();
			
			shortestPath(src, n) ;                //calling shortestPath method
			
			System.out.println("Distance is "+ sp[dest]) ;            
			scan.close();
				System.out.println( "Path is ") ;
				
				System.out.print(src + " " );          //printing the path in array form
				for( int j = 0; j < n; j++ ){
					if (pathArray[ j ] != -1) {
						if (pathArray[ j ]  != dest )
							System.out.print(pathArray[ j ] + "  " );
					}
				}	
				System.out.print(dest + " " );
			
        }


		static void shortestPath(int v, int n) {
		pathArray = new int[n] ;

		int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = 0;
            sp[i] = mat2[v][i];      //Assigning random matrix to sp[]
			
        }
		for (int i = 0;i < n;i++)
				pathArray[i] = -1 ;
		
        s[v] = 1;
        sp[v] = 0;                 
        for (int i = 1; i < n - 1; i++) {
            int u = 0, dis = 0;
            for (int j = 0; j < s.length; j++) {
                if (s[j] == 0) {                          //Adding u to the list of visited vertices
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
                    if (sp[j] > (sp[u] + mat2[u][j])) {            //checking if weight of j is greater than weight of u+ distance between j and u
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