/*
This program uses Nearest Neighbor algorithm to compute Travelling Salesman Problem to  get route and shortest path. 
*/

import java.util.*;

 
public class HeuristicTSP
{
    private int N;
    private Stack<Integer> stack;
 
    public HeuristicTSP()
    {
        stack = new Stack<Integer>();   //Using stack to store integers, here city's index or nodes
    }
 
    public void salesmanTraveling(int adjMat[][])   //adjacentMatrix is given as the argument
    {
        N = adjMat[1].length - 1;
	System.out.println("Num of Cities : " + N);
	System.out.println("Shortest Route is : ");
		
	//save last city to be visited 
	int lastCity = 0;
	// creating array to check the node is visited or not.
        int[] visited = new int[N + 1];
        visited[1] = 1;
		
        stack.push(1);
        int element, distance = 0, city = 0, i;
		
	// Initialy min value to be set as maximum value, so that it will compare with very first node distance
        
        int min = Integer.MAX_VALUE;
        boolean flag = false;
        System.out.print(1 + "\t");
	// while the nodes in the stack are finished
        while (!stack.isEmpty())
        {
            element = stack.peek();
            i = 1;
            min = Integer.MAX_VALUE;
            while (i <= N)
            {
                if (adjMat[element][i] >= 1 && visited[i] == 0)
                {
					// check the value of nodes distance is less than minimum
                    if (min > adjMat[element][i])
                    {
						// value of min set equal to the adjMat	
                        min = adjMat[element][i];
                        city = i;
                        flag = true;
                    }
                }
                i++;
            } // End of While
            if (flag)
            {
                visited[city] = 1;
                stack.push(city);
                // Addition of distance
                distance = distance + adjMat[element][city];
                lastCity = city ;
                System.out.print(city + "\t");
                flag = false;
                continue;
            }
            stack.pop();
        } // End of While
		
		System.out.print(1) ;

		//System.out.print("Last distance " + adjMat[lastCity][1] ) ; 
		System.out.println();
		distance = distance + adjMat[lastCity][1] ;
                System.out.println("Distance is : " + distance );
		
    }
 
    public static void main(String... arg)
    {
            int totalCities;
            Scanner scan = null;
	    int maxdistance=30;      //maxdistance is to set the maximum value of weight in the random matrix
            System.out.println("Enter number of nodes in graph");
            scan = new Scanner(System.in);
            totalCities = scan.nextInt();
            int matrix[][] = new int[totalCities + 1][totalCities + 1];
			Random random = new Random();        //Random method to assign random distances to the edges
			
			for(int i=1;i<=totalCities; i++)
			{
				//for(int j=0;j<totalcities; j+=1)
				for(int j=1;j<=i; j++)
				{
					if (i==j)
					{
						matrix[i][j]=0;
					}
					else
					{
						matrix[i][j]=(random.nextInt()%(maxdistance-1));
						if (matrix[i][j]<0)
						{
							matrix[i][j] = matrix[i][j]*-1;
						}
											
						matrix[i][j]+=1;
						matrix[j][i] = matrix[i][j];
						
					}
					
					
				}
			}			
			
			
			
            System.out.println(" ");
            System.out.println("Distance Matrix");
            for (int i = 1; i <= totalCities; i++)
            {
                for (int j = 1; j <= totalCities; j++)
                {
				System.out.print(matrix[i][j]);
				System.out.print(" ");					
                }
				System.out.println(" ");
				System.out.println(" ");
            }

            
            HeuristicTSP heuristic = new HeuristicTSP();
            heuristic.salesmanTraveling(matrix);

            scan.close();
    }
}
