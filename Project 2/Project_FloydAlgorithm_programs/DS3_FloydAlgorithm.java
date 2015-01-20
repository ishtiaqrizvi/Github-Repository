	
import java.util.*;
import java.util.Random;

    public class DS3_FloydAlgorithm
    {
        static int l;
        static int n;			
        static int mat1[][];    
        public static final int Inf = 9999;
	static int[] aray;


	public static void main(String... arg)
        {
            int mat2[][];	
            int n;		
			
	    int a, b;
            Scanner scan = new Scanner(System.in);		

            System.out.println("Enter the number of Nodes in Graph"); 
            n = scan.nextInt();	

            mat2 = new int[n + 1][n + 1];
			
			Random rn = new Random();     //Random() is called from Random Class defined in java.util.Random
			
			String[][] shortPath =  new String[n][n];
			
			for(int i = 0; i < n; i++)
			{
				
				for (int j = 0; j < i; j++)
                {	
					int answer = rn.nextInt(20) + 1;
					mat2[i][j] = mat2[j][i] = answer;
						
					
                    if (i == j) 
                    {
						// Diagonal elements are set to Zero
                        mat2[i][j] = 0;
                        
                    }
					
                }
	
			}

           System.out.println("Matrix Elements are:");
		    for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {	// Enter Input for Matrix
					
					System.out.print(mat2[i][j]+"\t");
				}
				System.out.println("");
			}


            DS3_FloydAlgorithm floyd = new DS3_FloydAlgorithm(n);

	    System.out.println();
            System.out.println("Enter source From Array:"); 
            a = scan.nextInt();	
	    System.out.println();
            System.out.println("Enter destination From Array:"); 
            b = scan.nextInt();	
		    
            scan.close();
			
	    int[][] distance = floyd(mat2, shortPath);     //floyd method is called to get the shortest path from matrix mat2
		
	    int count = 0;
		    l = (n * (n));
		    aray = new int[l];
			
		    System.out.print("Array => [\t");
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
					if(i > j)
					{
						aray[count] = distance[i][j];               //to print the lower triangle matrix
						System.out.print(aray[count] + "\t");
						count++;
					}
                }
            }
			System.out.println(" ]"); 
		
		
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n ; j++) {			
					if (i == a && j == b) {
						System.out.print("[" + a + " to " + j + "] = ");	//printing source and destination
						System.out.println(distance[i][j]);
					}
				}
			
			         
				for (int j = 0; j < n ; j++) {			
					if (i == a && j == b) {				
						if (shortPath[i][j] != "-1")
						{
							System.out.print(" PATH  " + a + " " + shortPath[i][j].replaceAll(",", " ")  + " " + b);				
						}
						else
							System.out.print(" PATH  " + a + " " + b);				
						
					}
				}
			
			}
		}	
		
		
		

        public DS3_FloydAlgorithm(int n)
        {
            mat1 = new int[n + 1][n + 1];
            this.n = n;
        }

        public static int[][] floyd(int mat3[][], String[][] shortPath)		//floyd function calculate 
        {
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    mat1[i][j] = mat3[i][j];       //initializing distance to the adjacency matrix of the graph
                }
            }

			for(int i=0; i< n; i++)
				for(int j=0; j< n; j++)
					shortPath[i][j] = "-1" ;

	    //Floyd Algorithm Logic computing Floyd Matrix and shortest path			
            for (int k = 0; k < n; k++)
            {
                for (int i = 0; i < n; i++)
                {
                    for (int j = 0; j < n; j++)
                    {
                        if (mat1[i][k] + mat1[k][j] < mat1[i][j])
                        {    mat1[i][j] = mat1[i][k] + mat1[k][j];
							
						if (mat1[i][k] !=0 )  {
							if (shortPath[i][k] != "-1") 
								{
									shortPath[i][j] = shortPath[i][j] + ", " + shortPath[i][k] + " ";
									//System.out.println("INODE K " + i + " - " + k) ;
								}
								else 
								{
									if (shortPath[i][j] == "-1")
										shortPath[i][j] = ',' + String.valueOf(k) + " ";
									else
										shortPath[i][j] = shortPath[i][j] + ", " + k + " ";
								}
								
						}


						if (mat1[k][j] !=0 ) 
						{
							if (shortPath[k][j] != "-1") {
								shortPath[i][j] = shortPath[i][j] + ", " + k + shortPath[k][j] + " ";
								//System.out.println("INODE J" + k + " - " + j) ;
							}
						}
						else 
						{
							if ((mat1[i][k] + mat1[k][j]) > mat1[i][j]) {
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


            System.out.println();
		    System.out.println();			
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    System.out.print(mat1[i][j] + "\t");
                }

                System.out.println();
            }
			System.out.println();
			
			
			
		return mat1;			//Floyd Matrix is returned
        }


    }