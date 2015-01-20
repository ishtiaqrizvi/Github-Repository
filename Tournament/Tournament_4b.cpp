#include <iostream>
#include <cstdlib>
using namespace std;

int **scheduleOdd ;
int **scheduleEven ;                //declaring pointers

int BYE = -1 ;

void generateSchedule(int numPlayers) ;
void RotateTeams(int *teams, int numPlayers);
void generateSchedule_Odd(int numPlayers);           //declaring all global functions
void generateSchedule_Even(int numPlayers);
void displayScheduleHeader(int numPlayers) ;

int main()
{
	
	int numPlayers ;
	
	std::cout << "Enter number of players" << std::endl ;
	cin >> numPlayers ;
    
	scheduleOdd = new int*[numPlayers];

 	// allocate memory to the schedule matrix
	for(int i = 0; i < numPlayers; ++i)
    	scheduleOdd [i] = new int[numPlayers];

	generateSchedule(numPlayers) ;      //calling function generateSchedule()
	
	return 0 ;
}
	
void generateSchedule(int numPlayers) {

	if ((numPlayers % 2) == 0)  {
		generateSchedule_Even(numPlayers) ;

					displayScheduleHeader(numPlayers) ;			
					cout << "Day " << endl ;
				  for (int round = 0; round < numPlayers - 1; round++) //if number of teams are even then n-1
						{
							cout << round + 1 << "\t";

							for (int team = 0; team < numPlayers; team++)
							{
								
								cout <<  "\t" << scheduleEven[team][round] ;
								}
								cout << endl ;
							}			
		}
		
	else {
		generateSchedule_Odd(numPlayers) ; 
		
		displayScheduleHeader(numPlayers) ;
		cout << "Day " << endl ;
		  for (int round = 0; round < numPlayers; round++)
				{
					cout << round + 1 << "\t";

					for (int team = 0; team < numPlayers; team++)
					{
						
						cout <<  "\t" ;
						if(scheduleOdd[team][round] == -1) 
							cout << "-" ;                    //placing "-" to empty slots in the schedule
						else 
							cout << scheduleOdd[team][round] ;
						}
						cout << endl ;
					} 
 			
		}
}

void generateSchedule_Odd(int numPlayers)
        {
		int n2 = (numPlayers - 1) / 2;
		int *teams ;
		int round, team1, team2 ;
		teams = new int[numPlayers];	

		
		for (int i = 0; i < numPlayers; i++) 
			teams[i] = i;

            // Start the rounds.
            for (round = 0; round < numPlayers; round++)
            {
                for (int i = 0; i < n2; i++)
                {
                    team1 = teams[n2 - i];
                    team2 = teams[n2 + i + 1];
                    scheduleOdd[team1][round] = team2;
                    scheduleOdd[team2][round] = team1;
                }

                // Set the team with the bye.
                scheduleOdd[teams[0]][round] = BYE;

                // Rotate the array.
                RotateTeams(teams, numPlayers);
            }
        }

void generateSchedule_Even(int numPlayers)
        {
			int team, round, i ;
            // Generate the result for one fewer teams.
			generateSchedule_Odd(numPlayers - 1) ;


			scheduleEven = new int*[numPlayers];
			
			for(i = 0; i < numPlayers ; ++i)
				scheduleEven [i] = new int[numPlayers-1];			

			
            for (team = 0; team < numPlayers - 1; team++)
            {
                for (round = 0; round < numPlayers - 1; round++)
                {
                    if (scheduleOdd[team][round] == BYE)
                    {
                        // Change the bye to the new team.
                        scheduleEven[team][round] = numPlayers - 1;
                        scheduleEven[numPlayers - 1][round] = team;
                    }
                    else
                    {
                        scheduleEven[team][round] = scheduleOdd[team][round];
                    }
                }
            }

        }

        // Rotate the entries one position.
        void RotateTeams(int *teams, int numPlayers)
        {
            int tmp = teams[numPlayers-1];
			
			for (int i = (numPlayers - 2) ; i >= 0 ; i--) {
				teams[i+1] = teams[i] ;				
			}
				
            teams[0] = tmp;
					
        }
    
void displayScheduleHeader(int numPlayers) {
	int team  ;
	
	cout << "\t" << "Player" << "\t" ; ;
	for (team = 0;team < numPlayers ; team++) {
		cout << team << "\t" ;
	}
	cout << endl  ;
	
	

}