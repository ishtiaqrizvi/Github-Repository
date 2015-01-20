import java.util.*;

//has to be created in the same package as BruteAlgo class
public class MapMatrix {

	private int numcity;
	private int[][] matrix;
	private final int maxdistance=30;     //to create a random matrix of maximum weight of 30
	
        //MapMatrix is created using Random method 
	public MapMatrix(int totalcities)
	{
		Random random = new Random();
		this.matrix = new int[totalcities][totalcities];
		this.numcity = totalcities;
		for(int i=0;i<totalcities; i+=1)
		{
			//for(int j=0;j<totalcities; j+=1)
                        for(int j=0;j<i; j+=1)
			{
				if (i==j)
				{
					this.matrix[i][j]=0;
				}
				else
				{
					this.matrix[i][j]=(random.nextInt()%(maxdistance-1));
					if (this.matrix[i][j]<0)
					{
						this.matrix[i][j] = this.matrix[i][j]*-1;
					}
                                        
					this.matrix[i][j]+=1;
                                        this.matrix[j][i] = this.matrix[i][j];
					
				}
				
				
			}
		}
	}
	
	//to print the matrix
        public void Display()
	{
		
		for(int i=0;i<this.numcity; i+=1)
		{
			for(int j=0;j<this.numcity; j+=1)
			{
				if (this.matrix[i][j]==-1)
				{
					String s= "----";
					
					System.out.print(s+" ");
				}
				else
				{
					String s= "0"+this.matrix[i][j];
				
					s = s.substring(s.length()-2); // keep the rightmost 4 characters 
				
					System.out.print(s+" ");
				}
				if (j==(this.numcity-1))
				{
					System.out.println();
				}
				
				
			}
		}
		System.out.println();
	}
	
	public int NumCity()
	{
		return this.numcity;     //returns number of cities
	}
	
	public int GetDistanceFrom(int start, int finish)
	{
		return this.matrix[start][finish];              //returns distance between specified start and finish
	}
	
	
}