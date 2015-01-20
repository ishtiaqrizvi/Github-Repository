//Program uses Floyd's Algorithm to find shortest path
import java.util.*;
import java.util.Random;

    public class DS1_FloydAlgorithm
    {
	private int l;
        static int n;			
        static int mat1[][];
        public static final int Inf = 9999;
		private int[] aray;


		public static void main(String... arg)
        {
            int mat2[][];	
            int n;		
			
	    int a, b;
            Scanner scan = new Scanner(System.in);		

            System.out.println("Enter the number of Nodes in Graph"); 
            n = scan.nextInt();	

            mat2 = new int[n + 1][n + 1];
			
			Random rn = new Random();    //Calling Random() method from Random Class
			String[][] shortPath =  new String[n][n];
			
			
			for(int i = 0; i < n; i++)
			{
				
				for (int j = 0; j < i; j++)
                {	
					int answer = rn.nextInt(15) + 1;
					mat2[i][j] = mat2[j][i] = answer;
						
		    //diagonal elements are set to 0		
                    if (i == j) 
                    {
						
                        mat2[i][j] = 0;
                        
                    }

					
                }
	
			}

           System.out.println("Matrix Elements are:");
		    for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {	// Input for Matrix
					
					System.out.print(mat2[i][j]+"\t");
				}
				System.out.println("");
			}


            DS1_FloydAlgorithm floyd = new DS1_FloydAlgorithm(n);

		    System.out.println("Enter source Code to find Distance (0 to exit):");
			int src = scan.nextInt();

			System.out.println("Enter destination Code to find Distance (0 to exit):");
			int dest = scan.nextInt();
			
			int[][] distance = floyd(mat2, shortPath);   //floyd method is called to get the shortest path
			
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n ; j++) {			
					if (i == src && j == dest) {
						System.out.print("[" + src + " to " + j + "] = ");				
						System.out.println(distance[i][j]);               //printing the shortest distance
					}
				}
			
				for (int j = 0; j < n ; j++) {			
					if (i == src && j == dest) {				
						if (shortPath[i][j] != "-1")
							System.out.print(" PATH  " + src + " " + shortPath[i][j].replaceAll(",", " ")  + " " + dest);				
						else
							System.out.print(" PATH  " + src + " " + dest);				
						
					}
				}
		
			}
				
			
			System.out.println();

			
            scan.close();
        }

        public DS1_FloydAlgorithm(int n)
        {  //Constructor to initialize mat1 and n
            mat1 = new int[n][n];
            this.n = n;
        }


        public static int[][] floyd(int mat3[][], String[][] shortPath)	
        {
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    mat1[i][j] = mat3[i][j];        //assigning mat3[][] to mat1[][] before implementing Floyd's algorithm
                }
            }
			
			for(int i=0; i< n; i++)
				for(int j=0; j< n; j++)
					shortPath[i][j] = "-1" ;
            //Floyd's algorithm
            for (int k = 0; k < n; k++)
            {
                for (int i = 0; i < n; i++)
                {
                    for (int j = 0; j < n; j++)
                    {
                        if (mat1[i][k] + mat1[k][j] < mat1[i][j])   //finding shortest path logic
						{
						    mat1[i][j] = mat1[i][k] + mat1[k][j];
							
								if (mat1[i][k] !=0 )  {
									if (shortPath[i][k] != "-1") 
									{
										shortPath[i][j] = shortPath[i][j] + ", " + shortPath[i][k] + " ";
										
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

			
			return mat1;        //returning the floyd matrix from which the shortest path can be computed
        }


    }