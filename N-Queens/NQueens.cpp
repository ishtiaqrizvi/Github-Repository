//N queens problem using Recursive Backtracking

#include<iostream>
#include<conio.h>
#include<math.h>
#include<stdio.h>
#include<stdlib.h>
 
int *x, solCnt;               
//solCnt stores the total number of solutions for an n value whereas *x is a pointer to array which points to the first element of the array and can be incremented to  point to the next element

void displayQueens(int n) ;

int place(int row ,int column)
{
	int i;
	for(i=1;i<=row-1;i++)
	{

		if ((x[i]==column) || (abs(x[i]-column)==abs(i-row)))   //abs help us to get an absolute value
			return 0;
	}
	//queen is safe , place queen on the board
	return 1;
}

void nqueens(int row, int n)
{
	int column;                            //variable for column of the array 
	for(column=1;column<=n;column++)
	{
		if(place(row,column))
		{
			x[row]=column;         //if there is no issue in position then place the queen
			
			if((row==n) ) {  //last row has been examined
				solCnt++ ;
				std::cout << "Possible configuration... "   << "\t" <<  solCnt << std::endl ;				
				displayQueens(n);    //print the position of queens
				std::cout << std::endl ;
				
			}
			
			else                     //now check next row for next queen's position
			nqueens(row+1,n);        //Using recursive function
		}
	}
} 

 
void displayQueens(int n)
{
	int i,j;

	for(i=1;i<=n;i++)
	{
		std::cout << "\t" <<  i;        //i stores the number of solution
	}
	
	for(i=1;i<=n;i++)
	{
		std::cout << "\n" << "\n"  << i ;
		for(j=1;j<=n;j++)        
		{
			if(x[i]==j)             //if condition satisfies then there is no issue with the position so we can place the queen Q
				std::cout << "\tQ";   
			else
				std::cout << "\t-";   //backtracking step prints '-' for the else condition. This condition helps in removing queens from unwanted cells of the matrix
		}
	}
}
 
int  main()
{
                int k,n;
                std::cout<<"Enter the no. of queens ( greater than 3) :"; //Get input from user
				while (1) {
					std::cin>>n;
					if ( n > 3)
						break ;
				}
				solCnt = 0 ;
				x = new int[n];
				for (k = 1;k <= n ; k++)                     //Initializing x[] with 0
					x[k]= 0 ;
				k=1;
                                nqueens(k,n); //call the nqueens function to solve the problem
				getch();
			return 0 ;
}