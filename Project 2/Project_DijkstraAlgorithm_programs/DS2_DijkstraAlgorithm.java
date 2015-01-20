import java.util.*;
import java.util.*;


public class DS2_DijkstraAlgorithm
{
	static int mat1[][];
	static int pathArray[] ;
        public static int[] shortestPaths(int v, LinkedList<Edge>[] edges)
        {
		int temp = 0;
        // get the set of vertices
        int n = edges.length;
		int j ;
        // dist[i] is the distance from v to i
        int[] dist = new int[n];
		pathArray = new int[1000] ;		
		for(int i=0; i< n; i++)
			pathArray[i] = -1 ;
		
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        // seen[i] is true if there is a path from v to i
        boolean[] seen = new boolean[n];

        dist[v] = 0;

        // determine n-1 paths from v
        for ( j = 0; j < n; j++) {
            // choose closest unseen vertex
            int u = -1;

            for (int k = 0; k < n; k++) {
                if (!seen[k]) {
                    // check if u needs updating
                    if (u < 0 || dist[k] < dist[u]) {
                        u = k;
                    }
                }
            }

            if (u < 0 || dist[u] == Integer.MAX_VALUE) {
                break;
            }

            // at this point dist[u] is the cost of the shortest path from v to u
            

            // setting seen[u] to true and updating the distances
            seen[u] = true;
			
			
            for (Edge e : edges[u]) {
                int nbr = e.getTarget();
                int altDist = dist[u] + e.getCost();
				if(dist[nbr] > altDist)
				{
					if(nbr != v){
						/* if (Arrays.asList(pathArray).contains(String.valueOf(u)))
						{
							//System.out.println("ENTER In Contains");
							//list.removeAll(Arrays.asList(pathArray).contains(String.valueOf(u)));
						
						}
						else{ */
							pathArray[ temp ] = u;
							
							temp++;
						//}
					}
				}
				dist[nbr] = Math.min(dist[nbr], altDist);
				//System.out.println("ALTDIST : " + dist[nbr]);
				//pathArray[ nbr ] = u;
				
				
            }
        }

        return dist;
    }

    public static void main(String[] args)
    {
		int x;

		int start = 0;
		Scanner scan = new	Scanner(System.in);
		System.out.println("Enter number of Vertices");
		int n = scan.nextInt();
		LinkedList<Edge>[] cost = new LinkedList[n];
                for (int i = 0; i < n; i++) {
                  cost[i] = new LinkedList<Edge>();
        }

		
		Random rn = new Random();    //calling Random method from Random Class defined in java.util.Random
		for (int i = 0; i < n; i++) {
			for(int j=0; j< n; j++)
			{
			int weight = rn.nextInt(25) + 1;
				if(i != j)
				{
					if(i < j)                //adding edges weight to the present cost(weight) of i and vice versa
					{	
						cost[i].add(new Edge(j, weight));
						cost[j].add(new Edge(i, weight));
					}
				}		
			}
		}
		
        for (int i = 0; i < cost.length; i++) {
            System.out.print("From Node " + i);
            for (Edge e : cost[i]) {
    			int tar = e.getTarget();                       //printing the target with its weight
				int wt = e.getCost();
                System.out.print(" " + tar + "<" + wt + ">" );                   

            }
                System.out.println(" ");                   
        }
		
		System.out.println(" ");                   
		System.out.println("Enter source Code to find Distance:");
		int src = scan.nextInt();

		System.out.println("Enter Destination Code to find Distance:");
		int dest = scan.nextInt();
		
		int[] d = shortestPaths(src, cost);       //calling shortest path method with source and cost as arguments
        for (int i = 0; i < n; i++) {
			if(i == dest){
				System.out.print("d[" + src + " to " + i + "] = ");
				System.out.println(d[i]);            //printing the shortest path
			}	
        }
		
		System.out.print(src + " " );                        
		for( int j = 0; j < n*(n-1); j++ ){                 
			if (pathArray[ j ] != -1) 
			{
				if (pathArray[ j ]  != dest && pathArray[j] != src && pathArray != null)
					if(pathArray[ j ] != 0)
						System.out.print(pathArray[ j ] + "  " );
			}
		}	
		System.out.print(dest + " " );

		
    }
}

class Edge            //This class's objects will be instantiated
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