#include<iostream>
#include <list>
#include<stdio.h>
#include<stdlib.h>
#include <algorithm>

using namespace std;

// class to reperesent undirected graph
class Graph
{
    int V;    // No. of vertices
    list<int> *adjList;    // to store connections between vertices
	
public:

    Graph(int V)   {this->V = V; adjList = new list<int>[V]; }
    ~Graph() { delete [] adjList; } // garbage colelction
 
     // to add an edge to graph
    void addEdge(int v, int w);
 
    // check if this graph has an Euler path
    int isEulerian();
 
    // check if all non-zero degree vertices are connected or not 
    bool isConnected();
 
    // Function for Depth-First-Search starting from v. 
    void checkConnectionsWithDFS(int v, bool visited[]);
	
	void displayEulerPath();

	bool isValidNextEdge(int u, int v);       

	void deleteEdge(int u, int v);

	int countAdjVertices(int v, bool visited[]);	

	void printEulerPath(int u);
};
 
void Graph::addEdge(int v, int w)
{
    adjList[v].push_back(w);
    adjList[w].push_back(v);  //the graph is undirected
}
 
void Graph::checkConnectionsWithDFS(int v, bool visited[])
{
    // Mark the current node as visited and print it
    visited[v] = true;
 
    // Recur for all the vertices adjacent to this vertex
    list<int>::iterator i;
    for (i = adjList[v].begin(); i != adjList[v].end(); ++i)
        if (!visited[*i])
            checkConnectionsWithDFS(*i, visited);
}
 
// check if all non-zero degree vertices are connected.
bool Graph::isConnected()
{
    // Mark all the vertices as not visited
    bool *visited;
	visited = new bool[V] ;
    int i;
	int edgesFnd ;
	edgesFnd = 0 ;
	
	for (i = 0; i < V; i++) {
        if (adjList[i].size() != 0) {
            edgesFnd = 1 ;
			break ;
		}
	}
		
	// if no edges found in the graph, do not go ahead
	if (!edgesFnd)
		return 0 ;
	
    for (i = 0; i < V; i++)
        visited[i] = false;
 
    // Find a vertex with non-zero degree
    for (i = 0; i < V; i++)
        if (adjList[i].size() != 0)
            break;
 
    // If there are no edges in the graph, return true
    if (i == V)
        return true;
 
    // Start DFS traversal from a vertex with non-zero degree
    checkConnectionsWithDFS(i, visited);
 
    //Have all non-zero degree vertices been visited?
    for (i = 0; i < V; i++)
       if (visited[i] == false && adjList[i].size() > 0)
            return false;
 
    return true;
}


// Function to print Euler Path. It first finds an odd degree vertex and then prints the path 
void Graph::displayEulerPath()
{
  // Find a vertex with odd degree
  int u = 0;
  for (int i = 0; i < V; i++)
      if (adjList[i].size() & 1)
        {   u = i; break;  }
 
  printEulerPath(u);
  cout << endl;
}
 
// Print Euler tour starting from vertex u
void Graph::printEulerPath(int u)
{
  // Recur for all the vertices adjacent to this vertex
  list<int>::iterator i;
  for (i = adjList[u].begin(); i != adjList[u].end(); ++i)
  {
      int v = *i;
 
      // checks if edge u-v is a valid next edge
      if (v != -1 && isValidNextEdge(u, v))
      {
          cout << u << "-" << v << "  ";
          deleteEdge(u, v);
          printEulerPath(v);
      }
  }
}
 
// Check if edge u-v is a valid edge in  Euler Path
bool Graph::isValidNextEdge(int u, int v)
{
  // The edge u-v is valid in one of the following two cases:
 
  // 1) If v is the only adjacent vertex of u
  int count = 0;  // To store count of adjacent vertices
  int idx ;
  list<int>::iterator i;
  for (i = adjList[u].begin(); i != adjList[u].end(); ++i)
     if (*i != -1)
        count++;
  if (count == 1)
    return true;
 
 
  // If there are multiple adjacents, then u-v is not a bridge
  // Do following steps to check if u-v is a bridge
 
  // count of vertices reachable from u
  bool *visited;
  visited = new bool[V] ;
  
	for (idx = 0; idx < V; idx++)
		visited[idx] = false ;
	
  int count1 = countAdjVertices(u, visited);
 
  // Remove edge (u, v) and after removing the edge, count vertices reachable from u
  deleteEdge(u, v);

	for (idx = 0; idx < V; idx++)
		visited[idx] = false ;
  
  
  int count2 = countAdjVertices(u, visited);
 
  // check if edge is a bridge   - add edge back to graph
	addEdge(u, v);

	// check if edge is a bridge - count1 is greater, then edge (u, v) is a bridge
  return (count1 > count2)? false: true;
}
 
// This function removes edge u-v from graph. For this, it replaces value of adjcent vertex with -1.
void Graph::deleteEdge(int u, int v)
{
  // Find & replace v  with -1
  list<int>::iterator iv = find(adjList[u].begin(), adjList[u].end(), v);
  *iv = -1;
 
  // Find & replace u  with -1
  list<int>::iterator iu = find(adjList[v].begin(), adjList[v].end(), u);
  *iu = -1;
}
 
// Function to count adjacent vertices from v - uses DFS
int Graph::countAdjVertices(int v, bool visited[])
{
  // Mark the current node as visited
  visited[v] = true;
  int count = 1;
 
  // Recur for all vertices adjacent to this vertex
  list<int>::iterator i;
  for (i = adjList[v].begin(); i != adjList[v].end(); ++i)
      if (*i != -1 && !visited[*i])
          count += countAdjVertices(*i, visited);
 
  return count;
}

 
int Graph::isEulerian()
{
	// Check if graph has edges and all non-zero degree vertices are conected
    if (isConnected() == false)
        return 0;
 
    // Count vertices with odd degree
    int odd = 0;
    for (int i = 0; i < V; i++)
        if (adjList[i].size() & 1)
            odd++;
 
    // If count of vertices with odd degree is more than 2, then graph does not have Euler path
    if (odd > 2)
        return 0;
 
	
	displayEulerPath();	
    return (odd)? 1 : 2;
}
 

int main()
{
	int numV ;
	std::cout << "Please enter the number of vertices " << std::endl ;
	std::cin >> numV ;

    Graph g(numV);          //making g object of class 'Graph'
	
	std::cout << "Please enter the edges  enter '0  0'  to finish " << std::endl ;
	
	int edgeCtr, v1, v2  ;
	edgeCtr = 0 ;
	while (1) {
		
		edgeCtr++ ;
		if (edgeCtr > ((numV * (numV - 1))/2)) {
			std::cout << "Max number of edges entered. Checking for Euler path... " << std::endl << std::endl  ;
			break ;
		}

		std::cin >> v1  ;
		std::cin >> v2  ;
		
		if (v1 == 0 && v2 == 0)
			break ;

		g.addEdge(v1, v2);			//add edges
		
	}
		

	int x = g.isEulerian();         //checks if the graph contains Eulerian path
    if (!x)
        cout << "Graph does not have a Euler path \n";
    else 
        cout << "Graph has a Euler path\n";
		
	return 0;
	
}