import java.util.*;
import java.util.*;


public class DS2_FloydAlgorithm
{

    public static int[][] floydFunction(int v, LinkedList<Edge>[] edges, String[][] shortPath)
    {

        // get the set of vertices
        int n = edges.length;
		int[][] mat1 = new int[n][n] ;
			//String [][] shortPath = new int[n][n];

		for(int i=0; i< n; i++)
			for(int j=0; j< n; j++)
				shortPath[i][j] = "-1" ;
			
                //mat1[][] stores weights as elements
		for(int i=0; i< n; i++)
		{
			for(int j=0; j<n; j++)
			{
				mat1[i][j] = 9999999;   //mat1[][] is initialized with an arbitrary value
			}
		}

		 for (int i = 0; i < n; i++) {
           
            for (Edge e : edges[i]) {                   //Calling Edge Class by making an object
    			int tar = e.getTarget();
				int wt = e.getCost();     
				mat1[i][tar] = wt;
                            

            }
                  
        }
                //Floyd's algorithm implementation starts here 
		for (int k = 0; k < n; k++)
		{
			
			// Pick all vertices as source one by one
			for (int i = 0; i < n; i++)
			{
				// Pick all vertices as destination for the above picked source
				// 
				for (int j = 0; j < n; j++)
				{
					int distIk = 0 ;
					int distKj = 0 ;
					
					distIk =  GetDistance(edges, v, k) ;    //GetDistance method is used to get weight of the edge between v and k 
					
					distKj =  GetDistance(edges, k, j) ;
					
					if ((distIk + distKj) < mat1[i][j]) {
						mat1[i][j] = distIk + distKj; 
							
							if (mat1[i][k] !=0 )  {

								if (shortPath[i][k] != "-1") {
									shortPath[i][j] = shortPath[i][j] + ", " + shortPath[i][k] + " ";
									
								}
								else {
									if (shortPath[i][j] == "-1")
										shortPath[i][j] = ',' + String.valueOf(k) + " ";
									else
										shortPath[i][j] = shortPath[i][j] + ", " + k + " ";
								}
							
										
							}

							if (mat1[k][j] !=0 ) {
								if (shortPath[k][j] != "-1") {
									shortPath[i][j] = shortPath[i][j] + ", " + k + shortPath[k][j] + " ";
									
								}
							}
						else {
							if ((distIk + distKj) > mat1[i][j]) {
								if (shortPath[i][j].indexOf(',' + k + " ") > 0)
									shortPath[i][j].replaceAll(',' + String.valueOf(k), "");
									shortPath[i][k] = "-1" ;
									shortPath[k][j] = "-1" ;
							}
						
						}
						 
					}
				}
			}
		}
		

        

        return mat1;
    }

    public static void main(String[] args)
    {
		int x;
		int start = 0;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter number of Vertices");
		int n = scan.nextInt();

		String[][] shortPath =  new String[n][n];		
		
		LinkedList<Edge>[] cost = new LinkedList[n];   //LinkedList is initialized
        for (int i = 0; i < n; i++) {
            cost[i] = new LinkedList<Edge>();
        }

		Random rn = new Random();            //Random method is called from Random Class to generate random weights
		for (int i = 0; i < n; i++) {
			for(int j=0; j< n; j++)
			{
			int weight = rn.nextInt(15) + 1;
				if(i != j)
				{
					if(i < j)
					{	
						cost[i].add(new Edge(j, weight));  //Adding weights of j to weight of i
						cost[j].add(new Edge(i, weight));
					}
				}		
			}
		}
		
        for (int i = 0; i < cost.length; i++) {
            System.out.print("From Node " + i);
            for (Edge e : cost[i]) {
    			int tar = e.getTarget();
				int wt = e.getCost();
                System.out.print(" " + tar + "<" + wt + ">" );                   

            }
                System.out.println(" ");                   
        }
		
		System.out.println(" ");    
		while (true) {
			System.out.println("Enter source Code to find Distance (0 to exit):");
			int src = scan.nextInt();

			System.out.println("Enter destination Code to find Distance (0 to exit):");
			int dest = scan.nextInt();
			
			if ( src == 0 && dest == 0)
				break  ;
				
			int j ;
			int[][] d = floydFunction(src, cost, shortPath);    //calling floydFunction 
			for (int i = 0; i < n; i++) {
				for (j = 0; j < n ; j++) {			
					if (i == src && j == dest) {
						System.out.print("[" + src + " to " + j + "] = ");				
						System.out.println(d[i][j]);      //Printing the output
					}
				}
				for (j = 0; j < n ; j++) {			
					if (i == src && j == dest) {				
						if (shortPath[i][j] != "-1")
							System.out.print(" PATH  " + src + " " + shortPath[i][j].replaceAll(",", " ")  + " " + dest);				
						else
							System.out.print(" PATH  " + src + " " + dest);				
						
					}
				}
				System.out.println(" ");    
			}
		}		 
		
    }
	
	static int GetDistance(LinkedList<Edge>[] edges, int src, int dest)       //it returns the weight of the edge between specified source and destination
	{
		int numVertices = edges.length ;
		 
				for (Edge e : edges[src]) {
					if (e.getTarget() == dest) {
						int tar = e.getTarget();
						int wt = e.getCost();
						                   
						return wt;
					}
				                
        }
		

		return 0 ;			

	}	
}

class Edge                  //This class's objects will be instantiated
{
    int target, cost;

    public Edge(int target, int cost) {
        this.target = target;
        this.cost = cost;
    }

    public int getTarget() {
        return target;
    }

    public int getCost() {
        return cost;
    }
}