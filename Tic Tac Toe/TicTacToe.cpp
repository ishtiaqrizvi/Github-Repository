#include <cstdlib>
#include <iostream>
#include <string>
#include <cstring>
#include <ctime>

using namespace std;

//declaring global functions and variables
int inputConversion (string input);
void initArr ();
bool showWinner (int turn);

bool findWinner (int turn, int grid, int row, int col);
int turn, TDArr[6][6][6];    //size 6X6X6
void show (int *board);

int main()
    {
         string input;
         int grid,row,col, temp; 
         initArr ();
         
    while (true) {
			
         turn++;
		 
         show (&TDArr[0][0][0]);      
         if (turn%2==0)        //for every even turn player 'O' will be asked to play
		 {
		  cout<<"\nPlayer O, it's your turn!\n\n";
          
		  temp = 1;
		  srand(time(NULL));
		  
  
			
	while(temp == 1)
		{
			
			grid = rand() % 4+1;       //grid numbers geneated randomly from 1 to 4.
			row = rand() % 4+1;        //same random generation happens for row and columns. 
			col = rand() % 4+1;        //%4 + 1 signifies that random generation will be done using numbers between 1 to 4
					
			if (TDArr[grid][row][col] ==0) 
			{ 
				temp =0;
			}
			else{
				temp = 1;
			}
		} // WHILE END
		}
         else {
		  cout<<"\nPlayer X, it's your turn!\n\n";
          cout<<"Enter grid number ('Q' to Quit) : ";
          getline (cin,input);
          if ((input[0]=='q')||(input[0]=='Q')) break;       //for q it should come out
          grid=inputConversion(input);
         
		 if ((grid<1)||(grid>4)) // if entered number out of limit
		 {
		  cout<<"\nInvalid selection.\n\n";            
          system("pause");
		  turn--;   continue;
		 }
           
		  //checking if row number and column numbers are appropriately selected             
          cout<<"Row number : ";
          getline (cin,input);
          row=inputConversion(input);
          if ((row<1)||(row>4)) 
		  {cout<<"\nInvalid selection.\n\n";            
           system("pause");
		   turn--;   continue;
		  }
                       
          cout<<"Column number : ";
          getline (cin,input);
          col=inputConversion(input);
          if ((col<1)||(col>4)) 
		  {cout<<"\nInvalid selection.\n\n";            
           system("pause");
		   turn--;   continue;
		  }
          
		  if (TDArr[grid][row][col]!=0) // If position is already filled	
		  {cout<<"\nSelect Empty place!\n\n";
           system("pause");
		   turn--;	continue;
		  }
		 } // ELSE END 
         

		 if (turn%2==0) 
		  {TDArr[grid][row][col]=2; // stores O's positions
		  }
          else 
		  {TDArr[grid][row][col]=1; // stores X's positions
		  }
          
		  if (findWinner (turn,grid,row,col)) 
		  {if(showWinner(turn)) break;
		  }		 
         }
		 cout<<"\n";
         cout<<"\n\n";
         return 0;
}

void initArr () {
     for (int x=0;x<6;x++) {
         for (int y=0;y<6;y++) {
             for (int z=0;z<6;z++) {
                 TDArr[x][y][z]=0;       //Initialize the 3D array
                 }
             }
         }
     for (int i=0;i<6;i++) {
             for (int j=0;j<6;j++) {
                 TDArr[0][i][j]=5; TDArr[5][i][j]=5;
                 TDArr[i][j][0]=5; TDArr[i][j][5]=5;
                 TDArr[i][0][j]=5; TDArr[i][5][j]=5;}
                 }
				 
}
int inputConversion (string input) {
    int i, q; 
    string n;
    for (int i=0;input[i] != '\0';i++) 
	{n=n+input[i];
	}
    q=atoi(n.c_str());   //atoi converts string to integer
    return q;
}	 
	 
bool showWinner (int po) {
     char yn[100];
     show (&TDArr[0][0][0]);
     if (po%2==0) 
	 {cout<<"\n\nO is the winner!!\n\n";
	 }
     else cout<<"\n\nX is the winner!!\n\n";
     cout<<"Play again? (y/n) : ";
     cin>>yn;
     if (!strcmp(yn,"y")||!strcmp(yn,"Y")) 
	 {initArr (); 
	  cin.ignore(1); 
	  turn=0; 
	  return 0;
	 }
     else return 1;
}

	 
	 
bool findWinner (int turn,int grid,int row,int col) {
     int h=1;                   
     if (turn%2==0) h=2;        // to check who's inputting, X's or O's
     
     int x[4],y[4],z[4];        
     x[0]=grid;y[0]=row;z[0]=col;      //store the move of each player
     int count;
     
     for (int a=1;a<14;a++){    //check all possible direction 
           count=1;             // counter is 1
     
           int grd=grid; int rNo=row; int cNo=col;
           
           for (int i=0; TDArr[grd][rNo][cNo]!=5; ){

                 switch (a) {		//increments in each possible direction until hitting a 5
                        case 1:grd--;	break;                
                        case 2:grd--; rNo--; cNo--;	break;      
                        case 3:grd--; rNo--;		break;           
                        case 4:grd--; rNo--; cNo++;	break;      
                        case 5:grd--; cNo--;		break;           
                        case 6:grd--; cNo++;		break;           
                        case 7:grd--; rNo++; cNo--;	break;      
                        case 8:grd--; rNo++;		break;           
                        case 9:grd--; rNo++; cNo++;	break;      
                        case 10:cNo--;		break;             
                        case 11:rNo--; cNo--;	break;         
                        case 12:rNo--;		break;             
                        case 13:rNo--; cNo++;	break;         
                        }
                        
                 if (TDArr[grd][rNo][cNo]==h) 
				 {         x[count]=grd;
						   y[count]=rNo;
						   z[count]=cNo;
                           count++;
					}
                 
                 }
                 
                 
          grd=grid;rNo=row;cNo=col;   //reset placeholder variables
         
         
          for (int i=0;TDArr[grd][rNo][cNo]!=5;) { 
              
              
                switch (a) {		// and then back in the opposite direction
                      case 1:grd++;		break;                
                      case 2:grd++; rNo++; cNo++;		break;      
                      case 3:grd++; rNo++;		break;          
                      case 4:grd++; rNo++; cNo--;		break;      
                      case 5:grd++; cNo++;		break;          
                      case 6:grd++; cNo--;		break;          
                      case 7:grd++; rNo--; cNo++;		break;      
                      case 8:grd++; rNo--;		break;          
                      case 9:grd++; rNo--; cNo--;		break;      
                      case 10:cNo++;		break;             
                      case 11:rNo++; cNo++;		break;         
                      case 12:rNo++;		break;             
                      case 13:rNo++; cNo--;		break;         
                      }
                                   
              if (TDArr[grd][rNo][cNo]==h) 
			  {       x[count]=grd;
					  y[count]=rNo;
					  z[count]=cNo;
                      count++;
                }
               }
               if (count>3) // if 4 position is filled properly
			   {	for (int b=0;b<4;b++) 
					{
						TDArr[x[b]][y[b]][z[b]]=4; // only for last(4th) position array element is made 4.
						
					} 
				return 1;
				}
               }
	 
     return 0;
}

    

void show(int *board)
{
// This is used for display the grids and inputs.
     system ("cls");
     cout <<"Grid: 1\t\tGrid: 2\t\tGrid: 3\t\tGrid: 4\n\n\n";
     int n;
     for (int y=1;y<=4;y++) {
         for (int x=1;x<=4;x++) { 
		    for (int z=1;z<=4;z++) {
                 n=((x*36)+(y*6)+(z));   // (x*6*6) + (y*6) + z  6 is size of array taken
                 switch (*(board+n)) {
                        case 0: cout<<" "; 
								break;
                        case 1: cout<<"X"; 
								break;
                        case 2: cout<<"O"; 
								break;
                        case 4: cout<<(char)219;
								break; // show box on winner positions
                        }
                 if (z<4) cout<<(char)179; // vertical line(|) for creating grid 
                 }
             if (x<4) cout<<"\t\t";
             }
         if (y<4) {
                  cout<<"\n";
                  for (int d=1;d<32;d++) {
                       if (d%8==0) 
					   {cout<<"\t\t";
					   }
                       else if (d%2==0) 
					   {cout<<(char)197;} // horizontal and vertical intersect line for grid
                        else cout<<(char)196;} // horizontal (-) line for grid
                            cout<<"\n";
                      }
                  
         }
     cout<<"\n\n\n\n";
     return;
     }
