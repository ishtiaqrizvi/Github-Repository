/* This program can find shortest distance between Cities
by using Brute Force Algorithm */

import java.util.*;

public class BruteAlgo {
//private static final int N = 10;
	public class Map
	{
		public int from;     //from and to will be instantiated
		public int to;
	};
	
	private MapMatrix mymap;      //creating an object for class MapMatrix
	ArrayList<Map> bestroute;     //bestroute will save the solution
	
	public BruteAlgo(MapMatrix mm)
	{
		mymap = mm;
		bestroute = new ArrayList<Map>();
	}
	
	public boolean CalcShortRoute()       //to find the shortest route
	{
		ArrayList<Map> route = new ArrayList<Map>();
		OtherRoute(route);
		
		return true;
	}
	
	public void Display()
	{
		System.out.print("Best Route Distance:  ");
		Display(this.bestroute);
	}
	
	public void Display(ArrayList<Map> route)
	{
        int temp2 = 0, i;
		int startElement = 0 ;
		
		System.out.println(RouteDistance(route));
		System.out.println("*******************************************");
		for(i=0;i<route.size();i+=1)
		{
			Map map = route.get(i);
			if (i == 0) {
				startElement = map.from ;     //if i=0 then it is the start of the route
				System.out.println("Best path is");
				System.out.print(map.from + " ") ;
			}
			//System.out.println("map "+i+" from "+map.from+" to "+map.to+" distance "+this.mymap.GetDistanceFrom(map.from, map.to));
			//System.out.println(map.from+" to "+map.to+" distance "+this.mymap.GetDistanceFrom(map.from, map.to));
			System.out.print(map.to + " ");
			
            //temp2 = map.to;
		}
                //System.out.println("map "+i+" from "+temp2+" to 0 distance "+this.mymap.GetDistanceFrom(temp2, 0));
                //System.out.println(temp2+" to " + startElement + " distance "+this.mymap.GetDistanceFrom(temp2, startElement));
                System.out.println(temp2 + " ");
		System.out.println("");
	}
	
	private int OtherRoute(ArrayList<Map> route)
	{
		if (route.size()==this.mymap.NumCity()-1 )      //checking if the route size is equal to number of cities in the map matrix 
		{
			//Display(route);
			if (IsBestRoute(route)==true)           //calling IsBestRoute method to check whether the route is best route or not
			{
				bestroute=route;
				
			}
		}
		else
		{

			
			for(int i=0;i<this.mymap.NumCity();i+=1)       
			{
				Map nextroad = new Map();
				if (route.isEmpty()==true)
				{
					nextroad.from=0;                //if the route is empty nextroad's from will be set to 0
				}
				else
				{
					Map lastroad = route.get(route.size()-1);
					nextroad.from = lastroad.to;     //if our map reaches the lastroad its "to" is assigned to "from" of nextroad
				}
				nextroad.to = i;
				//make sure we visit new cities only
				if (i==nextroad.from)
				{
					continue;
				}
				if (CityIsOnRoute(route, nextroad.to))
				{
					continue;
				}
				ArrayList<Map>testroute = new ArrayList<Map>(route);
				testroute.add(nextroad);        //testroute adds the nextroad
				OtherRoute(testroute);
			}
		}
		return 0;
	}
	
        //method returning true when bestroute is found
	private boolean IsBestRoute(ArrayList<Map> route)
	{
		if ( RouteDistance(this.bestroute) > RouteDistance(route) )       
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
        //calculating the sum of weights in a route
	private int RouteDistance(ArrayList<Map> route)
	{
		int distance = 0, temp = 0;
		
		if (route.isEmpty()==true)
		{
			return java.lang.Integer.MAX_VALUE;
		}
		
		for(int i=0;i<route.size();i+=1)
		{
			Map map = route.get(i);
			distance += this.mymap.GetDistanceFrom(map.from, map.to);
                        temp = map.to;
		}
                distance += this.mymap.GetDistanceFrom(temp, 0);
		return distance;
	}
	
	//Checking if city identified by its index falls on the route by checking its index in the map matrix
        private boolean CityIsOnRoute(ArrayList<Map> route, int city)
	{
		for(int i=0;i<route.size();i+=1)
		{
			Map map = route.get(i);
			if (map.from==city || map.to==city)
			{
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args)
	{
		int N ;
        Scanner scan = null;		
		System.out.println("Enter number of nodes in graph");
		scan = new Scanner(System.in);
		N = scan.nextInt();
	
		MapMatrix mm =  new MapMatrix(N);
		mm.Display();
		
		BruteAlgo bAlgo = new BruteAlgo(mm);
		bAlgo.CalcShortRoute();
		bAlgo.Display();
		
	}
	
}
